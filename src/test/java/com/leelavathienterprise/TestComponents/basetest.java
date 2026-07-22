package com.leelavathienterprise.TestComponents;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leelavathienterprise.pageobjects.landingPage;

public class basetest {
   public  WebDriver driver;
   public landingPage land;
    public WebDriver initializeDriver() throws IOException{
        
        Properties prop = new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//leelavathienterprise//resources//GlobalData.properties");
        prop.load(fis);
      String browser=  System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
        if(browser.equalsIgnoreCase("chrome")){
        driver = new ChromeDriver();
    }
     else if(browser.equalsIgnoreCase("firefox")){
        driver = new FirefoxDriver();
    } else {
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataIntoMap(String filePath) throws IOException{
    //readJson to string
   String jsonContent= FileUtils.readFileToString(new File(filePath),
    StandardCharsets.UTF_8);
    //convert string to hashmap
    //jackson databind is dependency which helps to convert string to hashmap
    ObjectMapper objectMapper = new ObjectMapper();
    List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    return data;
}
  public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
 TakesScreenshot ts=((TakesScreenshot) driver);
    File src = ts.getScreenshotAs(OutputType.FILE);
    File file=new File(System.getProperty("user.dir") + "/target/ScreenShots/"+testCaseName+".png");
    FileUtils.copyFile(src,file );
    return System.getProperty("user.dir") + "/target/ScreenShots/"+testCaseName+".png";
}
     @BeforeMethod(alwaysRun=true)
    public landingPage launchApplication() throws IOException{
        driver=initializeDriver();
        land=new landingPage(driver);
      land.launchUrl();
      return land;
    }
    @AfterMethod(alwaysRun=true)
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}

