package com.mindtree.sdet.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.LandingPage;
import com.mindtree.sdet.pages.LoginPage;
import com.mindtree.sdet.pages.PageBase;
import com.mindtree.sdet.pages.SellingPage;

public class LandingPageTest extends PageBase{
	
	HomePage homePage;
	LoginPage loginPage;
	LandingPage landingPage;
	SellingPage sellingPage;
	public LandingPageTest() {
		super();
	}
	@BeforeMethod
	public void setUp(){
		homePage = new HomePage();
	}
		
	@Test(priority=2)
	public void clickAllFurnBtn()
	{
	//	testUtil.switchToFrame();
		sellingPage=landingPage.clickAllFurnBtn();
	}

}
