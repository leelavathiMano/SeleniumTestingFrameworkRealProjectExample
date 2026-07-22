package com.leelavathienterprise.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.leelavathienterprise.AbstractComponents.abstractComponents;

public class landingPage extends abstractComponents{
    WebDriver driver;
    public landingPage(WebDriver driver){
        //initializing the driver in the constructor of the class
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
@FindBy(id="userEmail")
WebElement userName;
@FindBy(id="userPassword")
WebElement password;
@FindBy(id="login")
WebElement loginButton;
@FindBy (css="#toast-container")
WebElement errorMessage;
public productCatalougePage loginApplication(String email, String pwd){
    userName.sendKeys(email);
    password.sendKeys(pwd);
    loginButton.click();
    return new productCatalougePage(driver);
}
public void launchUrl(){
    driver.get("https://rahulshettyacademy.com/client/#/auth/login"); 
}
public String getProperty(String string) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getProperty'");
}
public String getErrorMessage() {
waitForElementToAppear(errorMessage);
    return errorMessage.getText();
}
}
