package com.kamaldeep.LearnSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment1 {

	WebDriver wd;
	WebDriverWait wait;
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome Driver\\chromedriver.exe");
		wd= new ChromeDriver();
		wait = new WebDriverWait(wd, 10);
		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
		wd.manage().window().maximize();
		
		//wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void formSubmit()
	{
		WebElement contactUs =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact Us']"))) ;
		//WebElement contactUs=			wd.findElement(By.xpath("//a[text()='Contact Us']"));
		contactUs.click();
		
		WebElement name = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-name")));
				
				//wd.findElement(By.cssSelector("#input-name"));
		name.sendKeys("Kamaldeep");
		
		WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
				//wd.findElement(By.cssSelector("#input-email"));
		emailInput.sendKeys("kamal@gmail.com");
		WebElement inquiryInput = wd.findElement(By.cssSelector("#input-enquiry"));
		inquiryInput.sendKeys("Hello this is kamal");
		WebElement submitButton = wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//input[@value ='Submit']"))));
				
			//	wd.findElement(By.xpath("//input[@value ='Submit']"));
		submitButton.submit();
		WebElement message = wd.findElement(By.xpath("//p[text()='Your enquiry has been successfully sent to the store owner!']"));
		boolean isMessageDisplay = message.isDisplayed();
		Assert.assertTrue(isMessageDisplay,"Message is not displayed");
		
	
	}
	
	@AfterMethod
	public void tearDown()
	{
		wd.close();
	}
}
