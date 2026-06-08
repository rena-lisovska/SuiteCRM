package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.id("user_name");
    private final By PASSWORD_FIELD = By.id("username_password");
    private final By LOGIN_BUTTON = By.cssSelector("[title='Log In']");
    private final By ERROR_MESSAGE = By.xpath("//span[@class='error' and contains(text(),'valid username and password')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Opening the loginPage")
    public void open() {
        log.info("Opening the login page");
        driver.get(BASE_URL);
    }

    @Step("Login with username '{user}' and password '{password}'")
    public void login(String user, String password) {
        log.info("Login with name '{}' and password '{}'", user, password);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Error message for invalid credentials on loginPage")
    public String getErrorMessage() {
        log.info("Displaying an error message when the login is incorrect");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
