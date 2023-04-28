package src.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import src.main.*;

import java.util.List;

public class MainPage {
    private MyLogger logger;
    static final By cardListFinder = By.xpath("./child::div");

    public MainPage() {
        logger = MyLogger.getInstance();
    }
    private Button mainPageCards = new Button(By.xpath("//div[@class='category-cards']"), "mainPageCards");
    private BaseForm mainBanner = new BaseForm(By.className("home-banner"), "mainBanner");
    
    public boolean isMainBannerDisplayed(){
        return mainBanner.isFormOpen();
    }
    public void clickOnPositionCard (int number) {
        logger.info("Clicking on card in position: "+ number);
        List<WebElement> cardsList = mainPageCards.findElement().findElements(cardListFinder);
        cardsList.get(number).click();
    }



}
