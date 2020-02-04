package com.mindtree.sdet.pages;

import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SellingPage extends PageBase{

	//Page Factory -OR
			@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/product-catalogue/div/div/div/div[3]/div[2]/div/div[1]/div/button")
			WebElement chairSelect;
			
			//@FindBy(xpath="//button[contains(text(),'All Furnitures')]")
			@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/selected-product/div/div[1]/div[1]/div[2]/div[1]/div[2]/button")
			WebElement buyChairButton;
			
			@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/single-order-confirm/div/div/div[2]/div[3]/div[2]/tr[4]/td/button")
			WebElement placeOrderButton;
			
			@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/single-order-confirm/div/div/div[2]/div[2]/p-confirmdialog/div/div[3]/button[1]/span[2]")
			WebElement confirmYesButton;
			
			@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/app-payment-layout/div/div/div[1]/div[3]/p-radiobutton/div/div[2]/span")
			WebElement selectRadioButton;
			
			@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/app-payment-layout/div/div/div[2]/button")
			WebElement placeOrderFinalButton;
			
			@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/app-payment-layout/div/p-card/div/div/div/div")
			WebElement finalText;

			
			
			//Initializing the Page Objects
			public SellingPage(){		
				PageFactory.initElements(driver, this);		
			}

			//Actions
			
			public String buyChair()
			{
				//element.click();
				
				
				chairSelect.click();
				buyChairButton.click();
				placeOrderButton.click();
				confirmYesButton.click();
				selectRadioButton.click();
				placeOrderFinalButton.click();
				String ffText = finalText.getText();
				System.out.println(ffText);
				return ffText;
			}

}
