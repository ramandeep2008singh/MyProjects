package com.practice.StepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.practice.util.*;

/**
 * This class contains tests from web-UI
 * 
 * @author ramandeep.singh.nagi
 *
 */
public class UiStepDefinition extends UiTestUtil {

	/**
	 * Before method
	 */
	@Before
	public void setup() {

		login();
		// super.setup();
		// Logger logger = Logger.getLogger("devpinoylogger");
		// configure log4j properties file
		// PropertyConfigurator.configure("Log4j.properties");

	}

	/**************************** Given Methods *************************/
	/**
	 * @param browser
	 */
//	@Given("^I have opened the \"([^\"]*)\" browser$")
	@Given("^I have opened the (.*) browser$")
	public void openBrowser(String browser) {

		if (browser.equalsIgnoreCase(Constants.CHROME)) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + Constants.CHROME_DRIVER);
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER);
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase(Constants.IE)) {
			System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER);
			driver = new InternetExplorerDriver();

		} else if (browser.equalsIgnoreCase(Constants.EDGE)) {
			System.setProperty("webdriver.edge.driver", Constants.EDGE_DRIVER);
			driver = new EdgeDriver();

		}

	}
	
	@Given("^The portal is opened$")
	public void portalOpens() {
		
	}

	/**************************** Then Methods *************************/
	
	@Then("^Authentication Page opens$")
	public void authenticationPageOpens() {
		
	}
	
	@Then("^CREATE AN ACCOUNT page opens$")
	public void createAnAccPageOpens() {
		
	}
	
	@Then("^verify that the correct url with appender (.*) is loaded$")
	public void appUrlOpens(String appender) {
		
	}
	
	@Then("^verify that the correct url with appender (.*) is loaded$")
	public void appConfirmationUrl(String appender) {
		
	}
	
	/**************************** When Methods *************************/
	
	@When("^user clicks on Sign in button$")
	public void clickOnSignInBtn() {
		
	}
	
	@When("^user enters (.*)$")
	public void enterEmailAddr(String emailAddr) {
		// TODO: generate random email
	}
	
	@When("^user fills in information in the field YOUR PERSONAL INFORMATION$")
	public void fillUserPersonalInfo() {
		// TODO: enter all fiends data here
	}
	
	@When("^user clicks on Sign in button on the top$")
	public void clickOnTopSignInBtn() {
		
	}
	
	@When("^(.*) and (.*) are entered in the ALREADY REGISTERED? section$")
	public void enterEmailAndPass(String email, String pass) {
		
	}
	
	@When("^user clicks on Women option in the menu bar$")
	public void clickOnWomenOption() {
		
	}

	/**************************** And Methods *************************/
	/**
	 * @param url
	 */
	@And("^I have loaded the url (.*)$")
	public void loadURL(String url) {
		driver.get(url);
	}
	
	@And("^user clicks on Create an account button$")
	public void clickOnCreateAnAccountBtn() {
		
	}
	
	@And("^user fills in information in the field YOUR ADDRESS$")
	public void fillYourAddressFields() {
		//TODO: fill the fields
		
	}
	
	@And("^user clicks on Register button$")
	public void clickOnRegisterBtn() {
		
	}
	
	@And("^verify the (.*) is formed by concatenating FirstName and LastName$")
	public void verifyUsername(String userName) {
		
	}
	
	@And("^verify the Sign out button is present$")
	public void verifySignoutBtn() {
		
	}
	
	@And("^verify that MY ACCOUNT header is present$")
	public void verifyMyAccHeader() {
		
	}
	
	@And("^$verify that welcome message is also present")
	public void verifyWelcomeMsg() {
		
	}
	
	@And("^(.*) and (.*) are entered in the ALREADY REGISTERED? section$")
	public void enterEmailAndPassword(String email, String pass) {
		
	}
	
	@And("^Sign in button below the password field is clicked$")
	public void clickOnSignInBtnBelowPassField() {
		
	}
	
	@And("^user selects the product with title as Faded Short Sleeve T-shirts$")
	public void selectProductWithTitleFadedShort() {
		
	}
	
	@And("^user clicks on Add to cart button$")
	public void clickOnAddToCartBtn() {
		
	}
	
	@And("^user clicks on Proceed to checkout button on overlay$")
	public void clickOnProceedToChkOutOnOverlay() {
		
	}
	
	@And("^user clicks on Proceed to checkout button on SHOPPING-CART SUMMARY section$")
	public void clickProceedOnShoppingSummaryPage() {
		
	}
	
	@And("^user clicks on Proceed to checkout button on ADDRESSES section$")
	public void clickProceedOnAddrSection() {
		
	}
	
	@And("^user agrees to the termns and conditions by clicking on the checkbox$")
	public void clickOnIAgreeChkBox() {
		
	}
	
	@And("^user clicks on Proceed to checkout button on SHIPPING section$")
	public void clickOnProceedOnShippingSection() {
		
	}
	
	@And("^user clicks on the payment method of Pay by bank wire$")
	public void clickOnPaymentPayByBankWire() {
		
	}
	
	@And("^user clicks on the button I confirm my order$")
	public void clickOnBtnIConfirmMyOrder() {
		
	}
	@And("^verify that the order is complete$")
	public void verifyOrderIsComplete() {
		
	}
	
	@And("^verify the last breadcrumb is the current selection$")
	public void verifyLastBreadcrumb() {
		
	}
	
}
