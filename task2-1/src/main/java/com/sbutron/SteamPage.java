package com.sbutron;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SteamPage{

    //All page element relates to actions must be described here
    private WebElement footer;
    private WebElement privacyPolicyButton;
    private WebElement privacyPolicyHeader;
    private WebElement privacyLanguages;
    private List<WebElement> privacySupportedLanguages;
    private WebElement privacySigned;
    private WebElement searchBar;
    private WebElement searchBoxResult;
    private WebElement firstResult;

    private WebElement getFooter() {
        if(footer == null){
                    footer =getDriver().findElement(By.xpath("//div[@id='footer']"));
                }
    return footer;
    }
    private WebElement getPrivacyPolicyButton() {
        if (privacyPolicyButton == null) {
            privacyPolicyButton = getDriver().findElement(By.xpath("//div[@id='footer_text']/child::div//a[1]"));
        }
        return privacyPolicyButton;
    }

    private WebElement getPrivacyPolicyHeader() {
        if (privacyPolicyHeader == null) {
            privacyPolicyHeader = getDriver().findElement(By.className("blockbg"));
        }
        return privacyPolicyHeader;
    }

    private WebElement getPrivacyLanguages() {
        if (privacyLanguages == null) {
            privacyLanguages = getDriver().findElement(By.id("languages"));
        }
        return privacyLanguages;
    }

    private List<WebElement> getPrivacySupportedLanguages() {
        if (privacySupportedLanguages == null) {
            privacySupportedLanguages = getDriver().findElements(By.xpath("//*[@id='languages']/a"));
        }
        return privacySupportedLanguages;
    }

    private WebElement getPrivacySigned() {
        if (privacySigned == null) {
            privacySigned = getDriver().findElement(By.xpath("//*[@id='newsColumn']/i[3]"));
        }
        return privacySigned;
    }

    private WebElement getSearchBar() {
            searchBar = getDriver().findElement(By.id("store_nav_search_term"));
        return searchBar;
    }

    private WebElement getSearchBoxResult() {
            searchBoxResult = getDriver().findElement(By.xpath("//*[@id='term']"));
        return searchBoxResult;
    }

    private WebElement getFirstResult() {
        if (firstResult == null) {
            firstResult = getDriver().findElement(By.xpath("//div[@id='search_resultsRows']/child::a[1]"));
        }
        return firstResult;
    }

    public SteamPage() throws IOException {
    }
    private WebDriver getDriver(){
        try {
            return WebDriverSingleton.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Utility utility = new Utility();
    public void navigateToPrivacyPolicy(){ getPrivacyPolicyButton().click();}
    public boolean isPrivacyPolicyOpened(){ return getPrivacyPolicyHeader().isDisplayed();
        //Could also change to .contains("/privacy_agreement/")
    }
    public void goToFooter(){
        new Actions(getDriver()).scrollToElement(getFooter()).perform();
    }

    public void waitForSearchBox(){
        utility.waitForElement(getSearchBoxResult(), getDriver());
    }

    public boolean isPrivacyLanguagesListDisplayed(){ return getPrivacyLanguages().isDisplayed(); }
    public boolean isPrivacyLanguagesSwitchDisplayed(){
        return utility.checkSupportedLanguages(getPrivacySupportedLanguages());
    }
    public boolean isPrivacySigned(String year){
        return getPrivacySigned().getText().contains(year);
    }
    public void searchInStore(String game){
        getSearchBar().sendKeys(game);
        getSearchBar().submit();
    }
    public boolean isSearchPageDisplayed() {
        return getSearchBoxResult().isDisplayed();
    }

    public boolean isSearchBoxResultSucessful(String search){
            return getSearchBoxResult().getAttribute("value").contains(search);
    }


    public boolean isFirstResultMatching(String search) {
        return getFirstResult().getText().contains(search);
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