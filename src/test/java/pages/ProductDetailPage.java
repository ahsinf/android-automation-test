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
    private String plusBtnIdStr = "com.saucelabs.mydemoapp.android:id/plusIV";
    private By plusBtn = By.id(plusBtnIdStr);
    private By quantityValue = By.id("com.saucelabs.mydemoapp.android:id/noTV");

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

    // Method for increasing quantity
    public void setQuantity(int targetQuantity) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"" + plusBtnIdStr + "\"))"));

        // Click plus (target - 1) times
        for (int i = 1; i < targetQuantity; i++) {
            waitUtils.waitForElementVisible(plusBtn).click();
        }

        // Validate quantity value
        String currentQty = waitUtils.waitForElementVisible(quantityValue).getText();
        if (Integer.parseInt(currentQty) != targetQuantity) {
            throw new RuntimeException("Failed to set quantity! Expectation: " + targetQuantity + " but on the screen: " + currentQty);
        }
    }
}