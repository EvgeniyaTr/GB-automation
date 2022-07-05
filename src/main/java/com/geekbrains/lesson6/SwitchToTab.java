package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class SwitchToTab {

    WebDriver driver;

    public void switchToTab(WebDriver driver, int tabNumber) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNumber-1));
    }

}
