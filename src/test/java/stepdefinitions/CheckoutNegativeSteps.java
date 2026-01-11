package stepdefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import pages.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutNegativeSteps {

    ProductListPage productListPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;
    LoginPage loginPage;
    CheckoutShippingAddressPage checkoutShippingAddressPage;

    @Given("user has added a product to cart")
    public void userHasAddedProductToCart() {
        productListPage = new ProductListPage(DriverManager.getDriver());
        productListPage.selectFirstProduct();

        productDetailPage = new ProductDetailPage(DriverManager.getDriver());
        productDetailPage.addToCart();
    }

    @Given("user has logged in successfully")
    public void userHasLoggedIn() {
        productListPage.openCart();

        cartPage = new CartPage(DriverManager.getDriver());
        cartPage.proceedToCheckout();

        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("username", "password");
    }

    @Given("user is on checkout information page")
    public void userIsOnCheckoutInfoPage() {
        // already there after login
        checkoutShippingAddressPage = new CheckoutShippingAddressPage(DriverManager.getDriver());
    }

    @When("user leaves mandatory shipping fields empty")
    public void userLeavesMandatoryFieldsEmpty() {
        // intentionally do nothing
    }

    @When("user taps payment button")
    public void userTapsContinueButton() {
        checkoutShippingAddressPage.clickPaymentButton();
    }

    @Then("error message should be displayed for mandatory fields")
    public void errorMessageShouldBeDisplayedForMandatoryFields() {
        assertThat(checkoutShippingAddressPage.areAllMandatoryFieldErrorsDisplayed())
                .as("All mandatory field error messages should be displayed")
                .isTrue();
    }

}
