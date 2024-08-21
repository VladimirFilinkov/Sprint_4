import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.yandex.prakticum.pages.MainPage;

public class LogoYandexTest {
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
    public void clickLogoYandexTest(){
        //Ждем, пока появится логотип Самоката
        WebElement logoScooterElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getElementYandexLocator()));
        //Кликаем по логотипу
        mainPage.clickLogoYandex();

        // Ожидание появления нового окна
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        // Переключение на новое окно
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        //проверяем, что откралась страница главного экрана
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://ya.ru/";
        // Явная задержка для ожидания загрузки новой страницы
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверка текущего URL
        assert actualUrl.equals(expectedUrl) : "Ожидаемый URL не совпадает с фактическим. Фактический URL: " + actualUrl;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}