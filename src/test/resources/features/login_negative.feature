Feature: Login - Negative Scenarios

  Background:
    Given user has added a product to cart
    And user is on the login page via cart checkout

  Scenario: User cannot login with empty username
    When user leaves username field empty
    And user enters password "secret_sauce"
    And user taps login button
    Then error message "Username is required" should be displayed

  Scenario: User cannot login with empty password
    When user enters username "testing@example.com"
    And user leaves password field empty
    And user taps login button
    Then error message "Enter Password" should be displayed