package src.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import src.main.*;

import java.util.List;

public class WebTablesPage {
    private MyLogger logger;
    public WebTablesPage(){
        logger = MyLogger.getInstance();
    }
    private Button webTablesInAccordion = new Button(By.xpath("//*[@class='text' and contains(text(),'Web Tables')]"), "webTablesInAccordion");
    private Button addButton = new Button(By.id("addNewRecordButton"), "addButton");
    private Table tableList = new Table(By.className("rt-tbody"),"tableList");
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
    static By listGroup =  By.className("rt-tr-group");
    static By deleteRecordElement = By.xpath("//*[starts-with(@id,'delete-record-')]");

    public void clickWebTables() {
        logger.info("Clicking on Web Tables in left Accordion");
        webTablesInAccordion.click();
    }
    public void clickAddButton() {
        logger.info("Clicking Add button");
        addButton.click();
    }
    public String getRegistrationFormText() {
        logger.info("Getting Registration Form text");
        return registrationForm.getText();
    }
    public void fillForm(String fname, String lname, String email, String age, String salary, String dpt) {
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
    public String getTableTextInPosition(int position) {
        logger.info("Getting entry on table in position: "+position);
        List<WebElement> list = tableList.findElement().findElements(listGroup);
        String[] rowText = list.get(position).getText().split("\n");
        return String.join(",", rowText).trim();
    }
    public String getTableText() {
        logger.info("Getting entries on table");
        List<WebElement> list = tableList.findElement().findElements(listGroup);
        StringBuilder stringBuilder = new StringBuilder();
        for(WebElement element : list){
            stringBuilder.append(element.getText());
        }
        return stringBuilder.toString();
    }
    public boolean isRegistrationFormDisplayed() {
        logger.info("Looking if Registration form is closed ");
        return userForm.isFormOpen();
    }
    public void deleteRecordInPosition(int position) {
        logger.info("Deleting record in position: "+position);
        List<WebElement> deleteButtons = actionButtons.findElement().findElements(deleteRecordElement);
        deleteButtons.get(position).click();
    }
    public int getRecordsNumber() {
        logger.info("Getting number of records");
        return actionButtons.findElements().size();
    }

    public void clickSubmitButton () {
        logger.info("Clicking submit button");
        submitButton.click();
    }
}
