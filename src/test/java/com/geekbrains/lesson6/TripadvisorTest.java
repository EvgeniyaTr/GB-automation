package com.geekbrains.lesson6;


import com.geekbrains.lesson7.AdditionalAllureSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;


@Feature("Составление маршрутов поездок")
public class TripadvisorTest {
    WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        //driver = new ChromeDriver();
        driver = new EventFiringDecorator(new AdditionalAllureSteps()).decorate(new ChromeDriver());
        driver.get("https://www.tripadvisor.ru/");
        Cookie cookie = new Cookie("TAAUTHEAT", "EYz0sPL5_mnzmYEfABQCab7fMZ8ORguCqJF_E5GxfS34xaqNGvMQ8un_9qZtKZYOsO6qJ70qsf51GvvNs87yIBAz6UeqMK8_ATYyAJP0RgjbE1wZVVC-ljpjcSMF_MRO55z29UmSfgzMPgUIGsS-R0hfB3uAXISZqGc1hXJcLKrVIbVB3YQaT_mdZiLfCmlUXQVLrk1XElowVJYoX59UQ0rnvRnpvegF0xU");
        driver.manage().addCookie(cookie);
    }

    @Test
    @Story("Добавление точки интереса")
    @Description("Добавление пункта из категории Развлечения в поездку")
    void addMuseumOfAttractionsToTrip() {
        new MainPage(driver)
                .searchAttractionsByLocation("Ярославль")
                .showAllSpecialMuseum()
                .showMuseumInfoStream("ткань")
                .createNewTripWithMuseum("Ярославль")
                .checkNewTripCreated();
    }

    @Test
    @Story("Удаление поездки из личного кабинета")
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
