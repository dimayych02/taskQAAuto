package wildberries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class UITests {

    protected static WebDriver driver;
    private SearchPage searchPage;
    private final String URL = "https://www.wildberries.ru";


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        searchPage = new SearchPage(driver);
        driver.get(URL);
    }

    @Test
    public void filterItems() {
        searchPage.fillSearchField();
        searchPage.filterGoods();
        searchPage.checkGoodsAttributes();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

}
