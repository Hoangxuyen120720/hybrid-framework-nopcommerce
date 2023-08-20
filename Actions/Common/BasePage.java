package Common;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.users.AddressesPageObject;
import pageObjects.users.CustomerPageObject;
import pageObjects.users.DowloadableProductPageObject;
import pageObjects.users.PageGeneratorManager;
import pageObjects.users.RewardPointPageObject;
import pageOnjects.admin.AdminLoginPageObjects;
import pageUIs.users.BasePageUI;

public class BasePage {
	private static final String JavascriptExcutor = null;

	public static BasePage getBasePage() {
		return new BasePage();
	}
// Chứa hàm dùng chung cho cả layer page objects

	// 1. Access Modifier: pubic/ protected/ private/ default
	// 2. kiểu dữ liệu của hàm (Data type): void/ int/ boolean/ WebElement/
	// List<Webelement>...
	// Nó sẽ liên quan đến cái chưc năng mình viết trong thân hàm
	// 3. tên hàm: Đặt tên có nghĩa theo chức năng mình đang viết
	// Convention tuân thoe chuẩn của từng ngôn ngữ lập trình (java)
	// camelCase: Từ đầu tiên viết thường, chữ cái đầu tiên của các từ tiếp theo sẽ
	// viết thường
	// 4. Có tham số hay ko (tùy vào chức nâng cần viết)
	// Dua vao ham cua selenium minh goi ra dee lam gi
	// Web Browser: WWebDriver driver
	// WebElement: Webdriver, String locator
	// 5. Kiểu dữ liệu trả về cho hàm
	// Nếu như có return dữ liệu thì sẽ khớp với kiểu dữ liệu ở số 1,2
	// Nếu như có return thì nó sẽ là step cuối cùng

	public void openUrl(WebDriver driver, String url) {
		// 2 + 4 + 5
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();

	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();

	}

	public String getPageSource(WebDriver driver) {

		return driver.getPageSource();
	}

	public void backTopage(WebDriver driver) {

		driver.navigate();

	}

	public void refreshcurrentPage(WebDriver driver) {

		driver.navigate().refresh();

	}

	public void forwardtopage(WebDriver driver) {

		driver.navigate().forward();

	}

	public void acceptToAlert(WebDriver driver) {

		waitForAlertPresence(driver).accept();

	}

	public void cancelToAlert(WebDriver driver) {

		waitForAlertPresence(driver).dismiss();

	}

	public String getAlertText(WebDriver driver) {

		return waitForAlertPresence(driver).getText();

	}

	public void sendKeyToAlert(WebDriver driver, String valueToSendKey) {

		waitForAlertPresence(driver).sendKeys(valueToSendKey);

	}

