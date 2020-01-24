package com.mindtree.sdet.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.LoginPage;
import com.mindtree.sdet.pages.PageBase;

public class HomePageTest extends PageBase{
	HomePage homePage;
	LoginPage loginPage;
	Logger log=Logger.getLogger(HomePageTest.class);
	public HomePageTest() {
		super();
	}
	@BeforeMethod
	public void setUp(){
		homePage = new HomePage();		
	}
	
	@Test(enabled=true, priority = 1)
	public void homePageTitleTest()
	{
		String title=homePage.validateSignInPage();
		System.out.println("Title is"+title);
		log.info("*******Title is displayed*********");
		Assert.assertEquals(title, "Book My Furniture - QA(2.3.2)-Final");
	}
	
	@Test(enabled = true,priority = 2)
	public void clickSignInTest()
	{
		loginPage=homePage.ClickSignUpButton();
	}
	
	
	
	
}
