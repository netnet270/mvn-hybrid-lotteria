package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
	this.driver = driver;
    }

    public void closePopup() {
	if (isElementDisplayed(driver, HomePageUI.HOME_POPUP)) {
	    clickToElement(driver, HomePageUI.CLOSE_POPUP_BUTTON);
	}
    }
}
