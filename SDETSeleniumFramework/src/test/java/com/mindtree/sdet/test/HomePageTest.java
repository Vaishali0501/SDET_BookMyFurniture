package com.mindtree.sdet.test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.LoginPage;
import com.mindtree.sdet.pages.PageBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest extends PageBase {
	HomePage homePage;
	LoginPage loginPage;
	Logger log = Logger.getLogger(HomePageTest.class);
	private ExtentTest extentReportLogger = null;
	private String methodName;
	private static String testCategory = "Book My Furniture App";

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		System.out.println("In the set up method");
		this.extentReportLogger = PageBase.extentReportLogger;
		extentReportLogger.assignCategory(testCategory);
		homePage = new HomePage();

	}

	@Test(enabled = true, priority = 1)
	public void homePageTitleTest(Method method) {
		String title = homePage.validateSignInPage();
		// Reporter.log(title,true);
		log.info(title);
		log.info("*******Title is displayed*********");
		Assert.assertEquals(title, "Book My Furniture - QA(2.3.2)-Final");
		if (title.equalsIgnoreCase("Book My Furniture - QA(2.3.2)-Final")) {
			extentReportLogger.log(LogStatus.PASS, "The testcase is passed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);
		} else {
			extentReportLogger.log(LogStatus.FAIL, "The testcase has failed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
		}

	}

	@Test(enabled = true, priority = 2)
	public void clickSignInTest(Method method) {
		loginPage = homePage.ClickSignUpButton();

		if (loginPage == null) {

			extentReportLogger.log(LogStatus.FAIL, "The testcase is failed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
		} else {
			extentReportLogger.log(LogStatus.PASS, "The testcase is passed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);
		}
	}

}
