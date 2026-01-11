package stepdefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import pages.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    ProductListPage productListPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;
    LoginPage loginPage;

    @Given("user is on the login page via cart checkout")
    public void userIsOnLoginPageViaCart() {
        productListPage = new ProductListPage(DriverManager.getDriver());
        productListPage.openCart();

        cartPage = new CartPage(DriverManager.getDriver());
        cartPage.proceedToCheckout();

        // Initialize LoginPage after click continue checkout
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @When("user leaves username field empty")
    public void userLeavesUsernameEmpty() {
        loginPage.enterUsername("");
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("user leaves password field empty")
    public void userLeavesPasswordEmpty() {
        loginPage.enterPassword("");
    }

    @And("user taps login button")
    public void userTapsLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("error message {string} should be displayed")
    public void errorMessageShouldBeDisplayed(String expectedMessage) {
        String actualMessage = "";
        if (expectedMessage.toLowerCase().contains("username")) {
            actualMessage = loginPage.getUsernameErrorMessage();
        } else if (expectedMessage.toLowerCase().contains("password")) {
            actualMessage = loginPage.getPasswordErrorMessage();
        }

        assertThat(actualMessage)
                .as("The error message that appears on the screen does not match expectations!")
                .isEqualTo(expectedMessage);
    }
}