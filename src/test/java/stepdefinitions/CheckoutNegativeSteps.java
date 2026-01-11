package stepdefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import io.cucumber.java.en_scouse.An;
import pages.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutNegativeSteps {

    ProductListPage productListPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;
    LoginPage loginPage;
    CheckoutShippingAddressPage checkoutShippingAddressPage;
    CheckoutPaymentMethodPage checkoutPaymentMethodPage;

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

    // --- SCENARIO : SHIPPING NEGATIVE ---
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
                .as("The error message on the payment method does not appear!")
                .isTrue();
    }

    // --- SCENARIO 2: PAYMENT NEGATIVE ---
    @And("user is on payment method page")
    public void userIsOnPaymentMethodPage() {
        // Initialize payment object page
        checkoutPaymentMethodPage = new CheckoutPaymentMethodPage(DriverManager.getDriver());
    }

    @When("user leaves payment fields empty")
    public void userLeavesPaymentFieldsEmpty() {
        // Intentionally do nothing
    }

    @And("user taps review order button")
    public void userTapsReviewOrderButton() {
        checkoutPaymentMethodPage.clickReviewOrderButton();
    }

    @Then("error message should be displayed for payment fields")
    public void errorMessageShouldBeDisplayedForPaymentFields() {
        assertThat(checkoutPaymentMethodPage.areAllMandatoryFieldErrorsDisplayed())
                .as("The error message on the payment method does not appear!")
                .isTrue();
    }

}
