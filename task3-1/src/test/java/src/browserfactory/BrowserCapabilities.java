package src.browserfactory;

import org.openqa.selenium.remote.AbstractDriverOptions;

import java.io.IOException;

/*FIXME Idealmente pienso que esta clase debería proveer un método que las clases hijas sobreescriban
entonces puede ser que esta implementacion este errada */
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
