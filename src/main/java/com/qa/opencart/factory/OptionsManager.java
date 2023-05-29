package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	//this class is mainly used for run tc's on headless or incognito mode by using chrome options or firefox options
	//insted of creating the direct method inside the driver factory, created new class optiionsManger because 
	//we have to follow SRP
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	// we have to declare the variables with private and we have to declare the constructor
	public OptionsManager(Properties prop)
	{
		this.prop=prop;
	}
	
	//this is for chrome browser
	public ChromeOptions getChromeOptions()
	{
		co=new ChromeOptions();
		// addded newly 
		co.addArguments("remote-allow-origins=*");
		// the prop values are in string format, so we have to convert that one into boolean
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			co.addArguments("--incognito");
		}
		return co;
	}
	
	//this is for firefox browser
	public FirefoxOptions getFireFoxOptions()
	{
		fo=new FirefoxOptions();
		// the prop values are in string format, so we have to convert that one into boolean
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			fo.addArguments("--incognito");
		}
		return fo;
		
	}
	
	//this is for edge browser
	public EdgeOptions getEdgeOptions()
	{
		eo=new EdgeOptions();
		// the prop values are in string format, so we have to convert that one into boolean
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			eo.addArguments("--incognito");
		}
		return eo;
		
	}
}
