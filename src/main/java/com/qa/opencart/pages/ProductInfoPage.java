package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtils eleUtil;

	private By productHeader = By.tagName("h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']//li");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By numberOfQuantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By productAddedToCartsuccessMsg = By.xpath("//div[@class='alert alert-success alert-dismissible']");

	private LinkedHashMap<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}

	public String getProductInfoHeaderValue() {
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("Product header value is:" + productHeaderVal);
		return productHeaderVal;
	}

	public int getProductInfoImagesCount() {
		int productInfoimgCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIMEOUT)
				.size();
		System.out.println("Product info images count is:" + productInfoimgCount);
		return productInfoimgCount;
	}

	public Map<String, String> getProductInfo() {

		// hashmap is orderless , it will return the data randomnly it will not
		// manintain the sequence order
		// productInfoMap = new HashMap<String, String>();

		// if we want the data in sequence order then we have to use the linked hashmap
		productInfoMap = new LinkedHashMap<String, String>();

		// i want the data in the sorting order
		// productInfoMap = new TreeMap<String, String>();

		// header
		productInfoMap.put("ProductName", getProductInfoHeaderValue());

		// get mete data
		getProductMetaData();

		// get price data
		getPricedata();
		System.out.println(productInfoMap);
		return productInfoMap;

	}

	// fetching the meta data here
	private void getProductMetaData() {
//		Product Code: SAM1
//		Reward Points: 1000
//		Availability: Pre-Order
		// here we are storing the data into hashmap
		// Meta Data
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaList) {
			String meta = e.getText();
			// we are segrate the data into key and value pair, means split the data.
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			// adding the values to hash map
			productInfoMap.put(key, value);

		}
	}

	// fetching the price data here
	private void getPricedata() {
//		$241.99
//		Ex Tax: $199.99
		// Price data
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText(); // list is order based we are get the text by using index.
		String exTax = priceList.get(1).getText();
		String exTaxVal = exTax.split(":")[1].trim();

		productInfoMap.put("productPrice", price); // creating the custom key here
		productInfoMap.put("exTax", exTaxVal);
	}

	public void enterQuantityOfProduct(int quantity) {
		System.out.println("Product Quantity is:" + quantity);
		eleUtil.doSendKeys(numberOfQuantity, String.valueOf(quantity));
	}

	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMsg = eleUtil
				.waitForElementVisible(productAddedToCartsuccessMsg, AppConstants.DEFAULT_MEDIUM_TIMEOUT).getText();
		StringBuilder sb=new StringBuilder(successMsg);
		 String msg=sb.substring(0,successMsg.length()-1).replace("\n", "").toString();
		System.out.println(msg);
		return msg;

	}

}
