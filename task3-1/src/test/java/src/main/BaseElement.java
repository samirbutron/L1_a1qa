package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import src.browserfactory.Browser;
import src.utilities.WaitsUtil;

import java.io.IOException;

public class BaseElement {

    private Browser browser;
    private WaitsUtil waits = new WaitsUtil();
    protected By uniqueLocator;
    protected String elementName;

    public BaseElement(By uniqueLocator, String elementName) throws IOException {
        this.uniqueLocator = uniqueLocator;
        this.elementName = elementName;
        browser = Browser.getInstance();
    }

    public WebElement findElement() throws IOException {
        waits.waitForElementDisplayed(uniqueLocator);
        return browser.getDriver().findElement(uniqueLocator);
    }

    public void click() throws IOException {
        waits.waitForElementDisplayed(uniqueLocator);
        waits.waitForElementToBeClickable(uniqueLocator);
        browser.getDriver().findElement(uniqueLocator).click();
    }

    public String getText() throws IOException {
        waits.waitForElementDisplayed(uniqueLocator);
        return browser.getDriver().findElement(uniqueLocator).getText();
    }

    public boolean isElementDisplayed() throws IOException {
        return waits.waitForElementDisplayed(uniqueLocator);
    }

    public String getAttribute(String attribute) throws IOException {
        waits.waitForElementDisplayed(uniqueLocator);
        return browser.getDriver().findElement(uniqueLocator).getAttribute(attribute);
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
}
