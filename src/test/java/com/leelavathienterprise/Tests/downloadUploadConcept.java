package com.leelavathienterprise.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class downloadUploadConcept {
    @Test
    public void DownloadFile(){
WebDriver driver=new ChromeDriver();
driver.get("https://rahulshettyacademy.com/upload-download-test/");
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7)); 
driver.findElement(By.id("downloadButton")).click();
WebElement uploadElement=driver.findElement(By.id("fileinput"));
uploadElement.sendKeys("C:\\Users\\leela\\OneDrive\\Desktop\\myupload\\download.xlsx");

WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement toasterMsg=driver.findElement(By.cssSelector(".Toastify__toast-body"));
wait.until(ExpectedConditions.visibilityOf(toasterMsg));
System.out.println(toasterMsg.getText());
Assert.assertTrue(toasterMsg.getText().contains("Updated Excel Data Successfully."), "Updated Excel Data Successfully.");
wait.until(ExpectedConditions.invisibilityOf(toasterMsg));
List<WebElement> priceList=driver.findElements(By.xpath("//*[@id='cell-4-undefined']"));
priceList.forEach(s->System.out.println(s.getText()));
Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Kiwi']/parent::div/parent::div/div[@id='cell-4-undefined']")).getText(), "400");

    }
}
