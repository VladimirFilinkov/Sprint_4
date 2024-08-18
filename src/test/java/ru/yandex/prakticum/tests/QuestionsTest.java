package ru.yandex.prakticum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import ru.yandex.prakticum.pages.MainPage;
import java.time.Duration;



public class QuestionsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    @Before
    public void setUp(){
        String browser = System.getProperty("browser", "chrome"); // Получаем браузер из системных свойств, по умолчанию Chrome

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
    public void testFAQSection() {
        // Доскроллить до локатора вопроса 0
        WebElement questionElement0 = driver.findElement(mainPage.getQuestion0Locator());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement0);
        // Кликнуть по вопросу 0
        questionElement0.click();
        // Ожидание, пока текст ответа появится
        WebElement answerElement0 = driver.findElement(mainPage.getAnswer0Locator());
        wait.until(ExpectedConditions.visibilityOf(answerElement0));
        // Проверка текста ответа
        String expectedText0 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        String actualText0 = answerElement0.getText();

        assert actualText0.equals(expectedText0) : "Ожидаемый текст 0 не совпадает с фактическим.";

        // Доскроллить до локатора вопроса 1
        WebElement questionElement1 = driver.findElement(mainPage.getQuestion1Locator());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement1);
        // Кликнуть по вопросу 1
        questionElement1.click();
        // Ожидание, пока текст ответа появится
        WebElement answerElement1 = driver.findElement(mainPage.getAnswer1Locator());
        wait.until(ExpectedConditions.visibilityOf(answerElement1));
        // Проверка текста ответа
        String expectedText1 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
        String actualText1 = answerElement1.getText();

        assert actualText1.equals(expectedText1) : "Ожидаемый текст 1 не совпадает с фактическим.";

        // Доскроллить до локатора вопроса 2
        WebElement questionElement2 = driver.findElement(mainPage.getQuestion2Locator());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement2);
        // Кликнуть по вопросу 2
        questionElement2.click();
        // Ожидание, пока текст ответа появится
        WebElement answerElement2 = driver.findElement(mainPage.getAnswer2Locator());
        wait.until(ExpectedConditions.visibilityOf(answerElement2));
        // Проверка текста ответа
        String expectedText2 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
        String actualText2 = answerElement2.getText();

        assert actualText2.equals(expectedText2) : "Ожидаемый текст 2 не совпадает с фактическим.";

        // Доскроллить до локатора вопроса 3
        WebElement questionElement3 = driver.findElement(mainPage.getQuestion3Locator());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement3);
        // Кликнуть по вопросу 3
        questionElement3.click();
        // Ожидание, пока текст ответа появится
        WebElement answerElement3 = driver.findElement(mainPage.getAnswer3Locator());
        wait.until(ExpectedConditions.visibilityOf(answerElement3));
        // Проверка текста ответа
        String expectedText3 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
        String actualText3 = answerElement3.getText();

        assert actualText3.equals(expectedText3) : "Ожидаемый текст 3 не совпадает с фактическим.";

        // Доскроллить до локатора вопроса 4
        WebElement questionElement4 = driver.findElement(mainPage.getQuestion4Locator());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement4);
        // Кликнуть по вопросу 4
        questionElement4.click();
        // Ожидание, пока текст ответа появится
        WebElement answerElement4 = driver.findElement(mainPage.getAnswer4Locator());
        wait.until(ExpectedConditions.visibilityOf(answerElement4));
        // Проверка текста ответа
        String expectedText4 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
        String actualText4 = answerElement4.getText();

        assert actualText4.equals(expectedText4) : "Ожидаемый текст 4 не совпадает с фактическим.";

        // Доскроллить до локатора вопроса 5
        WebElement questionElement5 = driver.findElement(mainPage.getQuestion5Locator());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement5);
        // Кликнуть по вопросу 5
        questionElement5.click();
        // Ожидание, пока текст ответа появится
        WebElement answerElement5 = driver.findElement(mainPage.getAnswer5Locator());
        wait.until(ExpectedConditions.visibilityOf(answerElement5));
        // Проверка текста ответа
        String expectedText5 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
        String actualText5 = answerElement5.getText();

        assert actualText5.equals(expectedText5) : "Ожидаемый текст 5 не совпадает с фактическим.";

        // Доскроллить до локатора вопроса 6
        WebElement questionElement6 = driver.findElement(mainPage.getQuestion6Locator());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement6);
        // Кликнуть по вопросу 6
        questionElement6.click();
        // Ожидание, пока текст ответа появится
        WebElement answerElement6 = driver.findElement(mainPage.getAnswer6Locator());
        wait.until(ExpectedConditions.visibilityOf(answerElement6));
        // Проверка текста ответа
        String expectedText6 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
        String actualText6 = answerElement6.getText();

        assert actualText6.equals(expectedText6) : "Ожидаемый текст 6 не совпадает с фактическим.";

        // Доскроллить до локатора вопроса 7
        WebElement questionElement7 = driver.findElement(mainPage.getQuestion7Locator());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement7);
        // Кликнуть по вопросу 0
        questionElement7.click();
        // Ожидание, пока текст ответа появится
        WebElement answerElement7 = driver.findElement(mainPage.getAnswer7Locator());
        wait.until(ExpectedConditions.visibilityOf(answerElement7));
        // Проверка текста ответа
        String expectedText7 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
        String actualText7 = answerElement7.getText();

        assert actualText7.equals(expectedText7) : "Ожидаемый текст 7 не совпадает с фактическим.";
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
