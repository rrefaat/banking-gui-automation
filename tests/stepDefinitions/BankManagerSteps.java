package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.BankManagerPage;
import utils.LoggerUtil;
import config.setup;
import utils.TestDataUtil;

import java.util.List;

public class BankManagerSteps {
    WebDriver driver = setup.getDriver();
    private BankManagerPage bankManagerPage = new BankManagerPage(driver);

}
