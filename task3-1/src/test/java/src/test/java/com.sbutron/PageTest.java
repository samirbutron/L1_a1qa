package src.test.java.com.sbutron;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import src.utilities.Browser;
import src.utilities.SettingsReader;

import java.io.IOException;

public class PageTest {
    private Browser browser = new Browser();
    private WebDriver driver;
    private final SettingsReader settingsReader = new SettingsReader();

    @BeforeTest
    public void setup() throws IOException {
        settingsReader.readFile();
        browser = Browser.getInstance();
    }

    @AfterTest
    public void teardown(){
        Browser.quitInstance();
    }

    @Test
    public void PrivacyPolicy() throws IOException {
        browser.goToUrl("https://demoqa.com");
        browser.getDriver();


    }
}
