package com.leelavathienterprise.pageobjects;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.leelavathienterprise.AbstractComponents.abstractComponents;
public class productCatalougePage extends abstractComponents{
    WebDriver driver;
    public productCatalougePage(WebDriver driver){
        //initializing the driver in the constructor of the class
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css=".mb-3")
    List<WebElement> products;

    By productBy=By.cssSelector(".mb-3");
    By addToCart=By.xpath(".//button[contains(.,'Add To Cart')]");
    By toastMsg=By.id("toast-container");
    @FindBy(css=".ng-animating")
    WebElement animatingElement;
//      driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
    @FindBy(css="button[routerlink='/dashboard/cart']")
    WebElement cartButton;


    public List<WebElement> getProductList(){
         waitForElementToAppear(productBy);
        return products;
        
    }
    public WebElement getProductByname(String dressname){
         WebElement desiredProduct = getProductList().stream()
          .filter(product -> product.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(dressname))
          .findFirst()
          .orElse(null);
           return desiredProduct;}
           
public void addToCart(String dressname){
      WebElement desiredProduct = getProductByname(dressname);
     // if (desiredProduct != null) {
     //   System.out.println(desiredProduct.findElement(By.cssSelector("b")).getText());
        desiredProduct.findElement(addToCart).click();
        waitForElementToAppear(toastMsg);
        waitforElementToDisappear(animatingElement);
      }
    public void goToCartPage(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(cartButton));
        cartButton.click();
    }
   
}

    

