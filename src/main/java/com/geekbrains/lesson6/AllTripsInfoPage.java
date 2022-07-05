package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AllTripsInfoPage extends BasePage {

    public AllTripsInfoPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//div[@data-automation]/div/div[contains(.,'Ярославль')]")
    private WebElement tripCard;

    @FindBy(xpath = "//div[.=\"Создайте свою первую поездку\"]")
    private WebElement createNewTripSuggest;

    public TripInfoPage selectTripByName(){
        webDriverWait.until(ExpectedConditions.visibilityOf(tripCard)).click();
        return new TripInfoPage(driver);
    }

    public void checkNoTripsInAccount(){
        webDriverWait.until(ExpectedConditions.visibilityOf(createNewTripSuggest)).isDisplayed();
    }

}
