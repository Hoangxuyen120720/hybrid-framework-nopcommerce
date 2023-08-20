package com.nopcommerce.users;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.BaseTest;
import pageObjects.users.AddressesPageObject;
import pageObjects.users.CustomerPageObject;
import pageObjects.users.DowloadableProductPageObject;
import pageObjects.users.HomePageObject;
import pageObjects.users.LoginPageObject;
import pageObjects.users.PageGeneratorManager;
import pageObjects.users.RegisterPageObject;
import pageObjects.users.RewardPointPageObject;

public class Level_12_Dynamic_Locator extends BaseTest {

	private WebDriver driver;
	private String emailAddress = getEmailAddress();
	
	// ko thuoc sider bar ko goi dc
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	
	
	// Thuocj side bar neen goi ddc
	private CustomerPageObject customerPage;
	private DowloadableProductPageObject dowloadableProductPage;
	private RewardPointPageObject rewardPointPage;
	private AddressesPageObject addressesPage;

	@Parameters({ "browser", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String userUrl) {

		// User
		driver = getBrowserDriver(browserName, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Register_01_Register() {

		registerPage = homePage.clickRegisterLink();

		registerPage.enterToFirstNameTextbox("Xuyen");
		registerPage.enterToLastNameTextbox("Hoang");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordNameTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123456");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		homePage = registerPage.clickToHomePageLogo();

		loginPage = homePage.clickLoginLink();

		homePage = loginPage.loginAsUser(emailAddress, "123456");

		customerPage = homePage.clickTomyAccountLink();

		Assert.assertEquals(customerPage.getFirstNameAttributeValue(), "Xuyen");
		Assert.assertEquals(customerPage.getLastNameAttributeValue(), "Hoang");
		Assert.assertEquals(customerPage.getEmailAttributeValue(), emailAddress);
	}

	@Test
	public void Register_02_Switch_Page() {
		
		// Customer Infor => Dowloadable products
		dowloadableProductPage = customerPage.openDowloadableproductPage();
		dowloadableProductPage.sleepInSecond(2);
		
		// Dowloadable products => Addresses
		addressesPage = dowloadableProductPage.openAddressesPage();
		
		// Addresses => Rewards points

		rewardPointPage = addressesPage.openRewardPointPage();
		// Rewards points => Customer Infor
		
		customerPage = rewardPointPage.openCustomerInforPage();
		// Customer Infor => Addresses
		addressesPage = customerPage.openAddressesPage();
		
		// Addresses => Dowloadable products 
		dowloadableProductPage = addressesPage.openDowloadableproductPage();
	
		
	
	}
	@Test
	public void Register_03_Switch_Page() {
		
		// Customer Infor => Dowloadable products
		dowloadableProductPage = (DowloadableProductPageObject) customerPage.openDynamicSideBarPage("Downloadable products");
		dowloadableProductPage.sleepInSecond(2);
		
		// Dowloadable products => Addresses
		addressesPage = (AddressesPageObject) dowloadableProductPage.openDynamicSideBarPage("Addresses");
		
		// Addresses => Rewards points

		rewardPointPage = (RewardPointPageObject) addressesPage.openDynamicSideBarPage("Reward points");
		// Rewards points => Customer Infor
		
		customerPage = (CustomerPageObject) rewardPointPage.openDynamicSideBarPage("Customer info");
		// Customer Infor => Addresses
		addressesPage = (AddressesPageObject) customerPage.openDynamicSideBarPage("Addresses");
		
		// Addresses => Dowloadable products 
		dowloadableProductPage = (DowloadableProductPageObject) addressesPage.openDynamicSideBarPage("Downloadable products");
	
	}
	
	@Test
	public void Register_04_Switch_Page() {
		
		// Rewards points => Customer Infor
		rewardPointPage.openDynamicSideBarPageByName("Customer info");
		customerPage = PageGeneratorManager.getCustomerPage(driver);
		
		
		// Customer Infor => Rewards points
		customerPage.openDynamicSideBarPageByName("Addresses");
		addressesPage = PageGeneratorManager.getAddressesPage(driver);
		
		addressesPage.openDynamicSideBarPageByName("Customer info");
		customerPage = PageGeneratorManager.getCustomerPage(driver);
		
	}
	
	
	
	@AfterClass
	public void afterClass() {
		quitbrowserDriver();
	}

}
