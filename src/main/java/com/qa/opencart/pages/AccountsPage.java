package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils eleUtil;

	// 1. Private by lovators
	private By logoutLink = By.xpath("(//a[text()='Logout'])");
	private By serachField = By.name("search");
	private By searchIcon =By.xpath("//div[@id='search']//button");
	private By accHeadrers = By.xpath("//div[@id='content']/h2");

	// 2. Page constructor for each page
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}

	// 3. actions/methods

	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT,
				AppConstants.DEFAULT_ACCOUNT_PAGE_TITLE);
		// String title = driver.getTitle();
		System.out.println("Page title is:" + title);
		return title;
	}

	// here we are provide the values in form of hardcoded so, we no need provide
	// llike that and i want give wait time as 5 or 10
	// then we have to one package and we have to the appconstant file and
	public String getAccountPageURL() {
		String URL = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT,
				AppConstants.DEFALUT_ACCOUNT_PAGE_FRACTION_URL);
		// String URL=driver.getCurrentUrl();
		System.out.println("Page title is:" + URL);
		return URL;
	}

	public boolean isLogoutLinkExists() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
		// return driver.findElement(logoutLink).isDisplayed();
	}

	public boolean isSearchFieldExists() {
		return eleUtil.waitForElementVisible(serachField, AppConstants.DEFAULT_SHORT_TIMEOUT).isDisplayed();
		// return driver.findElement(serachField).isDisplayed();
	}

	public List<String> getAccountPageHeaders() {
		List<WebElement> headersList = eleUtil.waitForElementsPresence(accHeadrers,
				AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		// List<WebElement> headersList = driver.findElements(accHeadrers);
		// here we to add all the data into list because for future validation we can
		// use this data becuase this method will return the list data
		List<String> accHeadersList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String listText = e.getText();
			if (!listText.isEmpty()) {
				accHeadersList.add(listText);
			}

		}
		// always return the data no need to forget
		return accHeadersList;

	}
	
	public SearchResultsPage doSearchProduct(String SearchKey)
	{
		// here we checking if element is exists then send the value, before sending the value clear the text present in the field
		
		if(isSearchFieldExists())
		{
			eleUtil.doSendKeys(serachField, SearchKey);
			eleUtil.doClick(searchIcon);
			//once we click on the search icon it will redirected to the search page this is the landing page for this
			//particular method this method will return the new class object
			return new SearchResultsPage(driver); // if element present is true click on the element and retrun the new page
		}else
		{
			System.out.println("Search field is not present on the page....");
			return null; // if element not present if it is false, it should be written the null value.
		}
	}
}
