package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageUIs.users.LoginpageUI;

public class LoginPageObject extends BasePageFactory {
	private WebDriver driver;
	

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//input[@id='Email']")
	WebElement EmailTextbox;
	
	@FindBy(xpath = "//input[@id='Password']")
	WebElement passWordTextbox;
	
	@FindBy(xpath = "//button[contains(@class, 'login-button')]")
	WebElement loginButton;
	
	
	public HomePageObject loginAsUser(String emailAddress, String password) {
		enterToEmailTextbox(emailAddress);
		enterToPasswordNameTextbox(password);
		clickToLoginButton();
		return new HomePageObject(driver);
	}
	

	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, EmailTextbox);
		sendKeyToElement(driver, EmailTextbox, emailAddress);
		
	}

	public void enterToPasswordNameTextbox(String password) {
		waitForElementVisible(driver, passWordTextbox);
		sendKeyToElement(driver, passWordTextbox, password);
	}

	public void clickToLoginButton() {
		waitForElementVisible(driver, loginButton); 
		clickToElement(driver, loginButton); 	
	}

}
