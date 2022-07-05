package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;


public class AttractionsPage extends BasePage{

    public AttractionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(.,\"Спец\")]/ancestor::div[@data-tracking-title]/descendant::span[contains(.,\"Показать\")]")
    private WebElement showAllSpecialMuseumLink;

    public SpecialMuseumsPage showAllSpecialMuseum() {
        webDriverWait.until(ExpectedConditions.visibilityOf(showAllSpecialMuseumLink));
        actions.scrollToElement(showAllSpecialMuseumLink)
                .build()
                .perform();
        showAllSpecialMuseumLink.click();
        new SwitchToTab().switchToTab(driver,2);
        return new SpecialMuseumsPage(driver);
    }
}
