package com.mindtree.sdet.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mindtree.sdet.pages.PageBase;

public class Screenshot extends PageBase {
	
public static void takingScreenshotErrorMethod(String actionName) throws IOException {
		
		//WebDriver driver = new ChromeDriver();
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
		String presentDir = System.getProperty("user.dir");
        try {
        	FileUtils.copyFile(srcFile, new File(presentDir + "/screenshots/"+actionName+"_"+ System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
