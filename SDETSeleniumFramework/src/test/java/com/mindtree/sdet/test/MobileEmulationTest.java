package com.mindtree.sdet.test;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mindtree.sdet.pages.HomePage;
import com.mindtree.sdet.pages.PageBase;

public class MobileEmulationTest extends PageBase{
	
	
	@Test
    public void titleCheckMobile() throws Exception {
			
		String title= driverMobile.getTitle();
		System.out.println(title);
		Thread.sleep(5000);
		Assert.assertEquals(title, "Book My Furniture - QA(2.3.2)-Final");
	}
	
			
	//Closes all the browsers
		@AfterTest
		public void tearDown() throws Exception {
			cleanUp();
		}
		protected void cleanUp(){
			driverMobile.quit();
		}
		
	
}
