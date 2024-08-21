package ru.yandex.prakticum.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.yandex.prakticum.pages.MainPage;

public class LogoScooterTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @Before
    public void setUp(){
        String browser = System.getProperty("browser", "chrome");
        if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
    }
    @Test
    public void clickLogoScooterTest(){
        //Ждем, пока появится логотип Самоката
        WebElement logoScooterElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getElementScooterLocator()));
        //Кликаем по логотипу
        mainPage.clickLogoScooter();

        //проверяем, что откралась страница главного экрана
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/";
        assertEquals("Открытая страница не совпадает с ожидаемоц", actualUrl, expectedUrl);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

