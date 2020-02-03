package com.mindtree.sdet.util;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;

/**
 * Class provides data and settings for tests and saves test results
 */
public class TestData {
	private static WebDriver driver;
	public static final int WAIT_TIME = 10;
	public static final int TEST_LOGIN = 1;
	public static final int TEST_SEARCH = 2;
	public static final int TEST_NOTIFICATIONS = 3;
	public static final int TEST_ADD_REPOSITORY = 4;
	public static final int TEST_DELETE_REPOSITORY = 5;
	public static final int TEST_ADD_ISSUE = 6;
	public static final String SHEET_NAME_TEST_CASE = "TestCases";
	public static final String SHEET_NAME_TEST_DATA = "TestData";
	private final static String xlsPath = "/Users/sushant/git/SDET_BookMyFurniture/SDETSeleniumFramework/src/main/resources/data/TestCases.xls";
	private static XLSWorker xlsWorker = new XLSWorker(xlsPath);
	private static final int START_ROW_NUM_LOGIN_TEST = 1;
	private static final int START_ROW_NUM_SEARCH_TEST = 6;
	private static final int START_ROW_NUM_NOTIF_TEST = 8;
	private static final int START_ROW_NUM_ADD_REP_TEST = 10;
	private static final int START_ROW_NUM_DELETE_REP_TEST = 15;
	private static final int START_ROW_NUM_ADD_ISSUE = 18;
	private String repositoryName;
	private static TestData data;

	private TestData() {
	}


	/**
	 * Saves test results to the excel file (passed or fail)
	 *
	 * @param testCase
	 *            Test case
	 * @param step
	 *            Step of the test case
	 * @param passed
	 *            Test result
	 */
	public static void saveTestResult(int testCase, int step, boolean passed) {
		switch (testCase) {
		case TEST_LOGIN:
			xlsWorker.setCellData(TestData.SHEET_NAME_TEST_CASE, "Result", step
					+ START_ROW_NUM_LOGIN_TEST, passed ? "Passed" : "Fail");
			break;
		case TEST_SEARCH:
			xlsWorker.setCellData(TestData.SHEET_NAME_TEST_CASE, "Result", step
					+ START_ROW_NUM_SEARCH_TEST, passed ? "Passed" : "Fail");
			break;
		case TEST_NOTIFICATIONS:
			xlsWorker.setCellData(TestData.SHEET_NAME_TEST_CASE, "Result", step
					+ START_ROW_NUM_NOTIF_TEST, passed ? "Passed" : "Fail");
			break;
		case TEST_ADD_ISSUE:
			xlsWorker.setCellData(TestData.SHEET_NAME_TEST_CASE, "Result", step
					+ START_ROW_NUM_ADD_ISSUE, passed ? "Passed" : "Fail");
			break;

		default:
			System.out.println("No Test Case with number " + testCase
					+ " is found ");
			break;
		}
	}

	/**
	 * Saves test results from certain test data to the excel file (passed or
	 * fail)
	 *
	 * @param testCase
	 *            Test case
	 * @param passed
	 *            Test result
	 * @param data
	 *            Test data
	 */
	public static void saveTestResultWithData(int testCase, boolean passed,
			String data) {
		switch (testCase) {
		case TEST_LOGIN:
			// uncomment, if this test will have data
			// xlsWorker.setCellData(TestData.SHEET_NAME_TEST_DATA,
			// "testLogin",
			// data, passed ? "Passed" : "Fail");
			break;
		case TEST_SEARCH:
			xlsWorker.setCellData(TestData.SHEET_NAME_TEST_DATA, "testSearch",
					data, passed ? "Passed" : "Fail");
			break;
		case TEST_NOTIFICATIONS:
			// uncomment, if this test will have data
			// xlsWorker.setCellData(TestData.SHEET_NAME_TEST_DATA,
			// "testNotifications",
			// data, passed ? "Passed" : "Fail");
			break;

		case TEST_ADD_ISSUE:
			// uncomment, if this test will have data
			// xlsWorker.setCellData(TestData.SHEET_NAME_TEST_DATA,
			// " testAddIssue",
			// data, passed ? "Passed" : "Fail");
			break;
		default:
			System.out.println("No Test Case with number " + testCase
					+ " is found in the file");
			break;
		}
	}

	/**
	 * Method to get the data for dataProvider from the file
	 * 
	 ** @param testCase
	 *            Test case
	 *
	 * @return array of data
	 */
	public static Object[][] getSearchData(String sheetName) {
		try {
			return xlsWorker.getData(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can't read the test data");
			return null;
		}

	}

}
