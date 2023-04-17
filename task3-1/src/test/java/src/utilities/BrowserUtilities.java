package src.utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import src.browserfactory.Browser;

import java.io.IOException;
import java.util.Set;

public class BrowserUtilities {

    private final Browser browser;
    private final WaitsUtil waits = new WaitsUtil();

    public BrowserUtilities() throws IOException {
        browser = Browser.getInstance();
    }

    public String getTitle() throws IOException {
        return browser.getDriver().getTitle();
    }
    public boolean isEmergentWindowOpen() {
        return waits.waitFor(ExpectedConditions.alertIsPresent()) != null;
    }
    public boolean acceptAlert() {
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

    public void switchToFrame(WebElement frame) throws IOException {
        browser.getDriver().switchTo().frame(frame);
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

    public void toDefaultContent() throws IOException {
        browser.getDriver().switchTo().defaultContent();
    }
}
