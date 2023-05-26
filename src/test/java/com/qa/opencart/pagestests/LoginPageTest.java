package com.qa.opencart.pagestests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

// here we are added the epic and stories to iur test cases
@Epic("EPIC-100:Login Design")
@Story("US-Login: 101: Login Test story")
public class LoginPageTest extends BaseTest {

	// 4- 4th step, create the only test cases inside the test pages, we have to
	// create all the respective class object
	// inside the base test class and object reference should be in protected mode.
	// here by just object reference we can use
	// or call the methods, if we have 10 test cases we have to create 10 objects so
	// created in base test class, any child
	// can access the this particular object reference, if we created here we can
	// able to access here only

	
	// we can define some annotaions in test annotations methods
	@Severity(SeverityLevel.MINOR)
	@Description("Author:Sunil")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		// we have to write the assertion inside the test ng classes not inside the page
		// classes
		String actualTitle = loginPage.doGetTiltle();
		Assert.assertEquals(actualTitle, AppConstants.DEFAULT_LOGIN_PAGE_TITLE);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Author:Sunil")
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actualURL = loginPage.doGetCurrentURL();
		Assert.assertTrue(actualURL.contains(AppConstants.DEFALUT_LOGIN_PAGE_FRACTION_URL));
	}

	@Severity(SeverityLevel.MINOR)
	@Description("Author:Sunil")
	@Test(priority = 3)
	public void forgotPasswordLinkTest() {
		boolean flag = loginPage.isForgotPasswordLinkExists();
		Assert.assertTrue(flag);
	}

	// here we putting assertions- once we login to the app we have to verify , the
	// login is succesfully happened or not
	// here we written all the post login account page methods inside the
	// accountspages class but we can reuse those methods by
	// giving the reference to that class.
	@Severity(SeverityLevel.MINOR)
	
	@Description("Author:Sunil")
	@Test(priority = 4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isSearchFieldExists());
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.DEFAULT_ACCOUNT_PAGE_TITLE);

	}
}
