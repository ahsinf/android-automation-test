package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class ProductDetailPage {
    private AndroidDriver driver;
    private WaitUtils waitUtils;

    private String cartBtId = "com.saucelabs.mydemoapp.android:id/cartBt";
    private String scrollToAddCartBtn = "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().resourceId(\"" + cartBtId + "\"))";
    private By priceLabel = AppiumBy.id("com.saucelabs.mydemoapp.android:id/priceTV");
    private By addToCartBtn = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt");

    public ProductDetailPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public Double getProductPrice() {
        String priceText = waitUtils.waitForElementVisible(priceLabel).getText();
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    }

    public void addToCart() {
        waitUtils.waitForElementVisible(priceLabel);
        driver.findElement(AppiumBy.androidUIAutomator(scrollToAddCartBtn));
        waitUtils.waitForElementVisible(addToCartBtn).click();
    }
}