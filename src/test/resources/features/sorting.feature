Feature: Sort products by Price - Ascending

  Scenario: Sort products by Price Ascending
    Given user on the product list page
    When user selects sort by "Price - Ascending"
    Then the products should be sorted by price from low to high