package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;
import java.util.List;

public class CheckoutShippingAddressPage {

    private AndroidDriver driver;
    private WaitUtils waitUtils;

    private By fullNameField = By.id("com.saucelabs.mydemoapp.android:id/fullNameET");
    private By addressField = By.id("com.saucelabs.mydemoapp.android:id/address1ET");
    private By cityField = By.id("com.saucelabs.mydemoapp.android:id/cityET");
    private By zipCodeField = By.id("com.saucelabs.mydemoapp.android:id/zipET");
    private By countryFielld = By.id("com.saucelabs.mydemoapp.android:id/countryET");
    private By toPaymentButton = By.id("com.saucelabs.mydemoapp.android:id/paymentBtn");
    private List<By> mandatoryFieldErrors = List.of(
            By.id("com.saucelabs.mydemoapp.android:id/fullNameErrorTV"),
            By.id("com.saucelabs.mydemoapp.android:id/address1ErrorTV"),
            By.id("com.saucelabs.mydemoapp.android:id/cityErrorTV"),
            By.id("com.saucelabs.mydemoapp.android:id/zipErrorTV"),
            By.id("com.saucelabs.mydemoapp.android:id/countryErrorTV")
    );


    public CheckoutShippingAddressPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void clickPaymentButton() {
        waitUtils.scrollToText("To Payment");
        driver.findElement(toPaymentButton).click();
    }

    public boolean areAllMandatoryFieldErrorsDisplayed() {
        for (By error : mandatoryFieldErrors) {
            if (driver.findElements(error).isEmpty()) {
                return false;
            }
            if (!driver.findElement(error).isDisplayed()) {
                return false;
            }
        }
        return true;
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
