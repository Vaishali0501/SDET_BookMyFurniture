package com.mindtree.sdet.ExtentReportListener;

import java.io.File;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.*;

public class ExtentReportLog {
	
	public ExtentReports extent;
	public ExtentTest test;
	
	public void startReport(String outputDirectory)
	{
		
	extent = new ExtentReports(outputDirectory+"FurnitureTestResultLogs.html", true);	

}
	@Test
	public void StepLogsGeneration()
			{
				test = extent.startTest("StepLogsGeneration");
				test.log(LogStatus.INFO, "Start test method will return extent test object");
				
			}
	
	@AfterTest
	public void tearDown()
	{
		extent.endTest(test);
		test.log(LogStatus.INFO, "End test will stop capturing info about");
		extent.flush();
		//test.log("");
	}
}
