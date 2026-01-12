package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class CheckoutReviewOrderPage {
    private AndroidDriver driver;
    private WaitUtils waitUtils;

    private String amountId = "com.saucelabs.mydemoapp.android:id/amountTV"; // Delivery
    private String totalAmountId = "com.saucelabs.mydemoapp.android:id/totalAmountTV"; // Total Amount
    private String paymentBtnId = "com.saucelabs.mydemoapp.android:id/paymentBtn"; // Place Order

    public CheckoutReviewOrderPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    // find element delivery with scroll
    public String getShippingCost() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"" + amountId + "\"))"));
        return waitUtils.waitForElementVisible(By.id(amountId)).getText();
    }

    // get total amount
    public String getFinalTotal() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"" + totalAmountId + "\"))"));
        return waitUtils.waitForElementVisible(By.id(totalAmountId)).getText();
    }

    public void placeOrder() {
        waitUtils.waitForElementVisible(By.id(paymentBtnId)).click();
    }
}