package pages;

import dto.Account;
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
    public AccountNewPage createAccount(Account account) {
        new Input(driver, "Name").write(account.getName());
        new Input(driver, "Office Phone").write(account.getPhone());
        new Input(driver, "Website").write(account.getWebsite());
        new Input(driver, "Fax").write(account.getFax());
        new Email(driver, "Email Address").write(account.getEmail_address());
        new TextArea(driver, "Billing Address", "Street").writeAddress(account.getBillingStreet());
        new TextArea(driver, "Shipping Address", "Street").writeAddress(account.getShippingStreet());
        new Input(driver, "Billing Address", "City").writeAddress(account.getBillingCity());
        new Input(driver, "Shipping Address", "City").writeAddress(account.getShippingCity());
        new Input(driver, "Billing Address", "State/Region").writeAddress(account.getBillingState());
        new Input(driver, "Shipping Address", "State/Region").writeAddress(account.getShippingState());
        new Input(driver, "Billing Address", "Postal Code").writeAddress(account.getBillingPostalCode());
        new Input(driver, "Shipping Address", "Postal Code").writeAddress(account.getShippingPostalCode());
        new Input(driver, "Billing Address", "Country").writeAddress(account.getBillingCountry());
        new Input(driver, "Shipping Address", "Country").writeAddress(account.getShippingCountry());
        new TextArea(driver, "Description").write(account.getDescription());
        new Select(driver, "Type").select(account.getType());
        new Select(driver, "Industry").select(account.getIndustry());
        return this;
    }

    @Step("Saving New Account")
    public AccountModulePage saveNewAccount() {
        log.info("Saving New Account");
        SAVE_BUTTON.click();
        return new AccountModulePage(driver);
    }
}
