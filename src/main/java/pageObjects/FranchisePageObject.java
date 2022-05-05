package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.FranchisePageUI;

public class FranchisePageObject extends BasePage {
    private WebDriver driver;
    
    public FranchisePageObject(WebDriver driver) {
	this.driver = driver;
    }

    public void enterToEmailTextbox(String enterToValue) {
	waitForElementVisible(driver, FranchisePageUI.EMAIL_TEXTBOX);
	senkeyToElement(driver, FranchisePageUI.EMAIL_TEXTBOX, enterToValue);
    }

    public void clickToSendButton() {
	waitForElementClickable(driver, FranchisePageUI.SEND_BUTTON);
	clickToElement(driver, FranchisePageUI.SEND_BUTTON);
    }

    public String getErrorMessageAtEmailTextbox() {
	return getElementValidationMessage(driver, FranchisePageUI.EMAIL_TEXTBOX);
    }
    
}
