package com.practice.StepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import com.practice.util.Constants;
import com.practice.util.UiTestUtil;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * This class contains tests from web-UI
 * 
 * @author ramandeep.singh.nagi
 *
 */
public class UiStepDefinition extends UiTestUtil {

	/**************** Hooks (Before & After) ******************************/

	/**
	 * Inside Before method, initializing all required methods and generating
	 * the reports
	 */
	@Before
	public void beforeAnnotation(Scenario s) {
		initReports(s.getName());
		initializeMethods();
	}

	/**
	 * Inside After method, flushing the report and closing the browser
	 */
	@After
	public void afterAnnotation() {
		quit();
	}

	/**************************** Given Methods *************************/

	/**
	 * Method to open the browser
	 * 
	 * @param browser
	 */
	@Given("^I have opened the (.*) browser$")
	public void openBrowser(String browser) {
		infoLog("Opening browser " + browser);
		openBrowserWindow(browser);

	}

	/**
	 * Navigating to the portal
	 */
	@Given("^The portal is opened$")
	public void PortalIsOpened() {
		infoLog("The portal is opened");
		navigateToPortal(properties.getProperty("PortalUrl"));
	}

	/**************************** AND Methods *************************/

	/**
	 * Method to load the portal url
	 * 
	 * @param url
	 */
	@And("^I have loaded the url (.*)$")
	public void loadURL(String url) {
		infoLog("Opening url " + url);
		navigateToPortal(url);

	}

	/**
	 * Method to click on Create an account button
	 */
	@And("^user clicks on Create an account button$")
	public void clickOnCreateAnAccountBtn() {
		infoLog("Clicking on button: " + uiTestPageModel.getBtnCreateAnAcc().getText());
		uiTestPageModel.getBtnCreateAnAcc().click();
	}

	/**
	 * Filling YOUR ADDRESS fields
	 */
	@And("^user fills in information in the field YOUR ADDRESS$")
	public void fillYourAddressFields() {
		infoLog("Filling YOUR ADDRESS fields");
		uiTestPageModel.getTxtBoxCompany().sendKeys(properties.getProperty("Company"));
		uiTestPageModel.getTxtBoxAddress1().sendKeys(properties.getProperty("Address1"));
		uiTestPageModel.getTxtBoxAddress2().sendKeys(properties.getProperty("Address2"));
		uiTestPageModel.getTxtBoxCity().sendKeys(properties.getProperty("City"));
		select = new Select(uiTestPageModel.getDrpDwnState());
		select.selectByVisibleText(properties.getProperty("State"));
		uiTestPageModel.getTxtBoxPostCode().sendKeys(properties.getProperty("PostalCode"));
		uiTestPageModel.getTxtBoxAdditionalInfo().sendKeys(properties.getProperty("AdditionalInfo"));
		uiTestPageModel.getTxtBoxPhoneMobile().sendKeys(properties.getProperty("MobilePhone"));
		uiTestPageModel.getTxtBoxAlias().clear();
		uiTestPageModel.getTxtBoxAlias().sendKeys(properties.getProperty("Alias"));
	}

	/**
	 * Clicking on Register button
	 */
	@And("^user clicks on Register button$")
	public void clickOnRegisterBtn() {
		infoLog("Clciking on Register button");
		// reportFailure("failing to click on register button");
		uiTestPageModel.getBtnRegister().click();

	}

	/**
	 * Verifying the user name
	 */
	@And("^verify the username is formed by concatenating FirstName and LastName$")
	public void verifyUsername() {
		infoLog("Correct username is showing at the top");
		assertEquals(uiTestPageModel.getLinkUserName().getText(), properties.getProperty("UserName"),
				" Username is not correctly showing ");

	}

	/**
	 * Verifying the Sign-out link
	 */
	@And("^verify the Sign out button is present$")
	public void verifySignoutBtn() {
		infoLog("Sign-out button is present");
		assertTrue(uiTestPageModel.getLinkSignOut().isDisplayed(), " Sign-out button is not present ");

	}

