package src.browserfactory;

import org.openqa.selenium.firefox.FirefoxOptions;
import src.utilities.SettingsReader;
import src.utilities.Util;

public class FirefoxCaps extends BrowserCapabilities {
    private final SettingsReader settingsReader = new SettingsReader("src/test/java/src/config/config.json");
    public FirefoxCaps() {
    }

    public FirefoxOptions getCaps() {
        FirefoxOptions driverOptions;
        FirefoxOptions options = new FirefoxOptions();
        driverOptions = options.addArguments(settingsReader.getString("browserOptions"));
        return driverOptions;
    }
}
