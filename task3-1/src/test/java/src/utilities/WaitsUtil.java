package src.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.browserfactory.Browser;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class WaitsUtil {
    private WebDriverWait waitTime;
    private Browser browser;
    private SettingsReader testconfig = new SettingsReader("src/test/src/config/testconfig.json");
    private int timeOutInSeconds;
    private int pollingIntervalInSeconds;

    public WaitsUtil() throws IOException {
        browser = Browser.getInstance();
        timeOutInSeconds = testconfig.getInt("TIMEOUT_IN_SECONDS");
        pollingIntervalInSeconds = testconfig.getInt("POLLING_INTERVAL_IN_SECONDS");
        waitTime = new WebDriverWait(browser.getDriver(),Duration.ofSeconds(timeOutInSeconds));
    }
    public boolean waitForElementDisplayed(By locator) throws IOException {
        try {
            waitTime.until(ExpectedConditions.visibilityOf(browser.getDriver().findElement(locator))).isDisplayed();
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean waitForElementToBeClickable(By locator){
        try {
            waitTime.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public <T> T waitForTrue(Function<Boolean,T> condition) throws IOException {
        return waitTime.until((Function<WebDriver, T>) webDriver -> condition.apply(true));
    }

    public <T> T waitFor(ExpectedCondition<T> condition){
        return waitTime.until(condition);
    }


}
