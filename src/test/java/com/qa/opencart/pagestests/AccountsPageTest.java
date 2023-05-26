package com.qa.opencart.pagestests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	// here if we want to perform the valiadations on the account page we have to
	// satisfy the pre requiste, it means
	// we to login to the application first then post that we can able to the
	// validations, here am using before class annotation

	// this is only for specific class
	@BeforeClass
	public void accLoginSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String actualTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.DEFAULT_ACCOUNT_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void accPageURLTest() {
		String actualURL = accPage.getAccountPageURL();
		assertTrue(actualURL.contains(AppConstants.DEFALUT_ACCOUNT_PAGE_FRACTION_URL));
	}

	@Test(priority = 3)
	public void searchFieldTest() {
		Assert.assertTrue(accPage.isSearchFieldExists());
	}

//	@Test(priority=4)
//	public void logoutLinkTest()
//	{
//		Assert.assertFalse(accPage.isLogoutLinkExists());
//	}

	@Test(priority = 5)
	public void headersCountTest() {
		List<String> actualAccPageHeadersList = accPage.getAccountPageHeaders();
		System.out.println(actualAccPageHeadersList);
		Assert.assertEquals(actualAccPageHeadersList.size(), AppConstants.DEFALUT_ACCOUNT_PAGE_HEADER_COUNT);
	}
	
	@Test(priority = 5)
	public void accHeadersValueTest() {
		List<String> actualAccPageHeadersList = accPage.getAccountPageHeaders();
		Assert.assertEquals(actualAccPageHeadersList, AppConstants.EXPECTED_ACCOUNTPAGE_HEADERS_LIST);// so here we are maintaining the lists in contant file because these are static
		
	}
	
	// we can able to write the test cases in any test classes based on the use case so we have to write the test case 
	// in accounts page to search the products, but we have created separate search results page we no need to write
	
	@Test(dataProvider = "getProductdata")
	public void serachProductCountTest(String productKey)
	{
		searchResultsPage=accPage.doSearchProduct(productKey);
		Assert.assertTrue(searchResultsPage.getSearchProductsCount()>0);
	}
	
	// instead of adding the hardcoded value we have to pass the multiple values for same method we have to use the
	//data provider annotaion it will return 2dimensional object[][]
	
	@DataProvider
	public Object[][] getProductdata()
	{
		return new Object[][] {
			
			{"macbook"},
			{"samsung"},
			{"iphone"},
			{"Apple"}
			
		};
	}
	
	@DataProvider
	public Object[][] getProductTestdata()
	{
		return new Object[][] {
			
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"macbook","MacBook"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"iphone","iphone"},
			{"Apple"}
			
		};
	}
	@Test(dataProvider = "getProductTestdata")
	public void selectProductTest(String searchKey, String productName)
	{
		searchResultsPage=accPage.doSearchProduct(searchKey);
		if(searchResultsPage.getSearchProductsCount()>0)
		{
			productInfoPage=searchResultsPage.selectProduct(productName);
			String actualProductHeader=productInfoPage.getProductInfoHeaderValue();
			Assert.assertEquals(actualProductHeader, productName);
		}
	}
}
