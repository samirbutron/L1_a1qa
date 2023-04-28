package src.main;
import org.openqa.selenium.By;
import src.browserfactory.BrowserFactory;
import src.utilities.WaitsUtil;
import java.io.IOException;

public class CheckBox extends BaseElement{

    public CheckBox(By uniqueLocator, String elementName) throws IOException {
        super(uniqueLocator, elementName);
    }

    public void check() throws IOException {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        BrowserFactory.getDriver().findElement(uniqueLocator).click();
    }
    public void uncheck() throws IOException {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        if(BrowserFactory.getDriver().findElement(uniqueLocator).isSelected()){
            BrowserFactory.getDriver().findElement(uniqueLocator).click();
        }
    }
    public boolean isCheck() throws IOException {
        WaitsUtil.waitForElementDisplayed(uniqueLocator);
        return BrowserFactory.getDriver().findElement(uniqueLocator).isSelected();
    }
}
