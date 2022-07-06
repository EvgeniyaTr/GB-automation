package com.geekbrains.lesson6;

import io.qameta.allure.Step;
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

    @Step("Выбор поездки")
    public TripInfoPage selectTripByName(){
        webDriverWait.until(ExpectedConditions.visibilityOf(tripCard)).click();
        return new TripInfoPage(driver);
    }

    @Step("Проверка отсутствия поездок в ЛК")
    public void checkNoTripsInAccount(){
        webDriverWait.until(ExpectedConditions.visibilityOf(createNewTripSuggest)).isDisplayed();
    }

}
