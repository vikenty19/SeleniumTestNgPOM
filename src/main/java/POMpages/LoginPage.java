package POMpages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  {
    WebDriver driver ;
    @FindBy(css = "#input-email")
    WebElement emailField;

    @FindBy(id = "input-password")
    WebElement passwordField;
    @FindBy(css = "[type='submit']")
    WebElement submitBtn;
    @FindBy(css=".alert")
    WebElement warningAlertMessage;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
    public void enterEmail(String email){
       emailField.sendKeys(email);

    }
    public void enterPassword(String password){
        passwordField.sendKeys(password);

    }
    public AccountPage clickSubmitBtn(){
        submitBtn.click();
        return new AccountPage(driver);
    }
    public String actualWarningMessage(){
        String actualWarningMessage = warningAlertMessage.getText();
        return actualWarningMessage;

    }
}
