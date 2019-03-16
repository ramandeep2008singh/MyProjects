package com.practice.StepDefinitions;

import org.openqa.selenium.support.ui.Select;

import com.practice.util.UiTestUtil;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * This class contains tests from web-UI
 * 
 * @author ramandeep.singh.nagi
 *
 */
public class UiStepDefinition extends UiTestUtil {
	
	protected String EMAIL = "username" + Integer.toString(generateRandomEmail()) + "@gmail.com";
	protected String FIRST_NAME = "firstname" +  Integer.toString(generateRandomEmail());
	protected String LAST_NAME = "lastname" +  Integer.toString(generateRandomEmail());
	Select select;
	

	/**
	 * Before method
	 */
//	@Before
//	public void setup() {

//		login();
		// super.setup();
		// Logger logger = Logger.getLogger("devpinoylogger");
		// configure log4j properties file
		// PropertyConfigurator.configure("Log4j.properties");

//	}

	/**************************** Given Methods *************************/
	
	/**
	 * Method to open the browser
	 * @param browser
	 */
	@Given("^I have opened the (.*) browser$")
	public void openBrowser(String browser) {
		openBrowserWindow(browser);

	}
	

	/**************************** AND Methods *************************/
	
	/**
	 * Method to load the portal url
	 * @param url
	 */
	@And("^I have loaded the url (.*)$")
	public void loadURL(String url) {
		navigateToPortal(url);

	}
	
	/**
	 * Method to enter user email
	 * @param email
	 */
	@And("^user enters (.*)$")
	public void enterEmail(String email) {
		uiTestPageModel.getTxtBoxCreateAcc().sendKeys(EMAIL);
	}
	
	/**
	 * Method to click on  Create an account button
	 */
	@And("^user clicks on Create an account button$")
	public void clickOnCreateAnAccountBtn() {
		uiTestPageModel.getBtnCreateAnAcc().click();
	}
	
	
	/**************************** WHEN Methods *************************/
	
	/**
	 * Method to click on Sign in button on Home page
	 */
	@When("^user clicks on Sign in button$")
	public void clickSignInOnHomePage() {
		uiTestPageModel.getLinkSignin().click();
	}
	
	/**
	 * Method to fill all the fields of user's personal info
	 */
	@When("^user fills in information in the field YOUR PERSONAL INFORMATION$")
	public void fillUserPersonalInfo() {
		// TODO: enter all fiends data here
		uiTestPageModel.getRdBtnMr().click();
		// fill first name
		uiTestPageModel.getTxtBoxFirstName().sendKeys(FIRST_NAME);
		// last name
		uiTestPageModel.getTxtBoxFirstName().sendKeys(LAST_NAME);
		// TODO: enter pass from properties
		uiTestPageModel.getTxtBoxPassword().sendKeys("enter properties data here");
		// dob
		select = new Select(uiTestPageModel.getDrpDwnDays());
		select.selectByIndex(3);
		select = new Select(uiTestPageModel.getDrpDwnMonths());
		select.selectByIndex(3);
		select = new Select(uiTestPageModel.getDrpDwnYears());
		select.selectByIndex(3);
		
	}
	
	/**************************** THEN Methods *************************/
	
}
