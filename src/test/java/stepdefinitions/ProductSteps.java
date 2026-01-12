package stepdefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import pages.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductSteps {
    // Static list so that data is stored while the scenario is running
    public static List<Double> addedProductPrices = new ArrayList<>();

    private ProductListPage productListPage = new ProductListPage(DriverManager.getDriver());

    @Given("user on the product list page")
    public void userOnProductListPage() {
        // Ensure the "Products" header is visible as a sign of being in the catalog.
        assertThat(productListPage.isProductListDisplayed())
                .as("User is not on the product page!")
                .isTrue();

        // Reset price list if needed for new scenario
        addedProductPrices.clear();
    }

    @When("user selects sort by {string}")
    public void userSelectsSortBy(String sortOption) {
        productListPage.openSortMenu();

        // Dynamic selection logic based on sortOption text
        if (sortOption.equalsIgnoreCase("Price - Ascending")) {
            productListPage.selectSortByPriceAscending();
        } else {
            // else-if for sorting another option
            System.out.println("Sort option '" + sortOption + "' not implemented yet.");
        }
    }

    @Then("the products should be sorted by price from low to high")
    public void verifyPriceSortingAscending() {
        // Get price list from app after sorting
        List<Double> actualPrices = productListPage.getAllProductPrices();

        // Make a copy of the list and then sort it manually in Java.
        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        Collections.sort(sortedPrices);

        System.out.println("DEBUG: Price on Screen: " + actualPrices);
        System.out.println("DEBUG: Price Actually: " + sortedPrices);

        // Compare native List vs Java sorted List
        assertThat(actualPrices)
                .as("Product price order is incorrect (Ascending)!")
                .isEqualTo(sortedPrices);
    }
}
