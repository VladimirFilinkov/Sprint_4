package ru.yandex.prakticum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.prakticum.pages.MainPage;
import ru.yandex.prakticum.pages.OrderPage;
import static org.junit.Assert.assertTrue;
import java.time.Duration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderTests {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    @Before
    public void setUp(){
        String browser = System.getProperty("browser", "chrome"); // Получаем браузер из системных свойств, по умолчанию Chrome

        if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void testOrderProcessFromTopButton(){
        mainPage.clickOrderButtonTop();//кликаем по верхней кнопке Заказать
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillName("Владимир");
        orderPage.fillLastName("Филинков");
        orderPage.fillAddress("ул. Ленина, д. 8");
        orderPage.fillMetroStation();
        orderPage.fillPhone("80000000000");
        orderPage.clickNextButton();

        //заполнение данных на следующей странице
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String deliveryDate = LocalDate.now().plusDays(1).format(formatter);
        orderPage.selectDeliveryDate(deliveryDate);//выбрали завтрашнюю дату
        orderPage.selectRentPeriod();
        orderPage.selectScooterColorBlack();
        orderPage.selectScooterColorGrey();
        orderPage.fillComment("Комментарий для курьера");

        //Жмем кнопку Заказать
        orderPage.clickOrderButton();
        //проверяем, что кнопка нажалась, появился попап
        boolean isConfirmationPopupVisible = orderPage.isConfirmationPopupVisible();
        assertTrue("Окно подтверждения заказа не появилось", isConfirmationPopupVisible);
        //Жмем кнопку Да
        orderPage.confirmOrder();
        //Проверяем, что кнопка нажалась, появился следующий попап
        boolean isStatusButtonVisible = orderPage.isStatusButtonVisible();
        assertTrue("Кнопка 'Посмотреть статус' не доступна", isStatusButtonVisible);
        //Проверяем что кнопка Посмотреть статус нажалась, перешли на страницу статуса заказа
        orderPage.clickViewStatusButton();

        // Проверка успешного завершения заказа
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Отменить заказ']")));
       }

    @Test
    public void testOrderProcessFromButtonBottom(){
        mainPage.clickOrderButtonBottom();//кликаем по нижней кнопке Заказать
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillName("Иванов");
        orderPage.fillLastName("Иван");
        orderPage.fillAddress("Пархоменко, 17");
        orderPage.fillMetroStation();
        orderPage.fillPhone("+70000000000");
        orderPage.clickNextButton();

        //заполнение данных на следующей странице
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String deliveryDate = LocalDate.now().plusDays(2).format(formatter);
        orderPage.selectDeliveryDate(deliveryDate);//выбрали дату - послезавтра
        orderPage.selectRentPeriod();
        //orderPage.selectScooterColorBlack();
        //orderPage.selectScooterColorGrey();
        //orderPage.fillComment("Комментарий для курьера");

        //Жмем кнопку Заказать
        orderPage.clickOrderButton();
        //проверяем, что кнопка нажалась, появился попап
        boolean isConfirmationPopupVisible = orderPage.isConfirmationPopupVisible();
        assertTrue("Окно подтверждения заказа не появилось", isConfirmationPopupVisible);
        //Жмем кнопку Да
        orderPage.confirmOrder();
        //Проверяем, что кнопка нажалась, появился следующий попап
        boolean isStatusButtonVisible = orderPage.isStatusButtonVisible();
        assertTrue("Кнопка 'Посмотреть статус' не доступна", isStatusButtonVisible);
        //Проверяем что кнопка Посмотреть статус нажалась, перешли на страницу статуса заказа
        orderPage.clickViewStatusButton();

        // Проверка успешного завершения заказа
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Отменить заказ']")));
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
