package com.mindtree.sdet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadPage extends PageBase{
	
	@FindBy(xpath="/html/body/app-root/bmf-layout/div[3]/app-footer/footer/div[1]/p[1]/a[4]")
	WebElement aboutClick;
	
	public UploadPage(){		
		PageFactory.initElements(driver, this);		
	}
	
	public void uploadFileTest() {
		
		String message = uploadFile(aboutClick);
		
		if(message == "Uploaded Successfully") {
			//check the "I accept the terms of service" check box
	        driver.findElement(By.id("terms")).click();

	        // click the "UploadFile" button
	        driver.findElement(By.name("send")).click();	
		}
		else if(message == "Unable to upload") {
			System.out.println("Upload failed");
		}
	}

}
