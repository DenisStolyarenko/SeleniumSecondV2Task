package ctc.pages;

import ctc.Service.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateBTPage extends AbstractPage {
    private static final Date currentDate = new Date();
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final DateFormat inputFormat = new SimpleDateFormat("MM/dd/yy");
    private static long msDay = 7 * 24 * 60 * 60 * 1000;  //миллисекунд в 7 сутках
    private static final Integer estimatedBudget = new Random().nextInt(100000);//поправить на статичное значение

    private static final String plannedStartDate = inputFormat.format(currentDate); //
    private static final String plannedEndDate = inputFormat.format(currentDate.getTime() + msDay);

    private static final By SAVE_BUTTON_LOCATOR = By.xpath("//button[text()[contains(.,'Save Changes')]]");
    private static final By PLANNED_DURATION_LOCATOR = By.xpath("//span[@id='plannedDuration']");
    private static final By PLANNED_START_DATE_LOCATOR = By.xpath("//input[@id='plannedStartDate_ui']");
    private static final By PLANNED_END_DATE_LOCATOR = By.xpath("//input[@id='plannedEndDate_ui']");
    private static final By DESTINATION_COUNTRY_LOCATOR = By.xpath(".//select[@name='destinationCountryId']");
    private static final By DESTINATION_CITY_LOCATOR = By.xpath("//input[@name='destinationCity']");
    private static final By DESTINATION_ADDRESS_LOCATOR = By.xpath("//textarea[@name='destinationAddress']");
    private static final By DESCRIPTION_LOCATOR = By.xpath("//textarea[@id='description']");
    private static final By TICKET_LOCATOR = By.xpath("//input[@id='ticketsRequired']");
    private static final By CAR_LOCATOR = By.xpath("//input[@id='carRequired']");
    private static final By ESTIMATE_BUDGET_LOCATOR = By.xpath("//input[@class='textfield textfieldDigit textfieldAmount' and @name='estimatedBudget']");
    private static final By SUMMARY_LOCATOR = By.xpath("//input[@name='itemName']");
    public static final By BT_ID_LOCATOR = By.xpath("//span[@class='item' and contains(text(), 'Business Trip ID: #')]/a");

    public CreateBTPage fillMandatoryFields(String projectName, String country, String destinationCity, String destinationAddress) {
        String description = "Travel to " + destinationCity + " " + sdf.format(currentDate);
        ChooseProjectBlock chooseProjectPage = new ChooseProjectBlock().open().searchProjectOrCost(projectName).clickByOK();
        waitForElementEnabled(PLANNED_END_DATE_LOCATOR);
        Select countrySelect = new Select(Driver.getDriverInstance().findElement(DESTINATION_COUNTRY_LOCATOR));
        countrySelect.selectByVisibleText(country);
        Driver.getDriverInstance().findElement(DESTINATION_CITY_LOCATOR).sendKeys(destinationCity);
        Driver.getDriverInstance().findElement(DESTINATION_ADDRESS_LOCATOR).sendKeys(destinationAddress);
        Driver.getDriverInstance().findElement(DESCRIPTION_LOCATOR).sendKeys(description);
        if (!Driver.getDriverInstance().findElement(TICKET_LOCATOR).isSelected()) {
            Driver.getDriverInstance().findElement(TICKET_LOCATOR).click();
        }
        if (!Driver.getDriverInstance().findElement(CAR_LOCATOR).isSelected()) {
            Driver.getDriverInstance().findElement(CAR_LOCATOR).click();
        }
        Driver.getDriverInstance().findElement(ESTIMATE_BUDGET_LOCATOR).sendKeys(estimatedBudget.toString());
        Driver.getDriverInstance().findElement(PLANNED_START_DATE_LOCATOR).sendKeys(plannedStartDate);
        Driver.getDriverInstance().findElement(PLANNED_END_DATE_LOCATOR).sendKeys(plannedEndDate);
        Driver.getDriverInstance().findElement(SUMMARY_LOCATOR).sendKeys("BT created by Selenium " + sdf.format(currentDate));
        return this;
    }

    public CreateBTPage saveForm() {
        waitForElementVisibleEnabled(PLANNED_START_DATE_LOCATOR);
        waitForElementVisibleEnabled(SAVE_BUTTON_LOCATOR);
        waitForFillingPlanningDuration(PLANNED_DURATION_LOCATOR, "0");

        String executeString = Driver.getDriverInstance().findElement(SAVE_BUTTON_LOCATOR).getAttribute("onclick");
        ((JavascriptExecutor)Driver.getDriverInstance()).executeScript(executeString);
        return this;
    }

    public String getBTid(){
        return Driver.getDriverInstance().findElement(BT_ID_LOCATOR).getText();
    }


}
