import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.prakticum.pages.MainPage;
import ru.yandex.prakticum.pages.OrderPage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTests {

    private WebDriver driver;
    private MainPage mainPage;

    // Параметры теста
    private final String name; //Имя
    private final String lastName; //Фамилия
    private final String address; //Адрес
    private String metroStation; //Станция метро
    private final String phone; //Номер телефона
    private final String deliveryDate; //Дата аренды
    private String rentPeriod;
    private final boolean chooseBlackColor; //Черный самокат
    private final boolean chooseGreyColor; //Серый самокат
    private final String orderButtonPosition; // Выбор кнопки заказа
    private final String commentField; // Комментарий для курьера

    // Конструктор для передачи параметров
    public OrderTests(String name, String lastName, String address, String metroStation, String phone, String deliveryDate, String rentPeriod, boolean chooseBlackColor, boolean chooseGreyColor, String orderButtonPosition, String commentField) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentPeriod = rentPeriod;
        this.chooseBlackColor = chooseBlackColor;
        this.chooseGreyColor = chooseGreyColor;
        this.orderButtonPosition = orderButtonPosition;
        this.commentField = commentField;
    }

    // Определение параметров для каждого теста
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Владимир", "Филинков", "ул. Ленина, д. 8", "Раменки", "80000000000", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), "семеро суток", true, true, "top", "Комментарий для курьера"},
                {"Иван", "Иванов", "Пархоменко, 17", "Спартак", "+70000000000", LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), "двое суток", false, false, "bottom", ""}
        });
    }

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome"); // Получаем значение браузера из системного свойства

        if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else if ("chrome".equalsIgnoreCase(browser)) {
            driver = new ChromeDriver();
        }

        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void testOrderProcess() {
        OrderPage orderPage;

        // Выбор кнопки заказа на основе параметра
        if ("top".equalsIgnoreCase(orderButtonPosition)) {
            mainPage.clickOrderButtonTop();
        } else if ("bottom".equalsIgnoreCase(orderButtonPosition)) {
            mainPage.clickOrderButtonBottom();
        }
        //Заполняем данные
        orderPage = new OrderPage(driver);
        orderPage.fillName(name);
        orderPage.fillLastName(lastName);
        orderPage.fillAddress(address);
        orderPage.fillMetroStation(metroStation);
        orderPage.fillPhone(phone);
        orderPage.clickNextButton();
        //Перешли на след страницу
        orderPage.selectDeliveryDate(deliveryDate);
        orderPage.selectRentPeriod(rentPeriod);
        //Проверяем какой цвет самоката выбран
        if (chooseBlackColor) {
            orderPage.selectScooterColorBlack();
        }
        if (chooseGreyColor) {
            orderPage.selectScooterColorGrey();
        }

        //Вводим комментарий, если есть
        if (commentField != null && !commentField.isEmpty()) {
            orderPage.fillComment(commentField);
        }

        // Оформление заказа
        orderPage.clickOrderButton();
        assertTrue("Окно подтверждения заказа не появилось", orderPage.isConfirmationPopupVisible());
        orderPage.confirmOrder();
        assertTrue("Кнопка 'Посмотреть статус' не доступна", orderPage.isStatusButtonVisible());
        orderPage.clickViewStatusButton();

        // Проверка успешного завершения заказа
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Отменить заказ']")));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}