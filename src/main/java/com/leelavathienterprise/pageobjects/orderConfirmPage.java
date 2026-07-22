package com.leelavathienterprise.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.leelavathienterprise.AbstractComponents.abstractComponents;

public class orderConfirmPage extends abstractComponents{
    WebDriver driver;
    public orderConfirmPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
@FindBy(css=".hero-primary")
WebElement confirmMsg;
By confirmMessage = By.cssSelector(".hero-primary");

public String getConfirmationMsg(String expectedMsg){
    waitForElementToAppear(confirmMessage);
    String confirmMessage = confirmMsg.getText();
    Assert.assertTrue(confirmMessage.equalsIgnoreCase(expectedMsg));
    return confirmMessage;

}
}
