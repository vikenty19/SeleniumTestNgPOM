package com.tutorialsninja.qa.testcases;

import POMpages.AccountPage;
import POMpages.HomePage;
import POMpages.LoginPage;
import com.tutorialsninja.base.Base;
import listeners.MyListeners;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;

import static com.tutorialsninja.qa.utils.Utilities.generateRandomEmail;
import static com.tutorialsninja.qa.utils.Utilities.getTestDataFromExcel;

@Listeners({MyListeners.class})
public class Login extends Base {
    //Call the constructor from Base()
    public Login(){
        super();
    }
    LoginPage loginPage;


    @BeforeMethod
    public void openLoginPage() {

        setBrowserAndOpenUrl();
        HomePage homePage = new HomePage(driver);
        homePage.clickOnAccountBtn();
        loginPage= homePage.selectLoginBtn();


    }

    @Test(enabled = false,priority = 1,dataProvider ="validCredentialsData")
    public void verifyLoginWithValidCredentials(String email,String password) {
         loginPage.login(properties.getProperty("validEmail"),properties.getProperty("password"));
        AccountPage accountPage= loginPage.clickSubmitBtn();
        Assert.assertTrue(accountPage.isYourAccountOptionDisplayed(), "Edit your account info");

    }

    @Test(priority = 2, invocationCount = 1)
    public void verifyLoginWithInvalidCredentials() {
        loginPage.login(generateRandomEmail(),dataProp.getProperty("invalidPassword"));
        loginPage.clickSubmitBtn();
        String actualWarningMessage = loginPage.actualWarningMessage();
        String expectedWarningMessage = dataProp.getProperty("warningMessageForInvalidPassword");
        Assert.assertTrue((actualWarningMessage).contains(expectedWarningMessage));

    }

    @Test(priority = 4)//made it failed deliberately
    public void verifyLoginWithValidEmailAndInvalidPassport() {
        loginPage.login(properties.getProperty("validEmail"),"12345");//dataProp.getProperty("invalidPassword"));
        loginPage.clickSubmitBtn();
        String actualWarningMessage = loginPage.actualWarningMessage();
        String expectedWarningMessage = dataProp.getProperty("warningMessageForInvalidPassword");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
        loginPage.login(generateRandomEmail(),properties.getProperty("password"));
        loginPage.clickSubmitBtn();
        String actualWarningMessage =loginPage.actualWarningMessage();
        String expectedWarningMessage = dataProp.getProperty("warningMessageForInvalidPassword");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

    }

    @Test(priority = 5)
    public void verifyLoginWithoutCredentials() {
        loginPage.login("","");
        loginPage.clickSubmitBtn();
        String actualWarningMessage = loginPage.actualWarningMessage();;
        String expectedWarningMessage = dataProp.getProperty("warningMessageForInvalidPassword");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

    }
    @DataProvider(name = "validCredentials")
 public Object[][] loginWithTestData() {
        Object[][]data ={{"amotooricap7gmail.com","12345"},
                {"amotooricap3gmail.com","12345"},
                {"amotooricap1gmail.com","12345"}};
        return data;
    }
    @DataProvider(name = "validCredentialsData")
    public Object[][] excelTestData(){
        Object[][] testData =getTestDataFromExcel("LoginTest");
        return testData;
    }


}

