package pageObjects;

import org.openqa.selenium.WebDriver;

import Common.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage{

	// Chứa những action dạng hàm của page đó: click/ select/ verify/ getText/...

	WebDriver driver;
	
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
	
	
	
	
	public HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void clickRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
	}

	public void clickLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		
	}

	public void clickTomyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		
	}

}
