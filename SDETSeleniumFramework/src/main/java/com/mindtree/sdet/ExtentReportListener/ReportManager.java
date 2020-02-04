package com.mindtree.sdet.ExtentReportListener;

import java.io.File;

import javax.swing.text.html.HTML;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.mindtree.sdet.util.DriverManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


/**
 * @author vaishali
 *
 */
public class ReportManager {

	private static ExtentReports INSTANCE = null;

	private ReportManager() {

	}

	public synchronized static ExtentReports getReporter(String filePath, boolean replaceExisting) {
		if (INSTANCE == null) {
			INSTANCE = new ExtentReports(filePath, replaceExisting);
			
		}
		return INSTANCE;
	}

	private static String captureScreenshot(WebDriver driver, String ScreenshotName) {

		String destinationPath = null;
		String presentDir = "";
		try {
			//File destFolder = new File(screenshotPath);
			presentDir = System.getProperty("user.dir");
			
			destinationPath = presentDir + "/screenshots/"+ScreenshotName +".png";
		
			// Cast webdriver to Screenshot
			TakesScreenshot screenshot = (TakesScreenshot) driver;

			System.out.println("Screenshot taken");
			File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(sourceFile, new File(destinationPath));

		} catch (Exception e) {
			System.out.println("Error capturing screenshot...\n" + e.getMessage());
			e.printStackTrace();
		}
		return destinationPath;
	}

	public static String addLocalScreenshotToReport(WebDriver driver, String screenshotName,
			ExtentTest logger) {
		String screenshotImage = null;

		try {
			String screenshotAbsolutePath = captureScreenshot(driver, screenshotName);
			screenshotImage = logger.addScreenCapture(screenshotAbsolutePath);
		} catch (Exception e) {
			System.out.println("Error capturing screenshot of application\n" + e.getMessage());
			e.printStackTrace();
		}
		return screenshotImage;
	}
}