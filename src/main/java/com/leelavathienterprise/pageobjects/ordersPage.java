package com.leelavathienterprise.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.leelavathienterprise.AbstractComponents.abstractComponents;

public class ordersPage extends abstractComponents {
    WebDriver driver;
    public ordersPage(WebDriver driver)
    {
super(driver);
this.driver=driver;
PageFactory.initElements(driver, this);
    }
    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> orderedProductNames;
    By orderedProductBy = By.cssSelector("tr td:nth-child(3)");

    public boolean verifyOrderDisplay(String dressname){
        waitForElementToAppear(orderedProductBy);
        boolean match = orderedProductNames
        .stream().anyMatch(product->product.getText().trim().equalsIgnoreCase(dressname));
        return match;
    }

}
