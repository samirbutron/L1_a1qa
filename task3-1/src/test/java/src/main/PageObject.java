package src.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class PageObject {

    public PageObject() throws IOException {

    }

    private BaseElement alertsCard = new BaseElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div[3]"), "alertsCard");

    public boolean alertsCardDisplayed() throws IOException {
        return alertsCard.findElement().isDisplayed();
    }
}
