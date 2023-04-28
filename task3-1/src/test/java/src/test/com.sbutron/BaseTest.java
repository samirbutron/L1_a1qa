package src.test.com.sbutron;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import src.browserfactory.BrowserFactory;
import src.main.MyLogger;
import src.utilities.BrowserUtilities;
import src.utilities.SettingsReader;

public abstract class BaseTest {
    private  final SettingsReader testconfig = new SettingsReader("src/test/java/src/config/testconfig.json");

    @BeforeClass
    public void setWebpage() {
        MyLogger.info("STARTING TEST");
        MyLogger.info("Go to the main page: "+ testconfig.getString("url"));
        BrowserUtilities.goToUrl(testconfig.getString("url"));
    }
    @AfterClass
    public void tearDown() {
        MyLogger.info("End of the test - Browser Quit");
        BrowserFactory.quitInstance();
    }
}
