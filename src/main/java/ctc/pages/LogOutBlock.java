package ctc.pages;

import ctc.Service.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOutBlock extends AbstractPage {
    private final By LOGOUT_LOCATOR = By.xpath("//a[@href='logout.do']");

    public LogOutBlock logout(){
        waitForElementVisible(LOGOUT_LOCATOR);
        waitForElementEnabled(LOGOUT_LOCATOR);
        Driver.getDriverInstance().findElement(LOGOUT_LOCATOR).click();
        return this;
    }

    public LogOutBlock confirmation(){
        Driver.getDriverInstance().switchTo().alert().accept();
        return this;
    }


}
