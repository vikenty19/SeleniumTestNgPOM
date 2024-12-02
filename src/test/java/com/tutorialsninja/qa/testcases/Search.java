package com.tutorialsninja.qa.testcases;

import POMpages.HomePage;
import POMpages.SearchPage;
import com.tutorialsninja.base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends Base {
    public Search() {
        super();
    }

    SearchPage searchPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        setBrowserAndOpenUrl();
        homePage = new HomePage(driver);
    }

    @Test
    public void verifySearchWithValidProduct() {
        homePage.enterProductIntoSearchField(dataProp.getProperty("existingProduct"));
        searchPage = homePage.clickSearchBtn();
        Assert.assertTrue(searchPage.successSearchResultIsDisplayed());

    }

    @Test
    public void verifySearchWithNotInStockProduct() {
        homePage.enterProductIntoSearchField(dataProp.getProperty("not-existingProduct"));
        //create SearchPage object on HomePage method
        searchPage = homePage.clickSearchBtn();
        //   WebElement info = wait.until(ExpectedConditions
        //          .visibilityOfElementLocated(By.xpath("//div[@id= 'content']/input/following-sibling::p")));
        String actualMessage = searchPage.getNoProductMessageText();
        String warningMessage = "abc"; //dataProp.getProperty("Not-existingProductWarning");
        Assert.assertEquals(actualMessage, warningMessage);

    }

    @Test(dependsOnMethods = "verifySearchWithNotInStockProduct")
    public void verifySearchWithoutProduct() {
        searchPage = homePage.clickSearchBtn();
        String actualMessage = searchPage.getNoProductMessageText();
        String warningMessage = dataProp.getProperty("Not-existingProductWarning");
        Assert.assertEquals(actualMessage, warningMessage);

    }
}
