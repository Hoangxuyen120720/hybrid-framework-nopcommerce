package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageUIs.HomePageUI;

public class HomePageObject extends BasePageFactory{

	// Chứa những action dạng hàm của page đó: click/ select/ verify/ getText/...

	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Hamf khoi tao cua 1 class
	// cung ten voi class, ko co kieu tra ve, chay dau tien khi class nay goi toi, 
	// 1 class neu ko dc define ham khoi taoj thi mac dinh nos sex co 1 ham khoi tao rong
	// Neu dc define no se uu tien goi toi ham dc define do ( mko dung ham mac dinh)
	// 1 class co the co nhieu ham khoi tao
    // co the co 1 tham so hoac 0 tham so
	// Neu class hien tai ke thua class cha- thi khi taoj ham se co tu khoa super
	// => Goi toi ham khoi tao cua class cha
	// neu class hien tai ko ke thua class nao -> amc dinh se ke thua object
	// Tu khoa this: 1 class co the cos 2 class cung teen (global va local)
	// Global: Pham vi khai bao thuoc class
	// Local: Pham vi thuoc ham
	
	
	@FindBy(xpath = "//a[@class='ico-register']")
	WebElement registerLink;
	
	@FindBy(xpath = "//a[@class='ico-login']")
	WebElement loginLink;
	
	@FindBy(xpath = "//a[@class='ico-account']")
	WebElement myAccountLink;
	
	
	
	
	
	public void clickRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(driver, registerLink);
	}

	public void clickLoginLink() {
		waitForElementClickable(driver, loginLink);
		clickToElement(driver, loginLink);
		
	}

	public void clickTomyAccountLink() {
		waitForElementClickable(driver, myAccountLink);
		clickToElement(driver, myAccountLink);
		
	}

}
