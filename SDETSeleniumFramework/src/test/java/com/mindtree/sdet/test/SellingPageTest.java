package com.mindtree.sdet.test;

import java.io.FileInputStream;

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


public class SellingPageTest extends PageBase {

	HomePage homePage;
	LoginPage loginPage;
	LandingPage landingPage;
	SellingPage sellingPage;
	UploadPage uploadPage;
	ConfigReader configReader ;
	public SellingPageTest() {
		super();
	}
	@BeforeMethod
	public void setUp(){
		homePage = new HomePage();
		this.configReader = PageBase.configReader;
	}
	
	@Test
	public void clickbuyChairButton()
	{
	//	testUtil.switchToFrame();
		loginPage=homePage.ClickSignUpButton();
		System.out.println(configReader.getUsername() +"==="+configReader.getPassword());
		landingPage = loginPage.SignIn(configReader.getUsername(),configReader.getPassword());
		sellingPage=landingPage.clickAllFurnBtn();
		String fftextRd = sellingPage.buyChair();
		String[] confText = fftextRd.split("\n");
		System.out.println("FirstLine =======: " + confText[0]);
		Assert.assertEquals("Your order is successfully placed", confText[0]);
	}
	

}
