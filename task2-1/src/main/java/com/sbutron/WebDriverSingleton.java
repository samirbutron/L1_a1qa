package com.sbutron;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSingleton {

    public static WebDriver driver;

    private WebDriverSingleton(){

    }

    public static WebDriver getInstance(){
        if(driver == null){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions().addArguments(("--remote-allow-origins=*"));
            options.addArguments("incognito");
            driver = new ChromeDriver(options);
        }
        return driver;
    }
    public static void quitInstance(){
        driver.quit();
        driver = null;
    }
}
