package com.kamaldeep.LearnSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;




public class FirstSeleniumSession {
	
	
	WebDriver wd;
	
	@BeforeMethod	
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome Driver\\chromedriver.exe");
		wd = new ChromeDriver(); // opens instance of chrome browser and initialize instance of webdriver use child class constrctior 
		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		wd.manage().window().maximize();
	}
	
	@Test
	public void validateLogin()
	{
		
		WebElement emailInput = wd.findElement(By.cssSelector("#input-email"));
		WebElement passwordInput = wd.findElement(By.xpath("//input[@id='input-password']"));
		WebElement login = wd.findElement(By.cssSelector("input[value='Login']"));
		emailInput.sendKeys("tony@email.com");
		passwordInput.sendKeys("Password1");
		login.click();
		
		Assert.assertEquals("My Account", wd.getTitle());
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		wd.close();
	}

}
