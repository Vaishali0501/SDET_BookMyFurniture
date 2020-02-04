package com.mindtree.sdet.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileBrowserManager {
	
	//public MobileBrowserManager(){
	//}



public ChromeDriver getMobileDriver(String browser,Logger log,String driverPath,String baseUrl) {
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
		driver.get(baseUrl);
	} else if (browser != "chrome") {
		log.info("Please select chrome driver");
		return null;
	}
	return driver;
}
}
