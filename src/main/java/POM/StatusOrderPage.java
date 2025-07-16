package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StatusOrderPage {
    WebDriver driver;
    WebDriverWait wait;

    //Кнопка самокат в хедере страницы
    private By buttonScooterInHeaderPage = By.className("Header_LogoScooter__3lsAR");

    //Кнопка Яндекс в хедере страницы
    private By buttonYandexInHeaderPage = By.className("Header_LogoYandex__3TSOI");

    private final By statusOrderWindow = By.className("Track_OrderColumns__2r_1F");

    private By locatorStatusOrderWindowWhenNotOrder = By.className("Track_NotFound__6oaoY");

    public StatusOrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,5);
    }

    //Вспомогательный метод для исключений
    public void handleExceptions(Exception e) {
        System.out.println("Произошла ошибка: " + e.getMessage());
    }

    //Вспомогательный метод ожидания видимости элемента
    private WebElement waitUntilVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Вспомогательный метод ожидания кликабельности элемента
    private WebElement waitUntilClickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Метод клика по кнопке Самокат в хедере
    public void clickButtonScooterInHeader(){
       waitUntilVisible(statusOrderWindow);
       driver.findElement(buttonScooterInHeaderPage).click();
    }
    public boolean isDisplayedStatusOrderWindow(){
        try{
            waitUntilVisible(statusOrderWindow);
            return true;
        }catch (Exception e){
            handleExceptions(e);
            return false;
        }
    }

    //Метод проверки отображения страницы что заказ не найден
    public boolean isDisplayedPageWhenOrderNotFound(){
        try {
            waitUntilVisible(locatorStatusOrderWindowWhenNotOrder);
            return true;
        }catch (Exception e){
            handleExceptions(e);
            return false;
        }
    }
}
