package com.practice.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * UI test util methods
 * 
 * @author ramandeep.singh.nagi
 *
 */
public class UiTestUtil {

	/**
	 * variable for properties
	 */
	Properties properties = null;

	/**
	 * variable for file input stream
	 */
	FileInputStream fis;
	
	/**
	 * Initializing Object for WebDriver 
	 */
	WebDriver driver = null;

	/**
	 * Method to login into portal
	 */
	public void login() {

	}

	/**
	 * Method to setup the browser, properties file ...
	 */
	public void setup() {
		
		if (properties.getProperty(Constants.BROWSER).equalsIgnoreCase(Constants.CHROME)) {
			driver = new ChromeDriver();
			// TODO: add code here
		} else if (properties.getProperty(Constants.BROWSER).equalsIgnoreCase(Constants.FIREFOX)) {
			driver = new FirefoxDriver();
			// TODO: add code here			
		} else if (properties.getProperty(Constants.BROWSER).equalsIgnoreCase(Constants.IE)) {
			driver = new InternetExplorerDriver();
			// TODO: add code here
		} else if (properties.getProperty(Constants.BROWSER).equalsIgnoreCase(Constants.EDGE)) {
			driver = new EdgeDriver();
			// TODO: add code here
		}

	}

	/**
	 * loading properties file
	 */
	public void loadUiProperties() {
		properties = new Properties();
		try {
			fis = new FileInputStream(System.getProperty("user.dir")
					+ "//Ramandeep2008Singh//src//main//resources//properties//UiTests.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * loading properties file
	 */
	public void loadApiProperties() {
		properties = new Properties();
		try {
			fis = new FileInputStream(System.getProperty("user.dir")
					+ "//Ramandeep2008Singh//src//main//resources//properties//ApiTests.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to initialize page model class
	 */
	public void initializePageModel() {

	}

	/**
	 * Method to handle waits
	 */
	public void waits() {

	}

	/**
	 * Method for logging
	 */
	public void logs() {

	}

	/**
	 * Method for screenshots
	 */
	public void takeScreenshot() {

	}

	/**
	 * Method for retry failure cases
	 */
	public void retryTests() {

	}

	/**
	 * Method to close the browser
	 */
	public void tearDown() {

	}

}
