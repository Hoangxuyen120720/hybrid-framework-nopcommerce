package Common;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());

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

	public By getByXpath(String xpathExpression) {
		return By.xpath(xpathExpression);
	}

	public WebElement getElement(WebDriver driver, String xpathExpression) {
		return driver.findElement(getByXpath(xpathExpression));
	}

	public List<WebElement> getListElement(WebDriver driver, String xpathExpression) {
		return driver.findElements(getByXpath(xpathExpression));
	}

	public void clickToElement(WebDriver driver, String xpathExpression) {
		getElement(driver, xpathExpression).click();
	}

	public void sendKeyToElement(WebDriver driver, String xpathExpression, String value) {
		getElement(driver, xpathExpression).clear();
		getElement(driver, xpathExpression).sendKeys(value);
	}

	public void selectDropdown(WebDriver driver, String xpathExpression, String itemText) {
		new Select(getElement(driver, xpathExpression)).selectByVisibleText(itemText);
	}

	public void getFirstSelectedOptionText(WebDriver driver, String xpathExpression) {
		new Select(getElement(driver, xpathExpression)).getFirstSelectedOption().getText();
	}

	public boolean isDropdownmultiple(WebDriver driver, String xpathExpression) {
		return new Select(getElement(driver, xpathExpression)).isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String Xpathparent, String Xpathchild,
			String expectedText) {

		getElement(driver, Xpathparent).click();
		sleepInSecond(1);

		List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(Xpathchild)));

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

	public String getElementText(WebDriver driver, String xpathExpression) {
		return waitForElementVisible(driver, xpathExpression).getText();

	}

	public String getElementAttribute(WebDriver driver, String xpathExpression, String attributeName) {
		return getElement(driver, xpathExpression).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String xpathExpression, String propertyName) {
		return getElement(driver, xpathExpression).getCssValue(propertyName);
	}

	public String getHexaColorByRGBA(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex().toUpperCase();
	}

	public int getListElementSize(WebDriver driver, String xpathExpression) {
		return getListElement(driver, xpathExpression).size();
	}

	public void checkToCheckboxRadio(WebDriver driver, String xpathExpression) {
		if (!isElementDisplayed(driver, xpathExpression)) {
			getElement(driver, xpathExpression).click();
		}

	}

	public void unCheckToCheckbox(WebDriver driver, String xpathExpression) {
		if (isElementDisplayed(driver, xpathExpression)) {
			getElement(driver, xpathExpression).click();
		}

	}

	public boolean isElementDisplayed(WebDriver driver, String xpathExpression) {
		return getElement(driver, xpathExpression).isDisplayed();
	}

	public boolean isElementEnable(WebDriver driver, String xpathExpression) {
		return getElement(driver, xpathExpression).isEnabled();
	}

	public void switchToFrame(WebDriver driver, String xpathExpression) {
		driver.switchTo().frame(getElement(driver, xpathExpression));
	}

	public void switchToDefaultContent(WebDriver driver, String xpathExpression) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String xpathExpression) {
		new Actions(driver).moveToElement(getElement(driver, xpathExpression)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String xpathExpression) {
		new Actions(driver).doubleClick(getElement(driver, xpathExpression)).perform();
	}

	public void rightClickToElement(WebDriver driver, String xpathExpression) {
		new Actions(driver).contextClick(getElement(driver, xpathExpression)).perform();
	}

	public void dragAndDropToElement(WebDriver driver, String sourceXpath, String targetXpath) {
		new Actions(driver).dragAndDrop(getElement(driver, sourceXpath), getElement(driver, targetXpath)).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String xpathExpression, Keys key) {
		new Actions(driver).sendKeys(getElement(driver, xpathExpression), key).perform();
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

	public void hightlightElement(WebDriver driver, String xpathExpression) {
		WebElement element = getElement(driver, xpathExpression);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, xpathExpression));
	}

	public void scrollToElementOnTop(WebDriver driver, String xpathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getElement(driver, xpathExpression));
	}

	public void scrollToElementOnDown(WebDriver driver, String xpathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
				getElement(driver, xpathExpression));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathExpression, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getElement(driver, xpathExpression));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathExpression, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, xpathExpression));
	}

	public String getElementValidationMessage(WebDriver driver, String xpathExpression) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
				getElement(driver, xpathExpression));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathExpression) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(driver, xpathExpression));
		return status;
	}

	// Wait
	public WebElement waitForElementVisible(WebDriver driver, String xpathExpression) {
		return new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathExpression)));
	}

	public void waitForListElementVisible(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathExpression)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathExpression)));
	}

	public void waitForElementInvisivle(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathExpression)));
	}

}
