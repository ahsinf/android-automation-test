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
    private ProductDetailPage productDetailPage = new ProductDetailPage(DriverManager.getDriver());
    private CartPage cartPage;
    private CheckoutReviewOrderPage checkoutReviewOrderPage;
    private double unitPrice;

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

    // --- SCENARIO : REMOVE ITEM ON CART ---
    @Given("user has {int} product in the cart page")
    public void userHasProductInCart(int count) {
        productListPage.addProductByName("Sauce Lab Back Packs");
        productListPage.openCart();
        cartPage = new CartPage(DriverManager.getDriver());
        assertThat(cartPage.getNumberOfItems()).isEqualTo(count);
    }

    @When("user clicks {string} for the product")
    public void userClicksRemove(String action) {
        cartPage.removeItem();
    }

    @Then("the item should be missing from the cart")
    public void itemShouldBeMissing() {
        int count = cartPage.getCartItemCount();
        assertThat(count).as("The Cart should be empty!").isEqualTo(0);
    }

    @And("the {string} button should be displayed")
    public void goShoppingButtonShouldBeDisplayed(String btnName) {
        boolean isDisplayed = cartPage.isGoShoppingButtonDisplayed();
        assertThat(isDisplayed).as("Go Shopping button does not appear!").isTrue();
    }

    // --- SCENARIO : ADD MORE THAN 1 PCS OF THE SAME PRODUCT ---
    @When("user selects {string}")
    public void userSelectsProduct(String productName) {
        // Go to product details
        productListPage.selectProductByName(productName);
        productDetailPage = new ProductDetailPage(DriverManager.getDriver());
        // Save unit price for later validation
        unitPrice = productDetailPage.getProductPrice();
    }

    @And("user increases the quantity to {int}")
    public void userIncreasesQuantity(int quantity) {
        productDetailPage.setQuantity(quantity);
    }

    @And("user add the product to cart")
    public void userAddTheProductToCart() {
        productDetailPage.addToCart();
    }

    @And("user click the cart page")
    public void userClickTheCartPage() {
        productListPage.openCart();
        cartPage = new CartPage(DriverManager.getDriver());
    }

    @Then("the cart should contain {int} items of {string}")
    public void verifyItemCountInCart(int expectedQty, String productName) {
        // Verify the total number of items (badges) or in the shopping cart list
        int actualQty = cartPage.getNumberOfItems();
        assertThat(actualQty).as("The number of items in the cart is incorrect!").isEqualTo(expectedQty);
    }

    @And("the total price should be correctly accumulated for {int} pcs")
    public void verifyAccumulatedPrice(int quantity) {
        double expectedTotal = unitPrice * quantity;
        double actualTotal = cartPage.getTotalPrice();

        System.out.println("DEBUG: Unit Price: " + unitPrice);
        System.out.println("DEBUG: Qty: " + quantity);
        System.out.println("DEBUG: Expected Total: " + expectedTotal);

        assertThat(actualTotal)
                .as("Total accumulated price is wrong!")
                .isEqualTo(expectedTotal);
    }
}