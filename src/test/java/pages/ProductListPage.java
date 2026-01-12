package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepdefinitions.CartSteps;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductListPage {
    private AndroidDriver driver;
    private WaitUtils waitUtils;

    // --- Variabel Locator ---
    private final String catalogHeaderUi = "new UiSelector().text(\"Products\")";
    private final String cartIconId = "com.saucelabs.mydemoapp.android:id/cartIV";

    // Template for scroll and xpath (use %s as product name placeholder)
    private final String scrollTemplate = "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().description(\"%s\"))";
    private final String productImgXpath = "//android.widget.ImageView[@content-desc='%s']";

    private By firstProduct = By.xpath("//android.widget.ImageView[@content-desc=\"Sauce Lab Back Packs\"]");
    private By sortButton = By.id("com.saucelabs.mydemoapp.android:id/sortIV");
    private By priceAscOption = By.id("com.saucelabs.mydemoapp.android:id/priceAscCL");
    private By productPrice = By.id("com.saucelabs.mydemoapp.android:id/priceTV");
    private By productHeader = AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")");

    public ProductListPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void selectFirstProduct() {
        driver.findElement(firstProduct).click();
    }

    public void addProductByName(String productName) {
        waitUtils.waitForElementVisible(AppiumBy.androidUIAutomator(catalogHeaderUi));

        // Scroll to find the product (Use String.format to fill the product name into the template)
        String scrollAction = String.format(scrollTemplate, productName);
        driver.findElement(AppiumBy.androidUIAutomator(scrollAction));

        // Click ImageView product
        String dynamicXpath = String.format(productImgXpath, productName);
        waitUtils.waitForElementVisible(AppiumBy.xpath(dynamicXpath)).click();

        // Process in Detail Page
        ProductDetailPage detailPage = new ProductDetailPage(driver);

        Double price = detailPage.getProductPrice();
        CartSteps.addedProductPrices.add(price);

        detailPage.addToCart();

        // Back to product list
        driver.navigate().back();
    }

    public void openCart() {
        waitUtils.waitForElementVisible(AppiumBy.id(cartIconId)).click();
    }

    public void selectProductByName(String productName) {
        waitUtils.waitForElementVisible(AppiumBy.androidUIAutomator(catalogHeaderUi));

        // Scroll search product
        String scrollAction = String.format(scrollTemplate, productName);
        driver.findElement(AppiumBy.androidUIAutomator(scrollAction));

        // Click ImageView product
        String dynamicXpath = String.format(productImgXpath, productName);
        waitUtils.waitForElementVisible(AppiumBy.xpath(dynamicXpath)).click();
    }

    public boolean isProductListDisplayed() {
        try {
            return waitUtils.waitForElementVisible(productHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openSortMenu() {
        waitUtils.waitForElementVisible(sortButton).click();
    }

    public void selectSortByPriceAscending() {
        waitUtils.waitForElementVisible(priceAscOption).click();
    }

    public List<Double> getAllProductPrices() {
        // Take all the price elements visible on the screen
        List<WebElement> priceElements = driver.findElements(productPrice);
        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {
            // Remove the '$' symbol and change it to Double
            String priceText = element.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }
}