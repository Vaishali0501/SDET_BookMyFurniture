package com.mindtree.sdet.test;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mindtree.sdet.pages.UploadPage;
import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.LandingPage;
import com.mindtree.sdet.pages.LoginPage;
import com.mindtree.sdet.pages.PageBase;
import com.mindtree.sdet.pages.SellingPage;
import com.mindtree.sdet.util.ConfigReader;
import com.mindtree.sdet.util.TestData;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


/**
 * This test class tests the functionality of End-to-End product buying on the app.
 * The test is successful if we get Order Confirmation message
 * @author vaishali
 *
 */
public class SellingPageTest extends PageBase {

	
	// Instantiation
	private static HomePage homePage;
	private static LoginPage loginPage;
	private static LandingPage landingPage;
	private static SellingPage sellingPage;
	private static UploadPage uploadPage;
	private static ConfigReader configReader ;
	private static Logger log = Logger.getLogger(SellingPageTest.class);
	private static ExtentTest extentReportLogger = null;
	private static String testCategory = "Book My Furniture App";
	
	/**
	 * Constructor
	 */
	public SellingPageTest() {
		super();
	}
	
	/**
	 * Initializing page objects and variables
	 */
	@BeforeMethod
	public void setUp(){
		
		this.extentReportLogger = PageBase.extentReportLogger;
		extentReportLogger.assignCategory(testCategory);
		homePage = new HomePage();
		landingPage = new LandingPage();
		this.configReader = PageBase.configReader;
	}
	
	/**
	 * Test Method
	 */
	@Test
	public void clickbuyChairButton(Method method)
	{
		// Click sign-up button
		loginPage=homePage.ClickSignUpButton(null);
		
		// Entering Credentials and Logging in
		loginPage.signInUser(configReader.getUsername(),configReader.getPassword());
		
		//See all furnitures
		sellingPage=landingPage.clickAllFurnBtn(null);
		
		//Buying chair
		String fftextRd = sellingPage.buyChair();
		
		//Getting confirmation
		String[] confText = fftextRd.split("\n");
		System.out.println("FirstLine =======: " + confText[0]);
		Assert.assertEquals("Your order is successfully placed", confText[0]);
		
		
		//Extent report logging and attaching screenshot to report
		if (confText[0].equalsIgnoreCase("Your order is successfully placed")) {
			extentReportLogger.log(LogStatus.PASS, "The testcase is passed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);
		} else {
			extentReportLogger.log(LogStatus.FAIL, "The testcase has failed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
		}
	}
	

}
