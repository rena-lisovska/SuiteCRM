package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.*;

@Log4j2
public class AccountNewPage extends BasePage {

    @FindBy(xpath = "//div[@id='EditView_tabs']/following-sibling::div[@class='buttons']/input[@title='Save']")
    WebElement SAVE_BUTTON;

    public AccountNewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    @Step("Opening New Account page via a direct link")
    public AccountNewPage open() {
        log.info("Opening New Account page via a direct link");
        driver.get("https://demo.suiteondemand.com/index.php?module=Accounts&action=EditView&return_module=Accounts&return_action=DetailView");
        return this;
    }

    @Override
    @Step("Opened New Account page")
    public AccountNewPage isPageOpened() {
        log.info("New Account page opened");
        wait.until(ExpectedConditions.visibilityOf(SAVE_BUTTON));
        return this;
    }

    @Step("Creating New Account")
    public AccountNewPage createAccount(String name, String phone, String fax, String email, String billingStreet, String shippingStreet, String description, String type, String industry) {
        new Input(driver, "Name").write(name);
        new Input(driver, "Office Phone").write(phone);
        new Input(driver, "Fax").write(fax);
        new Email(driver, "Email Address").write(email);
        new TextArea(driver, "Billing Address", "Street").writeAddress(billingStreet);
        new TextArea(driver, "Shipping Address", "Street").writeAddress(shippingStreet);
        new TextArea(driver, "Description").write(description);
        new Select(driver, "Type").select(type);
        new Select(driver, "Industry").select(industry);
        return this;
    }

    @Step("Saving New Account")
    public AccountModulePage saveNewAccount() {
        log.info("Saving New Account");
        SAVE_BUTTON.click();
        return new AccountModulePage(driver);
    }
}
