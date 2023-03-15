import com.sbutron.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class SteamPage{

    private WebElement privacyPolicyButton = WebDriverSingleton.getInstance().findElement(By.XPath("//div[@id='footer_text']/child::div//a[1]"));

    //public MainPage(WebDriver webDriver){
    //    driver = webDriver;
    //}

    //public bool isLoginPageOpened(){

    //}

}