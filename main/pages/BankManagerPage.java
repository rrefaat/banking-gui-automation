package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class BankManagerPage {
    WebDriver driver;
    WebDriverWait wait;

    public BankManagerPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // Define WebElements separately
    private By bankManagerLoginBtn = By.cssSelector("button[ng-click='manager()']");
    private By addCustomerBtn = By.cssSelector("button[ng-click='addCust()']");
    private By firstNameInput = By.cssSelector("input[ng-model='fName']");
    private By lastNameInput = By.cssSelector("input[ng-model='lName']");
    private By postalCodeInput = By.cssSelector("input[ng-model='postCd']");
    private By submitBtn = By.cssSelector("button[type='submit']");

    // Methods to perform actions
    public void loginAsManager() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bankManagerLoginBtn)).click();
    }
    public void addCustomer(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addCustomerBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInput)).sendKeys(postalCode);
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn)).click();
    }

    public boolean verifyCustomerCreation() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        // Get alert text
        String alertText = alert.getText();
        System.out.println("Alert Message: " + alertText);
        // Check if the message contains expected text
        boolean isSuccess = alertText.contains("Customer added successfully with customer id");
        // Accept alert
        alert.accept();
        return isSuccess;
    }
}
