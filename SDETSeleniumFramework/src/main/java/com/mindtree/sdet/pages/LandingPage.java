package com.mindtree.sdet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author vaishali
 *
 */
public class LandingPage extends PageBase {

	// Page Factory -OR
	@FindBy(xpath = "//span[contains(text(),'Hi')]")
	WebElement welcomeText;

	@FindBy(xpath = "//*[contains(@class,'circle-text-chair')]")
	WebElement allChairButton;

	// Initializing the Page Objects
	public LandingPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String validateWelcomeText() {
		
		System.out.println("Inside validate welcome text ====");
		return getTextForElement(welcomeText);

	}

	public SellingPage clickAllFurnBtn() {

		clickElement(allChairButton);
		return new SellingPage();
	}

}
