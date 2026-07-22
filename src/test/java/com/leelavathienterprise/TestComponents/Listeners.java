package com.leelavathienterprise.TestComponents;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.leelavathienterprise.resources.ExtentReports.extentReportconfiguration;
public class Listeners extends basetest implements ITestListener {
    WebDriver driver;
    ExtentReports extent=new extentReportconfiguration().preparExtentReportConfig();
    // Note: The method preparExtentReportConfig() should return ExtentReports instance.
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); 
    // ThreadLocal is a class that provides to make test to  synchronize and thread safe. 
    // While running parallel test or execution of test cases to avoid the overriding of test cases in extent report we use ThreadLocal class.
    //while running tests are concurrently, each object creation has its own threads, preventing conflicts and ensuring accurate
    //  reporting for each test case.
    ExtentTest test;
     @Override
    public void onTestStart(ITestResult result) {
test = extent.createTest(result.getMethod().getMethodName());
extentTest.set(test);// it will assign one unique thread id(ErrorValidations)-->test to each test case and it will be stored in thread local memory.
    }
    @Override
    public void onTestSuccess(ITestResult result) { 
        // This will execute when a test passes after the script passed this will execute 
    
    extentTest.get().log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult result)  {  
         // This will execute when a test fails
        System.out.println("Test failed: " + result.getName());
        extentTest.get().fail(result.getThrowable());
                //test.log(Status.FAIL,"Test failed: " + result.getThrowable());
                try {
                    driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }


        String filePath=null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        
         
    }       
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }   
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + result.getName());
    }   
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test started: " + context.getName());
    }
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test finished: " + context.getName());
        extent.flush(); // This will write the test results to the report
    }
}
