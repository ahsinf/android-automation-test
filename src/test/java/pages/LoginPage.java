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

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void login(String username, String password) {
        waitUtils.waitForElementVisible(usernameField);

        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}