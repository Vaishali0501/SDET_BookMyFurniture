package com.mindtree.sdet.util;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.mindtree.sdet.ExtentReportListener.ReportManager;
import com.mindtree.sdet.pages.PageBase;
import com.relevantcodes.extentreports.ExtentReports;

import junit.framework.Assert;

/**
 * 
 * @author vaishali
 *
 */
public class InitializeDriver{
	String baseUrl;
	public static ExtentReports reporter = null;
	public static String reportFile = "";
	public static WebDriver driver;
	public static EventFiringWebDriver event_driver = null;
	public static EventListenerWebDriver eventListener = null;
	DriverManager dm = new DriverManager();
	public static ChromeDriver driverMobile;
	public static final MobileBrowserManager mobObj = new MobileBrowserManager();
	
	Logger log = Logger.getLogger(PageBase.class);
	
	/**
	 * Method to initialize properties of driver
	 * @param browser
	 * @param baseUrlProperty
	 * @param mobile
	 * @return
	 */
	public WebDriver initialize(String browser, String baseUrlProperty, String mobile) {
		 baseUrl = baseUrlProperty;
						
		//Setting up Reporter and Logger for report
		String reportFile = "./testresources/testreports/testReporter.html";
		reporter = ReportManager.getReporter(reportFile, true);
		
		//logger = reporter.startTest(browserName.toUpperCase() + " - "+ testMethodName);
		log.info("we are here");
		log.info("***************"+baseUrl+"****************");
		
		
			driver=dm.getWebdriver(browser);
			driverManage();
			driver.get(baseUrl);
			return driver;

	}
	
	/**
	 * Method to manage the driver
	 */
	public void driverManage() {
		event_driver = new EventFiringWebDriver(driver);
		// Now creating the object of EventListenerHandler, and register it with
		// EventFiringWebDriver
		
		 eventListener = new EventListenerWebDriver();
		 
			
		 event_driver.register(eventListener);
		 driver = event_driver;
		 System.out.println("After driver event");
		// Setting up Default driver settings
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		 System.out.println("After driver delete cookie");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// System.out.println("prop.getProperty(\"url\")")
		System.out.println("Before driver return");
		
	}


}
