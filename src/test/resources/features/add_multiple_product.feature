Feature: Add Multiple Product and Checkout

  Scenario: Add multiple products and complete checkout process
    Given user is on the product list page
    When user adds "Sauce Lab Back Packs" to cart
    And user adds "Sauce Lab Fleece T-Shirt" to cart
    And user opens the cart page
    And user proceeds to checkout multiple product
    And user login with credential valid
    And user enters shipping information
    And user enters payment details
    And user reviews the order with "DHL Standard Delivery"
    Then the final total price should be "$ 85.97"
    And user completes the order