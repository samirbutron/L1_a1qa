package src.browserfactory;

import org.openqa.selenium.chrome.ChromeOptions;
import src.utilities.SettingsReader;
import src.utilities.Util;

import java.io.IOException;

public class ChromeCaps extends BrowserCapabilities {

    private final SettingsReader settingsReader = new SettingsReader("src/test/src/config/config.json");
    private Util util = new Util();

    public ChromeCaps() throws IOException {
    }

    public ChromeOptions getCaps() throws IOException {
        ChromeOptions driverOptions = null;
        ChromeOptions options = new ChromeOptions();
        driverOptions = options.addArguments(util.listToString(settingsReader.getList("browserOptions")));
        return driverOptions;
    }

}
