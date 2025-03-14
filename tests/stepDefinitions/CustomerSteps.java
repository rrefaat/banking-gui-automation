package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import config.setup;
import pages.*;
import utils.LoggerUtil;
import utils.TestDataUtil;

import java.time.Duration;
import java.util.List;

public class CustomerSteps {

    private final WebDriver driver = setup.getDriver();
    private final CustomerPage customerPage = new CustomerPage(driver);
    private final BankManagerPage bankManagerPage = new BankManagerPage(driver);
    private final LoginPage loginPage = new LoginPage(driver);
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private final List<String[]> testData = TestDataUtil.readCustomerData();

    @Given("I am logged in as a Bank Manager")
    public void loginAsBankManager() {
        LoggerUtil.log("Logging in as a Bank Manager...");
        bankManagerPage.loginAsManager();
    }

    @When("I add a new customer")
    public void addCustomerFromCSV() {
        for (String[] record : testData) {
            bankManagerPage.addCustomer(record[0], record[1], record[2]);
        }
    }

    @Then("I should see a success message confirming the customer was created")
    public void verifyCustomerCreation() {
        Assert.assertTrue("Customer creation failed!", bankManagerPage.verifyCustomerCreation());
    }

    @Given("I open an account with {string}")
    public void openAccountForCustomer(String currency) {
        for (String[] record : testData) {
            customerPage.openAccount(record[0] + " " + record[1], currency);
        }
    }

    @Then("I should see a confirmation that the account was successfully created")
    public void verifyAccountCreation() {
        Assert.assertTrue("Account creation failed!", customerPage.verifyAccountCreation());
    }

    @And("I navigate back to home")
    public void navigateBackToHome() {
        loginPage.navigateToHome();
    }

    @When("I log in as customer")
    public void loginAsCustomer() {
        for (String[] record : testData) {
            loginPage.loginAsCustomer(record[0] + " " + record[1]);
        }
    }

    @Then("I should see the correct account number displayed")
    public void verifyCorrectAccountNumberDisplayed() {
        Assert.assertTrue("Account number mismatch!", customerPage.verifyDisplayedAccountNumber());
    }

    @Given("I navigate to the Customers page")
    public void navigateToCustomersPage() {
        customerPage.clickCustomersButton();
    }

    @When("I search for a customer by first name {string}")
    public void searchCustomerByName(String firstName) {
        customerPage.searchCustomer(firstName);
    }

    @Then("I should see {string} details displayed")
    public void verifyCustomerFound(String firstName) {
        Assert.assertTrue("Customer not found!", customerPage.isCustomerFound(firstName));
    }

    @When("I click on the postcode column to sort ascending")
    public void sortCustomersAscending() {
        customerPage.sortByPostcodeColumn();
    }

    @Then("I should see the customers sorted in ascending order")
    public void verifyAscendingOrder() {
        Assert.assertTrue("Customers are not sorted in ascending order!", customerPage.isSortedAscending());
    }

    @When("I click on the postcode column to sort descending")
    public void sortCustomersDescending() {
        customerPage.sortByPostcodeColumn();
    }

    @Then("I should see the customers sorted in descending order")
    public void verifyDescendingOrder() {
        Assert.assertTrue("Customers are not sorted in descending order!", customerPage.isSortedDescending());
    }

    @Given("I search for the customer")
    public void searchForCustomer() {
        for (String[] record : testData) {
            customerPage.searchCustomer(record[0]);
            Assert.assertTrue("Customer not found!", customerPage.isCustomerFound(record[0]));
        }
    }

    @When("I delete the customer")
    public void deleteCustomer() {
        customerPage.deleteCustomer();
    }

    @Then("I should verify the customer no longer exists")
    public void verifyCustomerDeleted() {
        for (String[] record : testData) {
            customerPage.searchCustomer(record[0]);
            Assert.assertFalse("Customer was not deleted!", customerPage.isCustomerFound(record[0]));
        }
    }

    @Given("I am logged in as a customer {string}")
    public void loginAsCustomer(String customerName) {
        loginPage.loginAsCustomer(customerName);
    }

    @When("I make a deposit of {string}")
    public void makeDeposit(String amount) {
        customerPage.goToDepositTab();
        customerPage.makeDeposit(amount);
    }

    @Then("I should see a success message confirming the deposit")
    public void verifyDepositSuccess() {
        Assert.assertTrue("Deposit not successful!", customerPage.isDepositSuccess());
    }

    @And("I check the transactions history for the deposit")
    public void checkTransactionHistory() {
        customerPage.goToTransactions();
        customerPage.searchTransactions();
        Assert.assertTrue("Transaction not recorded!", customerPage.isTransactionRecorded());
    }
}
