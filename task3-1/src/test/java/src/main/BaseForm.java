package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseForm {
    private By uniqueFormLocator;
    private String formName;
    private WebDriver driver;
    private Waits waits;

    /*public BaseForm(By uniqueFormLocator, String formName) {
        this.uniqueFormLocator = uniqueFormLocator;
        this.formName = formName;
        driver = Browser.getDriver();
        waits = Browser.getWaits();
    }

    public boolean isFormOpen(){
        return waits.waitForPresenceOfElement(uniqueFormLocator);
    }

     public Label getFormLabel(){
        return new Label(uniqueFormLocator, formName + "unique locator");
     }*/
}
