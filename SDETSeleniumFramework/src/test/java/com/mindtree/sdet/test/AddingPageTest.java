package com.mindtree.sdet.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mindtree.sdet.pages.AboutPage;
import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.LandingPage;
import com.mindtree.sdet.pages.LoginPage;
import com.mindtree.sdet.pages.PageBase;
import com.mindtree.sdet.util.ConfigReader;

/**
 * 
 * @author vaishali
 *
 */
public class AddingPageTest extends PageBase{
	
	HomePage homePage;
	LoginPage loginPage;
	AboutPage aboutPage;
	LandingPage landingPage;
	ConfigReader configReader;
	public AddingPageTest() {
		super();
	}
	@BeforeMethod
	public void setUp(){
		aboutPage = new AboutPage();		
		homePage = new HomePage();
		loginPage=homePage.ClickSignUpButton();
		loginPage.signInUser(configReader.getUsername(),configReader.getPassword());
	}
	
	
	@Test(priority=2)
	public void clickAboutTest()
	{
		aboutPage.AboutPageClick();
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		//driver.quit();
	}
	

}
