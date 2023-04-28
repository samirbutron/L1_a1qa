package src.utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.browserfactory.BrowserFactory;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class WaitsUtil {
    private static WebDriverWait waitTime;
    private SettingsReader testconfig = new SettingsReader("src/test/java/src/config/testconfig.json");
    private int timeOutInSeconds;
    private int pollingIntervalInSeconds;

    private WaitsUtil() {
        timeOutInSeconds = testconfig.getInt("TIMEOUT_IN_SECONDS");
        pollingIntervalInSeconds = testconfig.getInt("POLLING_INTERVAL_IN_SECONDS");
        waitTime = new WebDriverWait(BrowserFactory.getDriver(),Duration.ofSeconds(timeOutInSeconds));
    }
    static{
        new WaitsUtil();
    }
    public static boolean waitForElementDisplayed(By locator) {
        try {
            waitTime.until(ExpectedConditions.visibilityOf(BrowserFactory.getDriver().findElement(locator))).isDisplayed();
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public static boolean waitForElementToBeClickable(By locator){
        try {
            waitTime.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static <T> T waitForTrue(Function<Boolean,T> condition) {
        return waitTime.until((Function<WebDriver, T>) webDriver -> condition.apply(true));
    }

    public static <T> T waitFor(ExpectedCondition<T> condition){
        return waitTime.until(condition);
    }


}
