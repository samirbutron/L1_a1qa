package src.main;

import org.openqa.selenium.By;
import src.browserfactory.Browser;
import src.utilities.WaitsUtil;

import java.io.IOException;

public class CheckBox extends BaseElement{

    private Browser browser;
    private WaitsUtil waits = new WaitsUtil();

    public CheckBox(By uniqueLocator, String elementName) throws IOException {
        super(uniqueLocator, elementName);
    }

    public void check() throws IOException {
        waits.waitForElementDisplayed(uniqueLocator);
        browser.getDriver().findElement(uniqueLocator).click();
    }
    public void uncheck() throws IOException {
        waits.waitForElementDisplayed(uniqueLocator);
        if(browser.getDriver().findElement(uniqueLocator).isSelected()){
            browser.getDriver().findElement(uniqueLocator).click();
        }
    }
    public boolean isCheck() throws IOException {
        waits.waitForElementDisplayed(uniqueLocator);
        return browser.getDriver().findElement(uniqueLocator).isSelected();
    }
}
