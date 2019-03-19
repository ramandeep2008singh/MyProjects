package com.practice.util;

/**
 * Common constants for API and UI tests
 * 
 * @author ramandeep.singh.nagi
 *
 */
public class Constants {

	/**
	 * Constant for Browser
	 */
	public static final String BROWSER = "browser";

	/**
	 * Constant for Chrome
	 */
	public static final String CHROME = "chrome";

	/**
	 * Constant for Firefox
	 */
	public static final String FIREFOX = "firefox";

	/**
	 * Constant for InternetExplorer
	 */
	public static final String IE = "ie";

	/**
	 * Constant for Edge
	 */
	public static final String EDGE = "edge";

	/**
	 * Constant for UI Test property file
	 */
	public static final String UI_TEST = "/properties/UiTests.properties";
	public static final String UI_TEST_FULL_PATH = "//src//main//resources//properties//UiTests.properties";

	/**
	 * Constant for API Test property file
	 */
	public static final String API_TEST = "/properties/ApiTests.properties";
	public static final String API_TEST_FULL_PATH = "//src//main//resources//properties//ApiTests.properties";

	/**
	 * Browser briver/binaries path
	 */
	public static final String CHROME_DRIVER = "//src//main//resources//drivers//chromedriver.exe";
	public static final String FIREFOX_DRIVER = "//src//main//resources//drivers//geckodriver.exe";
	public static final String IE_DRIVER = "//src//main//resources//drivers//IEDriverServer.exe";
	public static final String EDGE_DRIVER = "//src//main//resources//drivers//MicrosoftWebDriver.exe";

	/**
	 * Constant for GRID HUB URL
	 */
	public static final String GRID_HUB = "http://localhost:4444/wd/hub";

	/**
	 * Constant for attributes
	 */
	public static final String VALUE = "value";
	public static final String CLASS = "class";

	/**
	 * 
	 * URLs for REST API Testing
	 * 
	 */

	public static final String GET_ALL_EMPLOYEES = "http://dummy.restapiexample.com/api/v1/employees";
	
	public static final String GET_EMPLOYEE = "http://dummy.restapiexample.com/api/v1/employee/";

	public static final String CREATE_URL = "http://dummy.restapiexample.com/api/v1/create";
	
	public static final String UPDATE_URL = "http://dummy.restapiexample.com/api/v1/update/";
	
	public static final String DELETE_URL = "http://dummy.restapiexample.com/api/v1/delete/";

	public static final String RESPONSE_200 = "200";
}
