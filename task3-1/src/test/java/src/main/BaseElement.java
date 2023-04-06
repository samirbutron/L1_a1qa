package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseElement {
    private By uniqueLocator;
    private String elementName;
    private WebDriver driver;
    private WaitsUtil waits;

    public BaseElement(By uniqueLocator, String elementName) {
        this.uniqueLocator = uniqueLocator;
        this.elementName = elementName;
    }

    /*public WebElement getElement(){
        return driver.findElement(uniqueLocator);
    }

    public void click(){
        waits.waitForElementPresence(uniqueLocator);
        waits.waitForElementToBeClickable(uniqueLocator);
        getElement().click();
    }

    public boolean isElementPresent(){
        return waits.waitForElementPresence(uniqueLocator);
    }*/


}
