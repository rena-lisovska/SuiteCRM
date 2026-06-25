package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextArea {

    WebDriver driver;
    String labelAddress;
    String label;

    public TextArea(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public TextArea(WebDriver driver, String labelAddress, String label) {
        this.driver = driver;
        this.labelAddress = labelAddress;
        this.label = label;
    }

    public void write(String text) {
        driver.findElement(By.xpath(String.format("//div[contains(text(), '%s')]/parent::div//textarea", label)))
                .sendKeys(text);
    }

    public void writeAddress(String text) {
        driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]//ancestor::" +
                "div[contains(@class, 'edit-view-field')]//label[contains(text(), '%s')]//ancestor::" +
                "tr//textarea", labelAddress, label))).sendKeys(text);
    }
}
