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
import pageOnjects.admin.AdminDashboardPageObject;
import pageOnjects.admin.AdminLoginPageObjects;

public class Level_09_Switch_Site_ULR extends BaseTest {

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

	private AdminLoginPageObjects adminLoginPage;
	private AdminDashboardPageObject adminDashboadPage;
	
	
	private String userURL, adminURL;
	
	
	@Parameters({ "browser", "userUrl", "adminUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String userUrl, String adminUrl) {

		// User
		driver = getBrowserDriver(browserName, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		this.userURL = userUrl;
		this.adminURL = adminUrl;
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
	public void Register_02_Switch_URL() {
		
		// Customer Page
		
		// logout ra
		customerPage.userAbleToLogout(driver);
		
		
		// qua trang admin
		homePage.openUrl(driver, adminURL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		// login vao thanh cong
		
		adminDashboadPage = adminLoginPage.clickToLoginButton2();
		
		//adminDashboadPage = adminLoginPage.loginAsAdmin("admin@yourstore.com", "admin");
		Assert.assertTrue(adminDashboadPage.isPageLoadedSuccess(driver));
		// logout ra
		
		adminLoginPage = adminDashboadPage.adminAbleToLogout(driver);
		// Chuyen sang trang user
		
		adminLoginPage.openUrl(driver, userURL);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		// login vao
		
		loginPage = homePage.clickLoginLink();
		homePage = loginPage.loginAsUser(emailAddress, "123456");
		
		// Dki tai kghoan ow trang user xong qua admin der verify
		
		
		
	
	
	
	
	}

	@AfterClass
	public void afterClass() {
		quitbrowserDriver();
	}

}
