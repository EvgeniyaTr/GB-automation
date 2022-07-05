package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;


public class SpecialMuseumsPage extends BasePage{
    public SpecialMuseumsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@href,\"Maket\")]//span[@name='title']/div[contains(.,'макет')]")
    private WebElement showMuseumInfoByNameLink;

    public MuseumInfoPage showMuseumInfo() {
        webDriverWait.until(ExpectedConditions.visibilityOf(showMuseumInfoByNameLink));
        actions.scrollToElement(showMuseumInfoByNameLink)
                .build()
                .perform();
        showMuseumInfoByNameLink.click();
        new SwitchToTab().switchToTab(driver,3);
        return new MuseumInfoPage(driver);
    }
}
