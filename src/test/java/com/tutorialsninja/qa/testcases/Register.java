package com.tutorialsninja.qa.testcases;
import POMpages.AccountSuccessPage;
import POMpages.HomePage;
import POMpages.RegisterPage;
import com.tutorialsninja.base.Base;
import com.tutorialsninja.qa.utils.Utilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Register extends Base {
    public Register(){
        super();
    }
    RegisterPage registerPage;

    @BeforeMethod
    public void openRegisterPage(){
      //  loadProperties(); //call it from Base class 1 way public void loadProperties()
        setBrowserAndOpenUrl();
        HomePage homePage = new HomePage(driver);
     //   homePage.clickOnAccountBtn();
        registerPage= homePage.navigateToRegisterPage();


    }


    @Test
    public void verifyRegisterUserWithMandatoryFields() {

        registerPage.registerUserWithValidCredentials(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),
                Utilities.generateRandomEmail(),properties.getProperty("password"), dataProp.getProperty("telephoneNumber") );
        AccountSuccessPage accountSuccessPage= registerPage.clickOnContinueBtn();
        String actualMessage =accountSuccessPage.successMessage();
        String successMessage = dataProp.getProperty("accountCreatedMessage");
         Assert.assertEquals(actualMessage,successMessage,"account is NOT created");
        driver.quit();
    }
    @Test
     public void verifyRegisterUserWithExistingEmail(){

        registerPage.registerUserWithValidCredentials(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),
                properties.getProperty("validEmail"),properties.getProperty("password"), dataProp.getProperty("telephoneNumber") );
        registerPage.clickOnContinueBtn();
        String expectedWarningMessage = dataProp.getProperty("accountAlreadyRegisteredWarning");
         Assert.assertTrue(registerPage.warningMessage().contains(expectedWarningMessage));


     }
     @Test
     public void registerUserWithoutCredentials(){
      /*   registerPage.enterFirstName("");
         registerPage.enterLastName("");
         registerPage.enterEmail("");
         registerPage.enterTelephoneNumber("");
         registerPage.enterPassword("");
         registerPage.enterConfirmationPassword("");
         registerPage.selectPrivacyPolicy();*/
         registerPage.clickOnContinueBtn();
         String firstNameWarning = dataProp.getProperty("firstNameWarning");
         //there are several warning messages more, but i just not put it here
         Assert.assertTrue(registerPage.getFirstNameWarning().contains(firstNameWarning));



     }

}
