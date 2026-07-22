package com.leelavathienterprise.resources.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public  class extentReportconfiguration {

    public static ExtentReports preparExtentReportConfig(){
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\target\\ExtentReports\\index.html");
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

       ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA Engineer", "Leelavathi");
        return extent;
    }

    
}
