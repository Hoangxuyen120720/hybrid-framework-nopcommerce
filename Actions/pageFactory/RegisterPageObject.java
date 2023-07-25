package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Common.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory{

	private WebDriver driver;
	
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(xpath = "//button[@id='register-button']")
	WebElement registerButton;
	
	@FindBy(xpath = "//span[@id='FirstName-error']")
	WebElement firstNameErrorMsg;
	
	@FindBy(xpath = "//span[@id='LastName-error']")
	WebElement lastNameErrorMsg;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	WebElement emailErrorMsg;
	
	@FindBy(xpath = "//span[@id='Password-error']")
	WebElement passWordErrorMsg;
	
	@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
	WebElement confirmPassWordErrorMsg;
	
	@FindBy(xpath = "//div[@class='result']")
	WebElement registerSuccessMsg;
	
	@FindBy(xpath = "//input[@id='FirstName']")
	WebElement firstNameTextbox;
	
	@FindBy(xpath = "//input[@id='LastName']")
	WebElement lastNameTextbox;
	
	@FindBy(xpath = "//input[@id='Email']")
	WebElement emailTextbox;
	
	@FindBy(xpath = "//input[@id='Password']")
	WebElement passWordTextbox;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	WebElement confirmPassWordTextbox;
	
	@FindBy(xpath = "//div[@class='header-logo']/a")
	WebElement homePageLogoImg;
	
	

	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
		
	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, firstNameErrorMsg);
		return	getElementText(driver, firstNameErrorMsg);
		
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, lastNameErrorMsg);
		return	getElementText(driver, lastNameErrorMsg);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, emailErrorMsg);
		return	getElementText(driver, emailErrorMsg);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, passWordErrorMsg);
		return	getElementText(driver, passWordErrorMsg);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, confirmPassWordErrorMsg);
		return	getElementText(driver, confirmPassWordErrorMsg);
	}

	public void clickToHomePageLogo() {
		waitForElementClickable(driver, homePageLogoImg);
		clickToElement(driver, homePageLogoImg);
		
	}

	public void enterToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendKeyToElement(driver, firstNameTextbox, firstName);
		
	}

	public void enterToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendKeyToElement(driver, lastNameTextbox, lastName);
		
		
	}

	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(driver, emailTextbox, email);
		
		
	}

	public void enterToPasswordNameTextbox(String password) {
		waitForElementVisible(driver, passWordTextbox);
		sendKeyToElement(driver, passWordTextbox, password);
		
		
	}

	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPassWordTextbox);
		sendKeyToElement(driver, confirmPassWordTextbox, confirmPassword);
		
		
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMsg);
		return getElementText(driver, registerSuccessMsg);
	}

	

	
	
}
