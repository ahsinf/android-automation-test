package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class CartPage {

    private AndroidDriver driver;
    private WaitUtils waitUtils;

    private By checkoutButton = By.id("com.saucelabs.mydemoapp.android:id/cartBt");

    public CartPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void proceedToCheckout() {
        waitUtils.waitForElementVisible(checkoutButton);

        driver.findElement(checkoutButton).click();
    }
}