package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class MainPage extends BasePage {

    @FindBy(xpath = "//h1[text()='Welcome to the SuiteCRM 7 Demo']")
    WebElement TITLE;

    @FindBy(xpath = "//div[contains(@class,'desktop-bar')]//a[normalize-space(.)='CREATE']")
    WebElement CREATE_BUTTON;

    @FindBy(xpath = "//div[contains(@class,'desktop-bar')]//*[@id='quickcreatetop']//a[contains(@href,'module=Accounts') and contains(@href,'EditView')]")
    WebElement CREATE_ACCOUNT_BUTTON;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    @Step("Opening the Main page")
    public MainPage open() {
        log.info("Opening the Main page");
        driver.get(BASE_URL + "/index.php?module=Home&action=Demo");
        return this;
    }

    @Override
    @Step("Main page is opened")
    public MainPage isPageOpened() {
        log.info("Main page page is opened");
        wait.until(ExpectedConditions.visibilityOf(TITLE));
        return this;
    }

    @Step("Opening the New Account page through the 'Create Account' button in the tool bar")
    public AccountNewPage openNewAccount() {
        log.info("Opening the New Account page through the 'Create Account' button in the tool bar");
        CREATE_BUTTON.click();
        CREATE_ACCOUNT_BUTTON.click();
        return new AccountNewPage(driver);
    }
}
