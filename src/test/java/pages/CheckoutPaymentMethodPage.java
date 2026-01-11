package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class CheckoutPaymentMethodPage {

    private AndroidDriver driver;
    private WaitUtils waitUtils;

    private By fullNameField = By.id("com.saucelabs.mydemoapp.android:id/nameET");
    private By cardNumberField = By.id("com.saucelabs.mydemoapp.android:id/cardNumberET");
    private By expirationDateField = By.id("com.saucelabs.mydemoapp.android:id/expirationDateET");
    private By securityCodeField = By.id("com.saucelabs.mydemoapp.android:id/securityCodeET");
    private By reviewOrderButton = By.id("com.saucelabs.mydemoapp.android:id/paymentBtn");

    public CheckoutPaymentMethodPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void fillPaymentMethod() {
        waitUtils.waitForElementVisible(fullNameField);

        driver.findElement(fullNameField).sendKeys("Test User");
        driver.findElement(cardNumberField).sendKeys("123456789");
        driver.findElement(expirationDateField).sendKeys("1226");
        driver.findElement(securityCodeField).sendKeys("123");

        waitUtils.hideKeyboardIfShown();
        waitUtils.waitForElementClickable(reviewOrderButton);

        driver.findElement(reviewOrderButton).click();
    }
}
