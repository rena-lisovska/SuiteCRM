package tests;

import dto.Account;
import org.testng.annotations.Test;

import static dto.AccountFactory.getAccount;

public class AddAccountTest extends BaseTest {
    Account account = getAccount(
            "Integrator",
            "Energy");

    @Test
    public void checkAddAccount() {
        loginPage.open()
                .isPageOpened()
                .loginWithPositiveCred("will", "will")
                .isPageOpened()
                .openNewAccount()
                .isPageOpened()
                .createAccount(account)
                .saveNewAccount();
    }
}
