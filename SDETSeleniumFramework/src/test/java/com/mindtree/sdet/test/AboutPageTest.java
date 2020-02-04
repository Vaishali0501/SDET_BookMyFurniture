
package com.mindtree.sdet.test;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mindtree.sdet.pages.AboutPage;
import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.LandingPage;
import com.mindtree.sdet.pages.LoginPage;
import com.mindtree.sdet.pages.PageBase;
import com.mindtree.sdet.pages.SellingPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author vaishali
 *
 */
public class AboutPageTest extends PageBase {

	private ExtentTest extentReportLogger = null;
	private static String newTitle = null;
	private static String testCategory = "Book My Furniture App";
	AboutPage aboutPage;

	public AboutPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		this.extentReportLogger = PageBase.extentReportLogger;
		extentReportLogger.assignCategory(testCategory);
		aboutPage = new AboutPage();
	}

	@Test(enabled = true, priority = 1)
	public void ClickAboutPageButton(Method method) {
		newTitle = aboutPage.AboutPageClick();
		Assert.assertEquals(newTitle, "HTTP Status 404 – Not Found");
		extentReportLogger.log(LogStatus.PASS, "The testcase is passed ===>>");
		PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);
	}

}
