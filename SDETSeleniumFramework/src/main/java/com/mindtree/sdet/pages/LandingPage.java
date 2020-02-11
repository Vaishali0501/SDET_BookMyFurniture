package com.mindtree.sdet.pages;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author vaishali
 *
 */
public class LandingPage extends PageBase {

	String wt = null;
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
	public String validateWelcomeText(Method method) {
		
	//	System.out.println("Inside validate welcome text ====");
		 wt = getTextForElement(welcomeText);
		
		if (wt.contains("Hi,")) {
			extentReportLogger.log(LogStatus.PASS, "The testcase is passed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);
			return wt;
		} else {
			extentReportLogger.log(LogStatus.FAIL, "The testcase has failed ===>>");
			PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
		}
		
		return wt;

	}

	public SellingPage clickAllFurnBtn(Method method) {

		if(clickElementButton(allChairButton))
		{
		
		extentReportLogger.log(LogStatus.PASS, "The testcase is passed ===>>");
		PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), true);
		}
		else
		{
		extentReportLogger.log(LogStatus.FAIL, "The testcase is passed ===>>");
		PageBase.reportTestCaseStatus(driver, extentReportLogger, method.getName(), false);
		}
		return new SellingPage();
	}

}
