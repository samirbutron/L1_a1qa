package com.sbutron;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class SteamPageTest
{

    private WebDriver driver;
    private final String url = "https://store.steampowered.com";


    @BeforeTest
    public void setup() {
        driver = WebDriverSingleton.getInstance();
    }

    @AfterTest
    public void teardown(){
        WebDriverSingleton.quitInstance();
    }

    @Test
    public void PrivacyPolicy(){
        //Step 1 -> Navigate to main page
        driver.get(url);
        //Assert.assertTrue();
        //Step 2 -> Scroll and open Privacy Policy
        //Privacy policy is open in new tab
        //Swithc language elements list displayed
        //English, Spanish French support
        //Policy revision signed in the current year

    }

    @Test
    public void GameSearch(){
        //Step 1 -> Navigate main page

        //Step 2 -> Search "Dota 2" in the search field
            //Result page opens
            //Search box on result page contains searched name
            //The first name is equal to searched name

        //Step 3 -> Save infomration about the 1st and 2nd results from the list (name, platforms, release date, review, summary, result, price)

        //Step 4 -> Search the second name (recieved from result list) in the search field in the header
            //Search box on result page contains searched name
            //Result list conains 2 stored items from the previous search. All stored data are matched
    }
}
