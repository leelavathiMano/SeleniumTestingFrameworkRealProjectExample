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

public class cartPage extends abstractComponents{
    WebDriver driver;
public cartPage(WebDriver driver) {
    super(driver);
    this.driver=driver;
    PageFactory.initElements(driver, this);
}
@FindBy(css=".cart h3")
List<WebElement> cartProducts;
By cartProductBy = By.cssSelector(".cart h3");
//        driver.findElement(By.cssSelector(".totalRow button")).click();
@FindBy(css=".totalRow button")
WebElement checkoutButton;

public WebElement checkDesiredProductinCart(String dressname){
    new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(cartProductBy));
    WebElement match = cartProducts.stream().filter(product->product.getText().trim().equalsIgnoreCase(dressname)).findFirst().orElse(null);
    return match;

}
public void clickonCheckoutButton(){
    checkoutButton.click();
}

}