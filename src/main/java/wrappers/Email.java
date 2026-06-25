package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Email {

    private final WebDriver driver;
    private final String label;

    public Email(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String email) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//input[contains(@id,'Accounts0emailAddress')]", label))));
        element.clear();
        element.sendKeys(email);
    }
}
