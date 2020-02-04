package com.mindtree.sdet.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mindtree.sdet.test.SellingPageTest;
import com.mindtree.sdet.util.InitializeDriver;

/**
 * This Class is for Home page actions and navigation
 * 
 * @author vaishali
 *
 */
public class HomePage extends PageBase {

	public static boolean clickElement;
	public static String getTextStr = null;
	private static Logger log = Logger.getLogger(HomePage.class);
	public static LoginPage logPg = null;
	// Page Factory -OR
	@FindBy(xpath = "//button[@class='mat-button mat-button-base ng-star-inserted']")
	WebElement signInButton;

	@FindBy(xpath = "/html/body/app-root/bmf-layout/div[1]/nav/span/h3/span")
	WebElement homeButton;

	@FindBy(xpath = "//h2[contains(text(),'Sign in to your account')]")
	WebElement signUpTitle;

	// Initializing the Page Objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String validateSignInPage() {
		
		return getTitleForDriver(driver);
	}

	/**
	 * Clicks singn-up button and Sign's in
	 * 
	 * @return
	 */
	public LoginPage ClickSignUpButton() {

		clickElement = clickElement(signInButton);

		if (clickElement) {
			getTextStr = getTextForElement(signUpTitle);

		} else {
			log.info("Unable to click on the element");
		}

		if (getTextStr.equalsIgnoreCase("how_to_reg Sign in to your account")) {
			logPg = new LoginPage();
			log.info(getTextStr);
		} else {
			return logPg;
		}
		return new LoginPage();
	}

}
