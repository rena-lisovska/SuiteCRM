package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AccountModulePage extends BasePage {

    public AccountModulePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage open() {
        return this;
    }

    @Override
    @Step("Opened Account Module page after save new Account")
    public AccountModulePage isPageOpened() {
        log.info("Opened Account Module page after save new Account");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Name:')]/following-sibling::div")));
        return this;
    }

    @Step("Checking account saving")
    public String getAccountName() {
        log.info("Checking account saving");
        return driver.findElement(By.cssSelector(".module-title-text")).getText();
    }
}
