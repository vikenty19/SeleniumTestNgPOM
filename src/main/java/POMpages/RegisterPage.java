package POMpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;


    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(id ="input-firstname")
    WebElement firstNameField;

    @FindBy(id = "input-lastname")
    WebElement lastNameField;
    @FindBy(id = "input-email")
    WebElement emailField;
    @FindBy(id="input-password")
    WebElement passwordField;

    @FindBy(id = "input-telephone")
    WebElement telephoneField;

    @FindBy(id="input-confirm")
    WebElement passwordConfirmationField;
    @FindBy(xpath ="//input[@name= 'agree']")   //"//input[@name= 'agree']"
    WebElement privacyPolicyField;

    @FindBy(css = "input.btn")
    WebElement continueBtn;
    @FindBy(css=".alert")
    WebElement alertMessage;
    @FindBy(xpath = "//div[contains(@class,'alert-dismissible)]")
    WebElement privacyPolicyWarning;

    @FindBy(xpath = "//input[@id = 'input-firstname']/following-sibling::div")
    WebElement firstNameWarning;
    public String getFirstNameWarning(){
        String firstName =firstNameWarning.getText();
        return firstName;
    }
  public String retrievePrivacyPolicyWarning(){

      String privacyPolicyText = privacyPolicyWarning.getText();
      return privacyPolicyText;
  }

    public void enterFirstName(String firstName){

        firstNameField.sendKeys(firstName);
    }
    public void enterLastName(String lastName){

        lastNameField.sendKeys(lastName);
    }
    public void enterEmail(String email){

        emailField.sendKeys(email);
    }
    public void enterTelephoneNumber(String phone){

       telephoneField.sendKeys(phone);
    }
    public void enterPassword(String password){

        passwordField.sendKeys(password);
    }
    public void enterConfirmationPassword(String password){

        passwordConfirmationField.sendKeys(password);
    }
    public void selectPrivacyPolicy(){

        privacyPolicyField.click();
    }
    public  AccountSuccessPage clickOnContinueBtn(){
        continueBtn.click();
        return new AccountSuccessPage(driver);
    }
    public String warningMessage(){
        String alert = alertMessage.getText();
        return alert;

    }

}
