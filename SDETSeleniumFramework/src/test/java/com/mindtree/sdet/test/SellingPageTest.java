package com.mindtree.sdet.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mindtree.sdet.pages.UploadPage;
import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.LandingPage;
import com.mindtree.sdet.pages.LoginPage;
import com.mindtree.sdet.pages.PageBase;
import com.mindtree.sdet.pages.SellingPage;
import com.mindtree.sdet.util.ConfigReader;

import junit.framework.Assert;

public class SellingPageTest extends PageBase {

	HomePage homePage;
	LoginPage loginPage;
	LandingPage landingPage;
	SellingPage sellingPage;
	UploadPage uploadPage;
	ConfigReader configReader;
	public SellingPageTest() {
		super();
	}
	@BeforeMethod
	public void setUp(){
		homePage = new HomePage();
		loginPage=homePage.ClickSignUpButton();
		landingPage = loginPage.SignIn(configReader.getUsername(),configReader.getPassword());
		sellingPage=landingPage.clickAllFurnBtn();
	}
	
	@Test(priority=1)
	public void clickbuyChairButton()
	{
	//	testUtil.switchToFrame();
		String fftextRd = sellingPage.buyChair();
		String[] confText = fftextRd.split("\n");
		System.out.println("FirstLine : " + confText[0]);
		Assert.assertEquals("Your order is successfully placed", confText[0]);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		//driver.close();
		//driver.quit();
	}

}
