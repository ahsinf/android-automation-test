package stepdefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import pages.*;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CartSteps {

    // Static list so that data is stored while the scenario is running
    public static List<Double> addedProductPrices = new ArrayList<>();

    private ProductListPage productListPage = new ProductListPage(DriverManager.getDriver());
    private CartPage cartPage;
    private CheckoutReviewOrderPage checkoutReviewOrderPage;

    @Given("user is on the product list page")
    public void userIsOnProductListPage() {
        // Reset price list every time a new scenario starts
        addedProductPrices.clear();
    }

    @When("user adds {string} to cart")
    public void userAddsProductToCart(String productName) {
        // This method now handles Click -> Get Price -> Add -> Back
        productListPage.addProductByName(productName);
    }

    @And("user opens the cart page")
    public void userOpensTheCartPage() {
        productListPage.openCart();
        cartPage = new CartPage(DriverManager.getDriver());
    }

    @Then("the cart should contain {int} items")
    public void theCartShouldContainItems(int expectedCount) {
        assertThat(cartPage.getNumberOfItems())
                .as("The number of items in the cart is incorrect!")
                .isEqualTo(expectedCount);
    }

    @And("the total price should be the sum of both products")
    public void verifyTotalPrice() {
        double expectedTotal = 0;
        for (Double price : addedProductPrices) {
            expectedTotal += price;
        }

        double actualTotal = cartPage.getTotalPrice();

        System.out.println("DEBUG: Expected Total: " + expectedTotal);
        System.out.println("DEBUG: Actual Total: " + actualTotal);

        assertThat(actualTotal)
                .as("Total price does not match!")
                .isEqualTo(expectedTotal);
    }

    @And("user proceeds to checkout multiple product")
    public void proceedToCheckoutMultipleProduct() {
        cartPage.proceedToCheckout();
    }

    @And("user login with credential valid")
    public void userLoginValid(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("username", "password");
    }

    @And("user enters shipping information")
    public void enterShippingInfo() {
        CheckoutShippingAddressPage shippingPage = new CheckoutShippingAddressPage(DriverManager.getDriver());
        shippingPage.fillShippingAddress();
    }

    @And("user enters payment details")
    public void enterPaymentDetails() {
        CheckoutPaymentMethodPage paymentPage = new CheckoutPaymentMethodPage(DriverManager.getDriver());
        paymentPage.fillPaymentMethod();
    }

    @And("user reviews the order with {string}")
    public void reviewOrder(String deliveryMethod) {
        checkoutReviewOrderPage = new CheckoutReviewOrderPage(DriverManager.getDriver());
        String actualMethod = checkoutReviewOrderPage.getShippingCost();
        System.out.println("Shipping Method Found: " + actualMethod);
    }

    @Then("the final total price should be {string}")
    public void verifyFinalTotal(String expectedTotal) {
        String actualTotal = checkoutReviewOrderPage.getFinalTotal();
        System.out.println("Final Check: " + actualTotal + " (Expected: " + expectedTotal + ")");
        assertThat(actualTotal).isEqualTo(expectedTotal);
    }

    @And("user completes the order")
    public void completesOrder() {
        checkoutReviewOrderPage.placeOrder();
    }
}