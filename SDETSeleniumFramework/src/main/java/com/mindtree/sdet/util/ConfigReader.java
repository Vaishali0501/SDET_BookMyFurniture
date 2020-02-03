package com.mindtree.sdet.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private String url = "";
	private String username = "";
	private String password = "";
	


	/**
	 * Class constructor loads settings from config.properties file and saves to fields
	 */
	public ConfigReader() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("/Users/sushant/git/SDET_BookMyFurniture/SDETSeleniumFramework/src/main/resources/data/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Cant't read config.properties file!");
			return;
		}
		Properties p = new Properties();
		try {
			p.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cant't read config.properties file!");
			return;
		}
		username = p.getProperty("USERNAME");
		password = p.getProperty("PASSWORD");
		url = p.getProperty("URL");
		
	}


	/**
	 * Method to get the user name
	 *
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Method to get the password
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	

	/**
	 * Method to get URL
	 * 
	 * @return url
	 */
	public String getURL() {
		return url;
	}


}
