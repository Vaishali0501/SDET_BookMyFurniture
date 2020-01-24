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
	public void clickAllFurnBtn()
	{
	//	testUtil.switchToFrame();
		sellingPage=landingPage.clickAllFurnBtn();
	}

}
