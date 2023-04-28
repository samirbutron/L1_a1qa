package src.browserfactory;

import org.openqa.selenium.remote.AbstractDriverOptions;

public class BrowserCapabilities {
    private static final String CHROME_BROWSER = "chrome";
    private static final String FIREFOX_BROWSER = "firefox";
    public static AbstractDriverOptions getCaps(String browserType) {
        AbstractDriverOptions options;
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
