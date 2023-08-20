package pageObjects.users;

import org.openqa.selenium.WebDriver;

import pageOnjects.admin.AdminDashboardPageObject;
import pageOnjects.admin.AdminLoginPageObjects;

public class PageGeneratorManager {

	// Tất cả các class trong pageobject đều phải có 1 hàm để gọi khởi tạo ra
	/* User */
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static CustomerPageObject getCustomerPage(WebDriver driver) {
		return new CustomerPageObject(driver);
	}
	
	public static DowloadableProductPageObject getDowloadablePage(WebDriver driver) {
		return new DowloadableProductPageObject(driver);
	}
	
	public static AddressesPageObject getAddressesPage(WebDriver driver) {
		return new AddressesPageObject(driver);
	}
	
	public static RewardPointPageObject getRewardPointPage(WebDriver driver) {
		return new RewardPointPageObject(driver);
	}
	
	
	/* Admin */
	public static AdminLoginPageObjects getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObjects(driver);
	}
	
	public static AdminDashboardPageObject getAdminDashboadPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}
	
	
}
