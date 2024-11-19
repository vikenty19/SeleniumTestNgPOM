package POMpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver ;
    @FindBy(css = ".fa-user")
  private    WebElement myAccountMenu;
    @FindBy(linkText = "Login")
  private    WebElement loginOption;
    @FindBy(linkText ="Register")
    private  WebElement registerOption;
    @FindBy(name = "search")
    private WebElement searchField;
    @FindBy(css = ".input-group-btn>button")
    private WebElement searchBtn;
//constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
        //page Factory initialaize connection between Webelements and locators
        PageFactory.initElements(driver,this);

    }
    //actions
    public void clickSearchBtn(){
        searchBtn.click();
    }
    public void clickOnAccountBtn(){
        myAccountMenu.click();

    }
    public void selectLoginBtn(){
        loginOption.click();
    }

    public void selectRegisterBtn(){
        registerOption.click();
    }
    public void enterProductIntoSearchField(String productName){
        searchField.sendKeys(productName);

    }
}
