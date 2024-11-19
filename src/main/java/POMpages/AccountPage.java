package POMpages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage  {
    WebDriver driver;
   public AccountPage(WebDriver driver){
       this.driver = driver;
       PageFactory.initElements(driver,this);
   }

    @FindBy(xpath = "//h2[contains(text(), 'My Account')]")
    WebElement yourAccountInfoOption;

    public Boolean isYourAccountOptionDisplayed(){
      Boolean accountAssertion =yourAccountInfoOption.isDisplayed();
        return accountAssertion;

    }
}
