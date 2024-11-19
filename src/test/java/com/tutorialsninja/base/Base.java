package com.tutorialsninja.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public static WebDriver driver = null;
    public static WebDriverWait wait;
   public Properties properties;
   public Properties dataProp;
  //  String browser = "chrome";
    //create a constructor
    public  Base(){
        //for reading base data of project
         properties = new Properties();

        File file = new File(System
                .getProperty("user.dir") + "\\src\\main\\java\\properties\\config.properties");
      try {
          FileInputStream fis = new FileInputStream(file);
          properties.load(fis);//C:\Users\Acer\Documents\GitHub1\HybridTestNgProjectsrs\main\java\com\tutorialninja.qa.config\config.properties
      }catch (Throwable e){
          e.printStackTrace();
      }
        //for reading other Data (negative cases)
        dataProp = new Properties();
      File file1 = new File(System
              .getProperty("user.dir")+"/src/main/java/testData/testData.properties");
      try {FileInputStream fis2 = new FileInputStream(file1);
          dataProp.load(fis2);
      } catch (Throwable e) {
          e.printStackTrace();
      }
    }
    public WebDriver  pickBrowserAndOpenUrl(String pickBrowser) {

        switch (pickBrowser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return driver = new EdgeDriver();
            default:
                WebDriverManager.chromedriver().setup();
                return driver = new ChromeDriver();


        }

    }

    public void setBrowserAndOpenUrl() {
        pickBrowserAndOpenUrl(properties.getProperty("browser"));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(properties.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}