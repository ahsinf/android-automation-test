package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class ProductDetailPage {

    private AndroidDriver driver;
    private WaitUtils waitUtils;

    private By addToCartButton = By.id("com.saucelabs.mydemoapp.android:id/cartBt");

    public ProductDetailPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void addToCart() {
        waitUtils.scrollToText("Add to cart");
        waitUtils.waitForElementVisible(addToCartButton);

        driver.findElement(addToCartButton).click();
    }
}