	/**
	 * Checking the header MY ACCOUNT
	 */
	@And("^verify that MY ACCOUNT header is present$")
	public void verifyMyAccHeader() {
		infoLog("MY ACCOUNT header is present");
		assertEquals(uiTestPageModel.getLblMyAccount().getText(), properties.getProperty("HeaderMyAccountText"),
				" Header is not present ");

	}

	/**
	 * Verifying the welcome message
	 */
	@And("^verify that welcome message is also present$")
	public void verifyWelcomeMsg() {
		infoLog("Welcome message is present");
		assertEquals(uiTestPageModel.getLblSubHeading().getText(), properties.getProperty("WelcomeText"),
				" Welcome message is not present ");
		uiTestPageModel.getLinkSignOut().click();

	}

	/**
	 * Entering email
	 * 
	 * @param emailAddr
	 */
	@And("^user enters (.*)$")
	public void enterEmailAddr(String emailAddr) {
		infoLog("Entering email " + EMAIL);
		uiTestPageModel.getTxtBoxCreateAcc().sendKeys(generateRandomEmail());
	}

	/**
	 * Clicking on the sign-in button below the email/pass fields
	 */
	@And("^Sign in button below the password field is clicked$")
	public void clickOnSignInBtnBelowPassField() {
		infoLog("Sign in button below the passsword field is clicked");
		uiTestPageModel.getBtnSignIn().click();
		if (isElementPresent(uiTestPageModel.getLblErrorMsgPanel())) {
			uiTestPageModel.getTxtBoxCreateAcc().sendKeys(generateRandomEmail());
		}
	}

	/**
	 * Entering credentials
	 */
	@And("^Email and Password are entered in the ALREADY REGISTERED section$")
	public void enterEmailAndPass() {
		infoLog("email and password are entered in the fields");
		waitForElementDisplayed(By.cssSelector("#email"));
		uiTestPageModel.getTxtBoxEmail().sendKeys(properties.getProperty("AlreadyRegisteredEmail"));
		uiTestPageModel.getTxtBoxPassword().sendKeys(properties.getProperty("Password"));

	}

