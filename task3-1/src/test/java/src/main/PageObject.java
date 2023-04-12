package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class PageObject {

    public PageObject() throws IOException {

    }
    private BaseElement mainPageCards = new BaseElement(By.xpath("//div[@class='category-cards']"), "mainPageCards");
    private BaseElement pageMainHeader = new BaseElement(By.className("main-header"),"pageMainHeader");

    //FIXME Other approach would be to find the List<WebElements> in the accordion
    private BaseElement leftPanelAccordion = new BaseElement(By.className("accordion"),"leftPanelAccordion");
    private BaseElement alertInAccordion = new BaseElement(By.xpath("//*[@class='text' and contains(text(),'Alerts')]"),"alertInAccordion");
    private BaseElement alertButton = new BaseElement(By.id("alertButton"), "alertButton");
    private BaseElement confirmButton = new BaseElement(By.id("confirmButton"), "confirmButton");
    private BaseElement confirmAcceptanceText = new BaseElement(By.id("confirmResult"), "confirmAcceptanceText");
    private BaseElement promptButton = new BaseElement(By.id("promtButton"), "promptButton");
    private BaseElement promptAcceptanceText = new BaseElement(By.id("promptResult"), "promptAcceptanceTest");
    public void clickAlertsCard () throws IOException {
        List<WebElement> cardsList = mainPageCards.findElement().findElements(By.xpath("./child::div"));
        cardsList.get(2).click();
    }
    public void clickAlerts() throws IOException {
        alertInAccordion.findElement().click();
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

}
