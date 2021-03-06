package ctc.pages;

import ctc.Service.Driver;
import org.openqa.selenium.By;

public class DashBoardPage extends AbstractPage {
    private final By LOGGED_LABEL_LOCATOR = By.xpath("//td[@id='headerLogin']/div[@class='blInfoLogin']");
    private final By DASHBOARD_LABEL_LOCATOR = By.xpath("//td[@class='header1']/h1[text()='Dashboard']");

    public String readLoggedinText(){
        waitForElementPresent(DASHBOARD_LABEL_LOCATOR);
        return Driver.getDriverInstance().findElement(LOGGED_LABEL_LOCATOR).getText();
    }
}
