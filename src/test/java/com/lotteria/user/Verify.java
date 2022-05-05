package com.lotteria.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.FranchisePageObject;
import pageObjects.HomePageObject;
import pageObjects.PromotionPageObject;

public class Verify extends BaseTest {
    private String emailInvalid;

    @Parameters({ "browser", "url" })
    @BeforeClass
    public void BeforeClass(String browserName, String appUrl) {
	driver = getBrowserDriver(browserName, appUrl);
	homePage = PageGeneratorManager.getHomePage(driver);

	emailInvalid = "abc";

	homePage.closePopup();
	homePage.selectItemInLanguageDropdown(driver, "English");
    }

    @Test
    public void TC_01() {
	homePage.openMenuPage(driver, "Franchising");
	franchisePage = PageGeneratorManager.getFranchisePage(driver);

	franchisePage.enterToEmailTextbox(emailInvalid);
	franchisePage.clickToSendButton();

	Assert.assertEquals(franchisePage.getErrorMessageAtEmailTextbox(),
		"Please include an '@' in the email address. '" + emailInvalid + "' is missing an '@'.");
    }

    @Test
    public void TC_02() {
	homePage.openMenuPage(driver, "Order now");
	promotionPage = PageGeneratorManager.getPromotionPage(driver);

	promotionPage.selectItemInSortDropdown("Price from high to low");
	promotionPage.sleepInSecond(2);

	Assert.assertTrue(promotionPage.ísProductPriceSorted("DESC"));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
	if (driver != null) {
	    driver.quit();
	}
    }

    private WebDriver driver;
    private HomePageObject homePage;
    private FranchisePageObject franchisePage;
    private PromotionPageObject promotionPage;
}
