package Common;

import java.lang.invoke.SwitchPoint;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class BaseTest {
// Chua những hàm dùng chung cho cả layer testcase

	private WebDriver driver;

	private String projectPath = System.getProperty("user.dir");

	protected WebDriver grtBrowserDriver(String browserName) {

		/*
		 * if (browserName.equalsIgnoreCase("Chrome")) {
		 * System.setProperty("webdriver.chrome.driver", projectPath +
		 * "\\browserDrivers\\chromedriver.exe"); driver = new ChromeDriver(); } else if
		 * (browserName.equalsIgnoreCase("Firefox")) {
		 * System.setProperty("webdriver.gecko.driver", projectPath +
		 * "\\browserDrivers\\geckodriver.exe"); driver = new FirefoxDriver(); } else if
		 * (browserName.equalsIgnoreCase("Edge")) {
		 * System.setProperty("webdriver.edge.driver", projectPath +
		 * "\\browserDrivers\\msedgedriver.exe"); driver = new EdgeDriver(); } else {
		 * throw new RuntimeException("Browser name is not vailid"); }
		 */
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:

			// tu tai chrome driver tuong ung voi chrome browser xong khoi tao len
			//driver = WebDriverManager.chromedriver().create();

			driver = new ChromeDriver();
			
			// Test 1 ver nao do WebDriverManager.chromedriver().driverVersion("").create();
			/*
			 * System.setProperty("webdriver.chrome.driver", projectPath +
			 * "\\browserDrivers\\chromedriver.exe"); driver = new ChromeDriver();
			 */

			// Headless Browser thi dung theo version 4, khoi tao voi set
			break;
		case FIREFOX:
			
			driver = new FirefoxDriver();
			//driver = WebDriverManager.firefoxdriver().create();
			/*
			 * System.setProperty("webdriver.gecko.driver", projectPath +
			 * "\\browserDrivers\\geckodriver.exe"); driver = new FirefoxDriver();
			 */
			break;
		case EDGE:
			
			driver = new EdgeDriver();
			//driver = WebDriverManager.edgedriver().create();
			/*
			 * System.setProperty("webdriver.edge.driver", projectPath +
			 * "\\browserDrivers\\msedgedriver.exe"); driver = new EdgeDriver();
			 */
			break;

		default:
			throw new RuntimeException("Browser name is not vailid");
		}

		System.out.println("Driver at Basetest: " + driver.toString());

		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().setSize(new Dimension(1280, 1024));
		driver.manage().window().setPosition(new Point(0, 0));
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		return driver;
	}

	public String getEmailAddress() {
		Random rand = new Random();
		return "Xu" + rand.nextInt(9999) + "@gmail.com";
	}

	protected void quitbrowserDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

}
