package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeadPage {
    WebDriver driver;
    WebDriverWait wait;

    //Кнопка поля куки "Да все привыкли"
    private By buttonCookie = By.id("rcc-confirm-button");

    //Локатор главной страницы
    private By locatorHeadPage = By.className("Home_FirstPart__3g6vG");

    //Кнопка "Заказать" в хедере сайта
    private By buttonOrderInHeaderPage = By.className("Button_Button__ra12g");

    //Кнопка "Статус заказа в хэдере сайта
    private By buttonStatusOrderInHeaderPage = By.className("Header_Link__1TAG7");

    //Кнопка самокат в хедере страницы
    private By buttonScooterInHeaderPage = By.className("Header_LogoScooter__3lsAR");

    //Кнопка Яндекс в хедере страницы
    private By buttonYandexInHeaderPage = By.className("Header_LogoYandex__3TSOI");

    //Кнопка "Заказать" в середине сайта
    private By buttonOrderInCenterPage = By.className("Button_Middle__1CSJM");

   //Локатор поля для ввода статуса заказа
   private By fieldSendStatusOrder = By.cssSelector("input[placeholder='Введите номер заказа']");

    //Список локаторов вопросов
    public enum ListQuestions {
        QUESTIONS_ONE("accordion__heading-0"),
        QUESTIONS_TWO("accordion__heading-1"),
        QUESTIONS_THREE("accordion__heading-2"),
        QUESTIONS_FOUR("accordion__heading-3"),
        QUESTIONS_FIVE("accordion__heading-4"),
        QUESTIONS_SIX("accordion__heading-5"),
        QUESTIONS_SEVEN("accordion__heading-6"),
        QUESTIONS_EIGHT("accordion__heading-7");

        private String id;


        ListQuestions(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    //Список локаторов с ответами на вопросы
    public enum ListResponse {
        RESPONSE_ONE("accordion__panel-0"),
        RESPONSE_TWO("accordion__panel-1"),
        RESPONSE_THREE("accordion__panel-2"),
        RESPONSE_FOUR("accordion__panel-3"),
        RESPONSE_FIVE("accordion__panel-4"),
        RESPONSE_SIX("accordion__panel-5"),
        RESPONSE_SEVEN("accordion__panel-6"),
        RESPONSE_EIGHT("accordion__panel-7");

        private String id;


        ListResponse(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    //Список с текстом ответа
    public enum ListTextResponse{
        RESPONSE_ONE("Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
        RESPONSE_TWO("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
        RESPONSE_THREE("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
        RESPONSE_FOUR("Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
        RESPONSE_FIVE("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
        RESPONSE_SIX("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
        RESPONSE_SEVEN("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
        RESPONSE_EIGHT("Да, обязательно. Всем самокатов! И Москве, и Московской области.");
        private String textResponse;


        ListTextResponse(String textResponse) {
            this.textResponse = textResponse;
        }

        public String getTextResponse() {
            return textResponse;
        }
    }

    //Локатор кнопки Go! в поле для ввода номера заказа
    private final By buttonGo=By.xpath(".//button[contains(text(),'Go!')]");

    public HeadPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver,5);
    }

    //Вспомогательный метод для клика
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    //Вспомогательный метод заполнения поля
    public void sendKeys(By locator, String keys) {
        driver.findElement(locator).sendKeys(keys);
    }

    //Вспомогательный метод для исключений
    public void handleExceptions(Exception e) {
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

    //Метод клика по кнопке заказать в хедере сайта
    public void clickButtonOrderInHeaderPage() {
        click(buttonOrderInHeaderPage);
    }

    //Метод клика по кнопке "Статус заказа" в хедере сайта
    public void clickButtonStatusOrderInHeaderPage() {
        click(buttonStatusOrderInHeaderPage);
    }

    //Метод клика по кнопке "Яндекс самокат" в хедере
    public void clickButtonTransitYandex() {
        click(buttonYandexInHeaderPage);
    }


    //Метод клика по кнопке куки
    public void clickButtonCookie(){
        click(buttonCookie);
    }
    //Метод клика по кнопке "Заказать" в середине сайта
    public void clickButtonOrderInCenterSite() {
        WebElement element = waitUntilVisible(buttonOrderInCenterPage);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        click(buttonOrderInCenterPage);
    }

    //Метод скрола до вопросов
    public void scrollToQuestions() {
        try {
            WebElement element = waitUntilVisible(By.id(ListQuestions.QUESTIONS_EIGHT.getId()));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        } catch (Exception e) {
            handleExceptions(e);
        }
    }

    //Метод клика по вопросу
    public void clickQuestions(By locator) {
        try {
            waitUntilClickable(locator);
            click(locator);
        } catch (Exception e) {
            handleExceptions(e);
        }
    }

    //Метол получения текста из вопроса
    public String getTextInResponse(By locator){
        try {
            WebElement element=waitUntilVisible(locator);
            String actualText= element.getText();
            return actualText;
        }catch (Exception e){
            handleExceptions(e);
            return "Произошла ошибка при получении текста";
        }
    }
    //Метод клика поля для ввода номера заказа
    public void clickFieldStatusOrder(){
        waitUntilClickable(fieldSendStatusOrder);
        click(fieldSendStatusOrder);
    }

    //Метод заполнения поля статуса заказа
    public void sendFieldstatusOrder(String statusOrder) {
        try {
            waitUntilClickable(fieldSendStatusOrder);
            sendKeys(fieldSendStatusOrder, statusOrder);
        } catch (Exception e) {
            handleExceptions(e);
        }
    }


    //Метод клика по кнопке GO!
    public void clickButtonGo(){
        click(buttonGo);
    }

    //Метод проверки отображения главной страница
    public boolean isDisplayedHeadPage(){
        try{
            waitUntilVisible(locatorHeadPage);
            return true;
        }catch (Exception e){
            handleExceptions(e);
            return false;
        }
    }
}
