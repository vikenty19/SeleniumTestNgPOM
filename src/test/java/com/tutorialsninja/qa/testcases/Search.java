package com.tutorialsninja.qa.testcases;

import POMpages.HomePage;
import POMpages.SearchPage;
import com.tutorialsninja.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Search extends Base {
    public Search(){
        super();
    }
  @Test
    public void verifySearchWithValidProduct(){
      setBrowserAndOpenUrl();
      HomePage homePage = new HomePage(driver);
      SearchPage searchPage =new SearchPage(driver);
      homePage.enterProductIntoSearchField(dataProp.getProperty("existingProduct"));
      homePage.clickSearchBtn();
      Assert.assertTrue(searchPage.successSearchResultIsDisplayed());



    }
    @Test
    public void verifySearchWithNotInStockProduct(){
      setBrowserAndOpenUrl();
      HomePage homePage = new HomePage(driver);
      SearchPage searchPage = new SearchPage(driver);
       homePage.enterProductIntoSearchField(dataProp.getProperty("not-existingProduct"));
       homePage.clickSearchBtn();
     //   WebElement info = wait.until(ExpectedConditions
      //          .visibilityOfElementLocated(By.xpath("//div[@id= 'content']/input/following-sibling::p")));
        String actualMessage = searchPage.getNoProductMessageText();
        String warningMessage = dataProp.getProperty("Not-existingProductWarning");
        Assert.assertEquals(actualMessage,warningMessage);

    }
    @Test
    public void verifySearchWithoutProduct(){
        setBrowserAndOpenUrl();
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        homePage.clickSearchBtn();

        String actualMessage = searchPage.getNoProductMessageText();
        String warningMessage = dataProp.getProperty("Not-existingProductWarning");
        Assert.assertEquals(actualMessage,warningMessage);


    }
}
