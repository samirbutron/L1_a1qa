package com.sbutron;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.Set;


public class SteamPageTest
{

    private WebDriver driver;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConfigJSON config = objectMapper.readValue(new File("C:\\Users\\samir\\Documents\\s.butron\\2.PageObject\\task2-1\\src\\config\\config.json"), ConfigJSON.class);

    private SteamPage steamPage;
    private WebDriverWait waitTime;


    public SteamPageTest() throws IOException {
    }


    @BeforeTest
    public void setup() {
        driver = WebDriverSingleton.getInstance();
        waitTime = new WebDriverWait(driver,Duration.ofSeconds(config.getExplicitWait()));
        steamPage = new SteamPage();
    }

    @AfterTest
    public void teardown(){
        WebDriverSingleton.quitInstance();
    }

    @Test
    public void PrivacyPolicy(){
        //Step 1 -> Navigate to main page
        driver.get(config.getUrl());
        //Step 2 -> Scroll and open Privacy Policy
        new Actions(driver).scrollToElement(steamPage.getFooter()).perform();
        Set<String> windowHandles = driver.getWindowHandles();
        steamPage.navigateToPrivacyPolicy();
        //Privacy policy is open in new tab
        waitTime.until(ExpectedConditions.numberOfWindowsToBe(2));
        Assert.assertEquals(driver.getWindowHandles().size(), 2, "New Tab didn't opened");
        Set<String> updatedWindowHandles = driver.getWindowHandles();
        String newWindowHandle = null;
        for (String windowHandle : updatedWindowHandles) {
            if (!windowHandles.contains(windowHandle)) {
                newWindowHandle = windowHandle;
                break;
            }
        }
        driver.switchTo().window(newWindowHandle);
        Assert.assertTrue(steamPage.isPrivacyPolicyOpened(), "Privacy Policy page didn't opened");
        //Switch language elements list displayed
        Assert.assertTrue(steamPage.isPrivacyLanguagesListDisplayed(), "Privacy Languages switch didn't displayed");
        //English, Spanish French support
        Assert.assertTrue(steamPage.isPrivacyLanguagesSwitchDisplayed(), "Privacy Policy language support didn't displayed");
        //Policy revision signed in the current year
        Assert.assertTrue(steamPage.isPrivacySigned(config.getYear()), " Privacy year is outdated");
    }

    @Test
    public void GameSearch() throws ParseException {
        //Step 1 -> Navigate main page
        driver.get(config.getUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains(config.getUrl()), "Main page didn't opened");
        //Step 2 -> Search "Dota 2" in the search field
        steamPage.searchInStore(config.getGame());
            //Result page opens
        waitTime.until(ExpectedConditions.visibilityOf(steamPage.getSearchBoxResult()));
        Assert.assertTrue(steamPage.isSearchPageDisplayed(), "Search page didn't loaded");
            //Search box on result page contains searched name
        Assert.assertTrue(steamPage.isSearchBoxResultSucessful(config.getGame()), "Game didn't appear in search box");
            //The first name is equal to searched name
        Assert.assertTrue(steamPage.isFirstResultMatching(config.getGame()), "Game searched isn't the first result");
        //Step 3 -> Save information about the 1st and 2nd results from the list (name, platforms, release date, review, summary, result, price)
        GameData game1 = new GameData();
        GameData game2;
        game1 = steamPage.saveGameDataInResultPosition("1");
        game2 = steamPage.saveGameDataInResultPosition("2");
        //Step 4 -> Search the second name (received from result list) in the search field in the header
        steamPage.searchInStore(game2.getName());
        //waitTime.until(ExpectedConditions.visibilityOf(steamPage.getSearchBoxResult()));
            //Search box on result page contains searched name
        Assert.assertTrue(steamPage.isSearchBoxResultSucessful(game2.getName()), "Game didn't appear in search box");
            //Result list contains 2 stored items from the previous search. All stored data are matched
        GameData game3;
        game3 = steamPage.saveGameDataInResultPosition("1");
        Assert.assertEquals(game3.hashCode(), game2.hashCode(), "Game data is not the same");

    }
}