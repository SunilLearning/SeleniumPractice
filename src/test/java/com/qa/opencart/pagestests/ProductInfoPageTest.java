package com.qa.opencart.pagestests;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@DataProvider
	public Object[][] productInfoImgTestData() {
		return new Object[][] { { "MacBook", "MacBook Air", 5 }, { "Samsung", "Samsung Galaxy Tab 10.1", 8 },
				{ "iPhone", "iPhone", 7 },

		};
	}

	@Test(dataProvider = "productInfoImgTestData")
	public void productInfoImagesCountTest(String searchKey, String productName, int imageCount) {
		searchResultsPage = accPage.doSearchProduct(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actualProductInfoImgCount = productInfoPage.getProductInfoImagesCount();
		Assert.assertEquals(actualProductInfoImgCount, imageCount);
	}

	@Test
	public void getProductInfoTest() {
		searchResultsPage = accPage.doSearchProduct("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		LinkedHashMap<String, String> actualProductInfoMap = (LinkedHashMap<String, String>) productInfoPage
				.getProductInfo();

		// suppose if we want validate the multiple features in same test case, the
		// feature is completely related to one feature
		// or test case normally we dont prefer hard assert we prefer soft assert , so
		// create object inside the base test class.

		// the below hard assertions we are added for single test case so this is
		// suitable for multiple validations
//		Assert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
//		Assert.assertEquals(actualProductInfoMap.get("productPrice"), "$2,000.00");
//		Assert.assertEquals(actualProductInfoMap.get("productName"), "MacBook Pro");

		// here we have put more than one assertion in single test case so we have to
		// use soft assertion here
		// by adding all assertions last line we have to use assertAll() method.
		softAssert.assertEquals(actualProductInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("productPrice"), "$2,000.00");

		// this line we must add by end of soft assertion statements
		softAssert.assertAll();
	}

	@DataProvider
	public Object[][] addProductToCartTestData() {
		return new Object[][] { { "samsung", "Samsung Galaxy Tab 10.1", 3}, { "iPhone", "iPhone", 5},

		};
	}

	// add to cart flow
	@Test(dataProvider = "addProductToCartTestData")
	public void addProductToCartTest(String searchKey, String productName, int qunatity) {
		searchResultsPage = accPage.doSearchProduct(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		productInfoPage.getProductInfo();
		productInfoPage.getProductInfoImagesCount();
		productInfoPage.enterQuantityOfProduct(qunatity);
		String actualMessage = productInfoPage.addProductToCart();
		
		// Success: You have added Samsung Galaxy Tab 10.1 to your shopping cart!
		// we want to validate multiple things in single test case we have to segregate
		// a variable
		softAssert.assertTrue(actualMessage.contains("Success"));
		softAssert.assertTrue(actualMessage.contains(productName));
		softAssert.assertAll();

	}

}
