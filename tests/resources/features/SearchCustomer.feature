Feature: Search for an existing customer

  Scenario: Search for a customer by first name
    Given I am logged in as a Bank Manager
    And I navigate to the Customers page
    When I search for a customer by first name "Hermoine"
    Then I should see "Hermoine" details displayed
