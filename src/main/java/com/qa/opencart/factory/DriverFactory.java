package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class DriverFactory {

	// Inside this particular class we have to intialize the web driver

	public static String highlight; // this is used for highliighting the element
	
	/**
	 * This method is used to intiate the broswer Here we written the cross browser
	 * logic
	 * 
	 * @param broswerName
	 */
	
	// here actually normal driver is not a thread safe, so we are using thread local for thread safe
	public static ThreadLocal<WebDriver>tlDriver=new ThreadLocal<WebDriver>();

	public WebDriver driver;

	// declared properties variable at class level, we can use anywhere in the
	// project
	public Properties prop;

	//declare the options manager variable at class level
	public OptionsManager optionsManager;
	/**
	 * This method is intilaising the driver on the basis of given browser name
	 * @param broswerName
	 * @return
	 */
	// 1. -First Step
	//here we have to replace the string browsername with prop reference because, with the help of prop we can read all the data
	public WebDriver initDriver(Properties prop) {
		// we have to pass the chrome options refrence to the browser intialisation, we have to create the object for optionsManger class
		optionsManager= new OptionsManager(prop);
		
		// this is for higlighting the element
		highlight=prop.getProperty("highlight").trim();
		
		// Cross browser logic
		String browserName=prop.getProperty("browser").toLowerCase().trim();
		System.out.println(browserName + "Broswer is Launched sucessfuly..");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			//here we have to pass the options manager refrence and access the respective methods
			
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			//below we make it as thread safety for the driver
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		} else if (browserName.equalsIgnoreCase("safari")) {
			//here we have to pass the options manager refrence and access the respective methods
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			//here we have to pass the options manager refrence and access the respective methods
			//driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
		} else {
			System.out.println("Please pass the right browser name" + browserName);
		}

		// here normal webdriver is null because we set as thread local driver , instead of normal driver refrence
		// we have to call the getDriver method , getDriver method is below.
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.get(prop.getProperty("url"));
//		return driver;
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	//here we written the method for to get the local thread of copy of each driver
	public synchronized static WebDriver getDriver()  //synchronized will give thraed copy for each driver
	{
		return tlDriver.get();
	}

	// step-16 read the properties from config.properties
	// we have to read the properties file with the help of java properties class,
	// so inside the driver factory class we have to
	// create one method for to read the data

	/**
	 * This method is used to read the data from the properties files
	 * @return 
	 */
//	public Properties initProperties() {
//		// here created object for properties
//		prop = new Properties();
//
//		try {
//			// to read data from files we have to use file input stream
//			FileInputStream ip = new FileInputStream(".\\src\\test\\resources\\Config\\config.properties");
//			// here we have load the data in properties file
//			try {
//				prop.load(ip);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return prop; // always evey methoud should written the data
//
//	}
	/**
	 * this method is reading the properties from the .properties file
	 * 
	 * @return
	 */
	public Properties initProp() {

		// mvn clean install -Denv="qa"
		// mvn clean install
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running test cases on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("no env is passed....Running tests on QA env...");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
					break;

				default:
					System.out.println("....Wrong env is passed....No need to run the test cases....");
					//throw new FrameworkException("WRONG ENV IS PASSED...");
				 break;
				}

			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
