package com.sbutron;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SteamPage{

    //All page element relates to actions must be described here
    @FindBy(xpath = "//div[@id='footer']")
    @CacheLookup
    private WebElement footer;
    @FindBy(xpath = "//div[@id='footer_text']/child::div//a[1]")
    @CacheLookup
    private WebElement privacyPolicyButton;
    @FindBy(className = "blockbg")
    @CacheLookup
    private WebElement privacyPolicyHeader;
    @FindBy(id = "languages")
    @CacheLookup
    private WebElement privacyLanguages;
    @FindBy(xpath = "//*[@id=\"languages\"]/a[1]")
    @CacheLookup
    private WebElement privacyLanguageEn;
    @FindBy(xpath = "//*[@id=\"languages\"]/a[2]")
    @CacheLookup
    private WebElement privacyLanguageEs;
    @FindBy(xpath = "//*[@id=\"languages\"]/a[3]")
    @CacheLookup
    private WebElement privacyLanguageFr;
    @FindBy(xpath = "//*[@id=\"languages\"]/a[4]")
    @CacheLookup
    private WebElement privacyLanguageDe;
    @FindBy(xpath = "//*[@id=\"languages\"]/a[5]")
    @CacheLookup
    private WebElement privacyLanguageIt;
    @FindBy(xpath = "//*[@id=\"languages\"]/a[6]")
    @CacheLookup
    private WebElement privacyLanguageRu;
    @FindBy(xpath = "//*[@id=\"languages\"]/a[7]")
    @CacheLookup
    private WebElement privacyLanguageJp;
    @FindBy(xpath = "//*[@id=\"languages\"]/a[8]")
    @CacheLookup
    private WebElement privacyLanguagePt;
    @FindBy(xpath = "//*[@id=\"languages\"]/a[9]")
    @CacheLookup
    private WebElement privacyLanguageBr;
    @FindBy(xpath = "//*[@id=\"newsColumn\"]/i[3]")
    @CacheLookup
    private WebElement privacySigned;
    @FindBy(id = "store_nav_search_term")
    @CacheLookup
    private WebElement searchBar;
    @FindBy(xpath = "//input[@id='term' and @name='displayterm']") //div[@class='searchbar_left']/input
    @CacheLookup
    private WebElement searchBoxResult;
    @FindBy(xpath = "//div[@id='search_resultsRows']/child::a[1]")
    @CacheLookup
    private WebElement firstResult;

    public SteamPage(){
        PageFactory.initElements(WebDriverSingleton.getInstance(), this);
    }
    private WebDriver getDriver(){
        return WebDriverSingleton.getInstance();
    }
    private WebElement findElement(By locator){
        return getDriver().findElement(locator);
    }
    public void navigateToPrivacyPolicy(){ privacyPolicyButton.click();}
    public boolean isPrivacyPolicyOpened(){ return privacyPolicyHeader.isDisplayed();
        //Could also change to .contains("/privacy_agreement/")
    }
    public WebElement getFooter(){
        return footer;
    }

    public boolean isPrivacyLanguagesListDisplayed(){ return privacyLanguages.isDisplayed(); }
    public boolean isPrivacyLanguagesSwitchDisplayed(){
        return privacyLanguageEn.isDisplayed() && privacyLanguageEs.isDisplayed() && privacyLanguageFr.isDisplayed() && privacyLanguageDe.isDisplayed() &&
                privacyLanguageIt.isDisplayed() && privacyLanguageRu.isDisplayed() && privacyLanguageJp.isDisplayed() && privacyLanguagePt.isDisplayed() &&
                privacyLanguageBr.isDisplayed();
    }
    public boolean isPrivacySigned(String year){
        return privacySigned.getText().contains(year);
    }
    public void searchInStore(String game){
        try {
            searchBar.sendKeys(game);
            searchBar.submit();
        } catch(StaleElementReferenceException e){
            searchBar = findElement(By.id("store_nav_search_term"));
            searchBar.sendKeys(game);
            searchBar.submit();
        }
    }
    public boolean isSearchPageDisplayed() {
        return searchBoxResult.isDisplayed();
    }
    public WebElement getSearchBoxResult() {
        return searchBoxResult;
    }

    public boolean isSearchBoxResultSucessful(String search){
        try {
            return searchBoxResult.getAttribute("value").contains(search);
        } catch (StaleElementReferenceException e){
            searchBoxResult = findElement(By.xpath("//input[@id='term' and @name='displayterm']"));
            return searchBoxResult.getAttribute("value").contains(search);
        }
    }


    public boolean isFirstResultMatching(String search) {
        return firstResult.getText().contains(search);
    }

    public GameData saveGameDataInResultPosition(String position) throws ParseException {
        GameData game = new GameData();
        //Get Name
        game.setName(getDriver().findElement(
                By.xpath("//div[@id='search_resultsRows']/a["+position+"]/div[2]/div[1]/span")).getText());

        //Get Date
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, yyyy");
        Date releaseDate = formatter.parse(getDriver().findElement(
                By.xpath("//*[@id=\"search_resultsRows\"]/a["+position+"]/div[2]/div[2]")).getText());
        game.setReleaseDate(releaseDate);


        ArrayList<String> platforms = new ArrayList<>();
        List<WebElement> platformElements = getDriver().findElements(
                By.xpath("//*[@id='search_resultsRows']/a["+position+"]/div[2]/div[1]/div/span"));
        for(WebElement platformElement : platformElements){
            String platform = platformElement.getAttribute("class").replace("platform_img ", "");
            platforms.add(platform);
        }
        game.setPlatforms(platforms);

        //Get review
        String reviewString = getDriver().findElement(By.xpath("//*[@id=\"search_resultsRows\"]/a["+position+"]/div[2]/div[3]/span"))
                .getAttribute("class").replace("search_review_summary ", "");
        Review review;
        switch (reviewString) {
            case "positive":
                review = Review.P;
                break;
            case "mixed":
                review = Review.M;
                break;
            default:
                review = Review.N;
                break;
        }
        game.setReview(review);

        //GetPrice
        String priceString = getDriver().findElement(By.xpath("//*[@id='search_resultsRows']/a["+position+"]/div[2]/div[4]/div[2]")).getText();
        Double price = 0.0;
        String[] priceWords = priceString.split("\n");
        String firstPrice = priceWords[0].trim();
        if(firstPrice.contains("Free")){
            price = 0.0;
        } else {
            String[] priceParts = firstPrice.split(" ");
            if (priceParts[0].equalsIgnoreCase("Mex$")) {
                price = Double.parseDouble(priceParts[1].replace(",", ""));
            } else {
                price = Double.parseDouble(firstPrice.replace(",", ""));
            }
        }
        game.setPrice(price);
        return game;
    }


}