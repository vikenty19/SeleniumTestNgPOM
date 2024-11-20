package com.tutorialsninja.qa.testcases;

import POMpages.AccountPage;
import POMpages.HomePage;
import POMpages.LoginPage;
import com.tutorialsninja.base.Base;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

import static com.tutorialsninja.qa.utils.Utilities.generateRandomEmail;
import static com.tutorialsninja.qa.utils.Utilities.getTestDataFromExcel;


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

    @Test(priority = 1,dataProvider ="validCredentialsData")
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

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassport() {
        loginPage.enterEmail(properties.getProperty("validEmail"));
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.login(properties.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
        loginPage.clickSubmitBtn();
        String actualWarningMessage = loginPage.actualWarningMessage();
        String expectedWarningMessage = dataProp.getProperty("warningMessageForInvalidPassword");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
        loginPage.enterEmail(generateRandomEmail());
        loginPage.enterPassword(properties.getProperty("password"));
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
        Object[][]data ={{"amotooricap9gmail.com","12345"},
                {"amotooricap3gmail.com","12345"},
                {"amotooricap1gmail.com","12345"}};
        return data;
    }
    @DataProvider(name = "validCredentialsData")
    public Object[][] excelTestData(){
        Object[][] testData =getTestDataFromExcel("Login");
        return testData;
    }


}