	/**
	 * Checking out the desired product in below methods
	 */
	@And("^user clicks the product with title as Faded Short Sleeve Tshirts$")
	public void selectProductWithTitleFadedShort() {
		infoLog("User is selecting the product");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)", "");
		uiTestPageModel.getLinkFadedShortTShirt().click();

	}

	@And("^user clicks on Add to cart button$")
	public void clickOnAddToCartButton() {
		infoLog("Clicking on add to cart");
		uiTestPageModel.getBtnAddToCart().click();
	}

	@And("^user clicks on Proceed to checkout button on overlay$")
	public void clickOnProceedToChkOutOnOverlay() {
		infoLog("Clicking on Proceed to checkout on an overlay");
		uiTestPageModel.getBtnProceedToCheckout().click();

	}

	@And("^user clicks on Proceed to checkout button on SHOPPING CART SUMMARY section$")
	public void clickProceedOnShoppingSummaryPage() {
		infoLog("Clicking...");
		uiTestPageModel.getBtnReviewProceedToCheckout().click();

	}

	@And("^user clicks on Proceed to checkout button on ADDRESSES section$")
	public void clickProceedOnAddrSection() {
		infoLog("Clicking...");
		uiTestPageModel.getBtnProcessAddress().click();

	}

	@And("^user agrees to the terms and conditions by clicking on the checkbox$")
	public void clickOnIAgreeChkBox() {
		infoLog("selecting the checkbox terms");
		uiTestPageModel.getChkBoxIAgree().click();

	}

	@And("^user clicks on Proceed to checkout button on SHIPPING section$")
	public void clickOnProceedOnShippingSection() {
		infoLog("clicking ....");
		uiTestPageModel.getBtnProcessCarrier().click();

	}

	@And("^user clicks on the payment method of Pay by bank wire$")
	public void clickOnPaymentPayByBankWire() {
		infoLog("clicking on the payment method...");
		uiTestPageModel.getLinkBankWire().click();

	}

	@And("^user clicks on the button I confirm my order$")
	public void clickOnBtnIConfirmMyOrder() {
		infoLog("I confirm ...");
		uiTestPageModel.getBtnIConfirmMyOrder().click();

	}

	@And("^verify that the order is complete$")
	public void verifyOrderIsComplete() {
		assertEquals(uiTestPageModel.getLblOrderIsComplete().getText(),
				properties.getProperty("OrderIsCompleteTextMsg"), "Order page is not shown ");

	}

	@And("^verify the last breadcrumb is the current selection$")
	public void verifyLastBreadcrumb() {
		assertTrue(uiTestPageModel.getLblPaymentBrdCrmb().getAttribute(Constants.CLASS)
				.equals(properties.getProperty("ClassPropertyValue")), "Not an end of breadcrumb ");

	}

	/**************************** WHEN Methods *************************/

	/**
	 * Method to click on Sign in button on Home page
	 */
	@When("^user clicks on Sign in button$")
	public void clickSignInOnHomePage() {
		infoLog("Clicking on Sign in link: " + uiTestPageModel.getLinkSignin().getText());
		waitForElementDisplayed(By.cssSelector(".login"));
		uiTestPageModel.getLinkSignin().click();
	}

	/**
	 * Method to fill all the fields of user's personal info
	 */
	@When("^user fills in information in the field YOUR PERSONAL INFORMATION$")
	public void fillUserPersonalInfo() {
		infoLog("Entering personal info");
		uiTestPageModel.getRdBtnMr().click();
		uiTestPageModel.getTxtBoxFirstName().sendKeys(properties.getProperty("FirstName"));
		uiTestPageModel.getTxtBoxLastName().sendKeys(properties.getProperty("LastName"));
		uiTestPageModel.getTxtBoxPassword().sendKeys(properties.getProperty("Password"));
		PASSWORD = uiTestPageModel.getTxtBoxPassword().getAttribute(Constants.VALUE);
		select = new Select(uiTestPageModel.getDrpDwnDays());
		select.selectByIndex(3);
		select = new Select(uiTestPageModel.getDrpDwnMonths());
		select.selectByIndex(3);
		select = new Select(uiTestPageModel.getDrpDwnYears());
		select.selectByIndex(3);

	}

	/**
	 * Clicking on the sign-in button
	 */
	@When("^user clicks on Sign in button on the top$")
	public void clickOnTopSignInBtn() {
		waitForElementDisplayed(By.cssSelector(".login"));
		uiTestPageModel.getLinkSignin().click();

	}

	/**
	 * Selecting the category from top menu: Women
	 */
	@When("^user clicks on Women option in the menu bar$")
	public void clickOnWomenOption() {
		infoLog("User is clicking on Women section");
		uiTestPageModel.getLinkWomen().click();

	}

	/**************************** THEN Methods *************************/

	/**
	 * Verifying the URL appender
	 */
	@Then("^verify that the correct appender is loaded in Url$")
	public void appConfirmationUrl() {
		infoLog("Appender URL is loaded correctly");
		assertTrue(driver.getCurrentUrl().contains(properties.getProperty("ConfirmationAppUrl")),
				" Correct appender URL is missing ");

	}

	/**
	 * Verifying the URL appender
	 */
	@Then("^verify that correct appender in the URL is loaded after the login$")
	public void afterLoginUrl() {
		infoLog("Login is successful");
		boolean afterLoginUrl = driver.getCurrentUrl().contains(properties.getProperty("ConfirmationAppUrl"));
		assertTrue(afterLoginUrl, "Login is not successful ");
	}

	/**
	 * Verifying the URL appender
	 */
	@Then("^verify that correct appender in the URL is loaded after placing an order$")
	public void verifyConfirmationUrlForOrder() {
		infoLog("Verifying the url...");
		assertTrue(driver.getCurrentUrl().contains(properties.getProperty("OrderConfirmUrl")), "Url is not correct");
	}

}
