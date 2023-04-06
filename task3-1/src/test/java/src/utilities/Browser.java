package src.utilities;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Set;

public class Browser {

    private static Browser instance;
    public static WebDriver driver;

    private Browser() throws IOException {
        SettingsReader settingsReader = new SettingsReader();
        settingsReader.readFile();
        driver = BrowserFactory.getDriver(settingsReader.getString("browser"));
    }
    public static Browser getInstance() throws IOException {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    public WebDriver getDriver() throws IOException {
        if( driver == null){
            instance = new Browser();
        }
        return  driver;
    }

    public void goToUrl(String url){
        driver.get(url);
    }

    public static void quitInstance(){
        driver.quit();
        driver = null;
    }

    public Set<String> getWindowHandles(){
        return driver.getWindowHandles();
    }
}
