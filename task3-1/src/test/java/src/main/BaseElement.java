package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


}
