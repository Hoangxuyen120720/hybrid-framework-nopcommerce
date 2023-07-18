package com.nopcommerce.users;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Common.BasePage;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_03_Page_Object_Pattern  {

	private WebDriver driver;
	
	private String projectPath = System.getProperty("user.dir");
	private String emailAddress = getEmailAddress();
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void Register_01_EmtyData() {

		homePage =  new HomePageObject(driver);
		
		homePage.clickRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		
		registerPage.clickToRegisterButton();
		
		
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {

		registerPage.clickToHomePageLogo();
		homePage =  new HomePageObject(driver);
		
		homePage.clickRegisterLink();
		registerPage = new RegisterPageObject(driver);
		
		registerPage.enterToFirstNameTextbox("Xuyen");
		registerPage.enterToLastNameTextbox("Hoang");
		registerPage.enterToEmailTextbox("Xuyen@123@@");
		registerPage.enterToPasswordNameTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123456");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");

	}

	@Test
	public void Register_03_Invalid_Password() {

		registerPage.clickToHomePageLogo();
		homePage =  new HomePageObject(driver);
		
		homePage.clickRegisterLink();
		registerPage = new RegisterPageObject(driver);

		registerPage.enterToFirstNameTextbox("Xuyen");
		registerPage.enterToLastNameTextbox("Hoang");
		registerPage.enterToEmailTextbox("Xuyen@gmail.com");
		registerPage.enterToPasswordNameTextbox("123");
		registerPage.enterToConfirmPasswordTextbox("123");

		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getPasswordErrorMessage(),
				"Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_04_InCorrect_Comfirm_Password() {
		registerPage.clickToHomePageLogo();
		homePage =  new HomePageObject(driver);
		
		homePage.clickRegisterLink();
		registerPage = new RegisterPageObject(driver);
		
		registerPage.enterToFirstNameTextbox("Xuyen");
		registerPage.enterToLastNameTextbox("Hoang");
		registerPage.enterToEmailTextbox("Xuyen@gmail.com");
		registerPage.enterToPasswordNameTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("654321");

		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(),
				"The password and confirmation password do not match.");

	}

	@Test
	public void Register_05_Success() {
		registerPage.clickToHomePageLogo();
		homePage =  new HomePageObject(driver);
		
		homePage.clickRegisterLink();
		registerPage = new RegisterPageObject(driver);
		
		registerPage.enterToFirstNameTextbox("Xuyen");
		registerPage.enterToLastNameTextbox("Hoang");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordNameTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123456");

		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");


		registerPage.clickToHomePageLogo();
		homePage =  new HomePageObject(driver);
		
		homePage.clickLoginLink();
		loginPage = new LoginPageObject(driver);
		
		loginPage.enterToEmailTextbox(getEmailAddress());
		loginPage.enterToPasswordNameTextbox("123456");
	
		loginPage.clickToLoginButton();
		
		homePage =  new HomePageObject(driver);
		
		homePage.clickTomyAccountLink();
		customerPage = new CustomerPageObject(driver);
		
		Assert.assertEquals(customerPage.getFirstNameAttributeValue(), "Xuyen");
		Assert.assertEquals(customerPage.getLastNameAttributeValue(), "Hoang");
		Assert.assertEquals(customerPage.getEmailAttributeValue(), emailAddress);
		
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getEmailAddress() {
		Random rand = new Random();
		return "Xu" + rand.nextInt(9999) + "@gmail.com";
	}

}
