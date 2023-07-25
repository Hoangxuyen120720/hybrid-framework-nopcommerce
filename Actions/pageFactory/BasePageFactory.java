package pageFactory;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	
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
	
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}
	
	public void waitForListElementVisible(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementInvisivle(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.invisibilityOf(element));
	}

	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText();

	}
	
	public void waitForElementVisible(WebDriver driver, WebElement element) {
			new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
	}

	public String getElementAttribute(WebDriver driver, WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}
	
	public void sendKeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
