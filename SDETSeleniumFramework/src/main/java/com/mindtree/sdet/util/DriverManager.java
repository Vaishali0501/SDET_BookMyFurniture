package com.mindtree.sdet.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mindtree.sdet.pages.PageBase;

public class DriverManager {
	
	public static String driverPath = System.getProperty("user.dir") + "/src/main/resources/drivers/";
	
	Logger log = Logger.getLogger(PageBase.class);
	
	public WebDriver getWebdriver(String browser) {
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


}
