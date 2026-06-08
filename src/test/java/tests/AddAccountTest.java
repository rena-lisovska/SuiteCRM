package tests;

import org.testng.annotations.Test;

public class AddAccountTest extends BaseTest {

    @Test
    public void checkAddAccount() {
        loginPage.open();
        loginPage.login("will", "will");
        newAccountPage.open();
        newAccountPage.createAccount(
                "Test",
                "+375291112233",
                "+375173332211",
                "i@mail.ru",
                "ilis@yande",
                "Energy",
                "123",
                "Analyst",
                "Apparel");
        newAccountPage.saveNewAccount();
    }
}
