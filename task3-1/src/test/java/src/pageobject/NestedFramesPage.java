package src.pageobject;

import org.openqa.selenium.By;
import src.main.Frame;
import src.main.Label;
import src.main.MyLogger;
import src.utilities.BrowserUtilities;

public class NestedFramesPage {
    private MyLogger logger;
    public NestedFramesPage() {
        logger = MyLogger.getInstance();
    }
    private Frame nestedFramesInAccordion = new Frame(By.xpath("//*[@class='text' and contains(text(),'Nested Frames')]"), "nestedFramesInAccordion");
    private Frame frame1 = new Frame(By.id("frame1"), "frame1");
    private Frame childIframe = new Frame(By.tagName("iframe"), "nestedIframe");
    private Frame frameBody = new Frame(By.xpath("//body"), "frameBody");
    private Label nestedFramesHeader = new Label(By.className("main-header"),"nestedFramesHeader");
    public String getNestedFramesHeader(){
        return nestedFramesHeader.getText();
    }
    public void clickNestedFrames() {
        logger.info("Clicking on Nested Frames in left Accordion");
        nestedFramesInAccordion.click();
    }
    public String getFrame1Text() {
        logger.info("Getting Frame 1 text");
        BrowserUtilities.switchToFrame(frame1.findElement());
        return frameBody.getText();
    }
    public String getIFrameText() {
        logger.info("Getting Indented Frame in Frame1 text");
        BrowserUtilities.switchToFrame(childIframe.findElement());
        return frameBody.getText();
    }
}
