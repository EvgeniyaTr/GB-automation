package com.geekbrains.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


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

    @FindBy(xpath = "//div[@id=\"typeahead_results\"]/a/div/div")
    private List<WebElement> locations;

    /*
    поиск через стримы
    я так понимаю, что массив собирает не данные из поиска, а те, что появляются при клике на кнопку "Развлечения":
    "поблизости", "недавно просмотренные" и т.д.

    public AttractionsPage selectLocation(String location) {
        attractionsMainMenu.click();
        searchLineInModalWindow.sendKeys(location);
        locations.stream().filter(s -> s.getText().contains(location)).findFirst().get().click();
        return new AttractionsPage(driver);
    }

     */


    @Step("Поиск по локации в категории Развлечения")
    public AttractionsPage searchAttractionsByLocation(String location) {
        attractionsMainMenu.click();
        searchLineInModalWindow.sendKeys(location);
        webDriverWait.until(ExpectedConditions.visibilityOf(searchResultInModalWindow));
        searchResultInModalWindow.click();
        return new AttractionsPage(driver);
    }

    @Step("Переход на страницу Поездки")
    public AllTripsInfoPage goToAllTripsInfo() {
        webDriverWait.until(ExpectedConditions.visibilityOf(tripsMainMenu)).click();
        return new AllTripsInfoPage(driver);
    }

}


