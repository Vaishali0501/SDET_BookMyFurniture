package com.mindtree.sdet.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import com.mindtree.sdet.ExtentReportListener.ReportManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseReporterUtil {

	public TestCaseReporterUtil() {
	}

	/**
	 * This method is used to report the test cases to the ExtentReport for
	 * ExtentTest logging.
	 * 
	 * @param driver
	 * @param logger
	 * @param methodName
	 * @param testStatus
	 */
	public void reportTestCaseStatus(WebDriver driver, ExtentTest logger, String methodName,
			boolean testStatus) {

		
		
		try {
			if (testStatus) {
				String passMessage = "Verified '" + methodName + "'. Test case PASSED.";

				logger.log(LogStatus.PASS, passMessage,
				ReportManager.addLocalScreenshotToReport(driver, methodName,logger));
			} else {
				String failMessage = "Verified '" + methodName + "'. Test case FAILED.";
				logger.log(LogStatus.FAIL, failMessage,
				ReportManager.addLocalScreenshotToReport(driver, methodName,logger));
			}
		} catch (Exception e) {
			System.out.println("Error closing the Test Suite in @AfterSuite method \n" + e.getMessage());
			e.printStackTrace();
		}
	}
}
