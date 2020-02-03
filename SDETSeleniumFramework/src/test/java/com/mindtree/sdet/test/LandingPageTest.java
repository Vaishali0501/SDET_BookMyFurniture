package com.mindtree.sdet.test;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.LandingPage;
import com.mindtree.sdet.pages.LoginPage;
import com.mindtree.sdet.pages.PageBase;
import com.mindtree.sdet.pages.SellingPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LandingPageTest extends PageBase{
	
	HomePage homePage;
	LoginPage loginPage;
	LandingPage landingPage;
	SellingPage sellingPage;
	Logger log = Logger.getLogger(HomePageTest.class);
	private ExtentTest extentReportLogger = null;
	private static String testCategory = "Book My Furniture App";
	public LandingPageTest() {
		super();
	}
	@BeforeMethod
	public void setUp(){
		homePage = new HomePage();
		this.extentReportLogger = PageBase.extentReportLogger;
		extentReportLogger.assignCategory(testCategory);
	}
		
		//loginPage=homePage.ClickSignUpButton();
		//landingPage = loginPage.SignIn(prop.getProperty("username"), prop.getProperty("password"));
	
	/*@Test(priority=1)
	public void landingPageTextTest()
	{
		
		String mainText=landingPage.validateWelcomeText();
		System.out.println(mainText.split(" ")[0]);
		Assert.assertEquals(mainText.split(" ")[0],"Hi,");
		//}
	}
	*/
	@Test(priority=2)
	public void clickAllFurnBtn(Method method)
	{
	//	testUtil.switchToFrame();
		sellingPage=landingPage.clickAllFurnBtn();
		if (sellingPage == null) {

			extentReportLogger.log(LogStatus.FAIL, "The testcase is failed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
		} else {
			extentReportLogger.log(LogStatus.PASS, "The testcase is passed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);
		}
	}

}
