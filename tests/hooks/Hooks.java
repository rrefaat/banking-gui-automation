package hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import config.setup;
import utils.LoggerUtil;

public class Hooks {
    private static boolean isFeatureStarted = false; // Variable to track if the Feature has started

    @Before
    public void setup(Scenario scenario) {
        if (!isFeatureStarted) {
            LoggerUtil.log("Setting up WebDriver before first scenario...");
            setup.initializeDriver();
            isFeatureStarted = true; // Set variable when the first scenario runs
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@KeepSession")) {
            LoggerUtil.log("Keeping session open for: " + scenario.getName());
        } else {
            LoggerUtil.log("Closing WebDriver after test: " + scenario.getName());
            setup.quitDriver();
            isFeatureStarted = false; // Reset variable
        }
    }
}
