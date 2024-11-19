package com.tutorialsninja.qa.testcases;

import POMpages.AccountPage;
import POMpages.HomePage;
import POMpages.LoginPage;
import com.tutorialsninja.base.Base;
import com.tutorialsninja.qa.utils.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

import static com.tutorialsninja.qa.utils.Utilities.generateRandomEmail;
import static com.tutorialsninja.qa.utils.Utilities.getTestDataFromExcel;
import static org.testng.TestRunner.PriorityWeight.dependsOnMethods;

public class Login extends Base {
    //Call the constructor from Base()
    public Login(){
        super();
    }

    @BeforeMethod
    public void openLoginPage() {

        setBrowserAndOpenUrl();
        HomePage homePage = new HomePage(driver);
        homePage.clickOnAccountBtn();
         homePage.selectLoginBtn();


    }

    @Test(priority = 1,dataProvider ="validCredentialsData")
    public void verifyLoginWithValidCredentials(String email,String password) {
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        loginPage.enterEmail(properties.getProperty("validEmail"));
    //    driver.findElement(By.cssSelector("#input-email")).sendKeys(properties.getProperty("validEmail"));
        loginPage.enterPassword(properties.getProperty("password"));
        loginPage.clickSubmitBtn();
     //  driver.findElement(By.cssSelector("[type='submit']")).click();

        Assert.assertTrue(accountPage.isYourAccountOptionDisplayed(), "Edit your account info");

    }

    @Test(priority = 2, invocationCount = 1)
    public void verifyLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(generateRandomEmail());
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.clickSubmitBtn();
        String actualWarningMessage = loginPage.actualWarningMessage();
        String expectedWarningMessage = dataProp.getProperty("warningMessageForInvalidPassword");
        Assert.assertTrue((actualWarningMessage).contains(expectedWarningMessage));

    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassport() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(properties.getProperty("validEmail"));
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.clickSubmitBtn();
        String actualWarningMessage = loginPage.actualWarningMessage();
        String expectedWarningMessage = dataProp.getProperty("warningMessageForInvalidPassword");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmail(generateRandomEmail());
        loginPage.enterPassword(properties.getProperty("password"));
        loginPage.clickSubmitBtn();
        String actualWarningMessage =loginPage.actualWarningMessage();
        String expectedWarningMessage = dataProp.getProperty("warningMessageForInvalidPassword");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

    }

    @Test(priority = 5)
    public void verifyLoginWithoutCredentials() {
        LoginPage loginPage= new LoginPage(driver);
       loginPage.enterEmail("");
        loginPage.enterPassword("");
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

