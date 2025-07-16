package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;

public class YandexPage {

    WebDriver driver;

    //Локатор поиска на странице Яндекс
    private By locatorYandexPage = By.xpath(".//div[@class='dzen-desktop--ya-search-micro-app__yandexSearchContainer-Ym']");

    public YandexPage(WebDriver driver) {
        this.driver = driver;
    }

    //вспомогательный метод обработки исключений
    public void handleException(Exception e) {
        System.out.println("Произошла ошибка: " + e.getMessage());
    }

    //Метод проверки отображения страницы Яндекс
    public boolean isDisplayedYandexPage() {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            try {
                // Ожидаем, пока появится новая вкладка
                wait.until(driver -> driver.getWindowHandles().size() > 1);

                // Сохраняем идентификатор главного окна
                String mainHandleWindow = driver.getWindowHandle();
                Set<String> allWindowHandles = driver.getWindowHandles();

                // Переключаемся на новую вкладку
                for (String handle : allWindowHandles) {
                    if (!handle.equals(mainHandleWindow)) {
                        driver.switchTo().window(handle);
                        break;
                    }
                }
                // Ожидаем, пока элемент станет видимым
                wait.until(ExpectedConditions.visibilityOfElementLocated(locatorYandexPage));
                return true;
            } catch (Exception e) {
                handleException(e);
                return false;
            }
        }
    }


