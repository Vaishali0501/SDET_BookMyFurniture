package com.mindtree.sdet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends PageBase {
				
	//Page Factory -OR
	@FindBy(xpath="//button[@class='mat-button mat-button-base ng-star-inserted']")
	WebElement signInButton;
	
	@FindBy(xpath="/html/body/app-root/bmf-layout/div[1]/nav/span/h3/span")
	WebElement homeButton;
	
	//Initializing the Page Objects
	public HomePage(){		
		PageFactory.initElements(driver, this);		
	}

	//Actions
	public String validateSignInPage() {		
		System.out.println(driver.getTitle());
		return driver.getTitle();
	}
	
	public LoginPage ClickSignUpButton()
	{
		signInButton.click();
		System.out.println("SignUp button is clicked");
		return new LoginPage();
	}
	
	
}
