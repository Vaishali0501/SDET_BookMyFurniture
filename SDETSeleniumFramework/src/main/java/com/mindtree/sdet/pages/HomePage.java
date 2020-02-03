package com.mindtree.sdet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends PageBase {
				
	public static LoginPage logPg = null;
	//Page Factory -OR
	@FindBy(xpath="//button[@class='mat-button mat-button-base ng-star-inserted']")
	WebElement signInButton;
	
	@FindBy(xpath="/html/body/app-root/bmf-layout/div[1]/nav/span/h3/span")
	WebElement homeButton;
	
	@FindBy(xpath="//h2[contains(text(),'Sign in to your account')]")
	WebElement signUpTitle;
	
	//Initializing the Page Objects
	public HomePage(){		
		PageFactory.initElements(driver, this);		
	}

	//Actions
	public String validateSignInPage() {		
		return driver.getTitle();
	}
	
	public LoginPage ClickSignUpButton()
	{
		 
		signInButton.click();
		signUpTitle.getText();
		if(signUpTitle.getText().equalsIgnoreCase("how_to_reg Sign in to your account"))
		{
			logPg = new LoginPage(); 
		System.out.println(signUpTitle.getText());
		}
		else {
			return logPg;
		}
		return new LoginPage();
	}
	
	
}
