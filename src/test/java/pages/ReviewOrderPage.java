package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class ReviewOrderPage {

    private AndroidDriver driver;
    private WaitUtils waitUtils;

    private By placeOrderButton = By.id("com.saucelabs.mydemoapp.android:id/paymentBtn");
    private By successMessage = By.id("com.saucelabs.mydemoapp.android:id/completeTV");

    public ReviewOrderPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void placeOrder() {
        waitUtils.waitForElementVisible(placeOrderButton);
        driver.findElement(placeOrderButton).click();
    }

    public boolean isOrderSuccessful() {
        try {
            waitUtils.waitForElementVisible(successMessage);
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}