package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.FranchisePageObject;
import pageObjects.HomePageObject;
import pageObjects.PromotionPageObject;

public class PageGeneratorManager {
    public static HomePageObject getHomePage(WebDriver driver) {
	return new HomePageObject(driver);
    }
    
    public static FranchisePageObject getFranchisePage(WebDriver driver) {
	return new FranchisePageObject(driver);
    }
    
    public static PromotionPageObject getPromotionPage(WebDriver driver) {
	return new PromotionPageObject(driver);
    }
}
