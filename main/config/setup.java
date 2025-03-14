package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class setup {
    private static WebDriver driver;
    private static String baseURL;
    static {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("main/resources/config.properties");
            properties.load(fileInputStream);
            baseURL = properties.getProperty("baseURL");
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "tests/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(baseURL);
        }
        return driver;
    }

    public static void initializeDriver() {
        if (driver == null) {
            driver = getDriver();
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
