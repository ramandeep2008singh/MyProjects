package com.commerzbank.testing.ifil.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commerzbank.ccb.framework.base.logging.CcbLogger;
import com.commerzbank.testing.common.BaseUtil;
import com.commerzbank.testing.common.ModuleTestData;
import com.commerzbank.testing.common.SeleniumConstant;
import com.commerzbank.testing.common.SeleniumMessages;
import com.commerzbank.testing.ifil.pagemodel.ChangePinPageModel;
import com.commerzbank.testing.ifil.pagemodel.LockAccessPageModel;

/**
 * IfilTestUtil page.
 * 
 * @author zd2RANJ
 * 
 */
public class IfilTestUtil extends BaseUtil {
	/**
	 * Constant for IfilTestData_PROPERTIES.
	 */
	public ModuleTestData properties;

	/**
	 * Creating variable English
	 */
	private String english = "English";

	/** Initialize Lock-Access page */
	private LockAccessPageModel lockAccessPageModel;

	/** Initialize Lock-Access page */
	private ChangePinPageModel changePinModel;

	/** Instantiating the logger. */
	private static final CcbLogger LOG = CcbLogger.getLogger(IfilTestUtil.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUp() {

		loadTestData();
		super.setUp();
		initializePageFactory();
	}

	/**
	 * Loading the IFIL_German properties
	 * 
	 * @param browser
	 *        - browser on which scripts are running
	 * @param os
	 *        - system on which script is running
	 */
	@Override
	public void setUp(String browser, String os) {

		loadTestData();
		super.setUp(browser, os);
		initializePageFactory();
	}

	/**
	 * Loading the IFIL_EN properties
	 * 
	 * @param browser
	 *        - browser on which scripts are running
	 * @param os
	 *        - system on which script is running
	 * @param language
	 *        - application language type
	 * 
	 */
	public void setUp(String browser, String os, String language) {

		if (language.equalsIgnoreCase("EN")) {
			this.properties = ModuleTestData.getInstance(SeleniumConstant.IFIL_PROPERTIES_EN);
		} else {
			this.properties = ModuleTestData.getInstance(SeleniumConstant.IFIL_PROPERTIES);
		}
		setTestEnvironment(this.properties.getProperty("ApplicationUrl"), this.properties.getProperty("testingOn"));
		super.setUp(browser, os);
		this.lockAccessPageModel = PageFactory.initElements(driver, LockAccessPageModel.class);
	}

	/**
	 * Method to load required test data for execution.
	 */
	private void loadTestData() {

		this.properties = ModuleTestData.getInstance(SeleniumConstant.IFIL_PROPERTIES);
		setTestEnvironment(this.properties.getProperty("ApplicationUrl"), this.properties.getProperty("testingOn"));
	}

	/**
	 * Method to initalize PageFactory for required model class.
	 */
	private void initializePageFactory() {

		this.lockAccessPageModel = PageFactory.initElements(driver, LockAccessPageModel.class);
		this.changePinModel = PageFactory.initElements(driver, ChangePinPageModel.class);
	}

	/**
	 * Method to change application language to English
	 * 
	 * @param user
	 */
	public void Internationalisation(String user) {

		if (isElementPresent(By.linkText(this.english))) {
			driver.findElement(By.linkText(this.english)).click();
			setSleepTime(2000);
			login(user);
		}
	}

	/**
	 * Function for page navigation
	 * 
	 * @return true if navigation is successful or not
	 * */
	protected boolean pageNavigate() {

		if (this.changePinModel.getTopHeaderText().isDisplayed()) {
			driver.get(this.properties.getProperty("LockAccessURL"));
			return true;

		} else {
			return false;
		}
	}

	/**
	 * Function for page navigation
	 * 
	 * @return true if navigation is successful or not
	 */
	protected boolean pageNavigateToChangePin() {

		if (this.changePinModel.getTopHeaderText().isDisplayed()) {
			driver.get(this.properties.getProperty("ChangePinURL"));
			return true;

		} else {
			return false;
		}
	}

	/**
	 * Function for page navigation(Internationalization)
	 * 
	 * @return true if internationalization is done
	 */
	protected boolean pageInternationalizationLangauge() {

		Actions action = new Actions(driver);
		if (this.lockAccessPageModel.getOnlineBankingLink().isDisplayed()) {
			action.moveToElement(this.lockAccessPageModel.getOnlineBankingLink()).click().build().perform();
			assertTrue("Lock access link is displayed", this.lockAccessPageModel.getLockAccessEnglishLink().isDisplayed());
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(this.lockAccessPageModel.getLockAccessEnglishLink())).click();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to change application language
	 * 
	 * @param lang
	 *        EN/DE
	 * 
	 */
	@Override
	public void switchInternationalization(String lang) {

		((JavascriptExecutor) driver).executeScript("window.scroll(0,0);");
		if (lang.equalsIgnoreCase("EN")) {
			if (isElementPresent(By.linkText("English"))) {
				driver.findElement(By.linkText("English")).click();
			}

		} else {

			if (isElementPresent(By.linkText("Deutsch"))) {
				driver.findElement(By.linkText("Deutsch")).click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					LOG.error(SeleniumMessages.SE000014E, e);
				}

			}
		}
	}

	public static void scrollToBottom() {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");

	}

	public static void scrollTo(WebElement element) {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)", element);

	}

	/**
	 * Inputs the number to the TAN input field from property file.
	 * 
	 * @return boolean - if tan validation is successful or not
	 */
	protected boolean tanValidation() {

		try {
			String tan;
			tan = driver.findElement(By.id("activeLetterNo")).getText();
			tan = tan.substring(tan.length() - 3).replace(".", "").trim();
			if (tan.equalsIgnoreCase("0")) {
				return false;
			}
			LOG.error(SeleniumMessages.SE00001E, "Itan Valaue:", tan);
			// create method to scroll and put in base util
			Point pointTAN = driver.findElement(By.id("input-18735")).getLocation();
			((JavascriptExecutor) driver).executeScript("window.scroll(" + pointTAN.getX() + "," + (pointTAN.getY() - 100) + ");");
			String itanValue = this.properties.getProperty("tanOf" + tan);
			LOG.error(SeleniumMessages.SE00001E, "Itan Vlaidaition is successful", itanValue);
			driver.findElement(By.id("input-18735")).sendKeys(itanValue);
		} catch (NoSuchElementException e) {
			assertFalse("this locator is not avilable on the page", true);
			LOG.error(SeleniumMessages.SE00008E, e);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void resetPage() {

		// Auto-generated method stub

	}
}
