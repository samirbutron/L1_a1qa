package src.main;

import org.openqa.selenium.By;

import java.io.IOException;

public class Button extends BaseElement{

    public Button(By uniqueLocator, String elementName) throws IOException {
        super(uniqueLocator, elementName);
    }
}
