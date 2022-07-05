package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class TripInfoPage extends BasePage {
    public TripInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(.,'даты')]/parent::div/preceding-sibling::div//div[contains(@class,'over')]")
    private WebElement actionsToTripMenu;

    @FindBy(xpath = "//span[contains(.,'Удалить поездку')]")
    private WebElement removeTripSubMenu;

    @FindBy(xpath = "//button[.=\"Удалить\"]")
    private WebElement removeTripConfirmationButton;

    public AllTripsInfoPage removeTrip() {
        webDriverWait.until(ExpectedConditions.visibilityOf(actionsToTripMenu)).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(removeTripSubMenu)).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(removeTripConfirmationButton)).click();
        return new AllTripsInfoPage(driver);
    }
}
