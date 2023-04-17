package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import src.browserfactory.Browser;
import src.utilities.WaitsUtil;

import java.io.IOException;

public class BaseForm {
    private By uniqueFormLocator;
    private String formName;
    private Browser browser;
    private WaitsUtil waits;

    public BaseForm(By uniqueFormLocator, String formName) throws IOException {
        this.uniqueFormLocator = uniqueFormLocator;
        this.formName = formName;
        browser = Browser.getInstance();
        waits = new WaitsUtil();
    }

    public boolean isFormOpen() throws IOException {
        try {
            waits.waitForElementDisplayed(uniqueFormLocator);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

     public Label getFormLabel() throws IOException {
        return new Label(uniqueFormLocator, formName + "unique locator");
     }
}
