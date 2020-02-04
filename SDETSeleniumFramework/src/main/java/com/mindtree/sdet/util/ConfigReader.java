package com.mindtree.sdet.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private String url = "";
	private String username = "";
	private String password = "";
	private FileInputStream fis;
	private String hostName = "";
	private String port = "";
	private String fromAddress = "";
	private String toAddressList = "";
	private String emailSubject = "";
	private String emailBodyTextFile = "";
	private String attachmentPath = "";


	



	/**
	 * Class constructor loads settings from config.properties file and saves to fields
	 */
	public ConfigReader(String fileName) {
		
		try {
			fis = new FileInputStream(fileName);
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
		hostName = p.getProperty("hostName");
		port = p.getProperty("port");
		fromAddress = p.getProperty("fromAddress");
		toAddressList = p.getProperty("toAddressList");
		emailSubject = p.getProperty("emailSubject");
		emailBodyTextFile = p.getProperty("emailBodyTextFile");
		attachmentPath = p.getProperty("attachmentPath");
		
	}


	public FileInputStream getFis() {
		return fis;
	}


	public void setFis(FileInputStream fis) {
		this.fis = fis;
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

	public String getHostName() {
		return hostName;
	}


	public void setHostName(String hostName) {
		this.hostName = hostName;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}


	public String getFromAddress() {
		return fromAddress;
	}


	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}


	public String getToAddressList() {
		return toAddressList;
	}


	public void setToAddressList(String toAddressList) {
		this.toAddressList = toAddressList;
	}


	public String getEmailSubject() {
		return emailSubject;
	}


	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}


	public String getEmailBodyTextFile() {
		return emailBodyTextFile;
	}


	public void setEmailBodyTextFile(String emailBodyTextFile) {
		this.emailBodyTextFile = emailBodyTextFile;
	}


	public String getAttachmentPath() {
		return attachmentPath;
	}


	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

}
