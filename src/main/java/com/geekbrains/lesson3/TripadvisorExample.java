package com.geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TripadvisorExample {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        //сценарий 1 - авторизация

        webDriver.get("https://www.tripadvisor.ru/");
        webDriverWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//a[contains(@href,'Registration')]"))).click();
        webDriver.switchTo().frame(
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//iframe[@title='regcontroller']")))) ;
        webDriver.findElement(By.xpath("//button[contains(@class,'regEmail')]")).click();
        webDriver.findElement(By.id("regSignIn.email")).sendKeys("fue37442@jiooq.com");
        webDriver.findElement(By.id("regSignIn.password")).sendKeys("123qwe456asd");
        webDriver.findElement(
                By.xpath("//div[@class='coreForgotPassword']/following-sibling::div/button[contains(@class,'coreRegPrimaryButton')]"))
                .click();
        webDriver.switchTo().parentFrame();
        Thread.sleep(2000);


        //сценарий 2 - создание поездки в профиле

        webDriver.findElement(By.xpath("//a[@href='/Trips']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//button[contains(.,'Начать')]"))).click();
        webDriver.findElement(By.xpath("//input[@name=\"tripName\"]")).sendKeys("Казань");
        webDriver.findElement(By.xpath("//button[contains(.,\"Создать\")]")).click();


        webDriver.findElement(By.xpath("//div[contains(@aria-label,\"Закрыть\")]")).click();
        Thread.sleep(2000);


        //сценарий 3 - добавление информации о поездке
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@data-automation]/div//div[contains(.,\"Казань\")]" +
                        "/div[2]/div[contains(.,\"Казань\")]"))).click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(.,'добавьте места')]//input")));
        webDriver.findElement(By.xpath("//div[contains(.,'добавьте места')]//input")).
                sendKeys("казань");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//span[contains(.,'Казань')]/span[contains(.,'Приволжский')]"))).
                click();
        webDriver.findElement(By.xpath("//div[contains(.,'добавьте места')]//input")).clear();
        webDriver.findElement(By.xpath("//div[contains(.,'добавьте места')]//input")).
                sendKeys("Казанский кремль");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[contains(.,'Казанский Кремль')]"))).click();

        /*
        обновление страницы, при прохождении теста селениумом не отрисовываются кнопки "Добавить элемент" в списке
        дней поездки
        */
        webDriver.navigate().refresh();

        webDriver.findElement(By.xpath("//span[contains(.,'Добавить даты')]")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(.,'Создать маршрут')]"))).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@tabindex]//span[contains(.,'дни')]"))).click();
        webDriver.findElement(By.xpath("//span[contains(@class,'minus')]")).click();

        webDriver.findElement(By.xpath(
                "//div[contains(@data-rbd-draggable-id,'bucket-День 1')]/div/div[contains(.,'Добавить')]"))
                .click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='checkbox']/following::div/div[contains(.,'Татарстан')]"))).click();
        webDriver.findElement(By.xpath("//button[contains(.,'Готово')]")).click();

        webDriver.findElement(By.xpath(
                "//div[contains(@data-rbd-draggable-id,'bucket-День 2')]/div/div[contains(.,'Добавить')]"))
                .click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//input[@type='checkbox']/following::div/div/div[contains(.,'Казанский ')]"))).click();
        webDriver.findElement(By.xpath("//button[contains(.,'Готово')]")).click();

        webDriver.findElement(By.xpath("//button[contains(.,'Сохранить')]")).click();
        webDriver.findElement(By.xpath("//a[@href='/Trips']")).click();

        Thread.sleep(2000);


        //сценарий 4 - удаление поездки
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-automation]/div/div[contains(.,'Казань')]"))).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'ui_column ')]/div[not(@style)]//div[@class='overflow bptaq']/span")))
                .click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(.,'Удалить поездку')]"))).click();
        webDriver.findElement(By.xpath("//button[.=\"Удалить\"]")).click();

        Thread.sleep(2000);

        webDriver.quit();
    }
}
