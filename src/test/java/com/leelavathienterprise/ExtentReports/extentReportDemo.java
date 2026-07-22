package com.leelavathienterprise.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReportDemo {
    ExtentReports extent;

    @BeforeTest
    public void preparExtentReportConfig(){
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\target\\ExtentReports\\index.html");
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA Engineer", "Leelavathi");
    }

    @Test
    public void initialDemo(){
       ExtentTest test = extent.createTest("InitialDemo");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com");
        String title = driver.getTitle();
        extent.createTest("initialDemo").pass("Page title: " + title);
        driver.quit();
        test.fail("Result: Test failed due to some reason");
        extent.flush();
    }

    @AfterTest
    public void tearDownReport(){
        if (extent != null) {
            extent.flush();
        }

    }

}
