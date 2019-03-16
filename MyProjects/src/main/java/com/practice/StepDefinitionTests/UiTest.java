package com.practice.StepDefinitionTests;

import static org.testng.Assert.assertTrue;

import com.practice.util.UiTestUtil;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

/**
 * This class contains tests from web-UI
 * 
 * @author ramandeep.singh.nagi
 *
 */
public class UiTest extends UiTestUtil {

	/**
	 * Before method
	 */
	@Before
	public void setup() {

		login();
		super.setup();
//		Logger logger = Logger.getLogger("devpinoylogger");
		// configure log4j properties file
		// PropertyConfigurator.configure("Log4j.properties");

	}
	
	/****************************Given Methods*************************/

	
	/****************************Then Methods*************************/
	
	
	/****************************When Methods*************************/
	
	
	/****************************And Methods*************************/
	
	

}
