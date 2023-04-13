package src.test.java.com.sbutron;

import org.testng.Assert;
import org.testng.annotations.*;
import src.browserfactory.Browser;
import src.main.PageObject;
import src.utilities.SettingsReader;
import src.utilities.Util;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class PageTest {
    private Browser browser;
    private final SettingsReader config = new SettingsReader("src/test/java/src/config/config.json");
    private  final SettingsReader testconfig = new SettingsReader("src/test/java/src/config/testconfig.json");
    private final SettingsReader testdata = new SettingsReader("src/test/java/src/config/testdata.json");

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
        page.clickOnPositionCard(2);
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
        Assert.assertEquals(browser.getDriver().getTitle(),"DEMOQA", "Page title is incorrect");

        //2. Click on "Alerts,Frame & Windows" button
        page.clickOnPositionCard(2);
        //Click "Nested Frames"
        page.clickNestedFrames();
        //ASSERT Page with "Nested Frames" form is open
        Assert.assertEquals(page.getHeaderText(),"Nested Frames", "Accordion text is incorrect");
        //ASSERT There are messages "Parent frame" & "Child iframe" present
        Assert.assertEquals(page.getframeText(), "Parent frame", "Parent frame text is incorrect");

        //3. Select "Frames" option in a left menu
        page.clickFrames();
        //ASSERT Page with "Frames" form is open
        Assert.assertEquals(page.getHeaderText(),"Frames", "Page header is incorrect");
        //ASSERT Message from upper frame is equal to the message from lower frame
    }

    @Test//FIXME (dataProvider = "testdata")
    public void tables() throws IOException {
        //FIXME (String fname, String lname, String email, int age, int salary, String dpt)
        //1. Navigate to main page
        browser.goToUrl(testconfig.getString("url"));
        //ASSERT Main page opens
        Assert.assertEquals(browser.getDriver().getTitle(),"DEMOQA", "Page title is not correct");

        //2. Click on "Elements" button, click "Web Tables" button
        page.clickOnPositionCard(0);
        page.clickWebTables();
        //ASSERT Page with "Web Tables" form is open
        Assert.assertEquals(page.getHeaderText(),"Web Tables", "Page header is incorrect");

        //3. Click on "Add" button
        page.clickAddButton();
        //ASSERT "Registration Form" has appeared on page
        Assert.assertEquals(page.getRegistrationFormText(),"Registration Form", "Registration form title is incorrect");
        //4. Enter data for User# from the table and then click "Submit" button
        //page.fillForm(fname,lname,email,age,salary,dpt);
        page.fillForm("sam","but","email@s.com","30","140","inc");
        page.clickSubmitButton();
        //ASSERT Registration form has closed


        //ASSERT data of User# appeared in a table



        //5. CLick "Delete" button in a row which contains data of User#
        //ASSERT Number of records changed
        //ASSERT Data of User# has been deleted

    }

    /*FIXME
    @DataProvider(name="testdata")
    public List<Object> dataFeed() {

    }*/

    @Test
    public void handles() throws IOException {
        //1. Navigate to main page
        browser.goToUrl(testconfig.getString("url"));
        //ASSERT Main page opens
        Assert.assertEquals(browser.getDriver().getTitle(),"DEMOQA", "Page title is not correct");

        //2. Click on "Alerts,Frame & Windows" button, click "Browser Windows"
        page.clickOnPositionCard(2);
        page.clickBrowserWindows();
        //ASSERT Page with "Browser Windows" form is open
        Assert.assertEquals(page.getHeaderText(),"Browser Windows", "Page header is incorrect");

        //3. Click on "New Tab" button
        String oldTabHandle = browser.getDriver().getWindowHandle();
        Set<String> windowHandles = browser.getDriver().getWindowHandles();
        page.clickNewTab();
        //ASSERT New tab with sample page is open
        Set<String> updatedWindowHandles = browser.getDriver().getWindowHandles();
        String newTabHandle = util.getNewTabHandle(windowHandles,updatedWindowHandles);
        browser.getDriver().switchTo().window(newTabHandle);
        Assert.assertEquals(browser.getDriver().getCurrentUrl(),"https://demoqa.com/sample", "Page didn't openend or doesn't match URL");

        //4. Close current tab
        browser.getDriver().close();
        browser.getDriver().switchTo().window(oldTabHandle);
        //ASSERT Page with "Browser Windows" form is open
        Assert.assertEquals(page.getHeaderText(),"Browser Windows", "Page header is incorrect");

        //5. In the menu on the left click "Elements" -> "Links" button
        page.clickOnAccordionPosition(0);
        page.clickLinks();
        //ASSERT Page with "Links" form is open
        Assert.assertEquals(page.getHeaderText(),"Links", "Page header is incorrect");

        //6. Click on "Home" link
        page.clickHomeLink();
        oldTabHandle = browser.getDriver().getWindowHandle();
        updatedWindowHandles = browser.getWindowHandles();
        newTabHandle = util.getNewTabHandle(windowHandles,updatedWindowHandles);
        browser.getDriver().switchTo().window(newTabHandle);
        //ASSERT New tab with main page is open
        Assert.assertEquals(browser.getDriver().getCurrentUrl(),"https://demoqa.com/", "Page didn't openend or doesn't match URL");

        //7. Resume previous tab
        browser.getDriver().switchTo().window(oldTabHandle);
        //ASSSERT Page with "Links" form is open
        Assert.assertEquals(page.getHeaderText(),"Links", "Page header is incorrect");
    }
}

/*FIXME PENDIENTESSSS:
- I am not using the BaseForm class. I am certain I am not doing correctly the assertion of the "form" is open
- I am lacking a logger, an Where should the logger be used?
- I am struggling with the DDT part
- I am struggling with the iframe assertions
- Not sure if I can handle the popups within PageTest or it was necessary to handle them in PageObject
*/