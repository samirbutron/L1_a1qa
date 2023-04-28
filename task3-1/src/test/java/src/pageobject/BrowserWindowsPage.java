package src.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import src.main.Button;
import src.main.Label;
import src.main.MyLogger;
import java.util.List;

public class BrowserWindowsPage {
    private MyLogger logger;
    public BrowserWindowsPage(){
        logger = MyLogger.getInstance();
    }
    private Button browserWindowsInAccordion = new Button(By.xpath("//*[@class='text' and contains(text(),'Browser Windows')]"), "browserWindowsInAccordion");
    private Button newTabButton = new Button(By.id("tabButton"), "newTabButton");
    private Label browserWindowsHeader = new Label(By.className("main-header"),"browserWindowsHeader");
    public String getbrowserWindowsHeader(){
        return browserWindowsHeader.getText();
    }

    public void clickBrowserWindows() {
        logger.info("Clicking on Browser Windows in left Accordion");
        browserWindowsInAccordion.click();
    }
    public void clickNewTabButton() {
        logger.info("Clicking on 'New Tab' button");
        newTabButton.click();
    }

    private Button leftPanelAccordion = new Button(By.className("accordion"),"leftPanelAccordion");
    public void clickOnAccordionPosition(int position) {
        logger.info("Clicking on Accordion Element in position: "+position);
        List<WebElement> listAccordion = leftPanelAccordion.findElement().findElements(By.className("element-group"));
        listAccordion.get(position).click();
    }
}
