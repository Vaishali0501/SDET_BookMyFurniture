package com.mindtree.sdet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPage extends PageBase{
	
	//Page Factory -OR
		@FindBy(xpath="//span[contains(text(),'Hi')]")
		WebElement welcomeText;
		
		//@FindBy(xpath="//button[contains(text(),'All Furnitures')]")
		@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/app-homepage/div/home-category-list/div[1]/div/div/div[1]/div/div")
		WebElement allFurnitureBtn;
		
		//Initializing the Page Objects
		public LandingPage(){		
			PageFactory.initElements(driver, this);		
		}

		//Actions
		public String validateWelcomeText() {		
			String welText=welcomeText.getText();
			return welText;
		}
		
		public SellingPage clickAllFurnBtn()
		{
			//element.click();
			allFurnitureBtn.click();
			return new SellingPage();
		}

}
