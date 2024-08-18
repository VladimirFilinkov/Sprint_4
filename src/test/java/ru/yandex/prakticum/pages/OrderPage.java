package ru.yandex.prakticum.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public OrderPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Локаторы для полей ввода на первой странице заказа
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
        private By metroStationLubyanka = By.xpath(".//div[text()='Лубянка']");
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор для кнопки далее
    private By nextButton = By.xpath(".//button[text()='Далее']");

    //Локаторы для полей ввода на второй странице заказа
    private By deliveryDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentPeriodDropdown = By.xpath(".//div[text()='* Срок аренды']");
        private By rentPeriodOptions = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");
    private By scooterColorTitle = By.xpath(".//div[text()='Цвет самоката']");
    private By blackColorCheckbox = By.xpath(".//input[@id='black']");
    private By greyColorCheckbox = By.xpath(".//input[@id='grey']");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Локаторы для кнопок Заказать, Да, Посмореть статус
    private By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private By confirmationPopup = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    private By statusButton = By.xpath(".//button[text()='Посмотреть статус']");

    //Метод для заполнения поля Имя
    public void fillName (String name){

        driver.findElement(nameField).sendKeys(name);
    }
    //Метод для заполнения поля Фамилия
    public void fillLastName (String lastName){

        driver.findElement(lastNameField).sendKeys(lastName);
    }
    //Метод для заполнения поля Адрес
    public void fillAddress (String adress){

        driver.findElement(addressField).sendKeys(adress);
    }
    //Метод для заполнения поля Станция
    public void fillMetroStation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Ожидание видимости поля выбора станции метро и клика по нему
        wait.until(ExpectedConditions.elementToBeClickable(metroStationField)).click();
        // Ожидание видимости элемента 'Спартак' и клика по нему
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroStationLubyanka)).click();
    }
    //Метод для заполнения поля Телефон
    public void fillPhone (String phone){
        driver.findElement(phoneField).sendKeys(phone);
    }
    //Метод для клика по кнопке Далее
    public void clickNextButton (){
        driver.findElement(nextButton).click();
    }

    //Метод для выбора даты доставки самоката
    public void selectDeliveryDate (String deliveryDate) {
        driver.findElement(deliveryDateField).sendKeys(deliveryDate);
        driver.findElement(deliveryDateField).sendKeys(Keys.ENTER);

    }
    //Метод для выбора срока аренды
    public void selectRentPeriod(){
        driver.findElement(rentPeriodDropdown).click();
        driver.findElement(rentPeriodOptions).click();
    }
    //Метод для выбора черного цвета самоката
    public void selectScooterColorBlack(){
        driver.findElement(blackColorCheckbox).click();
    }
    //Метод для выбора серого цвета самоката
    public void selectScooterColorGrey(){
        driver.findElement(greyColorCheckbox).click();
    }
    //Метод для ввода комментария к заказу
    public void fillComment(String comment){
        driver.findElement(commentField).sendKeys(comment);
    }
    //метод для клика кнопки Заказать
    public void clickOrderButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).click();
        //driver.findElement(orderButton).click();
    }
    //метод для клика кнопки Да
    public void confirmOrder(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationPopup)).click();
        //        driver.findElement(confirmationPopup).click();
    }
    public void clickViewStatusButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(statusButton)).click();
    }

    //Метод для проверки видимости окна подтверждения заказа
    public boolean isConfirmationPopupVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationPopup));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //Метод для проверки видимости кнопки Посмотреть статус
    public boolean isStatusButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(statusButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    //Метод для проверки видимости старинцы статуса заказа
    public boolean isStatusPageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(statusButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}