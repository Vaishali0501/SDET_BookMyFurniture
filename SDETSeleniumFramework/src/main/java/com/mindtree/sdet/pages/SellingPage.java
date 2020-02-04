package com.mindtree.sdet.pages;

import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author vaishali
 *
 */
public class SellingPage extends PageBase{

	
	private static String finalOrderText = "";
	
	//Page Factory -OR
			@FindBy(xpath="//img[contains(@src, '4AAQSkZJRgABAQEAYABgAAD')]")
			WebElement chairSelect;
			
			//@FindBy(xpath="//button[contains(text(),'All Furnitures')]")
			@FindBy(xpath="//button[text() = \"Buy Now \"]")
			WebElement buyChairButton;
			
			@FindBy(xpath="//button[@type='button' and text() = \"Place Order\"]")
			WebElement placeOrderButton;
			
			@FindBy(xpath="//span[text() = \"Yes\"]")
			WebElement confirmYesButton;
			
			@FindBy(xpath="//label[text() = \"Cash\"]//preceding::span[1]")
			WebElement selectRadioButton;
			
			@FindBy(xpath="//button[text() = \" Place Order \"]")
			WebElement placeOrderFinalButton;
			
			@FindBy(xpath="//h2[text()]")
			WebElement finalText;

			
			
			//Initializing the Page Objects
			public SellingPage(){		
				PageFactory.initElements(driver, this);		
			}

			//Actions
			
			public String buyChair()
			{
				
				clickElement(chairSelect);
				clickElement(buyChairButton);
				clickElement(placeOrderButton);
				clickElement(confirmYesButton);
				clickElement(selectRadioButton);
				clickElement(placeOrderFinalButton);
				
				finalOrderText = getTextForElement(finalText);
				
				//String ffText = finalText.getText();
				log.info(finalOrderText);
				return finalOrderText;
			}

}
