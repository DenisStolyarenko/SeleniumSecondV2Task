package ctc.Service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 20;
    protected static WebDriver driver;

    public WebDriverFactory() {
    }

    public static WebDriver getWebDriverInstance() {
        if (driver != null) {
            return driver;
        }
        return driver = startBrowser();
    }

    private static WebDriver startBrowser(){
        WebDriver driver = null;
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeBrowser(){
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Cannot kill browser");
            } finally {
                driver = null;
            }
        }
    }

}
