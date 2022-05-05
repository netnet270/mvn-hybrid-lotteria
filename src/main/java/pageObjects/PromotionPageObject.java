package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.PromotionPageUI;

public class PromotionPageObject extends BasePage {
    private WebDriver driver;

    public PromotionPageObject(WebDriver driver) {
	this.driver = driver;
    }

    public void selectItemInSortDropdown(String valueItem) {
	waitForElementClickable(driver, PromotionPageUI.SORT_DROPDOWN);
	selectItemInDropdown(driver, PromotionPageUI.SORT_DROPDOWN, valueItem);
    }

    public boolean ísProductPriceSorted(String sortBy) {
	List<WebElement> productPriceElements = getListWebElement(driver, PromotionPageUI.PRODUCT_PRICE);
	List<Float> productPriceTexts = new ArrayList<Float>();

	for (WebElement productPrice : productPriceElements) {
	    productPriceTexts.add(Float.parseFloat(productPrice.getText().replace("₫", "").trim()));
	}

	List<Float> productPriceTextClone = new ArrayList<Float>(productPriceTexts);
	Collections.sort(productPriceTextClone);

	if (sortBy.equals("DESC")) Collections.reverse(productPriceTextClone);
	return productPriceTexts.equals(productPriceTextClone);
    }

}
