package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Common.BasePage;
import pageUIs.CustomerPageUI;

public class CustomerPageObject extends BasePageFactory {
	 WebDriver driver;

	public CustomerPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// TestNG: @BeforeClass/ @BeforeTest/ @Test/ @Parameter....
	
	// UI: Annotation
	// @FindBy/ @FindBys/ @FindAll/ @Cachelookup
	
	
	@FindBy(how = How.XPATH, using = "//input[@id='FirstName']")
	WebElement firstNameTextbox;
	
	@FindBy(xpath = "//input[@id='LastName']")
	WebElement lastNameTextbox;
	
	@FindBy(xpath = "//input[@id='Email']")
	WebElement emailTextbox;
	
	
	// Action
	
	public String getFirstNameAttributeValue() {
		waitForElementVisible(driver, firstNameTextbox);
		return getElementAttribute(driver, firstNameTextbox, "value");
		
	}


	public String getLastNameAttributeValue() {
		waitForElementVisible(driver, lastNameTextbox);
		return getElementAttribute(driver, lastNameTextbox, "value");
		
	}


	public String getEmailAttributeValue() {
		waitForElementVisible(driver, emailTextbox);
		return getElementAttribute(driver, emailTextbox, "value");
		
	}

}
