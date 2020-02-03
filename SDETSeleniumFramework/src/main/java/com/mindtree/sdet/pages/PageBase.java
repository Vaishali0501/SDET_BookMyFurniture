package com.mindtree.sdet.pages;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.mindtree.sdet.ExtentReportListener.ReportManager;
import com.mindtree.sdet.util.ConfigReader;
import com.mindtree.sdet.util.EventListenerWebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
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
	public static ExtentReports reporter = null;
	public static ExtentTest extentReportLogger = null;
	public static ChromeDriver driverMobile;
	public static Properties prop;
	public static EventFiringWebDriver event_driver;
	public static EventListenerWebDriver eventListener;
	private static final ConfigReader configReader = new ConfigReader();							  	
	public static String baseUrl;
	public static String reportFile = "";
	public static String testMethodName = null;
	public static String browserString = "";
	public static String driverPath = System.getProperty("user.dir") + "/src/main/resources/drivers/";
	
	
	// **To generate the logs
	Logger log = Logger.getLogger(PageBase.class);
	
	
	
	/***
	 * 
	 * @param browser
	 * @param baseUrlProperty
	 * @param mobile
	 * @throws Exception
	 */
	@Parameters({ "browser", "baseUrl", "mobile" })
	@BeforeClass(alwaysRun = true)
	public void setUp(String browser, String baseUrlProperty, String mobile) throws Exception {
		this.browserString = browser;
		initialize(browser, baseUrlProperty, mobile);
		//beforeMethod(browser,method);
	}
	
	/***
	 * 
	 * @param method
	 */
	@BeforeMethod
	public void beforeMethod(Method method) {
		try {
			extentReportLogger = reporter.startTest(browserString.toUpperCase() + " - "+ method.getName());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * @param password
	 * @param username
	 */
	public void setConfigProperties(String password,String username) {
		password = configReader.getPassword();
		username = configReader.getUsername();
	}

	
	/***
	 * 
	 * @param browser
	 * @return
	 */
	protected WebDriver getWebdriver(String browser) {
		try {
			
			//Initializing webdriver
			WebDriver driver = null;
			
			// If the browser is Firefox, then do this
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} 
			
			//If chrome then do this
			else if (browser.equalsIgnoreCase("chrome")) {
				log.info("Setting Browser");
				log.info("***************"+browser+"***********");
				System.setProperty("webdriver.chrome.driver",driverPath + "chromedriver");
				driver = new ChromeDriver();

			} 
			//If IE then do this
			else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				driver = new InternetExplorerDriver(capabilities);

			}
			return driver;
		} catch (Exception e) {
			log.notify();
			log.info("Unable to set the broswer");
			return null;
		}
	}

	
	/***
	 * 
	 * @param browser
	 * @return
	 */
	public ChromeDriver getWebDriverMobile(String browser) {
		ChromeDriver driver = null;
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",driverPath + "chromedriver");
			Map<String, Object> mobileEmulation = new HashMap<String, Object>();
			mobileEmulation.put("deviceName", "Pixel 2");
			log.info("Device name is pixel 2");
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capabilities);

		} else if (browser != "chrome") {
			log.info("Please select chrome driver");
			return null;
		}
		return driver;
	}

	// Function to initialize the browser and web URL
	// @parameters: browser,baseUrlProperty
	// EventFiringWebDriver used to take screenshots at all events

	protected void initialize(String browser, String baseUrlProperty, String mobile) {
		baseUrl = baseUrlProperty;
		
		
		
		//Setting up Reporter and Logger for report
		reportFile = "./test-resources/testreports/testReporter.html";
		reporter = ReportManager.getReporter(reportFile, true);
		
		//logger = reporter.startTest(browserName.toUpperCase() + " - "+ testMethodName);
		log.info("we are here");
		log.info("***************"+baseUrl+"****************");
		
		if (mobile.equalsIgnoreCase("no")) {
			driver = getWebdriver(browser);
		} else if (mobile.equalsIgnoreCase("yes")) {
			driverMobile = getWebDriverMobile(browser);
		}
		if (driver == null && driverMobile == null) {

			log.info("***************Driver not initalized please select the right option****************");
			Assert.fail("Driver not initialized, please select the right options");
		}
		
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

	/***
	 * 
	 * @param uploadElement
	 * @return
	 */
	public String uploadFile(WebElement uploadElement) {

		try {
			

			// enter the file path onto the file-selection input field
			uploadElement.sendKeys(System.getProperty("user.dir") + "/src/main/resources/data/TestCases.xls");

			return "Uploaded Successfully";

		} catch (NoSuchElementException e) {
			return "Unable to upload";
		}
	}

	// Closes all the browsers
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		reporter.flush();
		cleanUp();
	}

	protected void cleanUp() {
		if (driver != null) {
			driver.quit();
		} else {
			driverMobile.quit();
		}
	}
	
	
	/***
	 * This is reporter method
	 */
	public static void reportTestCaseStatus(WebDriver driver, ExtentTest logger, String methodName,
			boolean testStatus) {
	
		try {
			if (testStatus) {
				String passMessage = "Verified '" + methodName + "'. Test case PASSED.";

				logger.log(LogStatus.PASS, passMessage);
						//,
						//ReportManager.addLocalScreenshotToReport(driver, screenshotPath, methodName, logger));
			} else {
				String failMessage = "Verified '" + methodName + "'. Test case FAILED.";
				logger.log(LogStatus.FAIL, failMessage);
						//,
						//ReportManager.addLocalScreenshotToReport(driver, screenshotPath, methodName, logger));
			}
		} catch (Exception e) {
			System.out.println("Error closing the Test Suite in @AfterSuite method \n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
