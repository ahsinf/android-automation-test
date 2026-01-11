package stepdefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import pages.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutSteps {

    ProductListPage productListPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;
    LoginPage loginPage;
    CheckoutShippingAddressPage checkoutShippingAddressPage;
    CheckoutPaymentMethodPage checkoutPaymentMethodPage;
    ReviewOrderPage reviewOrderPage;

    @Given("user is on product list page")
    public void userIsOnProductListPage() {
        productListPage = new ProductListPage(DriverManager.getDriver());
    }

    @When("user selects a product")
    public void userSelectsAProduct() {
        productListPage.selectFirstProduct();
        productDetailPage = new ProductDetailPage(DriverManager.getDriver());
    }

    @When("user adds the product to cart")
    public void userAddsTheProductToCart() {
        productDetailPage.addToCart();
    }

    @When("user proceeds to checkout")
    public void userProceedsToCheckout() {
        productListPage.openCart();
        cartPage = new CartPage(DriverManager.getDriver());
        cartPage.proceedToCheckout();
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @When("user completes login with valid credentials")
    public void userCompletesLogin() {
        loginPage.login("username", "password");
        checkoutShippingAddressPage = new CheckoutShippingAddressPage(DriverManager.getDriver());
    }

    @When("user fills shipping address with valid data")
    public void userFillsShippingAddress() {
        if (checkoutShippingAddressPage == null) {
            checkoutShippingAddressPage = new CheckoutShippingAddressPage(DriverManager.getDriver());
        }
        checkoutShippingAddressPage.fillShippingAddress();
        checkoutPaymentMethodPage = new CheckoutPaymentMethodPage(DriverManager.getDriver());
    }

    @When("user selects payment method")
    public void userEnterPaymentMethod() {
        checkoutPaymentMethodPage.fillPaymentMethod();
    }

    @When("user reviews and places the order")
    public void userPlacesTheOrder() {
        reviewOrderPage = new ReviewOrderPage(DriverManager.getDriver());
        reviewOrderPage.placeOrder();
    }

    @Then("order should be placed successfully")
    public void orderShouldBePlacedSuccessfully() {
        assertThat(reviewOrderPage.isOrderSuccessful())
                .as("Order should be completed successfully")
                .isTrue();
    }
}