	public Alert waitForAlertPresence(WebDriver driver) {
// ve 3
		// return new WebDriverWait(driver,
		// 30).until(ExpectedConditions.alertIsPresent());
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());

	}

	public void switchtoWIndowByID(WebDriver driver, String otherID) {

		Set<String> allWindowIDs = driver.getWindowHandles();

		// Sau do dung vong lap duyet qua va kiem tra

		for (String id : allWindowIDs) {
			if (!id.equals(otherID)) {
				driver.switchTo().window(id);
			}
		}

	}

	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {

		// Lay het tat ca ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();

		// Sau do dung vong lap duyet qua va kiem tra

		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
			}
		}
		driver.switchTo().window(parentID);
	}

	public void switchtoWIndowByPAgeTitle(WebDriver driver, String expectedPaggeTitle) {

		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			// Switch tung ID truoc
			driver.switchTo().window(id);

			// Lay ra title cuar page nay
			String actualPageTitle = driver.getTitle();

			if (actualPageTitle.equals(expectedPaggeTitle)) {
				break;
			}
		}
	}

	public String getUrlByUserPass(String url, String username, String password) {

		String[] newurl = url.split("//");
		// http:
		// the-internet.herokuapp.com/basic_auth

		url = newurl[0] + "//" + "admin" + ":" + "admin" + "@" + newurl[1];

		return url;

	}

	public void sleepInSecond(long timeoutInSeconds) {
		try {
			Thread.sleep(timeoutInSeconds * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public By getByLocator(String locatorvalue) {
		By by = null;
		
		if(locatorvalue.startsWith("xpath=") || locatorvalue.startsWith("Xpath=") || locatorvalue.startsWith("XPATH=") ||locatorvalue.startsWith("XPath=")) {
			by = By.xpath(locatorvalue.substring(6));
		} else if (locatorvalue.startsWith("css=") || locatorvalue.startsWith("Css=") ||locatorvalue.startsWith("CSS=")) {
			by = By.cssSelector(locatorvalue.substring(4));
		} else if (locatorvalue.startsWith("id=") || locatorvalue.startsWith("Id=") || locatorvalue.startsWith("ID=")) {
			by = By.id(locatorvalue.substring(3));
		} else if (locatorvalue.startsWith("name=") || locatorvalue.startsWith("Name=") || locatorvalue.startsWith("NAME=")) {
			by = By.name(locatorvalue.substring(5));
		} else if (locatorvalue.startsWith("class=") || locatorvalue.startsWith("Class=") || locatorvalue.startsWith("CLASS=")) {
			by = By.className(locatorvalue.substring(6));
		} else if (locatorvalue.startsWith("tagname=") || locatorvalue.startsWith("Tagname=") || locatorvalue.startsWith("TAGNAME")) {
			by = By.tagName(locatorvalue.substring(8));
		}  else {
			throw new RuntimeException("Locator type is not vaild");
		}
		return by;
	}
	
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}
	
	public String getDynamicLocator(String locator, String... restParams) {
		return String.format(locator, (Object[])restParams);
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	public List<WebElement> getListElement(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}
	
	public void clickToElement(WebDriver driver, String locator, String... restParams) {
		getElement(driver, getDynamicLocator(locator, restParams)).click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}
	
	public void sendKeyToElement(WebDriver driver, String locator, String valueToSend, String... restParams ) {
		getElement(driver, getDynamicLocator(locator, restParams)).clear();
		getElement(driver, getDynamicLocator(locator, restParams)).sendKeys(valueToSend);
	}

	public void selectDropdown(WebDriver driver, String locator, String itemText) {
		new Select(getElement(driver, locator)).selectByVisibleText(itemText);
	}

	public void getFirstSelectedOptionText(WebDriver driver, String locator) {
		new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
	}

	public boolean isDropdownmultiple(WebDriver driver, String locator) {
		return new Select(getElement(driver, locator)).isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String locatorParent, String locatorChild,
			String expectedText) {

		getElement(driver, locatorParent).click();
		sleepInSecond(1);

		List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorChild)));

		for (WebElement tempElement : allItems) {
			if (tempElement.getText().equals(expectedText)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
				sleepInSecond(1);

				tempElement.click();
				sleepInSecond(1);

				break;
			}
		}
	}

	public String getElementText(WebDriver driver, String locator) {
		return waitForElementVisible(driver, locator).getText();

	}
	
	public String getElementText(WebDriver driver, String locator, String... restParams) {
		return waitForElementVisible(driver, getDynamicLocator(locator, restParams)).getText();
		
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... restParams) {
		return getElement(driver, getDynamicLocator(locator, restParams)).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getElement(driver, locator).getCssValue(propertyName);
	}

	public String getHexaColorByRGBA(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex().toUpperCase();
	}

	public int getListElementSize(WebDriver driver, String locator) {
		return getListElement(driver, locator).size();
	}

	public void checkToCheckboxRadio(WebDriver driver, String locator) {
		if (!isElementDisplayed(driver, locator)) {
			getElement(driver, locator).click();
		}

	}

	public void unCheckToCheckbox(WebDriver driver, String locator) {
		if (isElementDisplayed(driver, locator)) {
			getElement(driver, locator).click();
		}

	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String... restParams) {
		return getElement(driver, getDynamicLocator(locator, restParams)).isDisplayed();
	}

	public boolean isElementEnable(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver, String locator) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		new Actions(driver).moveToElement(getElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		new Actions(driver).doubleClick(getElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		new Actions(driver).contextClick(getElement(driver, locator)).perform();
	}

	public void dragAndDropToElement(WebDriver driver, String sourceXpath, String targetXpath) {
		new Actions(driver).dragAndDrop(getElement(driver, sourceXpath), getElement(driver, targetXpath)).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		new Actions(driver).sendKeys(getElement(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(WebDriver driver, String locator) {
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
				getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
				getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		return status;
	}

	// Wait
	public WebElement waitForElementVisible(WebDriver driver, String locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}
	
	public WebElement waitForElementVisible(WebDriver driver, String locator, String... restParams) {
		return new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locator, restParams))));
	}

	public void waitForListElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String... restParams) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
		.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locator, restParams))));
	}

	public void waitForElementInvisivle(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout))
				.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	public boolean isPageLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		// Dkien 1
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		// Dkien 2
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	// Chia page

	public RewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_PAGE_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_PAGE_LINK);
		return PageGeneratorManager.getRewardPointPage(driver);
	}

	public CustomerPageObject openCustomerInforPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_PAGE_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFO_PAGE_LINK);
		return PageGeneratorManager.getCustomerPage(driver);
	}

	public DowloadableProductPageObject openDowloadableproductPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.DOWNLOADABLE_PRODUCT_PAGE_LINK);
		clickToElement(driver, BasePageUI.DOWNLOADABLE_PRODUCT_PAGE_LINK);
		return PageGeneratorManager.getDowloadablePage(driver);
	}

	public AddressesPageObject openAddressesPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESSES_PAGE_LINK);
		clickToElement(driver, BasePageUI.ADDRESSES_PAGE_LINK);
		return PageGeneratorManager.getAddressesPage(driver);
	}

	public pageObjects.users.HomePageObject userAbleToLogout(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.USER_LOGOUT_LINK);
		clickToElement(driver, BasePageUI.USER_LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);

	}

	// Admin

	public AdminLoginPageObjects adminAbleToLogout(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADMIN_LOGOUT_LINK);
		clickToElement(driver, BasePageUI.ADMIN_LOGOUT_LINK);
		return PageGeneratorManager.getAdminLoginPage(driver);

	}
	
	private long longTimeout = GlobalContants.LONG_TIMEOUT;

}
