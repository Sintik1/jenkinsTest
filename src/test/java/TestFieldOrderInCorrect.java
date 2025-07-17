import POM.HeadPage;
import POM.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestFieldOrderInCorrect {
    WebDriver driver;
    private static final String BASE_URI= "https://qa-scooter.praktikum-services.ru/";


    //Вспомогательный метод перехода по кнопке "Заказать" в хедере
    public void navigateToOrder () {
        HeadPage objHeadPage = new HeadPage(driver);
        objHeadPage.clickButtonCookie();
        objHeadPage.clickButtonOrderInHeaderPage();
    }

    //Вспомогательный метод генерации данных для не корректного ввода
    private static String generateIncorrectData(){
        return RandomStringUtils.randomAlphabetic(5);
    }

    @Before
    public void setup(){
        //Запуск Firefox
        /*WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless"); // Запуск в headless режиме, если необходимо
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--disable-dev-shm-usage");
        driver = new FirefoxDriver(firefoxOptions);*/

        //Запуск хром
        //Запуск хром
        // Логирование версий
       try {
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
            driver.get(BASE_URI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    //Негативный сценарий отображения ошибки при не корректном вводе поля "Имя"
    public void isDisplayedErrorInCorrectSendName() {
        String expectedResult = "Введите корректное имя";
        navigateToOrder();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickFieldName();
        objOrderPage.sendFieldName(generateIncorrectData());
        objOrderPage.clickFieldLastName();
        String actualResult = objOrderPage.checkedIncorrectDataEntry();
        Assert.assertEquals("Текст ошибки не отобразился",expectedResult,actualResult);
    }

    @Test
    //Негативный сценарий отображения ошибки при не корректном вводе поля "Фамилия"
    public void isDisplayedErrorInCorrectSendLastName() {
        String expectedResult = "Введите корректную фамилию";
        navigateToOrder();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickFieldLastName();
        objOrderPage.sendLastName(generateIncorrectData());
        objOrderPage.clickFieldName();
        String actualResult = objOrderPage.checkedIncorrectDataEntry();
        Assert.assertEquals("Текст ошибки не отобразился",expectedResult,actualResult);
    }
    @Test
    //Негативный сценарий отображения ошибки при не корректном вводе поля "Адрес"
    public void isDisplayedErrorInCorrectSendAdress() {
        String expectedResult = "Введите корректный адрес";
        navigateToOrder();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickFieldAdress();
        objOrderPage.sendFieldAdress(generateIncorrectData());
        objOrderPage.clickFieldName();
        String actualResult = objOrderPage.checkedIncorrectDataEntry();
        Assert.assertEquals("Текст ошибки не отобразился",expectedResult,actualResult);
    }
    @Test
    //Негативный сценарий отображения ошибки при не корректном вводе поля "Номер телефона"
    public void isDisplayedErrorInCorrectSendPhoneNumber() {
        String expectedResult = "Введите корректный номер";
        navigateToOrder();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickPhoneNumber();
        objOrderPage.sendPhoneNumber(generateIncorrectData());
        objOrderPage.clickFieldName();
        String actualResult = objOrderPage.checkedIncorrectDataEntry();
        Assert.assertEquals("Текст ошибки не отобразился",expectedResult,actualResult);
    }

    @Test
    //Негативный сценарий отображения ошибки при не выборе станции метро
    public void isDisplayedErrorNotChoiseMetroStation(){
        String expectedText = "Выберите станцию";
        navigateToOrder();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickButtonNext();
        String actualResult=objOrderPage.isDisplayedErrorChoiseMetroStation();
        Assert.assertEquals("Ошибка не отобразилась: ", expectedText,actualResult);
    }

    @After
    public void tearDown(){
        if (driver != null) {
            try {
                System.out.println("Закрытие драйвера");
                driver.quit();
                System.out.println("Драйвер закрыт");
            } catch (Exception e) {
                System.err.println("Ошибка при закрытии драйвера: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    }

