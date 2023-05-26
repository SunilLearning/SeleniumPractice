package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultsPage {

	// each evey calss mainatin the private webdriver and separate constructor

	private WebDriver driver;
	private ElementUtils eleUtil;

	// page by locators
	private By productsListCount=By.xpath("//div[@class='row']//div[contains(@class, 'product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12')]");

	// page constructor
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}

	// page actions
	public int getSearchProductsCount() {
		int productCount= eleUtil.waitForElementsVisible(productsListCount, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Product Count is:"+productCount);
		return productCount;

	}

	public ProductInfoPage selectProduct(String productName) {
		By poductLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(poductLocator, AppConstants.DEFAULT_MEDIUM_TIMEOUT).click();
		return new ProductInfoPage(driver);

	}

}
