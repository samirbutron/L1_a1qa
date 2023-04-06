package src.utilities;

import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.IOException;

public class FirefoxCaps extends BrowserCapabilities {

    private final SettingsReader settingsReader = new SettingsReader();
    private final Util util = new Util();
    public FirefoxOptions getCaps() throws IOException {
        settingsReader.readFile();
        FirefoxOptions driverOptions = null;
        FirefoxOptions options = new FirefoxOptions();
        driverOptions = options.addArguments(util.listToString(settingsReader.getList("browserOptions")));
        return driverOptions;
    }
}
