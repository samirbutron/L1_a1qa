package com.sbutron;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;

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
        if (driver == null) {
            switch (config.getBrowser()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions optionsC = new ChromeOptions();
                    if (config.getRemoteAllowOrigins()) {
                        optionsC.addArguments("--remote-allow-origins=*");
                    }
                    if (config.getIncognito()) {
                        optionsC.addArguments("incognito");
                    }
                    driver = new ChromeDriver(optionsC);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().setSize(new Dimension(2000,1800));
                    break;
            }
        }
        return driver;
    }
    public static void quitInstance(){
        driver.quit();
        driver = null;
    }
}
