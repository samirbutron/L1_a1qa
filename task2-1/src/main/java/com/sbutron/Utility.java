package com.sbutron;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Utility {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConfigJSON config;
    private WebDriverWait waitTime;

    public Utility() throws IOException {
        this.config = objectMapper.readValue(new File("C:\\Users\\samir\\Documents\\s.butron\\2.PageObject\\task2-1\\src\\config\\config.json"), ConfigJSON.class);
    }

    public boolean checkSupportedLanguages(List<WebElement> elementList){
        for(WebElement languageElement : elementList) {
            if(!languageElement.isDisplayed()){
                return false;
            }
        }
        return true;
    }

    public String getSecondTabHandle(Set<String> windowHandles, Set<String> updatedWindowHandles) {
        String newWindowHandle = null;
        for (String windowHandle : updatedWindowHandles) {
            if (!windowHandles.contains(windowHandle)) {
                newWindowHandle = windowHandle;
                break;
            }
        }
        return newWindowHandle;
    }

    public void waitUntilWindows(int number, WebDriver driver){
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(config.getExplicitWait()));
        waitTime.until(ExpectedConditions.numberOfWindowsToBe(number));
    }
    public void waitForElement(WebElement element, WebDriver driver){
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(config.getExplicitWait()));
        waitTime.until(ExpectedConditions.visibilityOf(element));
    }
}
