package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class SpecialMuseumsPage extends BasePage{
    public SpecialMuseumsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@href,\"Maket\")]//span[@name='title']/div[contains(.,'макет')]")
    private WebElement showMuseumInfoByNameLink;

    @FindBy(xpath = "//h3")
    private List<WebElement> museums;

    public MuseumInfoPage showMuseumInfo() {
        webDriverWait.until(ExpectedConditions.visibilityOf(showMuseumInfoByNameLink));
        actions.scrollToElement(showMuseumInfoByNameLink)
                .build()
                .perform();
        showMuseumInfoByNameLink.click();
        new SwitchToTab().switchToTab(driver,3);
        return new MuseumInfoPage(driver);
    }

    public MuseumInfoPage showMuseumInfoStream(String museum) {
        WebElement selectedMuseum = museums.stream().filter(s -> s.getText().contains(museum)).findFirst().get();
        actions.scrollToElement(selectedMuseum)
                .build()
                .perform();
        selectedMuseum.click();
        new SwitchToTab().switchToTab(driver,3);
        return new MuseumInfoPage(driver);
    }

}
