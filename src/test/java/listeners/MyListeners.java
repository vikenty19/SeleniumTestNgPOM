package listeners;

import com.tutorialsninja.base.Base;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.io.FileHandler;

public class MyListeners extends Base implements ITestListener{

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Execution started");
    }
    @Override
    public void onTestStart(ITestResult result) {
      String testName= result.getName();
        System.out.println(testName+"   started executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String  testName = result.getName();
        System.out.println(testName + "    got executed successfully");

    }

 /*   @Override
    public void onTestFailure(ITestResult result) {
        String testName= result.getName();
        System.out.println(testName+"  got failed!");
        System.out.println(result.getThrowable());
        WebDriver driver =null;
        try {
            driver = (WebDriver)result.getTestClass()
                    .getRealClass()
                    .getDeclaredField("driver")
                    .get(result.getInstance());
            File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile,new File("./src/test/resources/Screenshots/FailedTests"+testName+".png"));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }*/
 @Override//made with chatGPT-5
 public void onTestFailure(ITestResult result) {
     String testName = result.getName();
     System.out.println(testName + " got failed!");
     System.out.println(result.getThrowable());

     try {
         WebDriver driver = getDriver(result.getInstance());
         if (driver != null) {
             File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
             FileHandler.copy(srcFile,
                     new File("./src/test/resources/Screenshots/FailedTests_" + testName + Utilities.timeStamp()+".png"));
             System.out.println("Screenshot saved for test: " + testName);
         } else {
             System.out.println("⚠ No driver found for test: " + testName);
         }
     } catch (Exception e) {
         throw new RuntimeException(e);
     }
 }

    private WebDriver getDriver(Object testInstance) throws Exception {
        Class<?> clazz = testInstance.getClass();
        while (clazz != null) {
            try {
                Field driverField = clazz.getDeclaredField("driver"); // look for 'driver'
                driverField.setAccessible(true);
                return (WebDriver) driverField.get(testInstance);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass(); // climb up to Base class
            }
        }
        return null;
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        String testName= result.getName();
        System.out.println(testName+"  got skipped");
        System.out.println(result.getThrowable());
    }



    @Override
    public void onFinish(ITestContext context) {

        System.out.println("Execution is completed!");
    }
}
