package com.geekbrains.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TripadvisorTest {
    WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get("https://www.tripadvisor.ru/");
        Cookie cookie = new Cookie("TAAUTHEAT", "EYz0sPL5_mnzmYEfABQCab7fMZ8ORguCqJF_E5GxfS34xaqNGvMQ8un_9qZtKZYOsO6qJ70qsf51GvvNs87yIBAz6UeqMK8_ATYyAJP0RgjbE1wZVVC-ljpjcSMF_MRO55z29UmSfgzMPgUIGsS-R0hfB3uAXISZqGc1hXJcLKrVIbVB3YQaT_mdZiLfCmlUXQVLrk1XElowVJYoX59UQ0rnvRnpvegF0xU");
        driver.manage().addCookie(cookie);
    }

    @Test
    void addMuseumOfAttractionsToTrip() {
        new MainPage(driver)
                .searchAttractionsByLocation("Ярославль")
                .showAllSpecialMuseum()
                .showMuseumInfoStream("ткань")
                .createNewTripWithMuseum("Ярославль")
                .checkNewTripCreated();
    }

    @Test
    void removeTrip() {
        new MainPage(driver)
                .goToAllTripsInfo()
                .selectTripByName()
                .removeTrip()
                .checkNoTripsInAccount();
    }


    @AfterEach
    void killDriver() {
        driver.quit();
    }
}
