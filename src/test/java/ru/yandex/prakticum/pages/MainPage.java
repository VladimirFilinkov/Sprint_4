
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

    //Локатор для верхней кнопки Заказать
    private final By orderButtonTop = By.className("Button_Button__ra12g");
    //Локатор для нижней кнопки Заказать
    private final By orderButtonBottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    //Локатор для логотипа Самокат
    private final By logoScooter = By.className("Header_LogoScooter__3lsAR");
    //Локатор для логотипа Яндекс
    private final By logoYandex = By.className("Header_LogoYandex__3TSOI");

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

    // Метод для получения локатора вопроса по индексу
    public By getQuestionLocatorByIndex(int index) {
        return By.xpath(".//div[@aria-controls='accordion__panel-" + index + "']");
    }

    // Метод для получения локатора ответа по индексу
    public By getAnswerLocatorByIndex(int index) {
        return By.xpath(".//div[@id='accordion__panel-" + index + "']");
    }

    //Метод для возвращения локатора логотипа Самокат
    public By getElementScooterLocator(){
        return logoScooter;
    }
    //Метод клика по логотипу Самокат
    public void clickLogoScooter(){
        driver.findElement(logoScooter).click();
    }

    //Метод для возвращения локатора логотипа Самокат
    public By getElementYandexLocator(){
        return logoScooter;
    }
    //Метод клика по логотипу Самокат
    public void clickLogoYandex(){
        driver.findElement(logoYandex).click();
    }
}

