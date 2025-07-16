package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    WebDriver driver;
    WebDriverWait wait;

    //Поле Имя
    private By fieldName = By.cssSelector("input[placeholder='* Имя']");

    //Поле фамилия
    private By fieldLastName = By.cssSelector("input[placeholder='* Фамилия']");

    //Поле адрес
    private By fieldAdress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");

    //Поле станция метро
    private By fieldMetroStation = By.cssSelector("div.select-search__value");
    private String index;

    //Поле номер телефона
    private By fieldPhoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка "Далее"
    private By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Кнопка статус заказа в хедере страницы
    private By buttonStatusOrderInHeaderPage = By.className("Header_Link__1TAG7");

    //Кнопка самокат в хедере страницы
    private By buttonScooterInHeaderPage = By.className("Header_LogoScooter__3lsAR");

    //Кнопка Яндекс в хедере страницы
    private By buttonYandexInHeaderPage = By.className("Header_LogoYandex__3TSOI");

    //Локатор текста не корректного ввода данных
    private By locatorIncorrectData =By.xpath(".//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6']");

    //Локатор отображения ошибки выбора станции
    private By locatorErrorChoiseMetroStation = By.cssSelector("div.Order_MetroError__1BtZb");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver,5);
    }

    //вспомогательный метод клика по элементу
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    //Вспомогательный метод заполнения поля
    public void sendKeys(By locator, String keys) {
        driver.findElement(locator).sendKeys(keys);
    }

    //вспомогательный метод обработки исключений
    public void handleException(Exception e) {
        System.out.println("Произошла ошибка: " + e.getMessage());
    }
    //Вспомогательный метод ожидания видимости элемента
    private WebElement waitUntilVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Вспомогательный метод ожидания кликабельности  элемента
    private WebElement waitUntilClickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Метод клика по полю "Имя"
    public void clickFieldName() {
        click(fieldName);
    }

    //Метод заполнения поля "Имя"
    public void sendFieldName(String name) {
        try {
            waitUntilClickable(fieldName);
            sendKeys(fieldName, name);
        } catch (Exception e) {
            handleException(e);
        }
    }

    //Метод клика поля "Фамилия"
    public void clickFieldLastName() {
        click(fieldLastName);
    }

    //Метод заполнения поля "Фамилия"
    public void sendLastName(String lastName) {
        try {
            waitUntilClickable(fieldLastName);
            sendKeys(fieldLastName, lastName);
        } catch (Exception e) {
            handleException(e);
        }
    }

    //Метод клика поля адрес доставки
    public void clickFieldAdress() {
        click(fieldAdress);
    }

    //Метод заполнения поля адрес доставки
    public void sendFieldAdress(String adress) {
        try {
            waitUntilClickable(fieldAdress);
            sendKeys(fieldAdress, adress);
        } catch (Exception e) {
            handleException(e);
        }
    }

    //Метод клика выбора станции метро
    public void clickMetroStation() {
        click(fieldMetroStation);
    }

    //Метод выбора станции метро
    public void choiseMetroStation(int index) {
        try {
           waitUntilVisible(By.xpath(".//li[@data-index='0']"));
            WebElement element = driver.findElement(By.xpath(".//li[@data-index='" + index + "']"));
            element.click();
        } catch (Exception e) {
            handleException(e);
        }
    }

    //Метод клика поля Номер телефона
    public void clickPhoneNumber() {
        click(fieldPhoneNumber);
    }

    //Метод заполнения поля номер телефона
    public void sendPhoneNumber(String phoneNumber) {
        try {
            waitUntilClickable(fieldPhoneNumber);
            sendKeys(fieldPhoneNumber, phoneNumber);
        } catch (Exception e) {
            handleException(e);
        }
    }

    //Метод клика по кнопке "Далее"
    public void clickButtonNext() {
        try {
            waitUntilClickable(buttonNext);
            click(buttonNext);
        } catch (Exception e) {
            handleException(e);
        }
    }

    //Метод проверки некорректного ввода данных в полях
    public String checkedIncorrectDataEntry(){
        try{
            WebElement element = waitUntilVisible(locatorIncorrectData);
            String actualResult = element.getText();
            return actualResult;
        }catch (Exception e){
            handleException(e);
            return "Произошла ошибка";
        }
    }

    //Метод отображения ошибки при не выборе станции
    public String isDisplayedErrorChoiseMetroStation(){
        try{
            WebElement element = waitUntilVisible(locatorErrorChoiseMetroStation);
            String actualResult = element.getText();
            return actualResult;
        }catch (Exception e){
            handleException(e);
            return "Произошла ошибка";
        }
    }

    //Метод клика по кнопке Самокат в хедере
    public void clickScooterButton(){
        click(buttonScooterInHeaderPage);
    }
}

