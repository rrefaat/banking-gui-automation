Feature: Sort customers by Postal Code

  Scenario: Sort customers in ascending and descending order
    Given I am logged in as a Bank Manager
    And I navigate to the Customers page
    When I click on the postcode column to sort descending
    Then I should see the customers sorted in descending order
    When I click on the postcode column to sort ascending
    Then I should see the customers sorted in ascending order
