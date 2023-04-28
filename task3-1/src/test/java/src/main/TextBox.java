package src.main;

import org.openqa.selenium.By;
import src.browserfactory.BrowserFactory;
import src.utilities.WaitsUtil;

public class TextBox extends BaseElement{

    public TextBox(By uniqueLocator, String elementName) {
        super(uniqueLocator, elementName);
    }

    public void type(String text) {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        BrowserFactory.getDriver().findElement(uniqueLocator).sendKeys(text);
    }

    public void clear() {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        BrowserFactory.getDriver().findElement(uniqueLocator).clear();
    }
}
