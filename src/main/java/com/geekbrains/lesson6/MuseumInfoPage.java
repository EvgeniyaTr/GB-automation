package com.geekbrains.lesson6;


import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MuseumInfoPage extends BasePage {
    public MuseumInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@data-automation=\"mainH1\"]/following::button[contains(@data-automation,'SAVE')]")
    private WebElement addMuseumToTripButton;

    @FindBy(xpath = "//input[@name='tripName']")
    private WebElement inputTripNameField;

    @FindBy(xpath = "//button[contains(.,\"Создать\")]")
    private WebElement createTripButton;

    @FindBy(xpath = "//div[.=\"Показать поездку\"]")
    private WebElement showTripPopup;

    @Step("Добавление музея в новую поездку")
    public MuseumInfoPage createNewTripWithMuseum(String tripName) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addMuseumToTripButton)).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(inputTripNameField)).sendKeys(tripName);
        createTripButton.click();
        return new MuseumInfoPage(driver);
    }

    @Step("Проверка создания новой поездки")
    public void checkNewTripCreated() {
        Assertions.assertTrue(webDriverWait.until(ExpectedConditions.visibilityOf(showTripPopup)).isDisplayed());
    }


}
