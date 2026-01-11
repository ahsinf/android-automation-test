@checkout
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
