package commons;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.BasePageUI;

public class BasePage {

    protected void openPageUrl(WebDriver driver, String pageUrl) {
	driver.get(pageUrl);
    }

    protected WebElement getWebElement(WebDriver driver, String xpathLocator) {
	return driver.findElement(By.xpath(xpathLocator));
    }

    public By getByXpath(String xpathLocator) {
	return By.xpath(xpathLocator);
    }

    private String getDynamicLocator(String xpathLocator, String... dynamicValues) {
	return String.format(xpathLocator, (Object[]) dynamicValues);
    }

    protected List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
	return driver.findElements(By.xpath(xpathLocator));
    }

    protected void senkeyToElement(WebDriver driver, String locator, String textValue) {
	WebElement element = getWebElement(driver, locator);
	element.clear();
	element.sendKeys(textValue);
    }

    protected void clickToElement(WebDriver driver, String xpathLocator) {
	getWebElement(driver, xpathLocator).click();
    }

    protected void clickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
	getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).click();
    }

    protected void selectItemInDropdown(WebDriver driver, String xpathLocator, String textItem) {
	Select select = new Select(getWebElement(driver, xpathLocator));
	select.selectByVisibleText(textItem);
    }

    protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
	    String expectedItem) {
	getWebElement(driver, parentLocator).click();
	WebDriverWait explicitWait = new WebDriverWait(driver, longTime);
	List<WebElement> allItems = explicitWait
		.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
	for (WebElement item : allItems) {
	    if (item.getText().trim().equals(expectedItem)) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
		item.click();
		break;
	    }
	}
    }

    protected boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
	return getWebElement(driver, xpathLocator).isDisplayed();
    }

    protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
	return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
		getWebElement(driver, xpathLocator));
    }

    protected WebElement waitForElementClickable(WebDriver driver, String xpathLocator) {
	WebDriverWait explicitWait = new WebDriverWait(driver, longTime);
	return explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
    }

    protected WebElement waitForElementClickable(WebDriver driver, String xpathLocator, String... dynamicValues) {
	WebDriverWait explicitWait = new WebDriverWait(driver, longTime);
	return explicitWait.until(
		ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
    }

    protected WebElement waitForElementVisible(WebDriver driver, String xpathLocator) {
	WebDriverWait explicitWait = new WebDriverWait(driver, longTime);
	return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
    }

    public void sleepInSecond(long second) {
	try {
	    Thread.sleep(second * 1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void selectItemInLanguageDropdown(WebDriver driver, String itemValue) {
	waitForElementClickable(driver, BasePageUI.LANGUAGE_DROPDOWN);
	selectItemInCustomDropdown(driver, BasePageUI.LANGUAGE_DROPDOWN, BasePageUI.LANGUAGE_DROPDOWN_ITEM, itemValue);
    }

    public void openMenuPage(WebDriver driver, String menuPageName) {
	waitForElementClickable(driver, BasePageUI.DYNAMIC_MENU_PAGE_BY_NAME, menuPageName);
	clickToElement(driver, BasePageUI.DYNAMIC_MENU_PAGE_BY_NAME, menuPageName);
    }

    private long longTime = 30;
}
