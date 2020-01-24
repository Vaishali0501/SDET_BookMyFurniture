
	package com.mindtree.sdet.test;

	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

import com.mindtree.sdet.pages.AboutPage;
import com.mindtree.sdet.pages.HomePage;
	import com.mindtree.sdet.pages.LandingPage;
	import com.mindtree.sdet.pages.LoginPage;
	import com.mindtree.sdet.pages.PageBase;
	import com.mindtree.sdet.pages.SellingPage;

	public class AboutPageTest extends PageBase{
		
		AboutPage aboutPage;
		public AboutPageTest() {
			super();
		}
		@BeforeMethod
		public void setUp(){
			aboutPage = new AboutPage();
		}
			
		@Test(enabled=true, priority = 1)
		public void ClickAboutPageButton()
		{
			aboutPage.AboutPageClick();
		}

	}



