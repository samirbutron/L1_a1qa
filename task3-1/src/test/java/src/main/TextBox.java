package src.main;

import org.openqa.selenium.By;
import src.browserfactory.Browser;
import src.utilities.WaitsUtil;

import java.io.IOException;

public class TextBox extends BaseElement{

    private Browser browser;
    private WaitsUtil waits = new WaitsUtil();

    public TextBox(By uniqueLocator, String elementName) throws IOException {
        super(uniqueLocator, elementName);
    }

    public void type(String text) throws IOException {
        waits.waitForElementDisplayed(uniqueLocator);
        browser.getDriver().findElement(uniqueLocator).sendKeys(text);
    }

    public void clear() throws IOException {
        waits.waitForElementDisplayed(uniqueLocator);
        browser.getDriver().findElement(uniqueLocator).clear();
    }
}
