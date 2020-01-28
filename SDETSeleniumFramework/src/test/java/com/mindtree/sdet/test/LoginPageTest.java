package com.mindtree.sdet.test;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.LandingPage;
import com.mindtree.sdet.pages.LoginPage;
import com.mindtree.sdet.pages.PageBase;
import com.mindtree.sdet.util.ConfigReader;
import com.mindtree.sdet.util.Screenshot;
import com.mindtree.sdet.util.TestData;
import com.mindtree.sdet.util.XLSWorker;

public class LoginPageTest extends PageBase {
	
	HomePage homePage;
	LoginPage loginPage;
	LandingPage landingPage;
	XLSWorker xlsWorker;
	Screenshot screenshot;
	String sheetName="customerinfo";

	ConfigReader configReader;
	Logger log=Logger.getLogger(LoginPageTest.class);
	
	public LoginPageTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		homePage = new HomePage();	
		loginPage=homePage.ClickSignUpButton();
		log.info("Entering Login Page");
		log.debug("This is a debug message");
		log.warn("This is a warning Message");
		log.fatal("This is a fatal error message");
	}
	
	
	//@Test(priority=1)
	//public void loginTest()
	//{
	//	landingPage=loginPage.SignIn(prop.getProperty("username"), prop.getProperty("password"));
	//}
	
//	@Test(priority=1)
	//public void createAccountBtn()
//	{
	//	loginPage.createAccountClick();
//	}
	
	@DataProvider
	public Object[][] getFurnitureTestData()
	{
		return TestData.getSearchData("TestData");
	}
	
	@Test(priority=1, dataProvider="getFurnitureTestData")
	public void registerUser(String Name, String MobileNumber, String Email, String Password)
	{
		//loginPage.createAccountClick();
		//loginPage.enterUserDetails("Sushant", "9872345142", "sushantbhargav@gmail.com", "Test@123");
		log.info("**********************Furniture Project*****************");
		System.out.println("**********************Furniture Project*****************");
		System.out.println("Mobile number : =====>> "+MobileNumber);
		log.info("**********************Starting Test Case-1**************");
		log.info("Entering"+Name+"to login to application");
		loginPage.enterUserDetails(Name, MobileNumber, Email, Password);
		log.info("**********************Ending Test Case-1****************");
	}
	

}
