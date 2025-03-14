package utils;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginHelper {
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginHelper(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void loginAsBankManager() {
        LoggerUtil.log("Logging in as Bank Manager...");
        loginPage.loginAsManager();
    }

    public void loginAsCustomer(String customerName) {
        LoggerUtil.log("Logging in as customer: " + customerName);
        loginPage.loginAsCustomer(customerName);
    }
}
