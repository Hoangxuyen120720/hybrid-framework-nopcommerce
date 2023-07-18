package com.nopcommerce.users;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.BaseTest;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest{

	private WebDriver driver;
	private String emailAddress = getEmailAddress();
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
       driver = grtBrowserDriver( browserName);
	}

	@Test
	public void Register_01_EmtyData() {

		homePage = new HomePageObject(driver);

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
		homePage = new HomePageObject(driver);

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
		homePage = new HomePageObject(driver);

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
		homePage = new HomePageObject(driver);

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
		homePage = new HomePageObject(driver);

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
		homePage = new HomePageObject(driver);

		homePage.clickLoginLink();
		loginPage = new LoginPageObject(driver);

		loginPage.enterToEmailTextbox(getEmailAddress());
		loginPage.enterToPasswordNameTextbox("123456");

		loginPage.clickToLoginButton();

		homePage = new HomePageObject(driver);

		homePage.clickTomyAccountLink();
		customerPage = new CustomerPageObject(driver);

		Assert.assertEquals(customerPage.getFirstNameAttributeValue(), "Xuyen");
		Assert.assertEquals(customerPage.getLastNameAttributeValue(), "Hoang");
		Assert.assertEquals(customerPage.getEmailAttributeValue(), emailAddress);

	}

	@AfterClass
	public void afterClass() {
		quitbrowserDriver();
	}

	
}
