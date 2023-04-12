package src.test.java.com.sbutron;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import src.browserfactory.Browser;
import src.main.PageObject;
import src.utilities.SettingsReader;
import src.utilities.Util;

import java.io.IOException;

public class PageTest {
    private Browser browser;
    private final SettingsReader config = new SettingsReader("src/test/java/src/config/config.json");
    private  final SettingsReader testconfig = new SettingsReader("src/test/java/src/config/testconfig.json");
    //FIXME aqui se agregaran los readers para los otros .json
    private PageObject page;
    private Util util;

    public PageTest() throws IOException {
    }

    @BeforeTest
    public void setup() throws IOException {
        browser = Browser.getInstance();
        page = new PageObject();
        util = new Util();
        //FIXME instanciar otras clases creadas... Util, WaitsUtil
    }

    @AfterTest
    public void teardown(){
        Browser.quitInstance();
    }

    @Test
    public void alerts() throws IOException {
        //1. Navigate to main page
        browser.goToUrl(testconfig.getString("url"));
        //ASSERT Main page opens
        Assert.assertEquals(browser.getDriver().getTitle(),"DEMOQA", "Page title is not correct");

        //2. Click on "Alerts,Frame & Windows" button, in a menu click "Alerts" button
        page.clickAlertsCard();
        page.clickAlerts();
        //ASSERT Alerts form has appeared on page
        Assert.assertEquals(page.getHeaderText(),"Alerts", "Page header is incorrect");

        //3. Click on "Click Button to see alert" button
        page.clickAlertButton();
        //ASSERT Alert with text "You clicked a button" is open
        Assert.assertTrue(page.alertButtonPopupIsOpen());

        //4. Click "OK" button
        //ASSERT Alert has closed
        Assert.assertTrue(page.acceptAlertButtonPopup());

        //5. Click on "On button click, confirm box will appear" button
        page.clickConfirmButton();
        //ASSERT Alert with text "Do you confirm action?" is open
        Assert.assertEquals( page.getConfirmButtonAlertText(),"Do you confirm action?", "Alert text is incorrect");

        //6. Click on "OK" button
        //ASSERT Alert has closed, text "You selected ok" has appeared on page
        Assert.assertTrue(page.acceptConfirmButtonPopup());
        Assert.assertEquals(page.getAcceptancePopupText(),"You selected Ok", "Acceptance text is incorrect");

        //7. CLick on "On button click", prompt box will appear button
        page.clickOnPromptButton();
        //ASSERT Alert with text "Please enter your name" is open
        Assert.assertEquals(page.getPromptButtonPopupText(),"Please enter your name", "Prompt text is incorrect");

        //8. Enter "randomly generated" text click "OK" button
        String randomString = util.generateRandomString();
        page.insertRandomTextToPrompt(randomString);
        page.acceptPromptButtonPopup();
        //ASSERT Alert has closed, appeared text equals to the one entered before
        Assert.assertEquals(page.getPromptButtonAcceptanceText(),"You entered " + randomString,"Prompt button popup text is different");
    }

    @Test
    public void iframe() throws IOException {
        //1. Navigate to main page
        browser.goToUrl(testconfig.getString("url"));
        //ASSERT Main page opens
        Assert.assertEquals(browser.getDriver().getTitle(),"DEMOQA", "Page title is not correct");

        //2. Click on "Alerts,Frame & Windows" button
        //ASSERT Page with "Nested Frames" form is open
        //Click "Nested Frames"
        //ASSERT There are messages "Parent frame" & "Child iframe" present

        //3. Select "Frames" option in a left menu
        //ASSERT Page with "Frames" form is open
        //ASSERT Message from upper frame is equal to the message from lower frame
    }

    @Test
    public void tables() throws IOException {
        //1. Navigate to main page
        browser.goToUrl(testconfig.getString("url"));
        //ASSERT Main page opens
        Assert.assertEquals(browser.getDriver().getTitle(),"DEMOQA", "Page title is not correct");

        //2. Click on "Elements" button, click "Web Tables" button
        //ASSERT Page with "Web Tables" form is open

        //3. Click on "Add" button
        //ASSERT "Registration Form" has appeared on page

        //4. Enter data for User# from the table and then click "Submit" button
        //ASSERT Registration form has closed
        //ASSERT data of User# appeared in a table

        //5. CLick "Delete" button in a row which contains data of User#
        //ASSERT Number of records changed
        //ASSERT Data of User# has been deleted

    }

    @Test
    public void handles() throws IOException {
        //1. Navigate to main page
        browser.goToUrl(testconfig.getString("url"));
        //ASSERT Main page opens
        Assert.assertEquals(browser.getDriver().getTitle(),"DEMOQA", "Page title is not correct");

        //2. Click on "Alerts,Frame & Windows" button, click "Browser Windows"
        //ASSERT Page with "Browser Windows" form is open

        //3. Click on "New Tab" button
        //ASSERT New tab with sample page is open

        //4. Close current tab
        //ASSERT Page with "Browser Window" form is open

        //5. In the menu on the left click "Elements" -> "Links" button
        //ASSERT Page with "Links" form is open

        //6. Click on "Home" link
        //ASSERT New tab with main page is open

        //7. Resume previous tab
        //ASSSERT Page with "Links" form is open

    }
}
