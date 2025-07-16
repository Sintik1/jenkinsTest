import POM.HeadPage;
import POM.OrderPage;
import POM.YandexPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestTransitInHomePage {
    WebDriver driver;
    private static final String BASE_URI= "https://qa-scooter.praktikum-services.ru/";



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

    //Cценарий перехода на страницу Яндекс
    @Test
    public void transitInPageYandex(){
        HeadPage objHeadPage= new HeadPage(driver);
        objHeadPage.clickButtonTransitYandex();

        //Переход на страницу Яндекс
        YandexPage objYandexPage = new YandexPage(driver);
        boolean isDisplayedYandexPage = objYandexPage.isDisplayedYandexPage();
        Assert.assertTrue("Страница не отобразилась",isDisplayedYandexPage);
    }

    //Cценарий перехода на главную страницу по кнопке "Самокат"
    @Test
    public void transitInHeadPage(){
        HeadPage objHeadPage= new HeadPage(driver);
        objHeadPage.clickButtonOrderInHeaderPage();

        //Переход на страницу создания заказа
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickScooterButton();
        boolean actualResult =objHeadPage.isDisplayedHeadPage();
        Assert.assertTrue("Страница не отобразилась",actualResult);
    }
}
