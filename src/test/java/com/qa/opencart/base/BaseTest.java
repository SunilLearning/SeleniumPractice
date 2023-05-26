package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	// 3. -3rd step create the base test class, all the pre requistite required for all the test classes.
	
	// create the object for diver factory class to intialise the browser
	
	// here we have to create the object for all page classes.
	WebDriver driver;
	// crate object for driver factory class
	DriverFactory df;
	//declare the properties variable
	protected Properties prop;
	//create object for all pages classes and protected members can access by child classes.
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	//if we declare in base test we can call the refrence by easily.
	protected SoftAssert softAssert;
	
	// This is the global setup
	@BeforeTest
	public void setUp()
	{
		df=new DriverFactory();
		//before intialing the driver read the properties file then intialise the driver
		prop=df.initProp();
		driver=df.initDriver(prop);
		loginPage=new LoginPage(driver);
		//creating the object for soft assert
		softAssert=new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
