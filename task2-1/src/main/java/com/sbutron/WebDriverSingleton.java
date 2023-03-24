package com.sbutron;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class WebDriverSingleton {

    public static WebDriver driver;
    private WebDriverSingleton() {

    }
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ConfigJSON config;

    static {
        try {
            config = objectMapper.readValue(new File("C:\\Users\\samir\\Documents\\s.butron\\2.PageObject\\task2-1\\src\\config\\config.json"), ConfigJSON.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebDriver getInstance() throws IOException {
        if(driver == null){
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            if (config.getRemoteAllowOrigins()) {
                options.addArguments("--remote-allow-origins=*");
            }
            if (config.getIncognito()) {
                options.addArguments("incognito");
            }
            driver = new ChromeDriver(options);
        }
        return driver;
    }
    public static void quitInstance(){
        driver.quit();
        driver = null;
    }
}
