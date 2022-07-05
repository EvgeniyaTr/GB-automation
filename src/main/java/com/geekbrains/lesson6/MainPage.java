package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@href,\"Attractions\")]/span[.='Развлечения']")
    private WebElement attractionsMainMenu;

    @FindBy(xpath = "//a[@href='/Trips']")
    private WebElement tripsMainMenu;

    @FindBy(xpath = "//div[contains(@data-test-attribute,'ATTRACTIONS')]//input[@type='search']")
    private WebElement searchLineInModalWindow;

    @FindBy(xpath = "//div[@id='typeahead_results']//a[contains(@href,'Yaroslavl')]")
    private WebElement searchResultInModalWindow;

    public AttractionsPage searchAttractionsByLocation(String location) {
        attractionsMainMenu.click();
        searchLineInModalWindow.sendKeys(location);
        webDriverWait.until(ExpectedConditions.visibilityOf(searchResultInModalWindow));
        searchResultInModalWindow.click();
        return new AttractionsPage(driver);
    }

    public AllTripsInfoPage goToAllTripsInfo() {
        webDriverWait.until(ExpectedConditions.visibilityOf(tripsMainMenu)).click();
        return new AllTripsInfoPage(driver);
    }

}


