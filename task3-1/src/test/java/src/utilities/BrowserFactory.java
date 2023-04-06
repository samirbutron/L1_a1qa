package src.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.io.IOException;

public class BrowserFactory {
    private static BrowserCapabilities browserCapabilities = new BrowserCapabilities();

    private BrowserFactory(){
    }
    public static WebDriver getDriver(String browser) throws IOException {
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = (ChromeOptions) browserCapabilities.getCaps(browser);
                WebDriver driverC = new ChromeDriver(options);
                return driverC;
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = (FirefoxOptions) browserCapabilities.getCaps(browser);
                WebDriver driverF = new FirefoxDriver();
                return driverF;
            }
            case default -> throw new IllegalArgumentException("Invalid browser name: " + browser);
        }
    }

    private static AbstractDriverOptions getCaps(String browserType) throws IOException {
        AbstractDriverOptions options = null;
        switch (browserType) {
            case "chrome" -> {
                ChromeCaps capabilitiesC = new ChromeCaps();
                options = capabilitiesC.getCaps();
            }
            case "firefox" -> {
                FirefoxCaps capabilitiesF = new FirefoxCaps();
                options = capabilitiesF.getCaps();
            }

            default -> throw new IllegalArgumentException("Invalid browser name:" + browserType);
        }
        return options;
    }
}
