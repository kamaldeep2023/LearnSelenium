package com.kamaldeep.LearnSelenium;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment2 {

	WebDriver wd;
	WebDriverWait wait;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome Driver\\chromedriver.exe");
		wd = new ChromeDriver();

		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
		wait = new WebDriverWait(wd, 10);
		wd.manage().window().maximize();

	}

	@Test(dependsOnMethods = "registerUser")
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
		email.sendKeys("K6@gmail.com");
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

		/*
		 * WebElement messageDisplayed = wait.until(ExpectedConditions
		 * .visibilityOf(wd.findElement(By.
		 * xpath("//h1[text()='Your Account Has Been Created!']")))); boolean
		 * isMessageDisplayed = messageDisplayed.isDisplayed();
		 * Assert.assertTrue(isMessageDisplayed); // Your Account Has Been Created!
		 * 
		 * WebElement continueButtonAfterRegister = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//a[text()='Continue']"))); continueButtonAfterRegister.click();
		 */

	}

	@Test(priority = 2)
	public void signIn() {
		wd.navigate().to("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		WebElement emailAddress = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		emailAddress.sendKeys("Kamal9@gmail.com");
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
		passwordChange.sendKeys("Password3");
		WebElement confirmpasswordChange = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-confirm")));
		confirmpasswordChange.sendKeys("Password3");
		WebElement continueChangepasswordButton = wait
				.until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//input[@value='Continue']"))));
		continueChangepasswordButton.click();

		WebElement passwordUpdateMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()=' Success: Your password has been successfully updated.']")));
		boolean isMessageDisplayed = passwordUpdateMessage.isDisplayed();
		Assert.assertTrue(isMessageDisplayed);
	}

	@Test(priority = 3)
	public void loginafterPasswordUpdate() {
		wd.navigate().to("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		WebElement emailAddress = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		emailAddress.sendKeys("Kamal9@gmail.com");
		WebElement password = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		password.sendKeys("Password3");
		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(wd.findElement(By.cssSelector("input[value='Login']"))));
		loginButton.submit();

		WebElement title = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='My Account']")));
		String titleMessage = title.getText();
		Assert.assertEquals(titleMessage, "My Account");

	}

	@AfterTest
	public void tearDown() {
		 wd.close();
	}
}
