package src.test.java.com.sbutron;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import src.browserfactory.Browser;
import src.main.PageObject;
import src.utilities.SettingsReader;

import java.io.IOException;

public class PageTest {
    private Browser browser;
    private final SettingsReader config = new SettingsReader("src/test/java/src/config/config.json");
    private  final SettingsReader testconfig = new SettingsReader("src/test/java/src/config/testconfig.json");
    //FIXME aqui se agregaran los readers para los otros .json
    private PageObject po;

    public PageTest() throws IOException {
    }

    @BeforeTest
    public void setup() throws IOException {
        browser = Browser.getInstance();
        po = new PageObject();
        //FIXME instanciar otras clases creadas... Util, WaitsUtil
    }

    @AfterTest
    public void teardown(){
        Browser.quitInstance();
    }

    @Test
    public void PrivacyPolicy() throws IOException {
        System.out.println(config.getString("browserOptions"));
        browser.goToUrl(testconfig.getString("url"));
        Assert.assertEquals(browser.getDriver().getTitle(),"DEMOQA", "Page title is not correct");
        Assert.assertEquals(po.alertsCardDisplayed(),true);


    }
}
