package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners implements ITestListener{

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

    @Override
    public void onTestFailure(ITestResult result) {
        String testName= result.getName();
        System.out.println(testName+"  got failed!");
        System.out.println(result.getThrowable());
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
