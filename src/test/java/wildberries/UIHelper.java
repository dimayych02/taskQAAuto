package wildberries;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class UIHelper extends UITests {

    //Неявное ожидание
    public static WebDriverWait getWaiter(long timeOutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        webDriverWait
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);
        return webDriverWait;
    }

    //Явное ожидание на видимость элемента
    public static WebElement waitForElementVisible(WebElement findStrategy, long timeoutInSeconds) {
        getWaiter(timeoutInSeconds)
                .until(ExpectedConditions.visibilityOf(findStrategy));
        return findStrategy;
    }

    public static void scrollingPage(){//скроллинг страницы
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,5000)");
    }
}
