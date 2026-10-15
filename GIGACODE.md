# Repository Guidelines

## Project Structure & Module Organization

```
SuiteCRM/
├── src/main/java/
│   ├── dto/            # Data transfer objects (Account.java, AccountFactory.java)
│   ├── pages/          # Page Object classes (BasePage, LoginPage, MainPage, AccountNewPage, etc.)
│   ├── steps/          # Step classes for BDD-style orchestration (LoginStep, AccountStep)
│   └── wrappers/       # Selenium element wrappers (Input, Select, TextArea, Checkbox, Email)
├── src/test/java/
│   ├── tests/          # Test classes (BaseTest, AddAccountTest, AddAccountImprovedTest)
│   ├── listeners/      # TestNG listeners (TestListener)
│   └── utils/          # Test utilities (AllureUtils, Retry)
└── src/test/resources/
    └── log4j2-test.xml # Logging configuration
```

The project follows the **Page Object Model (POM)** pattern with a step-layer on top. Pages handle DOM interactions, steps orchestrate multi-page flows, and tests declare business scenarios.

## Build, Test, and Development Commands

| Command | Description |
|---------|-------------|
| `mvn clean compile` | Compile source and test code |
| `mvn test` | Run tests (uses `src/test/resources/crossBrowser.xml` suite) |
| `mvn allure:serve` | Generate and open Allure report from existing results |
| `mvn allure:clean` | Clear previous Allure results |

Tests are configured via TestNG XML suites. The default suite is `crossBrowser.xml` (cross-browser execution). Create this file in `src/test/resources/` before running.

## Coding Style & Naming Conventions

- **Java 17**, UTF-8 encoding
- **Lombok** used extensively (`@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`)
- **Page Objects**: each page extends `BasePage`, provides `isPageOpened()` / `open()` methods returning `this` for fluent chaining
- **Steps**: coordinate multi-page interactions, instantiate pages via constructor
- **Wrappers**: encapsulate common Selenium operations (`Input`, `Select`, `TextArea`, `Checkbox`, `Email`)
- **DTOs**: plain data classes with factory pattern for test data generation (`AccountFactory` using JavaFaker)
- **Naming**: pages use `*Page.java`, steps use `*Step.java`, tests use `*Test.java`, methods use `should*` or `*AndVerify*` format

## Testing Guidelines

- **Framework**: TestNG 7.12.0
- **Assertions**: standard `org.testng.Assert.assertEquals`
- **Allure**: `@Epic`, `@Feature`, `@Story`, `@Severity` annotations for reporting
- **Retry**: `utils.Retry` — up to 3 retries for flaky tests, applied via `retryAnalyzer = Retry.class`
- **Groups**: use TestNG groups (e.g., `groups = "smoke"`) for selective test execution
- **Listeners**: `TestListener` captures screenshots on failure via `AllureUtils`
- **Data**: prefer `AccountFactory` for randomized data; add `UUID` to names for uniqueness on shared environments

## Commit & Pull Request Guidelines

Commit messages follow the observed project convention:

- **Imperative mood**: "Added Steps", "Fix log for isPageOpened"
- **Concise subject line** (no prefix like [FIX] or [FEAT])
- **PRs**: merge-based workflow (e.g., "Merge pull request #1 from feature/wrappers")

Branch naming: `feature/<name>`, `fix/<name>`.

## Security & Configuration Tips

- Demo credentials are hardcoded (`will` / `will`) — move to a config file or environment variables for production use
- `crossBrowser.xml` is referenced in `pom.xml` but not committed — create it per-environment
- Browser options (Chrome incognito, notifications disabled) are set in `BaseTest.setUp()`
