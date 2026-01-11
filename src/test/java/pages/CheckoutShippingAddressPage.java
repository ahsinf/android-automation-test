package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class CheckoutShippingAddressPage {

    private AndroidDriver driver;
    private WaitUtils waitUtils;

    private By fullNameField = By.id("com.saucelabs.mydemoapp.android:id/fullNameET");
    private By addressField = By.id("com.saucelabs.mydemoapp.android:id/address1ET");
    private By cityField = By.id("com.saucelabs.mydemoapp.android:id/cityET");
    private By zipCodeField = By.id("com.saucelabs.mydemoapp.android:id/zipET");
    private By countryFielld = By.id("com.saucelabs.mydemoapp.android:id/countryET");
    private By toPaymentButton = By.id("com.saucelabs.mydemoapp.android:id/paymentBtn");

    public CheckoutShippingAddressPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void fillShippingAddress() {
        waitUtils.waitForElementVisible(fullNameField);

        driver.findElement(fullNameField).sendKeys("Test User");
        driver.findElement(addressField).sendKeys("Test Address");
        driver.findElement(cityField).sendKeys("Jakarta");
        driver.findElement(zipCodeField).sendKeys("12345");
        driver.findElement(countryFielld).sendKeys("Indonesia");

        waitUtils.hideKeyboardIfShown();
        waitUtils.scrollToText("To Payment");
        waitUtils.waitForElementClickable(toPaymentButton);

        driver.findElement(toPaymentButton).click();
    }
}
