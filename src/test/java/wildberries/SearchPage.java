package wildberries;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class SearchPage {

    private WebDriver driver;

    private final String GOOD_VALUE = "транспортир";

    @FindBy(id = "searchInput")
    private WebElement buttonSearch;

    @FindBy(xpath = "//button[contains(@class,'dropdown-filter__btn--sorter')]")
    private WebElement buttonFilter;

    @FindBy(css = "button[class=swiper-button-next]")
    private WebElement nextAdvertiseButton;

    @FindBy(xpath = "//span[text()='По возрастанию цены']")
    private WebElement filterIncreasingPrice;

    @FindBy(css = "ins[class=price__lower-price]")
    private List<WebElement> listOfPrices;

    @FindBy(css = "span[class=product-card__name]")
    private List<WebElement> listOfProductNames;


    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public SearchPage fillSearchField() {
        //ждем пока по  кнопке  nextAdvertise можно кликнуть,чтобы можно было ввести текст 'траспнортир'
        nextAdvertiseButton = UIHelper.waitForElementVisible(nextAdvertiseButton, 10);
        buttonSearch.sendKeys(GOOD_VALUE, Keys.ENTER);
        return this;
    }

    public SearchPage filterGoods() {
        buttonFilter = UIHelper.waitForElementVisible(buttonFilter, 15);
        buttonFilter.click();
        filterIncreasingPrice.click();
        return this;
    }

    public SearchPage checkGoodsAttributes() {
        UIHelper.scrollingPage();

        int indexName = 0;
        for (WebElement element : listOfProductNames) {//Обходим stale element reference exception
            System.out.println(listOfProductNames.get(indexName).getText().replaceAll("/", ""));
            indexName++;
        }

        int indexPrice = 0;
        for (WebElement element : listOfPrices) {//Обходим stale element reference exception
            System.out.println(listOfPrices.get(indexPrice).getText());
            indexPrice++;
        }

        return this;
    }

}
