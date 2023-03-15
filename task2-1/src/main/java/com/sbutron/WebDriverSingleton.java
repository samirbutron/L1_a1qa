package com.sbutron;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSingleton {

    public static ChromeDriver driver;

    private WebDriverSingleton(){

    }

    public static ChromeDriver getInstance(){
        if(driver == null){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions().addArguments(("--remote-allow-origins=*"));
            driver = new ChromeDriver(options);
        }
        return driver;
    }
    public static void quitInstance(){
        driver.quit();
        driver = null;
    }
}
