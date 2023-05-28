package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

  
public class Feature2 {
	private By username= By.xpath("name");
	
	// added additional locators
	private By pass= By.id("id");
	private By email= By.name("name");
	
	public Feature2(WebDriver driver)
	{
		System.out.println("hello");
	}
	
	// added new methods
	public void getTilte()
	{
		System.out.println("title is displayed");
	}
	
	public void getURL()
	{
		System.out.println("url is displayed");
	}

}
