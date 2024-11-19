package com.tutorialsninja.qa.testcases;
import POMpages.AccountSuccessPage;
import POMpages.HomePage;
import POMpages.RegisterPage;
import com.tutorialsninja.base.Base;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Register extends Base {
    public Register(){
        super();
    }

    @BeforeMethod
    public void openRegisterPage(){
      //  loadProperties(); //call it from Base class 1 way public void loadProperties()
        setBrowserAndOpenUrl();
        HomePage homePage = new HomePage(driver);
        homePage.clickOnAccountBtn();
        homePage.selectRegisterBtn();


    }


    @Test
    public void verifyRegisterUserWithMandatoryFields() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        //just for example we can use LoginPage
        registerPage.enterEmail(Utilities.generateRandomEmail());
        registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(properties.getProperty("password"));
        registerPage.enterConfirmationPassword(properties.getProperty("password"));
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueBtn();
        AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
        String actualMessage =accountSuccessPage.successMessage();
        String successMessage = dataProp.getProperty("accountCreatedMessage");
         Assert.assertEquals(actualMessage,successMessage,"account is NOT created");
        driver.quit();
    }
    @Test
     public void verifyRegisterUserWithExistingEmail(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
         registerPage.enterEmail(properties.getProperty("validEmail"));
        registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));;
        registerPage.enterPassword(properties.getProperty("password"));
        registerPage.enterConfirmationPassword(properties.getProperty("password"));
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueBtn();
         String duplicateEmailWarning = registerPage.warningMessage();
         String expectedWarningMessage = dataProp.getProperty("accountAlreadyRegisteredWarning");
         Assert.assertTrue(duplicateEmailWarning.contains(expectedWarningMessage));


     }
     @Test
     public void registerUserWithoutCredentials(){
         RegisterPage registerPage = new RegisterPage(driver);
         registerPage.enterFirstName("");
         registerPage.enterLastName("");
         registerPage.enterEmail("");
         registerPage.enterTelephoneNumber("");
         registerPage.enterPassword("");
         registerPage.enterConfirmationPassword("");
         registerPage.selectPrivacyPolicy();
         registerPage.clickOnContinueBtn();
         String enterNameWarning = registerPage.getFirstNameWarning();
         String firstNameWarning = dataProp.getProperty("firstNameWarning");
         Assert.assertTrue(enterNameWarning.contains(firstNameWarning));



     }

}
