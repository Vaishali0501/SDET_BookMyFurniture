package com.mindtree.sdet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends PageBase {

	// Page Factory -OR

	public static SellingPage selPg = null;
	@FindBy(xpath = "//span[contains(text(),'Hi')]")
	WebElement welcomeText;

	@FindBy(xpath = "/html/body/app-root/bmf-layout/div[2]/app-homepage/div/home-category-list/div[1]/div/div/div[1]/div/div")
	WebElement allFurnitureBtn;

	@FindBy(xpath = "/html/body/app-root/bmf-layout/div[2]/product-catalogue/div/div/div/div[3]/div[1]/h2")
	WebElement allFurnitureTitle;

	// Initializing the Page Objects
	public LandingPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String validateWelcomeText() {
		String welText = welcomeText.getText();
		return welText;
	}

	public SellingPage clickAllFurnBtn() {
		// element.click();
		allFurnitureBtn.click();
		if (allFurnitureTitle.getText().equalsIgnoreCase("Furnitures")) {
			selPg = new SellingPage();
			return selPg;
		}
		else {
			return selPg;
		}
	}

}
