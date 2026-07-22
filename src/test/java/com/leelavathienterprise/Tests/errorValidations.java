package com.leelavathienterprise.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.leelavathienterprise.TestComponents.IRetryAnalyserMechanism;
import com.leelavathienterprise.TestComponents.basetest;
import com.leelavathienterprise.pageobjects.cartPage;
import com.leelavathienterprise.pageobjects.productCatalougePage;

public class errorValidations extends basetest{
    public productCatalougePage prod;
    public cartPage cart;
    protected String getProperty(String key) {
        return System.getProperty(key);
    }
    @Test(groups={"Errorhandling"}, retryAnalyzer=IRetryAnalyserMechanism.class)
    public void loginErrorValidations(){
         prod=land.loginApplication("leelachills123@gmail.com", "Leela@1995_invalid");
      Assert.assertEquals( land.getErrorMessage(), "Incorrect emails or password.");
    }

@Test(groups={"Errorhandling"})
public void productErrorValidations(){
             prod=land.loginApplication("leelachills123@gmail.com", "Leela@1995");

    String dressname="ZARA COAT 3";
    String wrongDressName="ZARA COAT 33";
    prod.getProductList();
    prod.addToCart(dressname);
    prod.goToCartPage();
    cart = new cartPage(driver);
       WebElement isProductInCart = cart.checkDesiredProductinCart(wrongDressName);
       Assert.assertNull(isProductInCart);
}

}
