package src.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import src.browserfactory.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Set;

public class BrowserUtilities {

    private Browser browser;
    private WaitsUtil waits = new WaitsUtil();
    //FIXME borrar logger?
    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserUtilities.class);

    public BrowserUtilities() throws IOException {
        browser = Browser.getInstance();
    }

    public String getTitle() throws IOException {
        return browser.getDriver().getTitle();
    }
    public boolean isEmergentWindowOpen() {
        return waits.waitFor(ExpectedConditions.alertIsPresent()) != null;
    }
    public boolean acceptAlert() throws IOException {
        waits.waitFor(ExpectedConditions.alertIsPresent()).accept();
        return waits.waitFor(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
    }

    public String getEmergentWindowText() throws IOException {
        waits.waitFor(ExpectedConditions.alertIsPresent());
        return browser.getDriver().switchTo().alert().getText();
    }

    public void insertTextToPopup(String text) throws IOException {
        waits.waitFor(ExpectedConditions.alertIsPresent());
        browser.getDriver().switchTo().alert().sendKeys(text);
    }

    public void switchToFrame() throws IOException {
        browser.getDriver().switchTo().frame(1);
    }
    public String getframeText() throws IOException {
        browser.getDriver().switchTo().frame(1);
        return browser.getDriver().findElement(By.tagName("body")).getText();
    }

    public String getChildIframeText() throws IOException {
        int size = browser.getDriver().findElements(By.tagName("iframe")).size();
        LOGGER.debug(String.valueOf(size));
        browser.getDriver().switchTo().frame(0);
        //WebElement frame2 = browser.getDriver().findElement(By.tagName("iframe"));
        //browser.getDriver().switchTo().frame(frame2);
        return browser.getDriver().findElement(By.tagName("body")).getText();
    }

    public String getWindowHandle() throws IOException {
        return browser.getDriver().getWindowHandle();
    }
    public Set<String> getWindowHandles() throws IOException {
        return browser.getDriver().getWindowHandles();
    }

    public String getNewTabHandle(Set<String> windowHandles, Set<String> updatedWindowHandles) {
        String newWindowHandle = null;
        for (String windowHandle : updatedWindowHandles) {
            if (!windowHandles.contains(windowHandle)) {
                newWindowHandle = windowHandle;
                break;
            }
        }
        return newWindowHandle;
    }

}
