
#!/usr/bin/env bash
set -e

MODEL="gemini-3.8-flash"
CONFIG_FILE="${PWD}/config.properties"

# Читаем выделенный код из stdin
CODE="$(cat | tr -d '\r')"

if [ -z "$CODE" ]; then
    echo "No code selected"
    exit 0
fi

# Читаем GEMINI_API_KEY из config.properties
if [ ! -f "$CONFIG_FILE" ]; then
    echo "ERROR: config.properties not found: $CONFIG_FILE"
    exit 1
fi

GEMINI_API_KEY=""

while IFS= read -r line || [ -n "$line" ]; do
    case "$line" in
        ""|\#*) continue ;;
        GEMINI_API_KEY=*)
            GEMINI_API_KEY="${line#GEMINI_API_KEY=}"
            break
            ;;
    esac
done < "$CONFIG_FILE"

# Убираем пробелы в начале и конце значения
GEMINI_API_KEY="$(printf '%s' "$GEMINI_API_KEY" | sed 's/^[[:space:]]*//;s/[[:space:]]*$//')"

if [ -z "$GEMINI_API_KEY" ]; then
    echo "ERROR: GEMINI_API_KEY is missing in config.properties"
    exit 1
fi

# Формируем промпт для ревью
PROMPT=$(cat <<EOF
Ты — опытный Java-разработчик и QA Automation Engineer.
Проведи Code Review предоставленного кода.

Проверь:
1. Ошибки синтаксиса и логики.
2. Потенциальные баги и граничные случаи.
3. Проблемы безопасности.
4. Читаемость, дублирование и неиспользуемый код.
5. Если это автотест — корректность assertions,
   достаточность проверок и риск нестабильного теста.

Правила:
- Указывай только обоснованные проблемы.
- Не выдумывай отсутствующий контекст.
- Для каждой проблемы укажи приоритет, причину,
  последствия и способ исправления.
- Отделяй подтверждённые дефекты от предположений.
- Не переписывай весь код без необходимости.
- Отвечай на русском языке.

Код для ревью:
$CODE
EOF
)

export GEMINI_API_KEY
export GEMINI_PROMPT="$PROMPT"

echo "===== DEBUG: RECEIVED CODE ====="
printf '%s\n' "$CODE"
echo "===== END DEBUG ====="

echo "===== AI CODE REVIEW (GEMINI CLOUD) ====="

powershell.exe -NoProfile -Command '
$ErrorActionPreference = "Stop"
[Console]::OutputEncoding = New-Object System.Text.UTF8Encoding($false)
$OutputEncoding = New-Object System.Text.UTF8Encoding($false)

try {
    $body = @{
        contents = @(
            @{
                parts = @(
                    @{
                        text = $env:GEMINI_PROMPT
                    }
                )
            }
        )
    } | ConvertTo-Json -Depth 10

    $headers = @{
        "x-goog-api-key" = $env:GEMINI_API_KEY
    }

    $response = Invoke-RestMethod `
        -Uri "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.8-flash:generateContent" `
        -Method Post `
        -Headers $headers `
        -ContentType "application/json; charset=utf-8" `
        -Body ([System.Text.Encoding]::UTF8.GetBytes($body))

    if (-not $response.candidates -or
        -not $response.candidates[0].content.parts) {
        throw "Gemini returned no text candidate."
    }

    foreach ($part in $response.candidates[0].content.parts) {
        if ($part.text) {
            Write-Output $part.text
        }
    }
}
catch {
    [Console]::Error.WriteLine("Gemini API request failed:")
    [Console]::Error.WriteLine($_.Exception.Message)
    exit 1
}
'

echo "========================================"