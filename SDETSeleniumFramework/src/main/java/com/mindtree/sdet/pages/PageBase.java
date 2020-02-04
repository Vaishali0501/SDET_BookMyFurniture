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
import org.testng.annotations.AfterMethod;
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
import com.mindtree.sdet.util.*;

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
	public static ConfigReader configReader;
	public static String baseUrl;
	public static String reportFile = "";
	public static String testMethodName = null;
	public static String browserString = "";
	public static String driverPath = System.getProperty("user.dir") + "/src/main/resources/drivers/";
	public static final TestCaseReporterUtil testReporter = new TestCaseReporterUtil();
	public static final MobileBrowserManager mobObj = new MobileBrowserManager();
	public static final CommonMethodUtility commonMethods = new CommonMethodUtility();
	public static final InitializeDriver iDriver = new InitializeDriver();
	DriverManager dm = new DriverManager();

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
		String filePath = System.getProperty("user.dir") + "/src/main/resources/data/config.properties";
		configReader = new ConfigReader(filePath);

		if (mobile.equalsIgnoreCase("no")) {
			this.driver = iDriver.initialize(browser, baseUrlProperty, mobile);
		} else if (mobile.equalsIgnoreCase("yes")) {
			this.driverMobile = getWebDriverMobile(browser, baseUrlProperty);
		}
		// beforeMethod(browser,method);
		reportFile = "./test-resources/testreports/testReporter.html";
		reporter = ReportManager.getReporter(reportFile, true);
	}

	/***
	 * 
	 * @param method
	 */
	@BeforeMethod
	public void beforeMethod(Method method) {
		try {
			extentReportLogger = reporter.startTest(browserString.toUpperCase() + " - " + method.getName());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/***
	 * 
	 * @param password
	 * @param username
	 */
	public void setConfigProperties(String password, String username) {
		password = configReader.getPassword();
		username = configReader.getUsername();
	}

	/***
	 * 
	 * @param browser
	 * @return
	 */
	public ChromeDriver getWebDriverMobile(String browser, String baseUrlProperty) {

		// Getting Mobile Browser Driver
		ChromeDriver driver = mobObj.getMobileDriver(browser, log, driverPath, baseUrlProperty);
		return driver;
	}

	/**
	 * Checks if the element presents on the page and visible
	 * 
	 * @param element
	 *            element to check
	 *
	 * @return true if the element presents on the page and visible
	 */
	protected boolean isElementPresent(WebElement element) {
		return commonMethods.isElementPresent(element);
	}

	/**
	 * Upload file method calling from common method utility
	 * 
	 * @param uploadElement
	 * @return
	 */
	public String uploadFile(WebElement uploadElement) {

		return commonMethods.uploadFile(uploadElement);
	}

	/**
	 * This method gets new window title from Common Method Utility class
	 * 
	 * @param aboutClick
	 * @return
	 */
	public String getNewWindowTitle(WebElement aboutClick) {

		return commonMethods.switchWindows(driver, aboutClick);
	}

	/**
	 * Click on element
	 * 
	 * @param element
	 * @return
	 */
	public boolean clickElement(WebElement element) {

		return commonMethods.click(element);
	}

	
	/**
	 * Check if element is enabled
	 * @param element
	 * @return
	 */
	public boolean checkElementEnabled(WebElement element) {
		return commonMethods.isElementEnabled(element);
	}
	
	
	/**
	 * Get element text
	 * 
	 * @param element
	 * @return
	 */
	public String getTextForElement(WebElement element) {
		return commonMethods.getTextForElement(element);
	}

	
	/**
	 * Used to send keys to WebElement
	 * @param element
	 * @param data
	 * @return
	 */
	public boolean sendKeysToElement(WebElement element,String data) {
		return commonMethods.sendKeys(element, data);
	}
	
	
	/**
	 * Return the title of the page
	 * @param driverNew
	 * @return
	 */
	public String getTitleForDriver(WebDriver driverNew) {
		return commonMethods.getTitleForDriver(driverNew);
	}
	
	
	/**
	 * AfterClass method for driver cleanup and flush the logs into ExtentReport
	 * 
	 * @throws Exception
	 */
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		reporter.flush();
		commonMethods.cleanUp(driver, driverMobile);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDownAfterMethod() throws Exception {
		commonMethods.cleanUp(driver, driverMobile);
	}

	/***
	 * This is reporter method, to put the logs into ExtentReport.
	 */
	public static void reportTestCaseStatus(WebDriver driver, ExtentTest logger, String methodName,
			boolean testStatus) {
		testReporter.reportTestCaseStatus(driver, logger, methodName, testStatus);
	}

}
