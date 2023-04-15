package src.test.java.com.sbutron;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import org.testng.Assert;
import org.testng.annotations.*;
import src.browserfactory.Browser;
import src.main.PageObject;
import src.utilities.BrowserUtilities;
import src.utilities.RandomUtils;
import src.utilities.SettingsReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PageTest {
    private Browser browser;
    //private static Logger LOGGER;
    private final SettingsReader config = new SettingsReader("src/test/java/src/config/config.json");
    private  final SettingsReader testconfig = new SettingsReader("src/test/java/src/config/testconfig.json");
    private final SettingsReader testdata = new SettingsReader("src/test/java/src/config/testdata.json");
    private PageObject page;
    private BrowserUtilities browserUtil;
    private RandomUtils randomUtil;

    public PageTest() throws IOException {
    }

    @BeforeTest
    public void setup() throws IOException {
        //ConfigurationSource source = new ConfigurationSource(new FileInputStream("src/test/log4j.xml"));
        //LoggerContext context = (LoggerContext) LogManager.getContext(false);
        //context.setConfigLocation(source);
        //Configurator.initialize(context, source);
        //LOGGER = (Logger) LogManager.getLogger();
        browser = Browser.getInstance();
        page = new PageObject();
        browserUtil = new BrowserUtilities();
        randomUtil = new RandomUtils();
    }

    @AfterTest
    public void teardown(){
        Browser.quitInstance();
    }

    @Test
    public void alerts() throws IOException {
        //LOGGER.info("logger loco");
        //LOGGER.info("otro mensaje de logger");
        //1. Navigate to main page
        browser.goToUrl(testconfig.getString("url"));
        //ASSERT Main page opens
        Assert.assertEquals(browserUtil.getTitle(),"DEMOQA", "Page title is not correct");

        //2. Click on "Alerts,Frame & Windows" button, in a menu click "Alerts" button
        page.clickOnPositionCard(2);
        page.clickAlerts();
        //ASSERT Alerts form has appeared on page
        Assert.assertEquals(page.getHeaderText(),"Alerts", "Page header is incorrect");

        //3. Click on "Click Button to see alert" button
        page.clickAlertButton();
        //ASSERT Alert with text "You clicked a button" is open
        Assert.assertTrue(browserUtil.isEmergentWindowOpen());

        //4. Click "OK" button
        //ASSERT Alert has closed
        Assert.assertTrue(browserUtil.acceptAlert());

        //5. Click on "On button click, confirm box will appear" button
        page.clickConfirmButton();
        //ASSERT Alert with text "Do you confirm action?" is open
        Assert.assertEquals( browserUtil.getEmergentWindowText(),"Do you confirm action?", "Alert text is incorrect");

        //6. Click on "OK" button
        //ASSERT Alert has closed, text "You selected ok" has appeared on page
        Assert.assertTrue(browserUtil.acceptAlert());
        Assert.assertEquals(page.getAcceptancePopupText(),"You selected Ok", "Acceptance text is incorrect");

        //7. CLick on "On button click", prompt box will appear button
        page.clickOnPromptButton();
        //ASSERT Alert with text "Please enter your name" is open
        Assert.assertEquals(browserUtil.getEmergentWindowText(),"Please enter your name", "Prompt text is incorrect");

        //8. Enter "randomly generated" text click "OK" button
        String randomString = randomUtil.generateRandomString();
        browserUtil.insertTextToPopup(randomString);
        browserUtil.acceptAlert();
        //ASSERT Alert has closed, appeared text equals to the one entered before
        Assert.assertEquals(page.getPromptButtonAcceptanceText(),"You entered " + randomString,"Prompt button popup text is different");
    }

    @Test
    public void iframe() throws IOException {
        //1. Navigate to main page
        browser.goToUrl(testconfig.getString("url"));
        //ASSERT Main page opens
        Assert.assertEquals(browser.getDriver().getTitle(),"DEMOQA", "Page title is incorrect");
        //LOGGER.info("Debugger estuvo aqui");
        //2. Click on "Alerts,Frame & Windows" button
        page.clickOnPositionCard(2);
        //Click "Nested Frames"
        page.clickNestedFrames();
        //ASSERT Page with "Nested Frames" form is open
        Assert.assertEquals(page.getHeaderText(),"Nested Frames", "Accordion text is incorrect");
        //ASSERT There are messages "Parent frame" & "Child iframe" present
        Assert.assertEquals(page.getFrame1Text(),"Parent frame","Parent frame text is incorrect");
        Assert.assertEquals(page.getIFrameText(), "Child Iframe", "Child frame text is incorrect");
        browserUtil.toDefaultContent();
        //3. Select "Frames" option in a left menu
        page.clickFrames();
        //ASSERT Page with "Frames" form is open
        Assert.assertEquals(page.getHeaderText(),"Frames", "Page header is incorrect");
        //ASSERT Message from upper frame is equal to the message from lower frame
        String frame1Text = page.getFrame1Text();
        browserUtil.toDefaultContent();
        String frame2Text = page.getFrame2Text();
        browserUtil.toDefaultContent();
        Assert.assertEquals(frame1Text,frame2Text,"Frames have different text");
    }

    @Test(dataProvider = "testdata")
    public void tables(String fname, String lname, String email, String age, String salary, String dpt) throws IOException {
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
        page.fillForm(fname,lname,email,age,salary,dpt);
        // call the fillForm method with the retrieved values
        page.clickSubmitButton();
        //ASSERT Registration form has closed
        //FIXME Maybe find another way to Assert
        //Assert.assertFalse(page.isUserFormDisplayed(), "Form may still be open");

        //ASSERT data of User# appeared in a table
        String dataProvided = fname+","+lname+","+age+","+email+","+salary+","+dpt;
        Assert.assertEquals(page.getTableTextInPosition(3),dataProvided,"Data retrieved from table is different from DataProvider");

        //5. CLick "Delete" button in a row which contains data of User#
        int recordsNumber = page.getRecordsNumber();
        page.deleteRecordInPosition(3);
        //ASSERT Number of records changed
        int newRecordsNumber = page.getRecordsNumber();
        Assert.assertNotEquals(recordsNumber,newRecordsNumber, "Number of records didn't change");
        //ASSERT Data of User# has been deleted
        Assert.assertTrue(page.getTableTextInPosition(3).isEmpty(), "Data is still available");
    }

    @DataProvider(name="testdata")
    public Object[][] dataFeed() {
        List<Object> userDataList = testdata.getList("userData");
        Object[][] userData = new Object[userDataList.size()][6];
        for (int i = 0; i < userDataList.size(); i++) {
            Map<String, Object> userDataMap = (Map<String, Object>) userDataList.get(i);
            userData[i][0] = userDataMap.get("firstName");
            userData[i][1] = userDataMap.get("lastName");
            userData[i][2] = userDataMap.get("email");
            userData[i][3] = userDataMap.get("age").toString();
            userData[i][4] = userDataMap.get("salary").toString();
            userData[i][5] = userDataMap.get("department");
        }
        return userData;
    }

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
        String oldTabHandle = browserUtil.getWindowHandle();
        Set<String> windowHandles = browserUtil.getWindowHandles();
        page.clickNewTabButton();
        //ASSERT New tab with sample page is open
        Set<String> updatedWindowHandles = browserUtil.getWindowHandles();
        String newTabHandle = browserUtil.getNewTabHandle(windowHandles,updatedWindowHandles);
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
        newTabHandle = browserUtil.getNewTabHandle(windowHandles,updatedWindowHandles);
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
- I am not using the BaseForm class. I am making assertions by getting headerText.
- I am lacking a logger, an Where should the logger be used?
- I am struggling with the DDT part
- I am struggling with the iframe assertions
*/