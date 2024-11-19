package POMpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//div[@class='caption']//a")
    private WebElement searchResult;

    @FindBy(xpath = "//div[@id= 'content']/input/following-sibling::p")
    WebElement noProductMessage;

    public String getNoProductMessageText(){
        String noProductText= noProductMessage.getText();
        return noProductText;
    }

    public Boolean successSearchResultIsDisplayed(){
        return  searchResult.isDisplayed();
    }


}
