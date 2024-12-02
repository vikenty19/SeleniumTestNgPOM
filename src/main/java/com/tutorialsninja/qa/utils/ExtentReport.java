package com.tutorialsninja.qa.utils;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReport {

    public void generateExtendReport(){
        ExtentReport extentReport = new ExtentReport();
        //create file and directory toi reports
        File extentReportFile = new File(System
                .getProperty("user.dir")+"/src/test/test-output/ExtentReports/extentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

    }
}
