package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.time.Instant;

public class LoginPage {
    private WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By bankManagerLoginButton = By.cssSelector("button[ng-click='manager()']");
    private By customerLoginButton = By.cssSelector("button[ng-click='customer()']");
    private By customerDropdown = By.id("userSelect");
    private By loginButton = By.xpath("//button[contains(text(),'Login')]");
    private By homeButton = By.cssSelector("button[ng-click='home()']");

    public void loginAsManager() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bankManagerLoginButton)).click();
    }

    public void loginAsCustomer(String customerName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(customerLoginButton)).click();
        // Locate the dropdown and select the customer by visible text
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(customerDropdown));

        // Select customer from dropdown
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(customerName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
    }
    public void navigateToHome(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeButton)).click();
    }
}
