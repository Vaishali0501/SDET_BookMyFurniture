package com.mindtree.sdet.pages;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mindtree.sdet.test.HomePageTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends PageBase {

	private static By by;
	private static boolean sendKeyUser;
	private static boolean sendKeyPass;

	private static boolean sendFormUser;
	private static boolean sendFormPass;
	private static boolean sendFormEmail;
	private static boolean sendFormMobile;

	Logger log = Logger.getLogger(LoginPage.class);

	// Page Factory -OR
	@FindBy(xpath = "//h2[contains(text(),\" Sign in to your account \")]//following :: input[1]")
	WebElement enterEmail;

	@FindBy(xpath = "//h2[contains(text(),\" Sign in to your account \")]//following :: input[2]")
	WebElement enterPassword;

	@FindBy(xpath = "//button[@type='submit' and text()='Sign In']")
	WebElement signInBtn;

	@FindBy(xpath = "//button[contains(text(),'Create your Account')]")
	WebElement accountBtn;

	@FindBy(xpath = "//input[@id='name' and @placeholder ='Enter User Name']")
	WebElement enterUserName;

	@FindBy(xpath = "//input[@formcontrolname='mobileNo' and @placeholder ='Enter Mobile Number']")
	WebElement enterMobileNo;

	@FindBy(xpath = "//h3[.=\" Mobile Number\"]//following::input[2]")
	WebElement enterNewEmail;

	@FindBy(xpath = "//h3[.=\" Mobile Number\"]//following::input[3]")
	WebElement enterNewPassword;

	@FindBy(xpath = "//button[contains(text(),'Register')]")
	WebElement registerBtn;

	@FindBy(xpath = "//mat-icon[contains(text(),'clear')]")
	WebElement closeBtn;

	// Initializing the Page Objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public boolean signInUser(String username, String password) {
		try {

			System.out.println("================");
			System.out.println(username);
			sendKeyUser = sendKeysToElement(enterEmail, username);
			sendKeyPass = sendKeysToElement(enterPassword, password);
			if (sendKeyUser && sendKeyPass) {
				if (checkElementEnabled(signInBtn)) {
					clickElementButton(signInBtn);
					log.info("SignIn clicked");
//					extentReportLogger.log(LogStatus.PASS, "The testcase is passed ===>>");
//					PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);
					return true;
				} else {
					log.info("Unable to click SignIn Button, please enter valid credentials");
//					extentReportLogger.log(LogStatus.FAIL, "The testcase is passed ===>>");
//					PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
					return false;
				}
				// return new LandingPage();
			} else {
				log.info("Unable to send the keys to username and password");
				
//				extentReportLogger.log(LogStatus.FAIL, "The testcase is passed ===>>");
//				PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
				return false;
				// return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
			// return new LandingPage();
		}
	}

	public void createAccountClick() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(accountBtn));
		clickElementButton(accountBtn);
	}

	/**
	 * Enter user details to register the user
	 * 
	 * @param name
	 * @param mobileNumber
	 * @param newEmail
	 * @param newPassword
	 */
	public void enterUserDetails(String name, String mobileNumber, String newEmail, String newPassword, Method method) {

		System.out.println("Printing Login Page");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(accountBtn));
		// accountBtn.click();
		clickElementButton(accountBtn);

		Actions actions = new Actions(driver);
		actions.moveToElement(enterUserName).click().perform();

		// Sending data to the user register form
		sendFormUser = sendKeysToElement(enterUserName, name);
		sendFormPass = sendKeysToElement(enterNewPassword, newPassword);
		sendFormEmail = sendKeysToElement(enterNewEmail, newEmail);
		sendFormMobile = sendKeysToElement(enterMobileNo, mobileNumber);

		// checking if data is sent
		if (sendFormUser && sendFormPass && sendFormEmail && sendFormMobile) {

			log.info("Data entered in form");
			clickElementButton(registerBtn);
			clickElementButton(closeBtn);
			extentReportLogger.log(LogStatus.PASS, "The testcase is passed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);

		} else {
			log.info("Unable to enter the data into form");
			extentReportLogger.log(LogStatus.FAIL, "The testcase is passed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
			
		}

	}

	

}
