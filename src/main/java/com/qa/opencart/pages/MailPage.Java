package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

  
public class MailPage {
	private By username= By.xpath("Amit");
	
	// added additional locators
	private By pass= By.id("Amit");
	private By email= By.name("Amit");
	
	public OrderPage(WebDriver driver)
	{
		System.out.println("Amit");
	}
	
	// added new methods
	public void getTilte()
	{
		System.out.println("Amit");
	}
	
	public void getURL()
	{
		System.out.println("Amit");
	}

}
