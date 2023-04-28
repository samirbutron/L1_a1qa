package src.main;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import src.utilities.WaitsUtil;

public class BaseForm {
    private By uniqueFormLocator;
    private String formName;

    public BaseForm(By uniqueFormLocator, String formName) {
        this.uniqueFormLocator = uniqueFormLocator;
        this.formName = formName;
    }

    public boolean isFormOpen() {
        try {
            WaitsUtil.waitForElementDisplayed(uniqueFormLocator);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

     public Label getFormLabel() {
        return new Label(uniqueFormLocator, formName + "unique locator");
     }
}
