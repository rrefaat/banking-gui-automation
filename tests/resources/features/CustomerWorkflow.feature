Feature: Complete Customer Workflow

    @KeepSession
    Scenario: Add a new customer
        Given I am logged in as a Bank Manager
        When I add a new customer
        Then I should see a success message confirming the customer was created

    @KeepSession
    Scenario: Open a new account for an existing customer
        Given I open an account with "Dollar"
        Then I should see a confirmation that the account was successfully created
        And I navigate back to home
        When I log in as customer
        Then I should see the correct account number displayed

    @KeepSession
    Scenario: Make a deposit and verify transaction
        Given I navigate back to home
        And I log in as customer
        When I make a deposit of "1000"
        Then I should see a success message confirming the deposit
        And I check the transactions history for the deposit

    Scenario: Delete an existing customer
        Given I navigate back to home
        And I am logged in as a Bank Manager
        And I navigate to the Customers page
        And I search for the customer
        When I delete the customer
        Then I should verify the customer no longer exists
