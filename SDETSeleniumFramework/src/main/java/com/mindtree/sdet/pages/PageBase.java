package com.mindtree.sdet.pages;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.apache.log4j.Logger;
import java.lang.Object;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mindtree.sdet.ExtentReportListener.ExtentReportNG;
import com.mindtree.sdet.util.ConfigReader;
import com.mindtree.sdet.util.EventListenerWebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

/**
 * 
 * @author Vaishali PageBase class is a Base class which has all common
 *         properties for running any Test Class Methods:
 *         setUp,getWebdriver,initialize,tearDown
 *
 */
public abstract class PageBase {

	// **Static Declarations
	public static WebDriver driver;
	public static ChromeDriver driverMobile;
	public static Properties prop;
	public static EventFiringWebDriver event_driver;
	public static EventListenerWebDriver eventListener;
	public static ExtentReportNG extent;
	// **Calling url,password,username from the class configReader
	private static final ConfigReader configReader = new ConfigReader();
	public static String password = configReader.getPassword();
	public static String username = configReader.getUsername();
	public static String baseUrl;

	// **To generate the logs
	Logger log = Logger.getLogger(PageBase.class);

	@Parameters({ "browser", "baseUrl", "mobile" })
	@BeforeClass(alwaysRun = true)
	public void setUp(String browser, String baseUrlProperty, String mobile) throws Exception {
		System.out.println(mobile);
		initialize(browser, baseUrlProperty, mobile);

	}

	protected WebDriver getWebdriver(String browser) {
		try {
			WebDriver driver = null;
			// If the browser is Firefox, then do this
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.out.println("Setting browser");
				System.out.println(browser);
				System.setProperty("webdriver.chrome.driver",
						"/Users/sushant/git/SDET_BookMyFurniture/SDETSeleniumFramework/src/main/resources/drivers/chromedriver");
				driver = new ChromeDriver();

			} else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				driver = new InternetExplorerDriver(capabilities);

			}
			return driver;
		} catch (Exception e) {
			System.out.println("getWebDriver : Unable to set the Webdriver");
			return null;
		}
		// return driver;
	}

	public ChromeDriver getWebDriverMobile(String browser) {
		ChromeDriver driver = null;
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"/Users/sushant/git/SDET_BookMyFurniture/SDETSeleniumFramework/src/main/resources/drivers/chromedriver");
			Map<String, Object> mobileEmulation = new HashMap<String, Object>();
			mobileEmulation.put("deviceName", "Pixel 2");
			System.out.println("The device name is pixel 2");
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capabilities);

		} else if (browser != "chrome") {
			System.out.println("Please select chrome driver");
			return null;
		}
		return driver;
	}

	// Function to initialize the browser and web URL
	// @parameters: browser,baseUrlProperty
	// EventFiringWebDriver used to take screenshots at all events

	protected void initialize(String browser, String baseUrlProperty, String mobile) {
		baseUrl = baseUrlProperty;
		log.info("we are here");
		System.out.println("URL is : ======== " + baseUrl);
		System.out.println(baseUrlProperty);
		if (mobile.equalsIgnoreCase("no")) {
			driver = getWebdriver(browser);
		} else if (mobile.equalsIgnoreCase("yes")) {
			driverMobile = getWebDriverMobile(browser);
		}
		if (driver == null && driverMobile == null) {

			System.out.println("Driver not initialized, please select the right options");
			Assert.fail("Driver not initialized, please select the right options");
		}
		// System.out.println(driver.getTitle());
		// System.out.println("=============");
		else {
			if (driver != null) {
				event_driver = new EventFiringWebDriver(driver);
				// Now creating the object of EventListenerHandler, and register it with
				// EventFiringWebDriver
				 eventListener = new EventListenerWebDriver();
				 event_driver.register(eventListener);
				 driver = event_driver;
				// Setting up Default driver settings
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				// System.out.println("prop.getProperty(\"url\")")
				driver.get(baseUrl);
			} else {
				driverMobile.get(baseUrl);
				// EventFiringWebDriver event_driver_Mobile = new
				// EventFiringWebDriver(driverMobile);
				// Now creating the object of EventListenerHandler, and register it with
				// EventFiringWebDriver
				// eventListener = new EventListenerWebDriver();
				// event_driver_Mobile.register(eventListener);
			}
		}
	}

	/**
	 * Checks if the element presents on the page and visible
	 * 
	 * @param element
	 *            element to check
	 *
	 * @return true if the element presents on the page and visible
	 */
	protected boolean isElementPresents(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public String uploadFile(WebElement uploadElement) {

		// driver.findElement(By.id("uploadfile_0"));

		try {
			// enter the file path onto the file-selection input field
			uploadElement.sendKeys("/Users/sushant/Documents/test2.csv");

			return "Uploaded Successfully";

		} catch (NoSuchElementException e) {
			return "Unable to upload";
		}
	}

	// Closes all the browsers
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		cleanUp();
	}

	protected void cleanUp() {
		if (driver != null) {
			driver.quit();
		} else {
			driverMobile.quit();
		}
	}
}
