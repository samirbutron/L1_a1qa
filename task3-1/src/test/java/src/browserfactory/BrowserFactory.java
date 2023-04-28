package src.browserfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import src.utilities.SettingsReader;

import java.io.IOException;

public class BrowserFactory {
    static SettingsReader settingsReader = new SettingsReader("src/test/java/src/config/config.json");
    private static WebDriver driverInstance;
    private BrowserFactory(){
        switch (settingsReader.getString("browser")) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = (ChromeOptions) BrowserCapabilities.getCaps(settingsReader.getString("browser"));
                driverInstance = new ChromeDriver(options);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = (FirefoxOptions) BrowserCapabilities.getCaps(settingsReader.getString("browser"));
                driverInstance = new FirefoxDriver(options);
            }
            case default -> throw new IllegalArgumentException("Invalid browser name: " + settingsReader.getString("browser"));
        }
    }
    public static WebDriver getDriver() {
        if(driverInstance == null ) {
            new BrowserFactory();
        }
        return driverInstance;
    }

    public static void quitInstance(){
        driverInstance.quit();
    }

    private static AbstractDriverOptions getCaps(String browserType) {
        AbstractDriverOptions options;
        switch (browserType) {
            case "chrome" -> {
                ChromeCaps capabilitiesC = new ChromeCaps();
                options = capabilitiesC.getCaps();
                return options;
            }
            case "firefox" -> {
                FirefoxCaps capabilitiesF = new FirefoxCaps();
                options = capabilitiesF.getCaps();
                return options;
            }

            default -> throw new IllegalArgumentException("Invalid browser name:" + browserType);
        }
    }
}
