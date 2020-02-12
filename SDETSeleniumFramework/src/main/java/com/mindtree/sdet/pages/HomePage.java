package com.mindtree.sdet.pages;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

/**
 * This Class is for Home page actions and navigation
 * 
 * @author vaishali
 *
 */
public class HomePage extends PageBase {

	public static boolean clickElement;
	public static String getTextStr = null;
	private static Logger log = Logger.getLogger(HomePage.class);

	
	public static LoginPage logPg = null;
	// Page Factory -OR
	@FindBy(xpath = "//button[@class='mat-button mat-button-base ng-star-inserted']")
	WebElement signInButton;

	@FindBy(xpath = "/html/body/app-root/bmf-layout/div[1]/nav/span/h3/span")
	WebElement homeButton;

	@FindBy(xpath = "//h2[contains(text(),'Sign in to your account')]")
	WebElement signUpTitle;

	// Initializing the Page Objects
	public HomePage(){
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String validateSignInPage(Method method) {
		
		String title = getTitleForDriver(driver);
		Assert.assertEquals(title, "Book My Furniture - QA(2.3.2)-Final");
		if (title.equalsIgnoreCase("Book My Furniture - QA(2.3.2)-Final")) {
			extentReportLogger.log(LogStatus.PASS, "***The test case has passed**");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);
		} else {
			extentReportLogger.log(LogStatus.FAIL, "The testcase has failed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
		}
		return getTitleForDriver(driver);
	}

	/**
	 * Clicks singn-up button and Sign's in
	 * 
	 * @return
	 */
	public LoginPage ClickSignUpButton(Method method) {
		
		clickElement = clickElementButton(signInButton);
		
		//signInButton.click();

		if (clickElement) {
			getTextStr = getTextForElement(signUpTitle);
			if (getTextStr.equalsIgnoreCase("how_to_reg Sign in to your account")) {
				logPg = new LoginPage();
				log.info(getTextStr);
				extentReportLogger.log(LogStatus.PASS, "The testcase is passed ===>>");
				PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);
				return logPg;
			}
		}

		else {
			extentReportLogger.log(LogStatus.FAIL, "The testcase is failed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
			return logPg;
		}

		return new LoginPage();
	}

}
