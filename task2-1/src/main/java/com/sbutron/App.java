package com.sbutron;

public class App 
{
    public static void main( String[] args )
    {
        //WebDriver driver = WebDriverSingleton.getInstance();
        SteamPage steamPage = new SteamPage();
        steamPage.saveGameDatainResultPosition("1");
    }
}
