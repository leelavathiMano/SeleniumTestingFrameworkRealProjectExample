package com.leelavathienterprise.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.leelavathienterprise.pageobjects.ordersPage;

public class abstractComponents {
    WebDriver driver;
    public abstractComponents(WebDriver driver){
        this.driver=driver;
    }
    @FindBy(css="button[routerlink='/dashboard/myorders']")
WebElement myOrdersButton;
    
    public void waitForElementToAppear(By locator){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitforElementToDisappear(WebElement element){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
   public void waitForElementToAppear(WebElement element){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void actionsOnSendKeys(WebElement element, String keys){
        Actions a = new Actions(driver);
        a.sendKeys(element, keys).build().perform();
    }
       public void waitForToasterMessageVisibility(By locator){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
       }
        public ordersPage goToMyOrdersPage(){
            waitForElementToAppear(myOrdersButton);
        myOrdersButton.click();
       
        ordersPage orders = new ordersPage(driver);
        return orders;
    }
    

}
