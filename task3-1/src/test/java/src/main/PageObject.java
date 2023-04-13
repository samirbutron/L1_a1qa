package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import src.browserfactory.Browser;

import java.io.IOException;
import java.util.List;

public class PageObject {

    //FIXME idealmente no deberia existir un Browser aqui
    private Browser browser;
    public PageObject() throws IOException {
        browser = Browser.getInstance();
    }
    private BaseElement mainPageCards = new BaseElement(By.xpath("//div[@class='category-cards']"), "mainPageCards");
    private BaseElement pageMainHeader = new BaseElement(By.className("main-header"),"pageMainHeader");

    //Other approach would be to find the List<WebElements> in the accordion
    private BaseElement leftPanelAccordion = new BaseElement(By.className("accordion"),"leftPanelAccordion");
    private BaseElement alertsInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Alerts')]"),"alertInAccordion");
    private BaseElement alertButton = new BaseElement(By.id("alertButton"), "alertButton");
    private BaseElement confirmButton = new BaseElement(By.id("confirmButton"), "confirmButton");
    private BaseElement confirmAcceptanceText = new BaseElement(By.id("confirmResult"), "confirmAcceptanceText");
    private BaseElement promptButton = new BaseElement(By.id("promtButton"), "promptButton");
    private BaseElement promptAcceptanceText = new BaseElement(By.id("promptResult"), "promptAcceptanceTest");
    private BaseElement nestedFramesInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Nested Frames')]"), "nestedFramesInAccordion");
    private BaseElement iframe = new BaseElement(By.id("frame1"), "iframe");
    private BaseElement nestedIframe = new BaseElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']"), "nestedIframe");
    private BaseElement framesInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Frames')]"),"framesInAccordion");
    private BaseElement webTablesInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Web Tables')]"), "webTablesInAccordion");
    private BaseElement addButton = new BaseElement(By.id("addNewRecordButton"), "addButton");
    private BaseElement registrationForm = new BaseElement(By.id("registration-form-modal"), "registrationForm");
    private BaseElement firstNameInForm = new BaseElement(By.id("firstName"),"firstNameInForm");
    private BaseElement lastNameInForm = new BaseElement(By.id("lastName"), "lastNameInForm");
    private BaseElement userEmailInForm = new BaseElement(By.id("userEmail"), "userEmailInForm");
    private BaseElement ageInForm = new BaseElement(By.id("age"), "ageInForm");
    private BaseElement salaryInForm = new BaseElement(By.id("salary"), "salaryInForm");
    private BaseElement departmentInForm = new BaseElement(By.id("department"), "departmentInForm");
    private BaseElement submitButton = new BaseElement(By.id("submit"),"submitButton");
    private BaseElement browserWindowsInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Browser Windows')]"), "browserWindowsInAccordion");
    private BaseElement newTabButton = new BaseElement(By.id("tabButton"), "newTabButton");
    private BaseElement linksInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Links')]"), "linksInAccordion");
    private BaseElement simpleLink = new BaseElement(By.id("simpleLink"), "simpleLink");
    public void clickOnPositionCard (int number) throws IOException {
        List<WebElement> cardsList = mainPageCards.findElement().findElements(By.xpath("./child::div"));
        cardsList.get(number).click();
    }
    public void clickAlerts() throws IOException {
        alertsInAccordion.findElement().click();
    }
    public void clickAlertButton() throws  IOException {
        alertButton.findElement().click();
    }
    public String getHeaderText() throws IOException {
        return pageMainHeader.findElement().getText();
    }
    public boolean alertButtonPopupIsOpen(){
        return alertButton.isEmergentWindowOpen();
    }
    public boolean acceptAlertButtonPopup() throws IOException {
        return alertButton.acceptAlert();
    }
    public void clickConfirmButton () throws IOException {
        confirmButton.findElement().click();
    }
    public String getConfirmButtonAlertText() throws IOException {
        return confirmButton.getEmergentWindowText();
    }
    public boolean acceptConfirmButtonPopup() throws  IOException{
        return confirmButton.acceptAlert();
    }
    public String getAcceptancePopupText() throws IOException {
        return confirmAcceptanceText.getText();
    }
    public void clickOnPromptButton() throws IOException {
        promptButton.findElement().click();
    }
    public String getPromptButtonPopupText() throws IOException {
        return promptButton.getEmergentWindowText();
    }
    public void insertRandomTextToPrompt(String text) throws IOException {
        promptButton.insertTextToPopup(text);
    }
    public boolean acceptPromptButtonPopup() throws IOException {
        return promptButton.acceptAlert();
    }
    public String getPromptButtonAcceptanceText() throws IOException {
        return promptAcceptanceText.getText();
    }
    public void clickNestedFrames() throws IOException {
        nestedFramesInAccordion.findElement().click();
    }
    public String getAccordionTextInPosition(int number) throws IOException {
        List<WebElement> listAccordion = leftPanelAccordion.findElement().findElements(By.className("element-group"));
        return listAccordion.get(number).getText();

    }
    public void clickOnAccordionPosition(int number) throws IOException {
        List<WebElement> listAccordion = leftPanelAccordion.findElement().findElements(By.className("element-group"));
        listAccordion.get(number).click();
    }

    //FIXME funcion esta regresando basura
    public String getframeText() throws IOException {
        browser.getDriver().switchTo().frame(iframe.findElement());
        return browser.getDriver().findElement(By.tagName("body")).getText();
    }

    //FIXME funcion esta regresando basura
    public String getChildIframeText() throws IOException {
        browser.getDriver().switchTo().frame(iframe.findElement());
        WebElement frame2 = browser.getDriver().findElement(By.tagName("iframe"));
        browser.getDriver().switchTo().frame(frame2);
        return browser.getDriver().findElement(By.tagName("body")).getText();
    }

    public void clickFrames () throws IOException {
        framesInAccordion.click();
    }
    public void clickWebTables() throws IOException {
        webTablesInAccordion.findElement().click();
    }
    public void clickAddButton() throws IOException {
        addButton.findElement().click();
    }
    public String getRegistrationFormText() throws IOException {
        return registrationForm.findElement().getText();
    }

    public void fillForm(String fname, String lname, String email, String age, String salary, String dpt) throws IOException {
        firstNameInForm.findElement().sendKeys(fname);
        lastNameInForm.findElement().sendKeys(lname);
        userEmailInForm.findElement().sendKeys(email);
        ageInForm.findElement().sendKeys(age);
        salaryInForm.findElement().sendKeys(salary);
        departmentInForm.findElement().sendKeys(dpt);
    }

    public void clickSubmitButton () throws IOException {
        submitButton.findElement().click();
    }

    public void clickBrowserWindows() throws IOException {
        browserWindowsInAccordion.findElement().click();
    }

    public void clickNewTab() throws IOException {
        newTabButton.findElement().click();
    }
    public void clickLinks() throws IOException {
        linksInAccordion.findElement().click();
    }
    public void clickHomeLink() throws IOException {
        simpleLink.findElement().click();
    }




}
