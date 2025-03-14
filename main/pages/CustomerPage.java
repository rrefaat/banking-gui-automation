package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerUtil;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class CustomerPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators
    private By openAccountBtn = By.cssSelector("button[ng-click='openAccount()']");
    private By customerDropdown = By.cssSelector("select[ng-model='custId']");
    private By currencyDropdown = By.cssSelector("select[ng-model='currency']");
    private By processBtn = By.cssSelector("button[type='submit']");
    private By displayedAccountNumber = By.xpath("//div[@ng-hide='noAccount']//strong[@class='ng-binding']");
    private By customersButton = By.xpath("//button[contains(text(),'Customers')]");
    private By searchField = By.xpath("//input[@ng-model='searchCustomer']");
    private By customerTableRows = By.xpath("//table//tr");
    private By columnPostCode = By.xpath("//a[contains(text(),'Post Code')]");
    private By deleteCustomerButton = By.xpath("//button[text()='Delete']");
    private By depositTab = By.xpath("//button[contains(text(),'Deposit')]");
    private By depositAmountInput = By.cssSelector("input[ng-model='amount']");
    private By depositSubmitButton = By.xpath("//button[text()='Deposit']");
    private By successMessage = By.xpath("//span[@ng-show='message']");
    private By transactionsTab = By.cssSelector("button[ng-click='transactions()']");
    private By startDateInput = By.id("start");
    private By endDateInput = By.id("end");
    private By transactionTable = By.cssSelector("table tbody tr");

    public void openAccount(String firstName, String currency) {
        wait.until(ExpectedConditions.elementToBeClickable(openAccountBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(customerDropdown)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(currencyDropdown)).sendKeys(currency);
        wait.until(ExpectedConditions.elementToBeClickable(processBtn)).click();
    }

    public boolean verifyAccountCreation() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        LoggerUtil.log("Alert Message: " + alertText);
        alert.accept();

        boolean isSuccess = alertText.contains("Account created successfully with account Number");
        if (isSuccess) {
            saveCustomerIdToFile(alertText.replaceAll("\\D+", ""));
        }
        return isSuccess;
    }

    private void saveCustomerIdToFile(String customerId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("main/resources/testData/customerAccountNumber.txt", true))) {
            writer.write("Customer ID: " + customerId);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyDisplayedAccountNumber() {
        WebElement accountNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(displayedAccountNumber));
        return getLastSavedAccountNumber().contains(accountNumberElement.getText().trim());
    }

    private String getLastSavedAccountNumber() {
        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader("main/resources/testData/customerAccountNumber.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine.trim();
    }

    public void searchCustomer(String customerName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(customerName);
    }

    public boolean isCustomerFound(String customerName) {
        return driver.findElements(customerTableRows).stream().anyMatch(row -> row.getText().contains(customerName));
    }

    public void sortByPostcodeColumn() {
        wait.until(ExpectedConditions.elementToBeClickable(columnPostCode)).click();
    }

    public boolean isSortedAscending() {
        return isSorted(true);
    }

    public boolean isSortedDescending() {
        return isSorted(false);
    }

    public boolean isSorted(boolean ascending) {
        List<String> postalCodes = driver.findElements(By.xpath("//table//tr/td[3]")).stream()
                .skip(1) // Skip the first row (header)
                .map(WebElement::getText)
                .collect(Collectors.toList());

        if (postalCodes.isEmpty()) {
            System.out.println("No postal codes found.");
            return false;
        }

        List<String> sortedPostalCodes = new ArrayList<>(postalCodes);
        if (ascending) {
            Collections.sort(sortedPostalCodes);
        } else {
            sortedPostalCodes.sort(Collections.reverseOrder());
        }

        boolean result = postalCodes.equals(sortedPostalCodes);
        if (!result) {
            System.out.println("Sorting failed. Expected: " + sortedPostalCodes + " | Found: " + postalCodes);
        }
        return result;
    }

    public void deleteCustomer() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteCustomerButton)).click();
    }

    public void makeDeposit(String amount) {
        wait.until(ExpectedConditions.elementToBeClickable(depositTab)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(depositAmountInput)).sendKeys(amount);
        driver.findElement(depositSubmitButton).click();
    }

    public boolean isDepositSuccess() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText().trim().equals("Deposit Successful");
    }

    public void goToTransactions() {
        wait.until(ExpectedConditions.elementToBeClickable(transactionsTab)).click();
    }

    public void searchTransactions() {
        setDateUsingJavaScript("start", LocalDate.now().minusDays(1).toString());
        setDateUsingJavaScript("end", LocalDate.now().toString());
    }

    private void setDateUsingJavaScript(String elementId, String dateValue) {
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
        jsExecutor.executeScript("arguments[0].value = arguments[1];", dateInput, dateValue);
    }

    public boolean isTransactionRecorded() {
        String todayDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
        String xpathExpression = "//tr[td[contains(text(),'" + todayDate + "')]]";
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            return !driver.findElements(By.xpath(xpathExpression)).isEmpty();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
