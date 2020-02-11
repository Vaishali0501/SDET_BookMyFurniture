package com.mindtree.sdet.test;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.LandingPage;
import com.mindtree.sdet.pages.LoginPage;
import com.mindtree.sdet.pages.PageBase;
import com.mindtree.sdet.pages.SellingPage;
import com.mindtree.sdet.util.ConfigReader;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LandingPageTest extends PageBase {

	private static HomePage homePage;
	private static LoginPage loginPage;
	private static LandingPage landingPage;
	private static SellingPage sellingPage;
	private static String welcomeText = "";
	private static boolean signInFlag;
	private static ConfigReader configReader;
	private ExtentTest extentReportLogger = null;
	private static String testCategory = "Book My Furniture App";

	Logger log = Logger.getLogger(LandingPageTest.class);

	public LandingPageTest() {
		super();
	}

	/**
	 * Setup method
	 */
	@BeforeMethod
	public void setUpTestEnv() {
		this.configReader = PageBase.configReader;
		this.extentReportLogger = PageBase.extentReportLogger;
		extentReportLogger.assignCategory(testCategory);
		homePage = new HomePage();
		loginPage = new LoginPage();
		landingPage = new LandingPage();

	}

	/**
	 * First Test: To validate the welcome text
	 */
	@Test(priority = 1)
	public void loginTextTest(Method method) {

		homePage.ClickSignUpButton(method);
		signInFlag = loginPage.signInUser(configReader.getUsername(), configReader.getPassword());

		if (signInFlag) {
			
			welcomeText = landingPage.validateWelcomeText(method);

		} else {
			Assert.assertTrue(signInFlag);
			log.info("Unable to login, please check credentials");
			extentReportLogger.log(LogStatus.FAIL, "The testcase has failed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
		}
	}

	@Test(priority = 2)
	public void clickAllFurnBtn(Method method) {
		sellingPage = landingPage.clickAllFurnBtn(method);

	}

}
