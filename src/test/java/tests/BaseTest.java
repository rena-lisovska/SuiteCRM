package tests;

import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import pages.NewAccountPage;

import java.util.HashMap;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    NewAccountPage newAccountPage;

    @Parameters({"browser"})
    @BeforeMethod(
            alwaysRun = true,
            description = "Настройка браузера"
    )
    public void setUp(@Optional("chrome") String browser, ITestContext iTestContext) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }

        iTestContext.setAttribute("driver", driver);

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        newAccountPage = new NewAccountPage(driver);
    }

    @AfterMethod(
            alwaysRun = true,
            description = "Закрытие браузера"
    )
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
