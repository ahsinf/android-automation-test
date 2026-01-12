package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class CartPage {

    private AndroidDriver driver;
    private WaitUtils waitUtils;

    private By checkoutButton = By.id("com.saucelabs.mydemoapp.android:id/cartBt");
    private By cartItemCount = By.id("com.saucelabs.mydemoapp.android:id/itemsTV");
    private By totalPriceText = By.id("com.saucelabs.mydemoapp.android:id/totalPriceTV");

    public CartPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void proceedToCheckout() {
        waitUtils.waitForElementVisible(checkoutButton);

        driver.findElement(checkoutButton).click();
    }

    public int getNumberOfItems() {
        String countText = waitUtils.waitForElementVisible(cartItemCount).getText();
        return Integer.parseInt(countText.replaceAll("[^0-9]", "")); // Just take the numbers
    }

    public double getTotalPrice() {
        String priceText = waitUtils.waitForElementVisible(totalPriceText).getText();
        return Double.parseDouble(priceText.replace("$", "")); // Remove the $ symbol and change it to double
    }
}