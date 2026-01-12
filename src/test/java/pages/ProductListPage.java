package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import stepdefinitions.CartSteps;
import utils.WaitUtils;

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
}