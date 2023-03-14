package com.sbutron;

import java.time.Duration;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class SteamPageTest
{

    @BeforeTest
    public void setup() {
        //WebDriverManager.edgedriver().setup();
        //WebDriver driver = new ChromeDriver();
    }

    //WebDriver driver = new ChromeDriver();
    //ChromeOptions options = new ChromeOptions();
    //options.addArguments("--remote-allow-origins=*");

    @AfterTest
    public void teardown(){
        //driver.quit();
    }



    // Test scenario "Invalid Login"

    //Method could be renamed to: assertMainPageTitleIsNotNull
    @Test
    public void mainPageIsDisplayed(){
        //WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://store.steampowered.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String mainTitle = driver.getTitle();
        //System.out.println(mainTitle); //Helps test the String obtain, check if output is the one desired
        Assert.assertNotNull(mainTitle);
        driver.quit();
    }

    //Method could be renamed to: assertLoginTitleIsNotNull
    @Test
    public void loginPageOpen(){
        //WebDriverManager.chromedriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://store.steampowered.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement loginButton = driver.findElement(By.className("global_action_link"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String loginTitle = driver.getTitle();
        //System.out.println(loginTitle); //Helps test the String obtain, check if output is the one desired
        Assert.assertNotNull(loginTitle);
    }

    @Test
    public void invalidLoginAttempt () {
        //WebDriverManager.chromedriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://store.steampowered.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement loginButton = driver.findElement(By.className("global_action_link"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement userPasswordBox = driver.findElement(By.xpath("//html/body/div[1]/div[7]/div[6]/div/div[1]/div/div/div/div[2]/div/form/div[1]/input"));

        WebElement userNameBox = driver.findElement(By.xpath("//html/body/div[1]/div[7]/div[6]/div/div[1]/div/div/div/div[2]/div/form/div[2]/input"));

        userPasswordBox.sendKeys("TestPassword");
        userNameBox.sendKeys("TestUser");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement submitButton = driver.findElement(By.className("newlogindialog_SignInButtonContainer_14fsn"));
        submitButton.click();

        WebElement formError = driver.findElement(By.className("newlogindialog_FormError_1Mcy9"));
        String signInMessage = formError.getText();
        //System.out.println(signInMessage); ////Helps test the String obtain, check if output is the one desired
        Assert.assertNotNull(signInMessage);
    }
}
