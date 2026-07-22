package com.leelavathienterprise.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.leelavathienterprise.AbstractComponents.abstractComponents;

public class shippingDetailsPage extends abstractComponents{
    WebDriver driver;
    public shippingDetailsPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
@FindBy(css="input[placeholder='Select Country']")
WebElement countryInput;
By visibleCountryNameOptions=By.cssSelector(".ta-results button");
@FindBy(css=".ta-results button")
List<WebElement> countryOptions;
//         driver.findElement(By.cssSelector(".btnn.action__submit")).click();
@FindBy(css=".btnn.action__submit")
WebElement placeOrderButton;

public void inputCountry(String countryname){
    actionsOnSendKeys(countryInput,countryname.substring(0, 3));
    waitForElementToAppear(visibleCountryNameOptions);
}
public void selectDesiredCoutry(String countryname){
     WebElement selectedCountry = countryOptions.stream()
                    .filter(option -> option.getText().trim().equalsIgnoreCase(countryname))
                    .findFirst()
                    .orElse(null);
    if (selectedCountry != null) {
        selectedCountry.click();
    }
}
public void clickonPlaceOrderButton(){
    placeOrderButton.click();

}
}
