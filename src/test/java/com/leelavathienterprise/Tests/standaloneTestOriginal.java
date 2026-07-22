package com.leelavathienterprise.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.leelavathienterprise.pageobjects.landingPage;

public class standaloneTestOriginal{
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
    String dressname="ZARA COAT 3";
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      driver.get("https://rahulshettyacademy.com/client/#/auth/login");
      landingPage land=new landingPage(driver);
      land.loginApplication("leelachills123@gmail.com", "Leela@1995");
      land.launchUrl();
     WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));


      List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
      System.out.println(products.size());

      WebElement desiredProduct = products.stream()
          .filter(product -> product.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(dressname))
          .findFirst()
          .orElse(null);

      if (desiredProduct != null) {
        System.out.println(desiredProduct.findElement(By.cssSelector("b")).getText());
        desiredProduct.findElement(By.xpath(".//button[contains(.,'Add To Cart')]")).click();
      }
    //  WebElement toastmsg=driver.findElement(By.id("toast-container"));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
      wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
      driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
      List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
    Boolean match=cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(dressname));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();
              Actions a = new Actions(driver);
              a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"ind").build().perform();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
                  List<WebElement> countryOptions = driver.findElements(By.cssSelector(".ta-results button"));
                  System.out.println(countryOptions.size());
                  WebElement selectedCountry = countryOptions.stream()
                    .filter(option -> option.getText().trim().equalsIgnoreCase("India"))
                    .findFirst()
                    .orElse(null);
                  if (selectedCountry != null) {
                selectedCountry.click();
         driver.findElement(By.cssSelector(".btnn.action__submit")).click();
         String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
         Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

                  }
     // a.moveToElement(countryOptions.get(0)).click().build().perform();
//List<WebElement> filteredCountryOptions = countryOptions.stream().filter(country->country.getText().contains("ind")).collect(Collectors.toList());
//filteredCountryOptions.get(1).click();
  }
    }


