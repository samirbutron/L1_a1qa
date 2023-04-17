package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.test.java.com.sbutron.PageTest;
import src.utilities.BrowserUtilities;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class PageObject {
    private final BrowserUtilities browserUtilities;
    private static final Logger logger = LoggerFactory.getLogger(PageTest.class);
    public PageObject() throws IOException {
        browserUtilities = new BrowserUtilities();
    }
    private BaseElement mainPageCards = new BaseElement(By.xpath("//div[@class='category-cards']"), "mainPageCards");
    private BaseElement pageMainHeader = new BaseElement(By.className("main-header"),"pageMainHeader");
    //Other approach would be to find the List<WebElements> in the accordion
    private BaseElement leftPanelAccordion = new BaseElement(By.className("accordion"),"leftPanelAccordion");
    private BaseElement alertsInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Alerts')]"),"alertInAccordion");
    private Button alertButton = new Button(By.id("alertButton"), "alertButton");
    private Button confirmButton = new Button(By.id("confirmButton"), "confirmButton");
    private BaseElement confirmAcceptanceText = new BaseElement(By.id("confirmResult"), "confirmAcceptanceText");
    private Button promptButton = new Button(By.id("promtButton"), "promptButton");
    private BaseElement promptAcceptanceText = new BaseElement(By.id("promptResult"), "promptAcceptanceTest");
    private BaseElement nestedFramesInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Nested Frames')]"), "nestedFramesInAccordion");
    private BaseElement frame1 = new BaseElement(By.id("frame1"), "frame1");
    private BaseElement frame2 = new BaseElement(By.id("frame2"), "frame2");
    private BaseElement childIframe = new BaseElement(By.tagName("iframe"), "nestedIframe");
    private BaseElement frameBody = new BaseElement(By.xpath("//body"), "frameBody");
    private BaseElement framesInAccordion = new BaseElement(By.xpath("//li[@id='item-2']//*[@class='text' and contains(text(),'Frames')]"),"framesInAccordion");
    private BaseElement webTablesInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Web Tables')]"), "webTablesInAccordion");
    private Button addButton = new Button(By.id("addNewRecordButton"), "addButton");
    private BaseElement tableList = new BaseElement(By.className("rt-tbody"),"tableList");
    private BaseForm userForm = new BaseForm(By.className("modal-content"), "userForm");
    private Button actionButtons = new Button(By.className("action-buttons"), "actionButtons");
    private TextBox registrationForm = new TextBox(By.id("registration-form-modal"), "registrationForm");
    private TextBox firstNameInForm = new TextBox(By.id("firstName"),"firstNameInForm");
    private TextBox lastNameInForm = new TextBox(By.id("lastName"), "lastNameInForm");
    private TextBox userEmailInForm = new TextBox(By.id("userEmail"), "userEmailInForm");
    private TextBox ageInForm = new TextBox(By.id("age"), "ageInForm");
    private TextBox salaryInForm = new TextBox(By.id("salary"), "salaryInForm");
    private TextBox departmentInForm = new TextBox(By.id("department"), "departmentInForm");
    private Button submitButton = new Button(By.id("submit"),"submitButton");
    private BaseElement browserWindowsInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Browser Windows')]"), "browserWindowsInAccordion");
    private Button newTabButton = new Button(By.id("tabButton"), "newTabButton");
    private BaseElement linksInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Links')]"), "linksInAccordion");
    private BaseElement simpleLink = new BaseElement(By.id("simpleLink"), "simpleLink");
    public void clickOnPositionCard (int number) throws IOException {
        logger.info("Clicking on card in position: "+ number);
        List<WebElement> cardsList = mainPageCards.findElement().findElements(By.xpath("./child::div"));
        cardsList.get(number).click();
    }
    public void clickAlerts() throws IOException {
        logger.info("Clicking on Alerts in left Accordion");
        alertsInAccordion.click();
    }
    public void clickAlertButton() throws  IOException {
        logger.info("Clicking on 'Click Button to see alert' button");
        alertButton.click();
    }
    public String getHeaderText() throws IOException {
        logger.info("Getting header page text");
        return pageMainHeader.getText();
    }
    public void clickConfirmButton () throws IOException {
        logger.info("Clicking on 'On button click, confirm box will appear' button");
        confirmButton.click();
    }
    public String getAcceptancePopupText() throws IOException {
        logger.info("Getting Confirm box acceptance text");
        return confirmAcceptanceText.getText();
    }
    public void clickOnPromptButton() throws IOException {
        logger.info("Clicking on 'On button click, prompt box will appear' button");
        promptButton.click();
    }
    public String getPromptButtonAcceptanceText() throws IOException {
        logger.info("Getting Prompt box acceptance text");
        return promptAcceptanceText.getText();
    }
    public void clickNestedFrames() throws IOException {
        logger.info("Clicking on Nested Frames in left Accordion");
        nestedFramesInAccordion.click();
    }
    public String getFrame1Text() throws IOException {
        logger.info("Getting Frame 1 text");
        browserUtilities.switchToFrame(frame1.findElement());
        return frameBody.getText();
    }
    public String getIFrameText() throws IOException {
        logger.info("Getting Indented Frame in Frame1 text");
        browserUtilities.switchToFrame(childIframe.findElement());
        return frameBody.getText();
    }
    public String getFrame2Text() throws IOException {
        logger.info("Getting Frame 2 text");
        browserUtilities.switchToFrame(frame2.findElement());
        return frameBody.getText();
    }

    public String getAccordionTextInPosition(int position) throws IOException {
        logger.info("Getting Text from Accordion element in position: "+position);
        List<WebElement> listAccordion = leftPanelAccordion.findElement().findElements(By.className("element-group"));
        return listAccordion.get(position).getText();

    }
    public void clickOnAccordionPosition(int position) throws IOException {
        logger.info("Clicking on Accordion Element in position: "+position);
        List<WebElement> listAccordion = leftPanelAccordion.findElement().findElements(By.className("element-group"));
        listAccordion.get(position).click();
    }
    public void clickFrames () throws IOException {
        logger.info("Clicking on Frames in left Accordion");
        framesInAccordion.click();
    }
    public void clickWebTables() throws IOException {
        logger.info("Clicking on Web Tables in left Accordion");
        webTablesInAccordion.click();
    }
    public void clickAddButton() throws IOException {
        logger.info("Clicking Add button");
        addButton.click();
    }
    public String getRegistrationFormText() throws IOException {
        logger.info("Getting Registration Form text");
        return registrationForm.getText();
    }
    public void fillForm(String fname, String lname, String email, String age, String salary, String dpt) throws IOException {
        logger.info("Filling Registration Form:");
        logger.info("Entering first name");
        firstNameInForm.sendKeys(fname);
        logger.info("Entering last name");
        lastNameInForm.sendKeys(lname);
        logger.info("Entering email");
        userEmailInForm.sendKeys(email);
        logger.info("Entering age");
        ageInForm.sendKeys(age);
        logger.info("Entering age");
        salaryInForm.sendKeys(salary);
        logger.info("Entering salary");
        departmentInForm.sendKeys(dpt);
    }
    public String getTableTextInPosition(int position) throws IOException {
        logger.info("Getting entry on table in position: "+position);
        List<WebElement> list = tableList.findElement().findElements(By.className("rt-tr-group"));
        String[] rowText = list.get(position).getText().split("\n");
        return String.join(",", rowText).trim();
    }
    public String getTableText() throws IOException {
        logger.info("Getting entries on table");
        List<WebElement> list = tableList.findElement().findElements(By.className("rt-tr-group"));
        StringBuilder stringBuilder = new StringBuilder();
        for(WebElement element : list){
            stringBuilder.append(element.getText());
        }
        return stringBuilder.toString();
    }
    public boolean isRegistrationFormDisplayed() throws IOException {
        logger.info("Looking if Registration form is closed ");
            return userForm.isFormOpen();
    }
    public void deleteRecordInPosition(int position) throws IOException {
        logger.info("Deleting record in position: "+position);
        List<WebElement> deleteButtons = actionButtons.findElement().findElements(By.xpath("//*[starts-with(@id,'delete-record-')]"));
        deleteButtons.get(position).click();
    }
    public int getRecordsNumber() throws IOException {
        logger.info("Getting number of records");
        return actionButtons.findElements().size();
    }

    public void clickSubmitButton () throws IOException {
        logger.info("Clicking submit button");
        submitButton.click();
    }
    public void clickBrowserWindows() throws IOException {
        logger.info("Clicking on Browser Windows in left Accordion");
        browserWindowsInAccordion.click();
    }
    public void clickNewTabButton() throws IOException {
        logger.info("Clicking on 'New Tab' button");
        newTabButton.click();
    }
    public void clickLinks() throws IOException {
        logger.info("Clicking on Links in left Accordion");
        linksInAccordion.click();
    }
    public void clickHomeLink() throws IOException {
        logger.info("Clicking on 'Home' links");
        simpleLink.click();
    }
}
