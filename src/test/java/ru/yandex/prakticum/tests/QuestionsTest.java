package ru.yandex.prakticum.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.yandex.prakticum.pages.MainPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QuestionsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    // Параметры для теста
    @Parameterized.Parameter(0)
    public int questionIndex;

    @Parameterized.Parameter(1)
    public String expectedAnswer;

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Before
    public void setUp() {
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
        // Локатор вопроса на основе параметра questionIndex
        By questionLocator = mainPage.getQuestionLocatorByIndex(questionIndex);
        By answerLocator = mainPage.getAnswerLocatorByIndex(questionIndex);

        // Доскроллить до вопроса
        WebElement questionElement = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement);

        // Кликнуть по вопросу
        questionElement.click();

        // Ожидание, пока текст ответа появится
        WebElement answerElement = driver.findElement(answerLocator);
        wait.until(ExpectedConditions.visibilityOf(answerElement));

        // Проверка текста ответа
        String actualText = answerElement.getText();
        assert actualText.equals(expectedAnswer) : "Ожидаемый текст не совпадает с фактическим.";
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}