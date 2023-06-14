package com.kamaldeep.LearnSelenium;

import org.testng.Assert;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment2 {

	WebDriver wd;
	WebDriverWait wait;
	Random random = new Random();
	int randomInt = random.nextInt(1000);
	String randomEmail = "a" + randomInt + "@gmail.com";

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome Driver\\chromedriver.exe");
		wd = new ChromeDriver();

		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
		wait = new WebDriverWait(wd, 10);
		wd.manage().window().maximize();

	}

	@Test(enabled = false)
	public void alreadyRegisterUser() {

		WebElement messageAlreadyAccountCreated = wait.until(ExpectedConditions.visibilityOf(
				wd.findElement(By.xpath("//div[text()=' Warning: E-Mail Address is already registered!']"))));
		boolean isMessageDisplayedAlreadyRegistered = messageAlreadyAccountCreated.isDisplayed();
		Assert.assertTrue(isMessageDisplayedAlreadyRegistered);
	}

	@Test(priority = 1)
	public void registerUser() {

		WebElement myAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='My Account']")));
		myAccount.click();
		WebElement register = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ul//a[text()='Register'])[1]")));
		register.click();
		WebElement firstName = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-firstname")));
		firstName.sendKeys("Kamaldeep");
		WebElement lastName = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-lastname")));
		lastName.sendKeys("Kaur");
		WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		email.sendKeys(randomEmail);
		WebElement telephone = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-telephone")));
		telephone.sendKeys("647-098-098");
		WebElement password = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		password.sendKeys("Password1");
		WebElement passwordConfirm = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-confirm")));
		passwordConfirm.sendKeys("Password1");

		WebElement newsLetter = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@name='newsletter'])[2]")));
		newsLetter.click();

		WebElement privacyPolicy = wd.findElement(By.cssSelector("div>input[name='agree']"));
		privacyPolicy.click();
		boolean isPolicySelected = privacyPolicy.isSelected();
		Assert.assertTrue(isPolicySelected); // Assert to see if policy is selected

		WebElement continueButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Continue']")));
		continueButton.submit();

		WebElement messageDisplayed = wait.until(ExpectedConditions
				.visibilityOf(wd.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']"))));
		String messageDisplayedMessage = messageDisplayed.getText();
		Assert.assertEquals(messageDisplayedMessage, "Your Account Has Been Created!","correct message is not displayed");// Your Account Has Been Created!

	}

	@Test(dependsOnMethods = "registerUser")

	public void signIn() {
		taskMethod();

		WebElement emailAddress = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		emailAddress.sendKeys(randomEmail);
		WebElement password = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		password.sendKeys("Password1");
		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(wd.findElement(By.cssSelector("input[value='Login']"))));
		loginButton.submit();
		WebElement title = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='My Account']")));
		String titleMessage = title.getText();
		Assert.assertEquals(titleMessage, "My Account");

		/*
		 * // if failedLoginAttempts exceed WebElement loginExceed =
		 * wd.findElement(By.xpath(
		 * "//div[text()=' Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.']"
		 * )); boolean isMessageDisplayed = loginExceed.isDisplayed();
		 * Assert.assertTrue("message not displayed", isMessageDisplayed);
		 */

		// unable to login

		/*
		 * WebElement incorrectSignInMessage =
		 * wait.until(ExpectedConditions.visibilityOf( wd.findElement(By.
		 * xpath("  //div[text()=' Warning: No match for E-Mail Address and/or Password.']"
		 * )))); boolean isMessageDisplayed1 = incorrectSignInMessage.isDisplayed();
		 * Assert.assertTrue(isMessageDisplayed1);
		 */

		// update password

		WebElement changePasswordLink = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Change your password']")));
		changePasswordLink.click();
		WebElement passwordChange = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		passwordChange.sendKeys("Password2");
		WebElement confirmpasswordChange = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-confirm")));
		confirmpasswordChange.sendKeys("Password2");
		WebElement continueChangepasswordButton = wait
				.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//input[@value='Continue']"))));
		continueChangepasswordButton.click();

		WebElement passwordUpdateMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[text()=' Success: Your password has been successfully updated.']")));
		String messageDisplayedMessage = passwordUpdateMessage.getText();
		Assert.assertEquals(messageDisplayedMessage, "Success: Your password has been successfully updated.","not correct message");
	}

	@Test(dependsOnMethods = "signIn")
	public void loginafterPasswordUpdate() {

		taskMethod();

		WebElement emailAddress = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		emailAddress.sendKeys(randomEmail);
		WebElement password = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		password.sendKeys("Password2");
		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(wd.findElement(By.cssSelector("input[value='Login']"))));
		loginButton.submit();

		WebElement title = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='My Account']")));
		String titleMessage = title.getText();
		Assert.assertEquals(titleMessage, "My Account");

	}

	public void taskMethod() {
		WebElement myAccountButton = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='My Account']")));
		myAccountButton.click();
		WebElement logutButton = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul//a[text()='Logout']")));
		logutButton.click();

		WebElement continueButton = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Continue']")));
		continueButton.click();
		WebElement myAccountButton1 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='My Account']")));
		myAccountButton1.click();

		WebElement loginButton1 = wait
				.until(ExpectedConditions.elementToBeClickable(wd.findElement(By.xpath("//a[text()='Login']"))));
		loginButton1.click();
	}

	@AfterTest
	public void tearDown() {
		//wd.close();
	}
}
