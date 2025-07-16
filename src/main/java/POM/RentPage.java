package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RentPage {
    WebDriver driver;
    WebDriverWait wait;

    //локатор выбора даты доставки
    private By fieldDateDeliverly = By.cssSelector("input[placeholder='* Когда привезти самокат']");

    //Локатор выпадающего календаря
    private By calendar = By.xpath(".//div[@class='react-datepicker__week']");

    //Поле срок аренды
    private By fieldTimeRent = By.cssSelector("div.Dropdown-placeholder");

    //Локаторы времени аренды
    public enum ListTimeRent {
        ONE_DAY(".//div[@class='Dropdown-option'and contains(text(),'сутки')]"),
        TWO_DAY(".//div[@class='Dropdown-option'and contains(text(),'двое суток')]"),
        THREE_DAY(".//div[@class='Dropdown-option'and contains(text(),'трое суток')]"),
        FOUR_DAY(".//div[@class='Dropdown-option'and contains(text(),'четверо суток')]"),
        FIVE_DAY(".//div[@class='Dropdown-option'and contains(text(),'пятеро суток')]"),
        SIX_DAY(".//div[@class='Dropdown-option'and contains(text(),'шестеро суток')]"),
        SEVEN_DAY(".//div[@class='Dropdown-option'and contains(text(),'семеро суток')]");

        private String xpath;

        ListTimeRent(String xpath) {
            this.xpath = xpath;
        }

        public String getXpath() {
            return xpath;
        }
    }

    //Локаторы чек-боксов цвета самокатов
    public enum ColorScooter {
        BLACK("black"),
        GREY("grey");

        private String id;

        ColorScooter(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    //Локатор выпадающего списка выбора срока аренды
    private By locatorListTimeRent= By.xpath(".//div[@class='Dropdown-menu']");

    //Локатор поля для ввода комментария
    private By fieldComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Локатор кнопки "Заказать"
    private By buttonOrdering = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Локатор модального окна с подтверждением заказа
    private By modalWindowOrder= By.xpath(".//div[@class='Order_Modal__YZ-d3']");

    //Локатор кнопки "Да" в модальном окне подтверждения заказа
    private By buttonYes = By.xpath("//*/div/div[2]/div[5]/div[2]/button[2]");

    //Локатор кнопки нет в модальном окне подтверждения
    private By buttonNo = By.xpath("Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i");

    //Локатор модального окна с текстом успешно оформленного заказа
    private By modalWindowSuccesOrder = By.className("Order_ModalHeader__3FDaJ");

    //Кнопка "Посмотреть статус" в модальном окне после создания заказа
    private final By buttonStatusOrderInModalWindow = By.xpath(".//button[contains(text(),'Посмотреть статус')]");

    //Локатор страницы со статусом заказа
    private final By statusOrderWindow = By.className("Track_OrderColumns__2r_1F");

    //Кнопка самокат в хедере страницы
    private By buttonScooterInHeaderPage = By.className("Header_LogoScooter__3lsAR");

    //Кнопка Яндекс в хедере страницы
    private By buttonYandexInHeaderPage = By.className("Header_LogoYandex__3TSOI");


    public RentPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver,5);
    }


    //Вспомогательный  метод клика
    public void click(By locator){
        driver.findElement(locator).click();
    }

    //Вспомогательный метод заполнения поля
    public void sendKeys(By locator,String keys){
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

    //Вспомогательный метод ожидания кликабельности элемента
    private WebElement waitUntilClickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Метод клика по полю выбора даты доставки
    public void clickFieldChoiseDateDeliverly(){
        click(fieldDateDeliverly);
    }

    //Метод выбора даты в календаре
    public void choiseDateDeliverly(String currentDate){
        try{
           waitUntilVisible(calendar);
            sendKeys(fieldDateDeliverly,currentDate);
            driver.findElement(fieldDateDeliverly).sendKeys(Keys.ENTER);
        }catch (Exception e){
            handleException(e);
        }
    }

    //Метод клика по полю выбора срока аренды
    public void clickFieldTimeRent(){
        click(fieldTimeRent);
    }

    //Метод выбора срока аренды
    public void choiseTimeRent(String timeRent){
        try{
            waitUntilVisible(locatorListTimeRent);
            click(By.xpath(timeRent));
        }catch (Exception e){
            handleException(e);
        }
    }
    //Метод выбора цвета самоката
    public void choiseColorScooter(String color){
        try{
            waitUntilVisible(By.id(color));
            click(By.id(color));
        }catch (Exception e){
            handleException(e);
        }
    }
    //Метод клика по полю комментарий
    public void clickFieldComment(){
        click(fieldComment);
    }

    //Метод заполнения поля комментарий
    public void sendFieldComment(String comment){
        try{
            waitUntilClickable(fieldComment);
            sendKeys(fieldComment,comment);
        }catch (Exception e){
            handleException(e);
        }
    }

    //Метод клика по кнопке "Заказать"
    public void clickButtonOrdering(){
        try{
            waitUntilClickable(buttonOrdering);
            click(buttonOrdering);
        }catch (Exception e){
            handleException(e);
        }
    }

    //Метод нажатия кнопки "Да" в окне подтверждения
    public void clickButtonYesInModalWindow() {
                   click(buttonYes);
                    }

    //Метод отображения модального окна с успешно оформленным заказом
    public boolean isDisplayedModalWindowSuccesOrder(){
        try{
            waitUntilVisible(modalWindowSuccesOrder);
            return true;
        }catch (Exception e){
            handleException(e);
            return false;
        }
    }

    //Метод нажатия кнопки "Посмотреть заказ" в модальном окне
    public void clickButtonStatusOrderInModalWindow(){
        click(buttonStatusOrderInModalWindow);
    }

    //Метод отображения страницы со статусом заказа
    public boolean isDisplayedStatusOrderWindow(){
        try{
            waitUntilVisible(statusOrderWindow);
            return true;
        }catch (Exception e){
            handleException(e);
            return false;
        }
    }

    //Метод получения номера заказа из модального окна успешного заказа
    public String getNumberOrder(){
        WebElement orderElement = driver.findElement(By.cssSelector("div.Order_Text__2broi"));

        // Получаем текст элемента
        String orderText = orderElement.getText();

        // Извлекаем номер заказа с помощью регулярного выражения
        String orderNumber = extractOrderNumber(orderText);
        return orderNumber;
    }

    //Вспомогательный метод получения номера заказа
    private static String extractOrderNumber(String text) {
        // Используем регулярное выражение для извлечения номера заказа
        String regex = "Номер заказа: (\\d+)";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1); // Возвращаем первую группу (номер заказа)
        }
        return null; // Если номер заказа не найден
    }
}

