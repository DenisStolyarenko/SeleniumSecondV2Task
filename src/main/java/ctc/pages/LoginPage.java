package ctc.pages;

import ctc.Service.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {
    private final String ADDITIONAL_URL = "/login.do?logout=true&tz=GMT%2B06:00";
    private static final By USER_NAME_INPUT_LOCATOR = By.xpath("//input[@name='username']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@name='password']");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//input[@name='Login']");
    private static final By LOGIN_PAGE_TEXT_LOCATOR = By.xpath("//td[@class='header1']/h1");

    public DashBoardPage login(String userName, String pwdName){
        Driver.getDriverInstance().findElement(USER_NAME_INPUT_LOCATOR).sendKeys(userName);
        Driver.getDriverInstance().findElement(PASSWORD_INPUT_LOCATOR).sendKeys(pwdName);
        Driver.getDriverInstance().findElement(LOGIN_BUTTON_LOCATOR).click();
        return new DashBoardPage();
    }

    public LoginPage open(String baseUrl){
        Driver.getDriverInstance().get(baseUrl + ADDITIONAL_URL);
        return this;
    }

    public String readIntroductionText(){
        return Driver.getDriverInstance().findElement(LOGIN_PAGE_TEXT_LOCATOR).getText();
    }
}
