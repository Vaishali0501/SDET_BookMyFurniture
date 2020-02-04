package com.mindtree.sdet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutPage extends PageBase {

	// Page Factory -OR
	@FindBy(xpath = "/html/body/app-root/bmf-layout/div[3]/app-footer/footer/div[1]/p[1]/a[4]")
	WebElement aboutClick;

	@FindBy(xpath = "/html/body/app-root/bmf-layout/div[1]/nav/div/form/button[1]")
	WebElement wishlistClick;

	// Initializing the Page Objects
	public AboutPage() {

		PageFactory.initElements(driver, this);
	}

	/**
	 * This method tests the functionality and usage of WindowHandlers
	 */
	public String AboutPageClick() {
		String getNewTitle = "";

		aboutClick.click();
		boolean present = isElementPresent(aboutClick);
		if (present) {

			getNewTitle = getNewWindowTitle(aboutClick);
			
			log.info("New window title present");
			return getNewTitle;

		} else {
			log.info("About page missing");
			return getNewTitle;
		}

	}

}
