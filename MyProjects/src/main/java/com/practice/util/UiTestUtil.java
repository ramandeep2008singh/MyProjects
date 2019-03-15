package com.practice.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

import com.practice.pagemodel.UiTestPageModel;

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
	 * Initializing object of Page model class
	 */
	UiTestPageModel uiTestPageModel;

	/** Instantiating the logger. */
	// private static final TestLogger LOG =
	// TestLogger.getLogger(UiTestUtil.class);

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
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + Constants.CHROME_DRIVER);
			driver = new ChromeDriver();
			// TODO: add code here
		} else if (properties.getProperty(Constants.BROWSER).equalsIgnoreCase(Constants.FIREFOX)) {
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER);
			driver = new FirefoxDriver();
			// TODO: add code here
		} else if (properties.getProperty(Constants.BROWSER).equalsIgnoreCase(Constants.IE)) {
			System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER);
			driver = new InternetExplorerDriver();
			// TODO: add code here
		} else if (properties.getProperty(Constants.BROWSER).equalsIgnoreCase(Constants.EDGE)) {
			System.setProperty("webdriver.edge.driver", Constants.EDGE_DRIVER);
			driver = new EdgeDriver();
			// TODO: add code here
		}

	}

	/**
	 * load properties file
	 */
	public void loadProperty(String giveProperty) {
		if (giveProperty.equalsIgnoreCase(Constants.UI_TEST)) {
			properties = new Properties();
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + Constants.UI_TEST_FULL_PATH);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (giveProperty.equalsIgnoreCase(Constants.API_TEST)) {
			properties = new Properties();
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + Constants.API_TEST_FULL_PATH);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Method to initialize page model class
	 */
	public void initializePageModel() {

		uiTestPageModel = PageFactory.initElements(driver, UiTestPageModel.class);

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
	 * Method to logout
	 */
	public void logout() {
		// TODO: write code here
	}

	/**
	 * Method to close the browser
	 */
	public void tearDown() {

	}

}
