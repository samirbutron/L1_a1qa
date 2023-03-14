package com.sbutron;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;


public class SteamPageTest
{

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().addArguments(("--remote-allow-origins=*"));
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }

    @Test
    public void InvalidLogin(){
        //Step 1 -> Navigate to main page
        driver.get("https://store.steampowered.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String mainTitle = driver.getTitle();
        Assert.assertNotNull(mainTitle, "Title is not displayed, page may not have loaded");

        //Step 2 -> Click login button
        WebElement loginButton = driver.findElement(By.className("global_action_link"));
        loginButton.click();
        Assert.assertTrue(driver.findElement(By.className("responsive_page_content")).isDisplayed());

        //Step 3 -> Input random string as credentials and click sign in button
        WebElement joinLogo = driver.findElement(By.xpath("//html/body/div[1]/div[7]/div[6]/div/div[2]/img")); //Couldn't find Loading element
        Assert.assertTrue(joinLogo.isDisplayed(), "Join-Steam logo didn't displayed");
        WebElement userPasswordBox = driver.findElement(By.xpath("//input[@type='password' and @class='newlogindialog_TextInput_2eKVn']"));
        WebElement userNameBox = driver.findElement(By.xpath("//input[@type='text' and @class='newlogindialog_TextInput_2eKVn']"));
        userPasswordBox.sendKeys("TestPassword");
        userNameBox.sendKeys("TestUser");

        WebElement submitButton = driver.findElement(By.className("newlogindialog_SignInButtonContainer_14fsn"));
        submitButton.click();

        WebElement formError = driver.findElement(By.className("newlogindialog_FormError_1Mcy9"));
        String signInMessage = formError.getText();
        Assert.assertNotNull(signInMessage, "Error not prompted");
    }
}
