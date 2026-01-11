package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProductListPage {

    private AndroidDriver driver;

    private By firstProduct = By.xpath("//android.widget.ImageView[@content-desc=\"Sauce Lab Back Packs\"]");
    private By cartIcon = By.id("com.saucelabs.mydemoapp.android:id/cartIV");

    public ProductListPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void selectFirstProduct() {
        driver.findElement(firstProduct).click();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }
}