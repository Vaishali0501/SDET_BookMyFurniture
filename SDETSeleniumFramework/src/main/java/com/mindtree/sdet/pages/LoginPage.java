package com.mindtree.sdet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends PageBase {
	
		By by;
	
	//Page Factory -OR
		@FindBy(xpath="//input[@id='emailId' and @placeholder='Enter Email']")
		WebElement enterEmail;
		
		@FindBy(xpath="//input[@id='password' and @placeholder='Enter Password']")
		WebElement enterPassword;
		
		@FindBy(xpath="//button[@type='submit' and @class='btn btn-primary btn-lg btn-block']")
		WebElement signInBtn;
		
		@FindBy(xpath="//button[contains(text(),'Create your Account')]")
		WebElement accountBtn;
		
		//@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/app-login/div/div/div[2]/div[3]/h3/div/div[2]/button")
		//WebElement accountBtn;
		//@FindBy(xpath="//input[@id='name' and @placeholder ='Enter User Name']")
		
		@FindBy(xpath="//input[@id='name' and @placeholder ='Enter User Name']")
		WebElement enterUserName;
		
		
		@FindBy(xpath="//input[@formcontrolname='mobileNo' and @placeholder ='Enter Mobile Number']")
		WebElement enterMobileNo;
		
		@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/app-login/div/p-sidebar/div/app-signup/div/div/form/div[3]/input")
		WebElement enterNewEmail;
		
		@FindBy(xpath="/html/body/app-root/bmf-layout/div[2]/app-login/div/p-sidebar/div/app-signup/div/div/form/div[4]/div[1]/input")
		WebElement enterNewPassword;
		
		@FindBy(xpath="//button[contains(text(),'Register')]")
		WebElement registerBtn;
		
		@FindBy(xpath="//mat-icon[contains(text(),'clear')]")
		WebElement closeBtn;
		
		
	
		
		
		
		
		
		//Initializing the Page Objects
		public LoginPage(){		
			PageFactory.initElements(driver, this);		
		}
		
		//Actions
		
		public LandingPage SignIn(String username, String password)
		{
			try {
			enterEmail.sendKeys(username);
			enterPassword.sendKeys(password);
			signInBtn.click();
			return new LandingPage();
			}catch(Exception e) {
				e.printStackTrace();
				return new LandingPage();
			}
		}
		
		public void createAccountClick()
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement element=wait.until(ExpectedConditions.visibilityOf(accountBtn));
			accountBtn.click();
		
		}
		
		public void enterUserDetails(String name, String mobileNumber, String newEmail, String newPassword)
		{
			
			System.out.println("Printing Login Page");
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement element=wait.until(ExpectedConditions.visibilityOf(accountBtn));
			accountBtn.click();
		
			Actions actions = new Actions(driver);

			actions.moveToElement(enterUserName).click().perform();
			
			
			enterUserName.sendKeys(name);
			enterMobileNo.sendKeys(mobileNumber);
			enterNewEmail.sendKeys(newEmail);
			enterNewPassword.sendKeys(newPassword);
			registerBtn.click();
			closeBtn.click();
			
		}


}
