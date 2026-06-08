package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Select {

    private final WebDriver driver;
    private final String label;
    private final String SELECT_PATTERN = "//div[contains(text(), '%s')]/following-sibling::div//";

    public Select(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        log.info("Selecting '{}' from '{}' dropdown", option, label);
        driver.findElement(By.xpath
                (String.format(SELECT_PATTERN + "select", label))).click();
        driver.findElement(By.xpath(String.format(SELECT_PATTERN + "option[contains(text(), '%s')]", label, option))).click();
    }
}
