package base;

import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

    @Before
    public void setUp() {
        AndroidDriver driver = DriverManager.getDriver();
        // Start recording before the scenario starts
        ((CanRecordScreen) DriverManager.getDriver()).startRecordingScreen();
    }

    @After
    public void tearDown(Scenario scenario) {
        String video = ((CanRecordScreen) DriverManager.getDriver()).stopRecordingScreen();

        // Save Video to Target Folder
        try {
            byte[] decodeVideo = Base64.getDecoder().decode(video);
            Path testVideoDir = Paths.get("target/videos");
            Files.createDirectories(testVideoDir);
            Path videoPath = testVideoDir.resolve(scenario.getName().replace(" ", "_") + ".mp4");
            Files.write(videoPath, decodeVideo);
        } catch (Exception e) {
            System.out.println("Failed to save video: " + e.getMessage());
        }

        // Screenshot
        final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.isFailed() ? "Failed Evidence" : "Success Evidence");

        DriverManager.quitDriver();
    }
}
