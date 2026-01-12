Feature: Checkout Product

  As a user
  I want to purchase a product
  So that I can complete my order successfully

  Scenario: End-to-End Happy Path Checkout Product
    Given user is on product list page
    When user selects a product
    And user adds the product to cart
    And user proceeds to checkout
    And user completes login with valid credentials
    And user fills shipping address with valid data
    And user selects payment method
    And user reviews and places the order
    Then order should be placed successfully

  Scenario: Remove Item from Cart
    Given user has 1 product in the cart page
    When user clicks "Remove Item" for the product
    Then the item should be missing from the cart
    And the "Go Shopping" button should be displayed

  Scenario: Add more than 1 pcs of the same product and verify total price
    Given user is on the product list page
    When user selects "Sauce Lab Back Packs"
    And user increases the quantity to 3
    And user add the product to cart
    And user click the cart page
    Then the cart should contain 3 items of "Sauce Lab Back Packs"
    And the total price should be correctly accumulated for 3 pcs
