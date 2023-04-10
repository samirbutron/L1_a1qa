package src.browserfactory;

import org.openqa.selenium.firefox.FirefoxOptions;
import src.utilities.SettingsReader;
import src.utilities.Util;

import java.io.IOException;

public class FirefoxCaps extends BrowserCapabilities {

    private final SettingsReader settingsReader = new SettingsReader("src/test/java/src/config/config.json");
    private final Util util = new Util();

    public FirefoxCaps() throws IOException {
    }

    public FirefoxOptions getCaps() throws IOException {
        FirefoxOptions driverOptions = null;
        FirefoxOptions options = new FirefoxOptions();
        driverOptions = options.addArguments(settingsReader.getString("browserOptions"));
        return driverOptions;
    }
}
