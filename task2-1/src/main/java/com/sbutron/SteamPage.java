package com.sbutron;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SteamPage{

    //All page element related to actions must be described here
    private WebElement mainPageContentCarrousel = WebDriverSingleton.getInstance().findElement(By.xpath("/html/body/div[1]/div[5]")); //@class='home_page_content'
    private WebElement footer = WebDriverSingleton.getInstance().findElement(By.name("footer"));
    private WebElement privacyPolicyButton = WebDriverSingleton.getInstance().findElement(By.xpath("//div[@id='footer_text']/child::div//a[1]"));
    private WebElement privacyPolicyBadge = WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"pageTitle\"]/img"));
    private WebElement privacyLanguages = WebDriverSingleton.getInstance().findElement((By.id("languages")));
    private WebElement privacyLanguageEn =  WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"languages\"]/a[1]"));
    private WebElement privacyLanguageEs =  WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"languages\"]/a[2]"));
    private WebElement privacyLanguageFr =  WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"languages\"]/a[3]"));
    private WebElement privacyLanguageDe =  WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"languages\"]/a[4]"));
    private WebElement privacyLanguageIt =  WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"languages\"]/a[5]"));
    private WebElement privacyLanguageRu =  WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"languages\"]/a[6]"));
    private WebElement privacyLanguageJp =  WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"languages\"]/a[7]"));
    private WebElement privacyLanguagePt =  WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"languages\"]/a[8]"));
    private WebElement privacyLanguageBr =  WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"languages\"]/a[9]"));
    private WebElement privacySigned =  WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"newsColumn\"]/i[3]"));
    private WebElement searchBar = WebDriverSingleton.getInstance().findElement(By.id("store_nav_search_term"));
    private WebElement resultsPage = WebDriverSingleton.getInstance().findElement(By.id("advancedform"));
    private WebElement searchBoxResult = WebDriverSingleton.getInstance().findElement(By.xpath("//div[@class='searchbar_left']/child::input"));

    public SteamPage(){
    }
    public boolean isMainPageOpened(){

        return mainPageContentCarrousel.isDisplayed();
    }
    public void navigateToPrivacyPolicy(){ privacyPolicyButton.click();}
    public boolean isPrivacyPolicyOpened(){ return privacyPolicyBadge.isDisplayed(); }
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
    public void searchInStore(String search){
        searchBar.sendKeys(search);

    }
    public boolean isSearchPageDisplayed() {
        return resultsPage.isDisplayed();
    }
    public boolean isSearchBoxResultSucessful(String search){
        return searchBoxResult.getText().contains(search);
    }

    public boolean isFirstResultMatching(String search) {
        WebElement firstResult = WebDriverSingleton.getInstance().findElement(By.xpath("//div[@id='search_resultsRows']/child::a[1]"));
        return firstResult.getText().contains(search);
    }

    public void saveGameDatainResultPosition(String position){
        GameData game = new GameData();
        String name = WebDriverSingleton.getInstance().findElement(
                By.xpath("//*[@id='search_resultsRows']/a["+position+"]/div[2]/div[1]/span")).getText();
        System.out.println();
        String platforms = WebDriverSingleton.getInstance().findElement(By.xpath("//*[@id=\"search_resultsRows\"]/a["+position+"]/div[2]/div[1]/div/span[1]")).getAttribute("class");
        System.out.println(platforms);
    }




}