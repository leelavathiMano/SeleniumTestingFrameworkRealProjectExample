package com.leelavathienterprise.Tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.leelavathienterprise.TestComponents.basetest;
import com.leelavathienterprise.pageobjects.cartPage;
import com.leelavathienterprise.pageobjects.landingPage;
import com.leelavathienterprise.pageobjects.orderConfirmPage;
import com.leelavathienterprise.pageobjects.ordersPage;
import com.leelavathienterprise.pageobjects.productCatalougePage;
import com.leelavathienterprise.pageobjects.shippingDetailsPage;

public class standaloneTest extends basetest{
         String dressname;

  public landingPage lp;
  public ordersPage orders;
@Test(dataProvider="loginData",groups={"purchase"})
  public void standaloneE2EFlow(HashMap<String,String> input) throws IOException {
  this.dressname = (String) input.get("dressname");

       String countryname="India";
       String confirmMsg="THANKYOU FOR THE ORDER.";
      landingPage lp=launchApplication();
     // String username=lp.getProperty("username");
      //String password=lp.getProperty("password");
    //driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    lp.loginApplication(input.get("username"), input.get("password"));
    productCatalougePage prod = new productCatalougePage(driver);
      prod.getProductList();
     prod.addToCart(input.get("dressname"));
     prod.goToCartPage();
     cartPage cart=new cartPage(driver);
    Assert.assertNotNull(cart.checkDesiredProductinCart(input.get("dressname")));
     cart.clickonCheckoutButton();
     shippingDetailsPage shipdetails=new shippingDetailsPage(driver);
     shipdetails.inputCountry(countryname);
     shipdetails.selectDesiredCoutry(countryname);
     shipdetails.clickonPlaceOrderButton();
     orderConfirmPage orderconfirm=new orderConfirmPage(driver);
     orderconfirm.getConfirmationMsg(confirmMsg);
     // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
        // driver.findElement(By.cssSelector(".btnn.action__submit")).click();
         // String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
        // Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
       //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

                  }
     // a.moveToElement(countryOptions.get(0)).click().build().perform();
//List<WebElement> filteredCountryOptions = countryOptions.stream().filter(country->country.getText().contains("ind")).collect(Collectors.toList());
//filteredCountryOptions.get(1).click();
@Test(dependsOnMethods= {"standaloneE2EFlow"}, groups={"purchase"})
public void orderTestHistory() throws IOException{
     lp = launchApplication();
     lp.loginApplication("leelachills123@gmail.com", "Leela@1995");
      //goToMyOrdersPage
   productCatalougePage prod = new productCatalougePage(driver);
   orders = prod.goToMyOrdersPage();
  Assert.assertTrue(orders.verifyOrderDisplay("ZARA COAT 3") || orders.verifyOrderDisplay("ADIDAS ORIGINAL"));
}


@DataProvider(name="loginData")
public Object[][] getData() throws IOException{
 /*  HashMap<String,String> map=new HashMap<String,String>();

  map.put("username", "leelachills123@gmail.com");
  map.put("password", "Leela@1995");
  map.put("dressname", "ZARA COAT 3");

   HashMap<String,String> map1=new HashMap<String,String>();

  map1.put("username", "leela123@gmail.com");
  map1.put("password", "Leela@1990");
  map1.put("dressname", "ADIDAS ORIGINAL");*/
  //Object[][] data=new Object[][]{{map}, {map1}};
List<HashMap<String,String>>data=getJsonDataIntoMap(System.getProperty("user.dir") + "/src/test/java/com/leelavathienterprise/dataSet/purchaseOrder.json");
Object[][] dataArray=new Object[][]{{data.get(0)}, {data.get(1)}};
 return dataArray;

}
  }
    


