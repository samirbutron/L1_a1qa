package src.utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import src.browserfactory.BrowserFactory;

import java.util.Set;

public class BrowserUtilities {
    private void BrowserUtil () {

    }
    public static void goToUrl(String url){
        BrowserFactory.getDriver().get(url);
    }

    public static String getTitle() {
        return BrowserFactory.getDriver().getTitle();
    }
    public static boolean isEmergentWindowOpen() {
        return WaitsUtil.waitFor(ExpectedConditions.alertIsPresent()) != null;
    }
    public static boolean acceptAlert() {
        WaitsUtil.waitFor(ExpectedConditions.alertIsPresent()).accept();
        return WaitsUtil.waitFor(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
    }

    public static String getEmergentWindowText() {
        WaitsUtil.waitFor(ExpectedConditions.alertIsPresent());
        return BrowserFactory.getDriver().switchTo().alert().getText();
    }

    public static void insertTextToPopup(String text) {
        WaitsUtil.waitFor(ExpectedConditions.alertIsPresent());
        BrowserFactory.getDriver().switchTo().alert().sendKeys(text);
    }

    public static void switchToFrame(WebElement frame) {
        BrowserFactory.getDriver().switchTo().frame(frame);
    }

    public static String getWindowHandle() {
        return BrowserFactory.getDriver().getWindowHandle();
    }
    public static Set<String> getWindowHandles() {
        return BrowserFactory.getDriver().getWindowHandles();
    }

    public static String getNewTabHandle(Set<String> windowHandles, Set<String> updatedWindowHandles) {
        String newWindowHandle = null;
        for (String windowHandle : updatedWindowHandles) {
            if (!windowHandles.contains(windowHandle)) {
                newWindowHandle = windowHandle;
                break;
            }
        }
        return newWindowHandle;
    }

    public static void toDefaultContent() {
        BrowserFactory.getDriver().switchTo().defaultContent();
    }

}
