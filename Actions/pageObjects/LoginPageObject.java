package pageObjects;

import org.openqa.selenium.WebDriver;

import Common.BasePage;
import pageUIs.LoginpageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;
	

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginpageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginpageUI.EMAIL_TEXTBOX, emailAddress);
		
	}

	public void enterToPasswordNameTextbox(String password) {
		waitForElementVisible(driver, LoginpageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginpageUI.PASSWORD_TEXTBOX, password);
	}

	public void clickToLoginButton() {
		waitForElementVisible(driver, LoginpageUI.LOGIN_BUTTON); 
		clickToElement(driver, LoginpageUI.LOGIN_BUTTON); 	
	}

}
