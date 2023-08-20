package com.nopcommerce.users;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.BaseTest;
import Common.GlobalContants;
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

public class Level_11_StringFormat_RestParameter extends BaseTest {

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

	private String adminUrl = GlobalContants.DEV_ADMIN_URL;
	private String userUrl = GlobalContants.DEV_USER_URL;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {

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
	public void Register_02_Switch_URL() {

		// Customer Page

		// logout ra
		customerPage.userAbleToLogout(driver);

		// qua trang admin
		homePage.openUrl(driver, adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		// login vao thanh cong

		adminDashboadPage = adminLoginPage.clickToLoginButton2();

		// adminDashboadPage =
		// adminLoginPage.loginAsAdmin("GlobalContants.DEV_ADMIN_USERNAME",
		// "GlobalContants.DEV_ADMIN_PASSWORD");
		Assert.assertTrue(adminDashboadPage.isPageLoadedSuccess(driver));
		// logout ra

		adminLoginPage = adminDashboadPage.adminAbleToLogout(driver);
		// Chuyen sang trang user

		adminLoginPage.openUrl(driver, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);

		// login vao

		loginPage = homePage.clickLoginLink();
		homePage = loginPage.loginAsUser(emailAddress, "123456");

		// Dki tai kghoan ow trang user xong qua admin der verify

	}

	@Test
	public void Register_03_Rest_Param() {

		String addressLink = "//div[@class='side-2]//a[text()='Address']";
		String oderLink = "//div[@class='side-2]//a[text()='Orders']";
		String sidebarLink = "//div[@class='side-2]//a[text()='%s']";

		String dynamicLink = "//div[@class='%s']//a[text()='%s']";

		String dynamicCountry = "//td[@data-key='females' and text()='%s']//following-sibling::td[@data-key='%s' and text()='Afghanistan']"
				+ "//following-sibling::td[@data-key='males' and text()='%s']//following-sibling::td[@data-key='total' and text()='%s']";

		
		
		
		// 0 param
		clickToElement(addressLink);
		clickToElement(oderLink);

		// 1 param
		clickToElement(sidebarLink, "Address");
		clickToElement(sidebarLink, "Orders");

		// 2 param
		clickToElement(dynamicLink, "footer", "Orders");
		clickToElement(dynamicLink, "header", "Login");

		// 4 param
		clickToElement(dynamicCountry, "384187", "Afghanistan", "407124", "791312");
		
		// n param
		clickToElement(dynamicCountry, "384187", "Afghanistan", "407124", "791312");
		///////////

		
		
	}

	public void clickToElement(String locatorValue) {
		System.out.println("Click to" + locatorValue);
	}

	// Ham click vao 1 element ko co dinh voi 1 tham so
	public void clickToElement(String locatorValue, String pageName) {
		locatorValue = String.format(locatorValue, pageName);
		System.out.println("Click to" + locatorValue);
	}

	// Ham click vao 1 element ko co dinh voi 2 tham so
	public void clickToElement(String locatorValue, String pagetype, String pageName) {
		locatorValue = String.format(locatorValue, pagetype, pageName);
		System.out.println("Click to" + locatorValue);
	}
	
	// Ham click vao 1 element ko co dinh voi 4 tham so
	public void clickToElement(String locatorValue, String female, String country, String male,String total) {
		locatorValue = String.format(locatorValue, female, country, male, total);
		System.out.println("Click to" + locatorValue);
	}

	// Varagument = Rest Paramater
	// Ham click vao 1 element ko co dinh voi n tham so
	public void clickToElement(String locatorValue, String...values) {// mang string
		locatorValue = String.format(locatorValue, (Object[])values); // String.format(String, Object)
		System.out.println("Click to" + locatorValue);
	}
	
	@AfterClass
	public void afterClass() {
		quitbrowserDriver();
	}

}
