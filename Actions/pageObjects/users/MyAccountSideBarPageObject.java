package pageObjects.users;

import org.openqa.selenium.WebDriver;

import Common.BasePage;
import pageUIs.users.BasePageUI;
import pageUIs.users.SideBarPageUI;

public class MyAccountSideBarPageObject extends BasePage {

	WebDriver driver;

	public MyAccountSideBarPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RewardPointPageObject openRewardPointPage() {
		waitForElementClickable(driver, SideBarPageUI.REWARD_POINT_PAGE_LINK);
		clickToElement(driver, SideBarPageUI.REWARD_POINT_PAGE_LINK);
		return PageGeneratorManager.getRewardPointPage(driver);
	}

	public CustomerPageObject openCustomerInforPage() {
		waitForElementClickable(driver, SideBarPageUI.CUSTOMER_INFO_PAGE_LINK);
		clickToElement(driver, SideBarPageUI.CUSTOMER_INFO_PAGE_LINK);
		return PageGeneratorManager.getCustomerPage(driver);
	}

	public DowloadableProductPageObject openDowloadableproductPage() {
		waitForElementClickable(driver, SideBarPageUI.DOWNLOADABLE_PRODUCT_PAGE_LINK);
		clickToElement(driver, SideBarPageUI.DOWNLOADABLE_PRODUCT_PAGE_LINK);
		return PageGeneratorManager.getDowloadablePage(driver);
	}

	public AddressesPageObject openAddressesPage() {
		waitForElementClickable(driver, SideBarPageUI.ADDRESSES_PAGE_LINK);
		clickToElement(driver, SideBarPageUI.ADDRESSES_PAGE_LINK);
		return PageGeneratorManager.getAddressesPage(driver);
	}

	public MyAccountSideBarPageObject openDynamicSideBarPage(String pageName) {
		waitForElementClickable(driver, SideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
		clickToElement(driver, SideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);

		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getCustomerPage(driver);
		case "Addresses":
			return PageGeneratorManager.getAddressesPage(driver);
		case "Reward points":
			return PageGeneratorManager.getRewardPointPage(driver);
		case "Downloadable products":
			return PageGeneratorManager.getDowloadablePage(driver);

		default:
			throw new RuntimeException("Sidebar page name incorrect");
		}
		
	}
	
	public void openDynamicSideBarPageByName (String pageName) {
		waitForElementClickable(driver, SideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
		clickToElement(driver, SideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
	}	
	
	
	
	
	
}
