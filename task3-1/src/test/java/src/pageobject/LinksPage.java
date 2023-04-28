package src.pageobject;

import org.openqa.selenium.By;
import src.main.Button;
import src.main.Label;
import src.main.Link;
import src.main.MyLogger;

public class LinksPage {
    private MyLogger logger;
    public LinksPage(){
        logger = MyLogger.getInstance();
    }
    private Button linksInAccordion = new Button(By.xpath("//*[@class='text' and contains(text(),'Links')]"), "linksInAccordion");
    private Link simpleLink = new Link(By.id("simpleLink"), "simpleLink");
    private Label linksHeader = new Label(By.className("main-header"),"linksHeader");
    public String getLinksHeader(){
        return linksHeader.getText();
    }

    public void clickLinks() {
        logger.info("Clicking on Links in left Accordion");
        linksInAccordion.click();
    }
    public void clickHomeLink() {
        logger.info("Clicking on 'Home' links");
        simpleLink.click();
    }

}
