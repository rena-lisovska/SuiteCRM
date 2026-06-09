package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    @FindBy(id = "user_name")
    WebElement USERNAME_FIELD;

    @FindBy(id = "username_password")
    WebElement PASSWORD_FIELD;

    @FindBy(css = "[title='Log In']")
    WebElement LOGIN_BUTTON;

    @FindBy(xpath = "//span[@class='error' and contains(text(),'valid username and password')]")
    WebElement ERROR_MESSAGE;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    @Step("Opening the Login page")
    public LoginPage open() {
        log.info("Opening the Login page");
        driver.get(BASE_URL);
        return this;
    }

    @Override
    @Step("Login page is opened")
    public LoginPage isPageOpened() {
        log.info("Login page is opened");
        wait.until(ExpectedConditions.visibilityOf(LOGIN_BUTTON));
        return this;
    }

    @Step("Positive login with username '{user}' and password '{password}'")
    public MainPage loginWithPositiveCred(String user, String password) {
        log.info("Login with name '{}' and password '{}'", user, password);
        USERNAME_FIELD.sendKeys(user);
        PASSWORD_FIELD.sendKeys(password);
        LOGIN_BUTTON.click();
        return new MainPage(driver);
    }

    @Step("Negative login with username '{user}' and password '{password}'")
    public LoginPage loginWithNegativeCred(String user, String password) {
        log.info("Login with name '{}' and password '{}'", user, password);
        USERNAME_FIELD.sendKeys(user);
        PASSWORD_FIELD.sendKeys(password);
        LOGIN_BUTTON.click();
        return this;
    }

    @Step("Error message for invalid credentials on loginPage")
    public String getErrorMessage() {
        log.info("Displaying an error message when the login is incorrect");
        return ERROR_MESSAGE.getText();
    }
}
