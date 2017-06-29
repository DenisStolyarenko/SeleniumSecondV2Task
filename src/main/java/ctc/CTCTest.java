package ctc;

import ctc.Service.Driver;
import ctc.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CTCTest {
    private WebDriver driver;

//    private static final String baseUrl = "https://tst1.epm-ctc.projects.epam.com/";
//    private static final String userName = "dst";
//    private static final String pwdName = "0";
    private static final String projectName = "ENRC-TRD";
    private static final String country = "Belarus";
    private static final String destinationCity = "Minsk";
    private static final String destinationAddress = "Minsk";
//    private static final String firstName = "Denis";
    private static final String textAfterSuccessfulLogin = "Logged in as ";
    private static final String sectionName = "Business Trips";
    private static final String textWelcome = "Welcome to EPAM Cost Tracking Center";


    @Test(description = "Log in")
    @Parameters({"baseUrl", "userName", "pwdName", "firstName"})
    public void loginTest(String baseUrl, String userName, String pwdName, String firstName){
        DashBoardPage loginPage = new LoginPage().open(baseUrl).login(userName, pwdName);
        Assert.assertTrue(loginPage.readLoggedinText().contains(textAfterSuccessfulLogin + firstName), "Impossible to login to CTC");
    }

    @Test(dependsOnMethods = "loginTest", description = "check opening the list of Bussiness Trips")
    @Parameters({"baseUrl"})
    public void openListOfBT(String baseUrl) {
        BTListPage btListPage = new BTListPage().open(baseUrl);
        Assert.assertEquals(btListPage.readListName(), sectionName, "The section did not found");
        btListPage.newBtClick();
    }

    @Test(dependsOnMethods = "openListOfBT", description = "create new BT")
    @Parameters({"projectName", "country", "destinationCity", "destinationAddress"})
    public void createNewBt(){
        CreateBTPage createBTPage = new CreateBTPage().fillMandatoryFields(projectName,country, destinationCity, destinationAddress).saveForm();
        Assert.assertEquals(createBTPage.getBTid().length(), 19, "Business Trip is not created");
    }

    @Test(dependsOnMethods = "createNewBt", description = "Log out")
    public void logOut() {
        LogOutBlock logOutBlock = new LogOutBlock().logout().confirmation();
        Assert.assertTrue(new LoginPage().readIntroductionText().contains(textWelcome),"Logout is not performed");
    }

    @AfterClass(description = "Close browser")
    public void closeBrowser(){
        Driver.closeBrowser();
    }
}
