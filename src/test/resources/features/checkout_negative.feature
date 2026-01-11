Feature: Checkout - Negative Scenarios

  Background:
    Given user has added a product to cart
    And user has logged in successfully
    And user is on checkout information page

  Scenario: User cannot proceed checkout with empty mandatory shipping fields
    When user leaves mandatory shipping fields empty
    And user taps payment button
    Then error message should be displayed for mandatory fields

  Scenario: User cannot proceed to review order with empty mandatory payment fields
    And user fills shipping address with valid data
    And user is on payment method page
    When user leaves payment fields empty
    And user taps review order button
    Then error message should be displayed for payment fields
