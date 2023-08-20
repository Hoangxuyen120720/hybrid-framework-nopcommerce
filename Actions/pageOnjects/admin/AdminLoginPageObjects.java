package pageOnjects.admin;

import org.openqa.selenium.WebDriver;

import Common.BasePage;
import pageObjects.users.PageGeneratorManager;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPageObjects extends BasePage{

	WebDriver driver;

	public AdminLoginPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.LOGIN_BUTTON);
		sendKeyToElement(driver, AdminLoginPageUI.LOGIN_BUTTON, password);
	
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
	}
	
	public AdminDashboardPageObject clickToLoginButton2() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboadPage(driver);
	}
	
	
	public AdminDashboardPageObject loginAsAdmin(String emailAddress, String password) {
		inputToEmailTextbox(emailAddress);
		inputToPasswordTextbox(password);
		clickToLoginButton();
		return PageGeneratorManager.getAdminDashboadPage(driver);
	}
	
	
	
	
}
