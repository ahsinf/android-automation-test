package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class WaitUtils {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public WaitUtils(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // --- Helper Wait ---
    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // --- Helper Keyboard ---
    public void hideKeyboardIfShown() {
        if (driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
    }

    // --- Helper Scroll ---
    public void scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
        ));
    }

    // Record the Video
    public void startRecording() {
        ((CanRecordScreen) driver).startRecordingScreen();
    }

    public String stopRecording() {
        return ((CanRecordScreen) driver).stopRecordingScreen();
    }
}