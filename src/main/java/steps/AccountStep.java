package steps;

import dto.Account;
import org.openqa.selenium.WebDriver;
import pages.AccountNewPage;
import pages.MainPage;

public class AccountStep {

    WebDriver driver;
    AccountNewPage accountNewPage;
    MainPage mainPage;

    public AccountStep(WebDriver driver) {
        this.driver = driver;
        accountNewPage = new AccountNewPage(driver);
        mainPage = new MainPage(driver);
    }

    public void createAccount(Account account) {
        mainPage.open()
                .isPageOpened()
                .openNewAccount();
        accountNewPage.isPageOpened()
                .createAccount(account)
                .saveNewAccount();
    }
}
