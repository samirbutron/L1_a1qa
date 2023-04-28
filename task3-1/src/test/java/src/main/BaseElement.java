package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import src.browserfactory.BrowserFactory;
import src.utilities.WaitsUtil;
import java.util.List;

public abstract class BaseElement {
    protected By uniqueLocator;
    protected String elementName;

    public BaseElement(By uniqueLocator, String elementName) {
        this.uniqueLocator = uniqueLocator;
        this.elementName = elementName;
    }

    public WebElement findElement() {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        return BrowserFactory.getDriver().findElement(uniqueLocator);
    }
    public List<WebElement> findElements() {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        return BrowserFactory.getDriver().findElements(uniqueLocator);
    }

    public void click() {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        WaitsUtil.waitForElementToBeClickable(uniqueLocator);
        BrowserFactory.getDriver().findElement(uniqueLocator).click();
    }

    public String getText() {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        return BrowserFactory.getDriver().findElement(uniqueLocator).getText();
    }

    public void sendKeys(String text) {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        BrowserFactory.getDriver().findElement(uniqueLocator).sendKeys(text);
    }

    public boolean isElementDisplayed() {
        return WaitsUtil.waitForElementDisplayed(uniqueLocator);
    }

    public String getAttribute(String attribute) {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        return BrowserFactory.getDriver().findElement(uniqueLocator).getAttribute(attribute);
    }
}
