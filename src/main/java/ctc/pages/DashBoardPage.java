package ctc.pages;

import ctc.Service.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashBoardPage extends AbstractPage {
    private By LOGGED_LABEL_LOCATOR = By.xpath("//td[@id='headerLogin']/div[@class='blInfoLogin']");
    private By DASHBOARD_LABEL_LOCATOR = By.xpath("//td[@class='header1']/h1[text()='Dashboard']");

    public String readLoggedinText(){
        waitForElementPresent(DASHBOARD_LABEL_LOCATOR);
        return Driver.getDriverInstance().findElement(LOGGED_LABEL_LOCATOR).getText();
    }
}
