package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddAccountTest extends BaseTest {

    @Test
    public void checkAddAccount() {
        loginPage.open()
                .isPageOpened()
                .loginWithPositiveCred("will", "will")
                .isPageOpened()
                .openNewAccount()
                .isPageOpened()
                .createAccount(
                        "Test11111",
                        "+375291112233",
                        "+375173332211",
                        "i@mail.ru",
                        "ilis@yande",
                        "Energy",
                        "123",
                        "Analyst",
                        "Apparel")
                .saveNewAccount();
    }
}
