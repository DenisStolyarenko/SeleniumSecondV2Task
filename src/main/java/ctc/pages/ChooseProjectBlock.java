package ctc.pages;

import ctc.Service.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChooseProjectBlock extends AbstractPage{
    private static final String frameLookupDialogName = "frLookupDialog";
    private static final By CHOOSE_PROJECT_LOCATOR = By.xpath("//img[contains(@onclick,'chooseprojectcostobject')]");
    private static final By SEARCH_INPUT_LOCATOR = By.xpath("//input[@name='keywordSearch']");
    private static final By GO_BUTTON_LOCATOR = By.xpath("//input[@value='Go']");
    private static final By OK_BUTTON_LOCATOR = By.xpath("//input[@value='OK']");

    public ChooseProjectBlock open(){
        waitForElementEnabled(CHOOSE_PROJECT_LOCATOR);
        Driver.getDriverInstance().findElement(CHOOSE_PROJECT_LOCATOR).click();
        return this;
    }

    public ChooseProjectBlock searchProjectOrCost(String projectName){
        Driver.getDriverInstance().switchTo().frame(frameLookupDialogName);
        Driver.getDriverInstance().findElement(SEARCH_INPUT_LOCATOR).sendKeys(projectName);
        Driver.getDriverInstance().findElement(GO_BUTTON_LOCATOR).click();
        Driver.getDriverInstance().findElement(By.xpath("//input[@type='checkbox' and @projectcostobjectname='" + projectName + "']")).click();
        return this;
    }

    public ChooseProjectBlock clickByOK (){
        waitForElementEnabled(OK_BUTTON_LOCATOR);
        Driver.getDriverInstance().findElement(OK_BUTTON_LOCATOR).click();
        return this;
    }

}
