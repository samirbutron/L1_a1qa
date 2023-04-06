package src.utilities;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.io.IOException;

public class BrowserCapabilities {
    private static final String CHROME_BROWSER = "chrome";
    private static final String FIREFOX_BROWSER = "firefox";
    public AbstractDriverOptions getCaps(String browserType) throws IOException {
        AbstractDriverOptions options = null;
        switch (browserType) {
            case CHROME_BROWSER -> {
                ChromeCaps capabilitiesC = new ChromeCaps();
                options = capabilitiesC.getCaps();
            }
            case FIREFOX_BROWSER -> {
                FirefoxCaps capabilitiesF = new FirefoxCaps();
                options = capabilitiesF.getCaps();
            }

            default -> throw new IllegalArgumentException("Invalid browser name:" + browserType);
        }
        return options;
    }

}
