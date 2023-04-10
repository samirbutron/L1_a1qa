package src.main;

import org.openqa.selenium.By;

import java.io.IOException;

public class Label extends BaseElement{

    public Label(By uniqueLocator, String elementName) throws IOException {
        super(uniqueLocator, elementName);
    }
}
