package steps;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginStep {

    WebDriver driver;
    LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public void auth(String user, String password) {
        loginPage.open()
                .isPageOpened()
                .loginWithPositiveCred(user, password);
    }
}
