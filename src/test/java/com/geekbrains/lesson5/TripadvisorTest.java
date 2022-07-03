package com.geekbrains.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class TripadvisorTest {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.tripadvisor.ru");
        Cookie cookie = new Cookie("TAAUTHEAT", "EYz0sPL5_mnzmYEfABQCab7fMZ8ORguCqJF_E5GxfS34xaqNGvMQ8un_9qZtKZYOsO6qJ70qsf51GvvNs87yIBAz6UeqMK8_ATYyAJP0RgjbE1wZVVC-ljpjcSMF_MRO55z29UmSfgzMPgUIGsS-R0hfB3uAXISZqGc1hXJcLKrVIbVB3YQaT_mdZiLfCmlUXQVLrk1XElowVJYoX59UQ0rnvRnpvegF0xU");
        driver.manage().addCookie(cookie);
    }

    @Test
    void addMuseumOfAttractionsToTrip() {
        //переход в раздел "Развлечения", выбор локации
        driver.findElement(By.xpath("//a[contains(@href,\"Attractions\")]/span[.='Развлечения']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@data-test-attribute,'ATTRACTIONS')]//input[@type='search']"))).
                sendKeys("Ярославль");
        //ожидание загрузки страницы, переход в раздел "Специализированные музеи"
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='typeahead_results']//a[contains(@href,'Yaroslavl')]"))).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                        ("//a[contains(.,\"Спец\")]/ancestor::div[@data-tracking-title]/descendant::span[contains(.,\"Показать\")]")));
        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(
                By.xpath("//a[contains(.,\"Спец\")]/ancestor::div[@data-tracking-title]/descendant::span[contains(.,\"Показать\")]")))
                        .build()
                        .perform();
        driver.findElement(By.xpath("//a[contains(.,\"Спец\")]/ancestor::div[@data-tracking-title]/descendant::span[contains(.,\"Показать\")]"))
                .click();
        //переход в новую вкладку, выбор музея
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//a[contains(@href,\"Maket\")]//span[@name='title']/div[contains(.,'макет')]")));
        actions.scrollToElement(driver.findElement(By.xpath
                ("//a[contains(@href,\"Maket\")]//span[@name='title']/div[contains(.,'макет')]")))
                .build()
                .perform();
        driver.findElement(By.xpath
                ("//a[contains(@href,\"Maket\")]//span[@name='title']/div[contains(.,'макет')]"))
                .click();
        //переход в новую вкладку, добавление музея в поездку
        ArrayList<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs1.get(2));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//h1[@data-automation=\"mainH1\"]/following::button[contains(@data-automation,'SAVE')]")))
                .click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//input[@name='tripName']"))).sendKeys("Ярославль");
        driver.findElement(By.xpath("//button[contains(.,\"Создать\")]")).click();
        //ассерты - ОР - отображается поп-ап с уведомлением о создании поездки и ссылкой на нее.
        Assertions.assertTrue(webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//div[.=\"Показать поездку\"]"))).isDisplayed());
    }


    @Test
    void removeTrip() {
        driver.findElement(By.xpath("//a[@href='/Trips']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//div[contains(@aria-label,\"Закрыть\")]"))).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//div[@data-automation]/div/div[contains(.,'Ярославль')]"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//span[contains(.,'даты')]/parent::div/preceding-sibling::div//div[contains(@class,'over')]")))
                .click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[contains(.,'Удалить поездку')]"))).click();
        driver.findElement(By.xpath("//button[.=\"Удалить\"]")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//div[.=\"Создайте свою первую поездку\"]")));
        //ассерты - на странице появляется текст "//div[.="Создайте свою первую поездку"]
        Assertions.assertTrue(driver.findElement(By.xpath("//div[.=\"Создайте свою первую поездку\"]"))
                .isDisplayed());
    }

    @AfterEach
    void tearDown() {
        //window.localStorage.clear() - удаление записей из localstorage
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
