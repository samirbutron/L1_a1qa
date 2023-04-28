package src.browserfactory;
import org.openqa.selenium.chrome.ChromeOptions;
import src.utilities.SettingsReader;
import src.utilities.Util;

public class ChromeCaps extends BrowserCapabilities {

    private final SettingsReader settingsReader = new SettingsReader("src/test/java/src/config/config.json");

    public ChromeCaps() {
    }

    public ChromeOptions getCaps() {
        ChromeOptions driverOptions;
        ChromeOptions options = new ChromeOptions();
        driverOptions = options.addArguments(Util.listToString(settingsReader.getList("browserOptions")));
        return driverOptions;
    }

}
