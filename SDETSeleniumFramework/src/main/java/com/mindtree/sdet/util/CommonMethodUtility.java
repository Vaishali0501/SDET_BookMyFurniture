package com.mindtree.sdet.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.mindtree.sdet.pages.PageBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author vaishali
 * 
 * This class defines all the common methods we have to use during our testing.
 *
 */
public class CommonMethodUtility {
	
	
	Logger log = Logger.getLogger(CommonMethodUtility.class);
	
	public CommonMethodUtility(){
		
	}
	
	/**
	 * Checks if the element presents on the page and visible
	 * 
	 * @param element
	 *            element to check
	 *
	 * @return true if the element presents on the page and visible
	 */
	public boolean isElementPresent(WebElement element) {
		try {
			boolean isPresent = element.isDisplayed();
			log.info("Element present");
			return isPresent;
		} catch (NoSuchElementException e) {
			log.error(e.getMessage());
			return false;
		}
		catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Method to check click using By location
	 * @param locator
	 * @param driver
	 * @return
	 */
	public boolean click(By locator,WebDriver driver) {
		try {
			driver.findElement(locator).click();
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	
	/**
	 * Mthod to click an Element using WebElement as parameter
	 * @param webElement
	 * @return
	 */
	public boolean click(WebElement webElement) {
		try {
			webElement.click();
			return true;
		} catch (NoSuchElementException e) {
			log.error(e.getMessage());
			return false;
		}catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

//sendKeys method checks the presence the webelement using try/catch block and performs operation if available.
	
	/**
	 * Method to send Keys into a Form or fill a TextBox or such elements.
	 * @param webElement
	 * @param name
	 * @return true if sendKeys is successful or false if not
	 */
	public boolean sendKeys(WebElement webElement, String name) {
		try {
			webElement.sendKeys(name);
			return true;
		} catch (NoSuchElementException e) {
			log.error(e.getMessage());
			return false;
		}catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}
//isDisplayed validates the operation is successful, test case result depends on this.

	
	/**
	 * 
	 * @param webElement
	 * @param message
	 * @return
	 */
	public boolean isDisplayed(WebElement webElement) {
		try {
			webElement.isDisplayed();
			log.info("WebElement displayed");
			return true;
		} catch (NoSuchElementException e) {
			log.error(e.getMessage());
			return false;
		}catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * Window handler method, clicks on About link and opens in new window, gets title and closes it
	 * @param driver
	 * @param aboutClick
	 * @return
	 */
	public String switchWindows(WebDriver driver,WebElement aboutClick) {
	
		try {
		
			Actions newTab = new Actions(driver);
			newTab.keyDown(Keys.SHIFT).click(aboutClick).keyUp(Keys.SHIFT).build().perform();
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			String newTitle = driver.getTitle();
			driver.close();
			driver.switchTo().window(tabs.get(0));
			return newTitle;
		}catch(Exception ex) {
			log.info("Error in window handler " + ex.getMessage() );
			return "Unable to open new window";
		}
		
	}

	/***
	 * Method to upload a file for UploadFile functionality.
	 * @param uploadElement
	 * @return Success message
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

	
	
	/**
	 * Clean up method to close the browser and clean up the driver
	 * @param driver
	 * @param driverMobile
	 */
	public void cleanUp(WebDriver driver,WebDriver driverMobile) {
		if (driver != null) {
			driver.quit();
		} else {
			driverMobile.quit();
		}
	}
	
	
	

	

}
