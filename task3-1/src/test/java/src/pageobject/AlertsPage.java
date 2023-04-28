package src.pageobject;

import org.openqa.selenium.By;
import src.main.*;

public class AlertsPage {
    private MyLogger logger = MyLogger.getInstance();
    public AlertsPage() {
    }
    private Button alertsInAccordion = new Button(By.xpath("//*[@class='text' and contains(text(),'Alerts')]"),"alertInAccordion");
    private Button alertButton = new Button(By.id("alertButton"), "alertButton");
    private Button confirmButton = new Button(By.id("confirmButton"), "confirmButton");
    private Label confirmAcceptanceText = new Label(By.id("confirmResult"), "confirmAcceptanceText");
    private Button promptButton = new Button(By.id("promtButton"), "promptButton");
    private Label promptAcceptanceText = new Label(By.id("promptResult"), "promptAcceptanceTest");
    private Label alertsHeader = new Label(By.className("main-header"),"alertsHeader");
    public String getAlertsHeaderText(){
        return alertsHeader.getText();
    }

    public void clickAlerts() {
        logger.info("Clicking on Alerts in left Accordion");
        alertsInAccordion.click();
    }
    public void clickAlertButton() {
        logger.info("Clicking on 'Click Button to see alert' button");
        alertButton.click();
    }
    public void clickConfirmButton () {
        logger.info("Clicking on 'On button click, confirm box will appear' button");
        confirmButton.click();
    }
    public String getAcceptancePopupText() {
        logger.info("Getting Confirm box acceptance text");
        return confirmAcceptanceText.getText();
    }
    public void clickOnPromptButton() {
        logger.info("Clicking on 'On button click, prompt box will appear' button");
        promptButton.click();
    }
    public String getPromptButtonAcceptanceText() {
        logger.info("Getting Prompt box acceptance text");
        return promptAcceptanceText.getText();
    }
}
