package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class PageObject {
    public PageObject() throws IOException {
    }
    private static Logger LOGGER = LoggerFactory.getLogger(PageObject.class);
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
    private BaseElement iframe = new BaseElement(By.id("frame1"), "iframe");
    private BaseElement nestedIframe = new BaseElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']"), "nestedIframe");
    private BaseElement framesInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Frames')]"),"framesInAccordion");
    private BaseElement webTablesInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Web Tables')]"), "webTablesInAccordion");
    private Button addButton = new Button(By.id("addNewRecordButton"), "addButton");
    private BaseElement tableList = new BaseElement(By.className("rt-tbody"),"tableList");
    private BaseForm userForm = new BaseForm(By.id("userForm"), "userForm");

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
        LOGGER.info("Clicking on the main page card in position :"+ number);
        List<WebElement> cardsList = mainPageCards.findElement().findElements(By.xpath("./child::div"));
        cardsList.get(number).click();
    }
    public void clickAlerts() throws IOException {

        alertsInAccordion.click();
    }
    public void clickAlertButton() throws  IOException {
        alertButton.click();
    }
    public String getHeaderText() throws IOException {
        return pageMainHeader.getText();
    }

    public void clickConfirmButton () throws IOException {
        confirmButton.click();
    }
    public String getAcceptancePopupText() throws IOException {
        return confirmAcceptanceText.getText();
    }
    public void clickOnPromptButton() throws IOException {
        promptButton.click();
    }
    public String getPromptButtonAcceptanceText() throws IOException {
        return promptAcceptanceText.getText();
    }
    public void clickNestedFrames() throws IOException {
        nestedFramesInAccordion.click();
    }
    public String getAccordionTextInPosition(int number) throws IOException {
        List<WebElement> listAccordion = leftPanelAccordion.findElement().findElements(By.className("element-group"));
        return listAccordion.get(number).getText();

    }
    public void clickOnAccordionPosition(int number) throws IOException {
        List<WebElement> listAccordion = leftPanelAccordion.findElement().findElements(By.className("element-group"));
        listAccordion.get(number).click();
    }
    public void clickFrames () throws IOException {
        framesInAccordion.click();
    }
    public void clickWebTables() throws IOException {
        webTablesInAccordion.click();
    }
    public void clickAddButton() throws IOException {
        addButton.click();
    }
    public String getRegistrationFormText() throws IOException {
        return registrationForm.getText();
    }
    public void fillForm(String fname, String lname, String email, String age, String salary, String dpt) throws IOException {
        firstNameInForm.sendKeys(fname);
        lastNameInForm.sendKeys(lname);
        userEmailInForm.sendKeys(email);
        ageInForm.sendKeys(age);
        salaryInForm.sendKeys(salary);
        departmentInForm.sendKeys(dpt);
    }
    public String getTableTextInPosition(int position) throws IOException {
        List<WebElement> list = tableList.findElement().findElements(By.className("rt-tr-group"));
        String[] rowText = list.get(position).getText().split("\n");
        return String.join(", ", rowText);
    }
    public String getTableText() throws IOException {
        List<WebElement> list = tableList.findElement().findElements(By.className("rt-tr-group"));
        StringBuilder stringBuilder = new StringBuilder();
        for(WebElement element : list){
            stringBuilder.append(element.getText());
        }
        return stringBuilder.toString();
    }
    public boolean isUserFormDisplayed() throws IOException {
        try{
            return userForm.isFormOpen();
        }catch (NoSuchElementException e){
            return false;
        }
    }
    public void clickSubmitButton () throws IOException {
        submitButton.click();
    }
    public void clickBrowserWindows() throws IOException {
        browserWindowsInAccordion.click();
    }
    public void clickNewTabButton() throws IOException {
        newTabButton.click();
    }
    public void clickLinks() throws IOException {
        linksInAccordion.click();
    }
    public void clickHomeLink() throws IOException {
        simpleLink.click();
    }
}
