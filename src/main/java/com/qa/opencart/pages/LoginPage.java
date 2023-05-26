package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {

	// Inside this paticular we have create the POM for each pages

	// 2nd -step

	// every page having own web driver, so we have to create the private web
	// driver.
	private WebDriver driver;

	// every page having element util -- each and every page we have decalre the
	// elements util
	private ElementUtils eleUtil;

	// 1. According to the POM structure 1st step we have to create the private By
	// locators
	private By emialId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@class='btn btn-primary']");
	private By forgotPasswordLink = By.linkText("Forgotten Password");

	// 2. According to the POM structure 2nd step is we have to create the page
	// constructor
	/**
	 * when ever we have create the object for this class, constructor will be
	 * executed at that time webdriver will assigned to the this class private
	 * webdriver
	 * 
	 * @param driver
	 */

	// inside the every page constructor we have to create the object for element
	// util class
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}

	// 3. According to the POM structure 3rd step is we have to create the page
	// actions/methods applicable for that respective page
	public String doGetTiltle() {
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT,
				AppConstants.DEFAULT_LOGIN_PAGE_TITLE);
		// String title = driver.getTitle();
		System.out.println("Title of the page is:" + title);
		return title;
	}

	@Step("Getting the title of the URL ")
	public String doGetCurrentURL() {
		String currentURL = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT,
				AppConstants.DEFALUT_LOGIN_PAGE_FRACTION_URL);
		// String currentURL = driver.getCurrentUrl();
		System.out.println("Current URL of the page is:" + currentURL);
		return currentURL;
	}

	public boolean isForgotPasswordLinkExists() {

		return eleUtil.waitForElementVisible(forgotPasswordLink, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
//		return driver.findElement(forgotPasswordLink).isDisplayed();
	}

	// 5 -step 5 : this method is landing on new page so this method create the new
	// class object

	@Step("Login to the app:username is{0} and Password is {1} ")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Appp credentials are:"+un+" "+pwd);
		eleUtil.waitForElementVisible(emialId, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		WebElement btn = eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_LONG_TIMEOUT, loginBtn);
		btn.click();
//		//  below is my own code here we are update the web driver wait because am getting element not iterable exception
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
//		element.click();
		return new AccountsPage(driver);
	}
}
