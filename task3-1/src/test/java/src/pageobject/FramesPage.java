package src.pageobject;

import org.openqa.selenium.By;
import src.main.Button;
import src.main.Frame;
import src.main.Label;
import src.main.MyLogger;
import src.utilities.BrowserUtilities;

public class FramesPage {
    private MyLogger logger;
    public void FramesPage (){
        logger = MyLogger.getInstance();
    }
    private Frame frame1 = new Frame(By.id("frame1"), "frame1");
    private Frame frame2 = new Frame(By.id("frame2"), "frame2");
    private Frame frameBody = new Frame(By.xpath("//body"), "frameBody");
    private Button framesInAccordion = new Button(By.xpath("//li[@id='item-2']//*[@class='text' and contains(text(),'Frames')]"),"framesInAccordion");
    private Label framesHeader = new Label(By.className("main-header"),"framesHeader");
    public String getFramesHeader(){
        return framesHeader.getText();
    }
    public void clickFrames () {
        logger.info("Clicking on Frames in left Accordion");
        framesInAccordion.click();
    }
    public String getFrame1Text() {
        logger.info("Getting Frame 1 text");
        BrowserUtilities.switchToFrame(frame1.findElement());
        return frameBody.getText();
    }
    public String getFrame2Text() {
        logger.info("Getting Frame 2 text");
        BrowserUtilities.switchToFrame(frame2.findElement());
        return frameBody.getText();
    }

}
