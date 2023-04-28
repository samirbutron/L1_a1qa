package src.test.com.sbutron;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import src.browserfactory.BrowserFactory;
import src.pageobject.*;
import src.utilities.BrowserUtilities;
import src.utilities.RandomUtils;
import src.utilities.SettingsReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainPageTests extends BaseTest {
    private final SettingsReader testdata = new SettingsReader("src/test/java/src/config/testdata.json");
    private  final SettingsReader testconfig = new SettingsReader("src/test/java/src/config/testconfig.json");
    private AlertsPage alertsPage;

    public MainPageTests() {
    }

    @BeforeTest
    public void testStart() {
        setWebpage();
    }

    @AfterTest
    public void testEnd(){
        tearDown();
    }

    @Test
    public void alerts() {
        MainPage mainPage = new MainPage();
        //1. Navigate to main page
        //ASSERT Main page opens
        Assert.assertTrue(mainPage.isMainBannerDisplayed(), "Main page didn't open" );

        //2. Click on "Alerts,Frame & Windows" button, in a menu click "Alerts" button
        mainPage.clickOnPositionCard(2);
        alertsPage = new AlertsPage();
        alertsPage.clickAlerts();
        //ASSERT Alerts form has appeared on page
        Assert.assertEquals(alertsPage.getAlertsHeaderText(),"Alerts", "Page header is incorrect");

        //3. Click on "Click Button to see alert" button
        alertsPage.clickAlertButton();
        //ASSERT Alert with text "You clicked a button" is open
        Assert.assertTrue(BrowserUtilities.isEmergentWindowOpen());

        //4. Click "OK" button
        //ASSERT Alert has closed
        Assert.assertTrue(BrowserUtilities.acceptAlert());

        //5. Click on "On button click, confirm box will appear" button
        alertsPage.clickConfirmButton();
        //ASSERT Alert with text "Do you confirm action?" is open
        Assert.assertEquals( BrowserUtilities.getEmergentWindowText(),"Do you confirm action?", "Alert text is incorrect");

        //6. Click on "OK" button
        //ASSERT Alert has closed, text "You selected ok" has appeared on page
        Assert.assertTrue(BrowserUtilities.acceptAlert());
        Assert.assertEquals(alertsPage.getAcceptancePopupText(),"You selected Ok", "Acceptance text is incorrect");

        //7. CLick on "On button click", prompt box will appear button
        alertsPage.clickOnPromptButton();
        //ASSERT Alert with text "Please enter your name" is open
        Assert.assertEquals(BrowserUtilities.getEmergentWindowText(),"Please enter your name", "Prompt text is incorrect");

        //8. Enter "randomly generated" text click "OK" button
        String randomString = RandomUtils.generateRandomString();
        BrowserUtilities.insertTextToPopup(randomString);
        BrowserUtilities.acceptAlert();
        //ASSERT Alert has closed, appeared text equals to the one entered before
        Assert.assertEquals(alertsPage.getPromptButtonAcceptanceText(),"You entered " + randomString,"Prompt button popup text is different");
    }

    @Test
    public void iframe() {
        MainPage mainPage = new MainPage();
        //1. Navigate to main page
        //ASSERT Main page opens
        Assert.assertEquals(BrowserFactory.getDriver().getTitle(),"DEMOQA", "Page title is incorrect");
        //2. Click on "Alerts,Frame & Windows" button
        mainPage.clickOnPositionCard(2);
        //Click "Nested Frames"
        NestedFramesPage nestedFramesPage = new NestedFramesPage();
        nestedFramesPage.clickNestedFrames();
        //ASSERT Page with "Nested Frames" form is open
        Assert.assertEquals(nestedFramesPage.getNestedFramesHeader(),"Nested Frames", "Accordion text is incorrect");
        //ASSERT There are messages "Parent frame" & "Child iframe" present
        Assert.assertEquals(nestedFramesPage.getFrame1Text(),"Parent frame","Parent frame text is incorrect");
        Assert.assertEquals(nestedFramesPage.getIFrameText(), "Child Iframe", "Child frame text is incorrect");
        BrowserUtilities.toDefaultContent();
        //3. Select "Frames" option in a left menu
        FramesPage framesPage = new FramesPage();
        framesPage.clickFrames();
        //ASSERT Page with "Frames" form is open
        Assert.assertEquals(framesPage.getFramesHeader(),"Frames", "Page header is incorrect");
        //ASSERT Message from upper frame is equal to the message from lower frame
        String frame1Text = framesPage.getFrame1Text();
        BrowserUtilities.toDefaultContent();
        String frame2Text = framesPage.getFrame2Text();
        BrowserUtilities.toDefaultContent();
        Assert.assertEquals(frame1Text,frame2Text,"Frames have different text");
    }

    @Test(dataProvider = "testdata")
    public void tables(String fname, String lname, String email, String age, String salary, String dpt) {
        MainPage mainPage = new MainPage();
        //1. Navigate to main page
        BrowserUtilities.goToUrl(testconfig.getString("url"));
        //ASSERT Main page opens
        Assert.assertEquals(BrowserFactory.getDriver().getTitle(),"DEMOQA", "Page title is not correct");

        //2. Click on "Elements" button, click "Web Tables" button
        mainPage.clickOnPositionCard(0);
        WebTablesPage webTablesPage = new WebTablesPage();
        webTablesPage.clickWebTables();
        //ASSERT Page with "Web Tables" form is open
        ////////////////////Assert.assertEquals(page.getHeaderText(),"Web Tables", "Page header is incorrect");

        //3. Click on "Add" button
        webTablesPage.clickAddButton();
        //ASSERT "Registration Form" has appeared on page
        Assert.assertEquals(webTablesPage.getRegistrationFormText(),"Registration Form", "Registration form title is incorrect");
        //4. Enter data for User# from the table and then click "Submit" button
        webTablesPage.fillForm(fname,lname,email,age,salary,dpt);
        // call the fillForm method with the retrieved values
        webTablesPage.clickSubmitButton();
        //ASSERT Registration form has closed
        Assert.assertFalse(webTablesPage.isRegistrationFormDisplayed(), "Form may still be open");

        //ASSERT data of User# appeared in a table
        String dataProvided = fname+","+lname+","+age+","+email+","+salary+","+dpt;
        Assert.assertEquals(webTablesPage.getTableTextInPosition(3),dataProvided,"Data retrieved from table is different from DataProvider");

        //5. CLick "Delete" button in a row which contains data of User#
        int recordsNumber = webTablesPage.getRecordsNumber();
        webTablesPage.deleteRecordInPosition(3);
        //ASSERT Number of records changed
        int newRecordsNumber = webTablesPage.getRecordsNumber();
        Assert.assertNotEquals(recordsNumber,newRecordsNumber, "Number of records didn't change");
        //ASSERT Data of User# has been deleted
        Assert.assertTrue(webTablesPage.getTableTextInPosition(3).isEmpty(), "Data is still available");
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
    public void handles() {
        MainPage mainPage = new MainPage();
        //1. Navigate to main page
        BrowserUtilities.goToUrl(testconfig.getString("url"));
        //ASSERT Main page opens
        Assert.assertEquals(BrowserFactory.getDriver().getTitle(),"DEMOQA", "Page title is not correct");

        //2. Click on "Alerts,Frame & Windows" button, click "Browser Windows"
        mainPage.clickOnPositionCard(2);
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage();
        browserWindowsPage.clickBrowserWindows();
        //ASSERT Page with "Browser Windows" form is open
        Assert.assertEquals(browserWindowsPage.getbrowserWindowsHeader(),"Browser Windows", "Page header is incorrect");

        //3. Click on "New Tab" button
        String oldTabHandle = BrowserUtilities.getWindowHandle();
        Set<String> windowHandles = BrowserUtilities.getWindowHandles();
        browserWindowsPage.clickNewTabButton();
        //ASSERT New tab with sample page is open
        Set<String> updatedWindowHandles = BrowserUtilities.getWindowHandles();
        String newTabHandle = BrowserUtilities.getNewTabHandle(windowHandles,updatedWindowHandles);
        BrowserFactory.getDriver().switchTo().window(newTabHandle);
        Assert.assertEquals(BrowserFactory.getDriver().getCurrentUrl(),"https://demoqa.com/sample", "Page didn't openend or doesn't match URL");

        //4. Close current tab
        BrowserFactory.getDriver().close();
        BrowserFactory.getDriver().switchTo().window(oldTabHandle);
        //ASSERT Page with "Browser Windows" form is open
        Assert.assertEquals(browserWindowsPage.getbrowserWindowsHeader(),"Browser Windows", "Page header is incorrect");

        //5. In the menu on the left click "Elements" -> "Links" button
        browserWindowsPage.clickOnAccordionPosition(0);
        LinksPage linksPage = new LinksPage();
        linksPage.clickLinks();
        //ASSERT Page with "Links" form is open
        Assert.assertEquals(linksPage.getLinksHeader(),"Links", "Page header is incorrect");

        //6. Click on "Home" link
        linksPage.clickHomeLink();
        oldTabHandle = BrowserFactory.getDriver().getWindowHandle();
        updatedWindowHandles = BrowserUtilities.getWindowHandles();
        newTabHandle = BrowserUtilities.getNewTabHandle(windowHandles,updatedWindowHandles);
        BrowserFactory.getDriver().switchTo().window(newTabHandle);
        //ASSERT New tab with main page is open
        Assert.assertEquals(BrowserFactory.getDriver().getCurrentUrl(),"https://demoqa.com/", "Page didn't openend or doesn't match URL");

        //7. Resume previous tab
        BrowserFactory.getDriver().switchTo().window(oldTabHandle);
        //ASSSERT Page with "Links" form is open
        Assert.assertEquals(linksPage.getLinksHeader(),"Links", "Page header is incorrect");
    }
}