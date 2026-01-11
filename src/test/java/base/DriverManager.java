package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;

public class DriverManager {

    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName("Android Device")
                    .setUdid("21f067d73b017ece")
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .setAppPackage("com.saucelabs.mydemoapp.android")
                    .setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity")
                    .setNoReset(false)
                    .setFullReset(false)

                    .setAppWaitActivity("com.saucelabs.mydemoapp.android.view.activities.MainActivity");

            try {
                driver = new AndroidDriver(
                        new URL("http://127.0.0.1:4723"),
                        options
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}