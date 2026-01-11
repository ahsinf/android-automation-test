Feature: Checkout - Negative Scenarios

  Background:
    Given user has added a product to cart
    And user has logged in successfully
    And user is on checkout information page

  Scenario: User cannot proceed checkout with empty mandatory shipping fields
    When user leaves mandatory shipping fields empty
    And user taps payment button
    Then error message should be displayed for mandatory fields
