package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Input {

    WebDriver driver;
    String labelAddress;
    String label;

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public Input(WebDriver driver, String labelAddress, String label) {
        this.driver = driver;
        this.labelAddress = labelAddress;
        this.label = label;
    }

    public void write(String text) {
        log.info("Entering text '{}' into field '{}'", text, label);
        driver.findElement(By.xpath(String.format("//div[contains(text(), '%s')]/parent::div//input", label)))
                .sendKeys(text);
    }

    public void writeAddress(String text) {
        log.info("Entering text '{}' in '{}' into field '{}'", text, labelAddress, label);
        driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]//ancestor::" +
                "div[contains(@class, 'edit-view-field')]//label[contains(text(), '%s')]//ancestor" +
                "::tr//input", labelAddress, label))).sendKeys(text);
    }
}
