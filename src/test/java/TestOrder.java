import POM.HeadPage;
import POM.OrderPage;
import POM.RentPage;
import POM.StatusOrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.LocalDate;


@RunWith(Parameterized.class)
public class TestOrder {
    WebDriver driver;

    private static final String BASE_URI= "https://qa-scooter.praktikum-services.ru/";
    private static LocalDate currentDate = LocalDate.now().plusDays(1);
    private String name;
    private String lastName;
    private String adress;
   private int indexMetroStation;
    private String phoneNumber;
    private String timeRent;
    private String colorScooter;
    private String comment;
    private String numberOrder;

    public TestOrder(String name, String lastName, String adress, int indexMetroStation, String phoneNumber,LocalDate currentDate,String timeRent,String colorScooter,String comment) {
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.indexMetroStation = indexMetroStation;
        this.phoneNumber = phoneNumber;
        this.currentDate = currentDate;
        this.timeRent = timeRent;
        this.colorScooter = colorScooter;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][]getTestData() {
        return new Object[][]{
                {"Тест", "Тест", "Москва", 224, "+79293487210",currentDate,RentPage.ListTimeRent.SIX_DAY.getXpath(),RentPage.ColorScooter.BLACK.getId(),"Тестовый комментарий"},
                {"Тест", "Тест", "Санкт-Петербург", 15, "+79291237210",currentDate,RentPage.ListTimeRent.SEVEN_DAY.getXpath(),RentPage.ColorScooter.GREY.getId(),"Тестовый комментарий"}
        };
    }
    @Before
    public void setup() {
        //Запуск Firefox
        /*WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless"); // Запуск в headless режиме, если необходимо
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--disable-dev-shm-usage");
        driver = new FirefoxDriver(firefoxOptions);*/

        //Запуск хром
        // Логирование версий
        System.out.println("Setting up WebDriver...");
        WebDriverManager.chromedriver().setup();
        System.out.println("ChromeDriver version: " + WebDriverManager.chromedriver().getDownloadedDriverVersion());

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(chromeOptions);
        System.out.println("Chrome browser launched successfully.");
        driver.manage().window().maximize();
        driver.get(BASE_URI);


    }

    //Cценарий создания заказа через кнопку Заказать в хедере
    @Test
    public void testOrderToOrderButtonInHeaderPage(){
     navigateToOrderInHeaderPage();
     sendDataInOrderPage();

     //Переход на страницу выбора аренды
     RentPage objRentPage = new RentPage(driver);
     sendDataINPageRent();
     boolean isDisplayedSuccessOrder = objRentPage.isDisplayedModalWindowSuccesOrder();
     Assert.assertTrue("Окно не отобразилось",isDisplayedSuccessOrder);
    }

    //Cценарий создания заказа через кнопку Заказать в хедере
    @Test
    public void testOrderToOrderButtonInMiddlePage(){
        navigateToOrderInMiddlePage();
        sendDataInOrderPage();

        //Переход на страницу выбора аренды
        RentPage objRentPage = new RentPage(driver);
        sendDataINPageRent();
        boolean isDisplayedSuccessOrder = objRentPage.isDisplayedModalWindowSuccesOrder();
        Assert.assertTrue("Окно не отобразилось",isDisplayedSuccessOrder);
    }

    @Test
    //Cценарий проверки статуса заказа
    public void checkedStatusOrder(){
        navigateToOrderInHeaderPage();
        sendDataInOrderPage();

        //Переход на страницу выбора аренды
        RentPage objRentPage = new RentPage(driver);
        sendDataINPageRent();
        objRentPage.clickButtonStatusOrderInModalWindow();
        boolean isDisplayedWindowStatusOrder = objRentPage.isDisplayedStatusOrderWindow();
        Assert.assertTrue("Страница не отобразилась",isDisplayedWindowStatusOrder);
    }


    @Test

    //Cценарий проверки статуса заказа c с главной страницы
    public void checkedStatusOrderInHeadPage(){
        navigateToOrderInHeaderPage();
        sendDataInOrderPage();

        //Переход на страницу выбора аренды
        RentPage objRentPage = new RentPage(driver);
        sendDataINPageRent();
        String numberOrder = objRentPage.getNumberOrder();
        objRentPage.clickButtonStatusOrderInModalWindow();

        //Переход на страницу статуса заказа
        StatusOrderPage objStatusOrderPage = new StatusOrderPage(driver);
        objStatusOrderPage.clickButtonScooterInHeader();

        //Возврат на главную страницу
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonStatusOrderInHeaderPage();
        objHeadPage.clickFieldStatusOrder();
        objHeadPage.sendFieldstatusOrder(numberOrder);
        objHeadPage.clickButtonGo();
        boolean actualResult = objStatusOrderPage.isDisplayedStatusOrderWindow();
        Assert.assertTrue("Страница не отобразилась",actualResult);
    }

    @Test
    //Негативный сценарий проверки отображения страницы статуса заказа когда заказ не найден
    public void testStatusOrderWhenOrderNotFound(){
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonCookie();
        objHeadPage.clickButtonStatusOrderInHeaderPage();
        objHeadPage.clickFieldStatusOrder();
        objHeadPage.sendFieldstatusOrder("1928342");
        objHeadPage.clickButtonGo();

        //Переход на страницу статуса заказа
        StatusOrderPage objStatusOrderPage = new StatusOrderPage(driver);
        boolean actualResult = objStatusOrderPage.isDisplayedPageWhenOrderNotFound();
        Assert.assertTrue("Картинка с сообщением что заказ не найден не отобразилась",actualResult);
    }

        //Вспомогательный метод перехода по кнопке "Заказать" в хедере
        public void navigateToOrderInHeaderPage() {
            HeadPage objHeadPage = new HeadPage(driver);
            objHeadPage.clickButtonCookie();
            objHeadPage.clickButtonOrderInHeaderPage();
        }

    //Вспомогательный метод перехода по кнопке "Заказать" в хедере
    public void navigateToOrderInMiddlePage() {
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonCookie();
        objHeadPage.clickButtonOrderInCenterSite();
    }

        //Вспомогательный метод заполнения данных
        public void sendDataInOrderPage(){
            OrderPage objOrderPage = new OrderPage(driver);
            objOrderPage.clickFieldName();
            objOrderPage.sendFieldName(name);
            objOrderPage.clickFieldLastName();
            objOrderPage.sendLastName(lastName);
            objOrderPage.clickFieldAdress();
            objOrderPage.sendFieldAdress(adress);
            objOrderPage.clickMetroStation();
            objOrderPage.choiseMetroStation(indexMetroStation);
            objOrderPage.clickPhoneNumber();
            objOrderPage.sendPhoneNumber(phoneNumber);
            objOrderPage.clickButtonNext();
        }

        //Вспомогательный метод заполнения страницы аренды для прямого сценария
        public void sendDataINPageRent(){
            RentPage objRentPage = new RentPage(driver);
            objRentPage.clickFieldChoiseDateDeliverly();
            objRentPage.choiseDateDeliverly(String.valueOf(currentDate));
            objRentPage.clickFieldTimeRent();
            objRentPage.choiseTimeRent(timeRent);
            objRentPage.choiseColorScooter(colorScooter);
            objRentPage.clickFieldComment();
            objRentPage.sendFieldComment(comment);
            objRentPage.clickButtonOrdering();
            objRentPage.clickButtonYesInModalWindow();
        }

    @After
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }
}
