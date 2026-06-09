package tests;

import dto.Account;
import org.testng.annotations.Test;

import static dto.AccountFactory.getAccount;
import static org.testng.Assert.assertEquals;

public class AddAccountTest extends BaseTest {
    Account account = getAccount(
            "Integrator",
            "Energy");

    @Test
    public void checkAddAccount() {
        loginStep.auth("will", "will");
        accountStep.createAccount(account);
        assertEquals(
                accountModulePage.getAccountName().toLowerCase(),
                account.getName().toLowerCase(),
                "Account name does not match");
    }
}
