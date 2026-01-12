package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitUtils;

public class LoginPage {

    private AndroidDriver driver;
    private WaitUtils waitUtils;

    private By usernameField = By.id("com.saucelabs.mydemoapp.android:id/nameET");
    private By passwordField = By.id("com.saucelabs.mydemoapp.android:id/passwordET");
    private By loginButton = By.id("com.saucelabs.mydemoapp.android:id/loginBtn");
    private By usernameError = By.id("com.saucelabs.mydemoapp.android:id/nameErrorTV");
    private By passwordError = By.id("com.saucelabs.mydemoapp.android:id/passwordErrorTV");
    private By genericError = By.xpath("//android.view.ViewGroup[contains(@content-desc, 'error-message')]//android.widget.TextView");

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void enterUsername(String username) {
        waitUtils.waitForElementVisible(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        waitUtils.waitForElementVisible(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        waitUtils.waitForElementVisible(loginButton).click();
    }

    public String getUsernameErrorMessage() {
        return waitUtils.waitForElementVisible(usernameError).getText();
    }

    public String getPasswordErrorMessage() {
        return waitUtils.waitForElementVisible(passwordError).getText();
    }

    public String getErrorMessage() {
        return waitUtils.waitForElementVisible(genericError).getText();
    }

    public void login(String username, String password) {
        waitUtils.waitForElementVisible(usernameField);

        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}