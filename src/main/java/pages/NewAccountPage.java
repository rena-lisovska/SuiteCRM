package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import wrappers.*;

@Log4j2
public class NewAccountPage extends BasePage {

    public NewAccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Opening new account page")
    public void open() {
        log.info("Opening new account page");
        driver.get("https://demo.suiteondemand.com/index.php?module=Accounts&action=EditView&return_module=Accounts&return_action=DetailView");
    }

    @Step("Creating new account")
    public void createAccount(String name, String phone, String fax, String email, String billingStreet, String shippingStreet, String description, String type, String industry) {
        log.info("Creating account with name '{}'", name);
        new Input(driver, "Name").write(name);
        new Input(driver, "Office Phone").write(phone);
        new Input(driver, "Fax").write(fax);
        new Email(driver, "Email Address").write(email);
        new TextArea(driver, "Billing Address", "Street").writeAddress(billingStreet);
        new TextArea(driver, "Shipping Address", "Street").writeAddress(shippingStreet);
        new TextArea(driver, "Description").write(description);
        new Select(driver, "Type").select(type);
        new Select(driver, "Industry").select(industry);
    }

    @Step("Saving new account")
    public void saveNewAccount() {
        log.info("Saving new account");
        driver.findElement(By.xpath("//div[@id='EditView_tabs']/following-sibling::div[@class='buttons']/input[@title='Save']"))
                .click();
    }
}
