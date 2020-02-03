package com.mindtree.sdet.pages;

import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AboutPage extends PageBase {
					
		//Page Factory -OR
		@FindBy(xpath="/html/body/app-root/bmf-layout/div[3]/app-footer/footer/div[1]/p[1]/a[4]")
		WebElement aboutClick;
		
		@FindBy(xpath="/html/body/app-root/bmf-layout/div[1]/nav/div/form/button[1]")
		WebElement wishlistClick;		
		
		//Initializing the Page Objects
		public AboutPage(){		
			PageFactory.initElements(driver, this);		
		}
		
		

		public void AboutPageClick()
		{
			aboutClick.click();						
			boolean present = isElementPresents(aboutClick);
			
			if(present) {
				Actions newTab = new Actions(driver);
				newTab.keyDown(Keys.SHIFT).click(aboutClick).keyUp(Keys.SHIFT).build().perform();
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1)); //switches to new tab
				driver.switchTo().window(tabs.get(0)); // switch back to main screen        
				driver.switchTo().window(tabs.get(1));
				//driver.close();
				driver.switchTo().window(tabs.get(0));
				
			}
			else if(!present) {
				System.out.println("About page missing");
			}

		}

}
