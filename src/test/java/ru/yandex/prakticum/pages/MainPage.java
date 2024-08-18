
package ru.yandex.prakticum.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //Локаторы для Вопроов и ответово важном
    private final By question_0 = By.xpath(".//div[@aria-controls='accordion__panel-0']");
    private final By answer_0 = By.xpath(".//div[@id='accordion__panel-0']/p");
    private final By question_1 = By.xpath(".//div[@aria-controls='accordion__panel-1']");
    private final By answer_1 = By.xpath(".//div[@id='accordion__panel-1']/p");
    private final By question_2 = By.xpath(".//div[@aria-controls='accordion__panel-2']");
    private final By answer_2 = By.xpath(".//div[@id='accordion__panel-2']/p");
    private final By question_3 = By.xpath(".//div[@aria-controls='accordion__panel-3']");
    private final By answer_3 = By.xpath(".//div[@id='accordion__panel-3']/p");
    private final By question_4 = By.xpath(".//div[@aria-controls='accordion__panel-4']");
    private final By answer_4 = By.xpath(".//div[@id='accordion__panel-4']/p");
    private final By question_5 = By.xpath(".//div[@aria-controls='accordion__panel-5']");
    private final By answer_5 = By.xpath(".//div[@id='accordion__panel-5']/p");
    private final By question_6 = By.xpath(".//div[@aria-controls='accordion__panel-6']");
    private final By answer_6 = By.xpath(".//div[@id='accordion__panel-6']/p");
    private final By question_7 = By.xpath(".//div[@aria-controls='accordion__panel-7']");
    private final By answer_7 = By.xpath(".//div[@id='accordion__panel-7']/p");

    //Методы для клика по вопросу и получения ответа
    public By getQuestion0Locator() {
        return question_0;
    }
    public By getAnswer0Locator() {
        return answer_0;
    }
    public By getQuestion1Locator() {
        return question_1;
    }
    public By getAnswer1Locator() {
        return answer_1;
    }
    public By getQuestion2Locator() {
        return question_2;
    }
    public By getAnswer2Locator() {
        return answer_2;
    }
    public By getQuestion3Locator() {
        return question_3;
    }
    public By getAnswer3Locator() {
        return answer_3;
    }
    public By getQuestion4Locator() {
        return question_4;
    }
    public By getAnswer4Locator() {
        return answer_4;
    }
    public By getQuestion5Locator() {
        return question_5;
    }
    public By getAnswer5Locator() {
        return answer_5;
    }
    public By getQuestion6Locator() {
        return question_6;
    }
    public By getAnswer6Locator() {
        return answer_6;
    }
    public By getQuestion7Locator() {
        return question_7;
    }
    public By getAnswer7Locator() {
        return answer_7;
    }
    //Локатор для верхней кнопки Заказать
    private final By orderButtonTop = By.className("Button_Button__ra12g");
    //Локатор для нижней кнопки Заказать
    private final By orderButtonBottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    //Метод для клика по верхней кнопке Заказать
    public void clickOrderButtonTop(){

        driver.findElement(orderButtonTop).click();
    }
    //Метод для клика по нижней кнопке Заказать
    public void clickOrderButtonBottom(){
        // Прокручиваем страницу до кнопки
        WebElement orderButtonBottomElement = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderButtonBottomElement);
        //клик по кнопке
        driver.findElement(orderButtonBottom).click();
    }
}