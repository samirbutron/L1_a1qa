package com.sbutron;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class SteamPageTest
{

    private WebDriver driver;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ConfigJSON config = objectMapper.readValue(new File("C:\\Users\\samir\\Documents\\s.butron\\2.PageObject\\task2-1\\src\\config\\config.json"), ConfigJSON.class);

    private SteamPage steamPage;
    private WebDriverWait waitTime;


    public SteamPageTest() throws IOException {
    }


    @BeforeTest
    public void setup() { driver = WebDriverSingleton.getInstance();
        driver.manage().timeouts().implicitlyWait(config.getImplicitlyWait(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(config.getPageLoadTimeout(), TimeUnit.SECONDS);
        waitTime = new WebDriverWait(driver,Duration.ofSeconds(config.getExplicitWait()));
        steamPage = new SteamPage();
    }

    @AfterTest
    public void teardown(){
        WebDriverSingleton.quitInstance();
    }

    @Test
    public void Test(){
        driver.get("https://store.steampowered.com");
    }

    @Test
    public void PrivacyPolicy(){
        //Step 1 -> Navigate to main page
        driver.get("https://store.steampowered.com");
        waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[5]")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertTrue(steamPage.isMainPageOpened(), "Main page didn't opened");
        //Step 2 -> Scroll and open Privacy Policy
        new Actions(driver).scrollToElement(steamPage.getFooter()).perform(); //Maybe find a better way to navigate to footer?
        steamPage.navigateToPrivacyPolicy();

        //Privacy policy is open in new tab
        String newTab = driver.getWindowHandle();
        Assert.assertEquals(driver.getWindowHandles().size() == 2, 2, "New Tab didn't opened");

        driver.switchTo().window(newTab);
        Assert.assertTrue(steamPage.isPrivacyPolicyOpened(), "Privacy Policy page didn't opened");
        //Switch language elements list displayed
        Assert.assertTrue(steamPage.isPrivacyLanguagesListDisplayed(), "Privacy Languages switch didn't displayed");
        //English, Spanish French support
        Assert.assertTrue(steamPage.isPrivacyLanguagesSwitchDisplayed(), "Privacy Policy language support didn't displayed");
        //Policy revision signed in the current year
        Assert.assertTrue(steamPage.isPrivacySigned(config.getYear()), " Privacy year is outdated");
    }

    @Test
    public void GameSearch(){
        //Step 1 -> Navigate main page
        driver.get(config.getUrl());
        Assert.assertTrue(steamPage.isMainPageOpened(), "Main page didn't opened");
        //Step 2 -> Search "Dota 2" in the search field
        steamPage.searchInStore(config.getGame());
            //Result page opens
        Assert.assertTrue(steamPage.isSearchPageDisplayed());
            //Search box on result page contains searched name
        Assert.assertTrue(steamPage.isSearchBoxResultSucessful(config.getGame()));
            //The first name is equal to searched name
        Assert.assertTrue(steamPage.isFirstResultMatching(config.getGame()));
        //Step 3 -> Save infomration about the 1st and 2nd results from the list (name, platforms, release date, review, summary, result, price)

        //Step 4 -> Search the second name (recieved from result list) in the search field in the header
            //Search box on result page contains searched name
            //Result list conains 2 stored items from the previous search. All stored data are matched
    }
}
