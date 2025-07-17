import POM.HeadPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@RunWith(Parameterized.class)
public class TestQuestions {
    WebDriver driver;
    private static final String BASE_URI= "https://qa-scooter.praktikum-services.ru/";
    private String locatorQuestions;
    private String locatorResponse;
    private String expectedResult;

    public TestQuestions(String locatorQuestions, String locatorResponse,String expectedResult) {
        this.locatorQuestions = locatorQuestions;
        this.locatorResponse = locatorResponse;
        this.expectedResult = expectedResult;
    }
    @Parameterized.Parameters
    public static Object[][]getTestData(){
        return new Object[][]{
                {HeadPage.ListQuestions.QUESTIONS_ONE.getId(),HeadPage.ListResponse.RESPONSE_ONE.getId(),HeadPage.ListTextResponse.RESPONSE_ONE.getTextResponse()},
                {HeadPage.ListQuestions.QUESTIONS_TWO.getId(),HeadPage.ListResponse.RESPONSE_TWO.getId(),HeadPage.ListTextResponse.RESPONSE_TWO.getTextResponse()},
                {HeadPage.ListQuestions.QUESTIONS_THREE.getId(),HeadPage.ListResponse.RESPONSE_THREE.getId(),HeadPage.ListTextResponse.RESPONSE_THREE.getTextResponse()},
                {HeadPage.ListQuestions.QUESTIONS_FOUR.getId(),HeadPage.ListResponse.RESPONSE_FOUR.getId(),HeadPage.ListTextResponse.RESPONSE_FOUR.getTextResponse()},
                {HeadPage.ListQuestions.QUESTIONS_FIVE.getId(),HeadPage.ListResponse.RESPONSE_FIVE.getId(),HeadPage.ListTextResponse.RESPONSE_FIVE.getTextResponse()},
                {HeadPage.ListQuestions.QUESTIONS_SIX.getId(),HeadPage.ListResponse.RESPONSE_SIX.getId(),HeadPage.ListTextResponse.RESPONSE_SIX.getTextResponse()},
                {HeadPage.ListQuestions.QUESTIONS_SEVEN.getId(),HeadPage.ListResponse.RESPONSE_SEVEN.getId(),HeadPage.ListTextResponse.RESPONSE_SEVEN.getTextResponse()},
                {HeadPage.ListQuestions.QUESTIONS_EIGHT.getId(),HeadPage.ListResponse.RESPONSE_EIGHT.getId(),HeadPage.ListTextResponse.RESPONSE_EIGHT.getTextResponse()},
        };
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
    //Метод проверки соответствия текста ответа в списке с вопросами
    public void checkedTextInResponse(){
        //Создал объект главной страницы
        HeadPage objHeadPage = new HeadPage(driver);
        //Выполнил скролл до вопросов
        objHeadPage.scrollToQuestions();
        //Клик по вопросу
        objHeadPage.clickQuestions(By.id(locatorQuestions));
        //Записал в переменную текст ответа
        String actualResult = objHeadPage.getTextInResponse(By.id(locatorResponse));
        //Сравнил ожидаемый результат с фактическим
        Assert.assertEquals("Текст не соответствует требованиям",expectedResult,actualResult);


    }
    @After
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }
}
