package src.utilities;

import org.openqa.selenium.chrome.ChromeOptions;
import java.io.IOException;

public class ChromeCaps extends BrowserCapabilities {

    private final SettingsReader settingsReader = SettingsReader.getInstance();
    private Util util = new Util();
    public ChromeOptions getCaps() throws IOException {
        settingsReader.readFile();
        ChromeOptions driverOptions = null;
        ChromeOptions options = new ChromeOptions();
        driverOptions = options.addArguments(util.listToString(settingsReader.getList("browserOptions")));
        return driverOptions;
    }

}
