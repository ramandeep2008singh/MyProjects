package com.commerzbank.testing.common;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.commerzbank.ccb.framework.base.logging.CcbLogger;
import com.google.common.base.Function;

/**
 * BaseUtil class for environment setting, and all common util methods which are
 * used by across all modules.
 * 
 * @author zd2path
 */
public abstract class BaseUtil extends TestCase {

	/**
	 * Rule to capture screen-shot for failure scripts.
	 */
	@Rule
	public ScreenshotTestRule screenShot = new ScreenshotTestRule();

	/**
	 * Rule to rerun failure script.
	 */
	@Rule
	public Retry retry = new Retry(1);

	/**
	 * Object for CommonTestData.
	 */
	protected static CommonTestData commonTestData;

	/**
	 * Object for ModuleTestData.
	 */
	protected ModuleTestData moduleTestData;

	/**
	 * Object to hold RemoteWebDriver value.
	 */
	public static RemoteWebDriver driver;

	/**
	 * Variable to hold userID value to compare for second login.
	 */
	private static String userID;

	/**
	 * Variable to hold complete url for portal where script will execute.
	 */
	protected String portalUrl;

	/**
	 * Variable to hold value for appender;
	 */
	@SuppressWarnings("unused")
	private String appender;

	/**
	 * Variable for applicationUrl.
	 */
	private String applicationUrl;

	/**
	 * variable to hold the value for environment where scripts will execute.
	 */
	protected String testingOn;

	/** Instantiating the logger. */
	private static final CcbLogger LOG = CcbLogger.getLogger(BaseUtil.class);

	/**
	 * Constant for htmlBody.
	 */
	private static final String HTML_BODY = "body";

	/**
	 * Method to get current driver.
	 * 
	 * @return
	 */
	public RemoteWebDriver getDriver() {

		return driver;
	}

	/**
	 * Method to set required values to execute test cases, for e.g. test
	 * environment, platform, and browser {@inheritDoc}
	 */
	@Override
	public void setUp() {

		// get OS and browser value from Jenkins job.
		try {
			if (InetAddress.getLocalHost().toString().trim().contains(SeleniumConstant.BUILD_MACHINE_INFO)) {
				String browser = System.getProperty("browser");
				String os = System.getProperty("OS");
				LOG.info("Browser value from jenkins: " + browser);
				LOG.info("Os value from jenkins: " + os);
				if (browser != null && os != null) {
					initializeEnvironment(browser, os);
				}
			} else {
				// else block is written for execution on local machine.
				setEnvironment();
				// pass browser value to execute scripts on desired
				// browser(either
				// firefox or chrome).
				if (driver == null || driver.toString().contains("null")) {
					setDesiredBrowser("firefox");
				}
				setDefaultWaitTime();
			}
		} catch (UnknownHostException e) {
			LOG.error(SeleniumMessages.SE000011E, e);
		}
	}

	/**
	 * Setup method to set all mandatory properties from the property files.
	 * Initialise all variables and also setup the environment for the current
	 * browser and platform.
	 * 
	 * @param browser
	 *        String The browser name
	 * @param os
	 *        String The Platform
	 */

	public void setUp(String browser, String os) {

		try {
			if (InetAddress.getLocalHost().toString().trim().contains(SeleniumConstant.BUILD_MACHINE_INFO)) {
				// get OS and browser value from Jenkins job.
				String browserVal = System.getProperty("browser");
				String osVal = System.getProperty("OS");
				LOG.info("Browser value from jenkins: " + browserVal);
				LOG.info("Os value from jenkins: " + osVal);
				if (browserVal != null && osVal != null) {
					initializeEnvironment(browserVal, osVal);
				} else {
					initializeEnvironment(browser, os);
				}
			} else {
				// else block is written for execution on local machine.
				setEnvironment();
				// pass browser value to execute scripts on desired
				// browser(either
				// firefox or chrome).
				if (driver == null || driver.toString().contains("null")) {
					setDesiredBrowser("firefox");
				}
				setDefaultWaitTime();
			}
		} catch (UnknownHostException e) {
			LOG.error(SeleniumMessages.SE000011E, e);
		} catch (Exception e) {
			LOG.error(SeleniumMessages.SE000010E, e);
		}
	}

	/**
	 * Method to set browser for execution for local machine. While calling this
	 * method either pass friefox or chrome browser to execute scripts.
	 * 
	 * @param desiredBrowser
	 *        String
	 */
	private void setDesiredBrowser(String desiredBrowser) {

		if ("firefox".equals(desiredBrowser)) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("intl.accept_languages", "en");
			profile.setPreference("browser.cache.disk.enable", false);
			profile.setEnableNativeEvents(true);
			driver = new FirefoxDriver(profile);
		} else {
			System.setProperty(SeleniumConstant.CHROME_DRIVER_KEY, "C:/CCB/software/chromedriver_win32_V2.15/chromedriver.exe");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(SeleniumConstant.CHROME_BROWSER_BINARY, "C:/Program Files/Google/Chrome/Application/chrome.exe");
			driver = new ChromeDriver();
		}
	}

	/**
	 * Method to initialize required set of parameters for execution. Once
	 * browser is initialized it won't call envSetup(browser, os) method again,
	 * if browser will change then only it will call again.
	 * 
	 * @param browser
	 * @param os
	 */
	private void initializeEnvironment(String browser, String os) {

		try {
			setEnvironment();
			if (driver == null || (!(driver.getCapabilities().getBrowserName().equals(browser))) || driver.toString().contains("null")) {
				envSetup(browser, os);
				setDefaultWaitTime();
			}
		} catch (MalformedURLException e) {
			LOG.error(SeleniumMessages.SE000013E, e);
		}
	}

	/**
	 * Method to prepare test environment and form portal url using application
	 * and test environment.
	 */
	private void setEnvironment() {

		commonTestData = CommonTestData.getInstance(SeleniumConstant.COMMON_PROPERTIES);
		this.moduleTestData = ModuleTestData.getInstance();
		this.portalUrl = commonTestData.getKey((this.testingOn).concat(commonTestData.getKey(SeleniumConstant.PORTAL_URL))).concat(
					this.applicationUrl);
		this.appender = commonTestData.getKey(this.testingOn.concat(SeleniumConstant.APPENDER));
	}

	/**
	 * Method to set default wait and execution time, and maximize window size.
	 */
	private void setDefaultWaitTime() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(32, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	/**
	 * Setup the environment based on the configurations in the property file.
	 * 
	 * @param browser
	 *        The browser name
	 * @param os
	 *        The platform
	 * @throws MalformedURLException
	 */
	private void envSetup(String browser, String os) throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();
		if ("MAC".equalsIgnoreCase(os)) {
			envSetupForMac(browser, caps);
		} else if ("VISTA".equalsIgnoreCase(os)) {
			envSetupForVista(browser, caps);
		} else if ("XP".equalsIgnoreCase(os)) {
			envSetupForXp(browser, caps);
		} else if ("WIN7".equalsIgnoreCase(os) || "ANY".equalsIgnoreCase(os)) {
			envSetupForWinMachine(browser, caps);
		}
		caps.setJavascriptEnabled(true);
		URL url = new URL(SeleniumConstant.HUB_URL);
		driver = new RemoteWebDriver(url, caps);
	}

	/**
	 * Method to set capabilities for MAC build server.
	 * 
	 * @param browser String
	 * @param caps DesiredCapabilities
	 */
	private void envSetupForMac(String browser, DesiredCapabilities caps) {

		caps.setPlatform(Platform.MAC);
		caps.setBrowserName(browser);
		if (browser.equals("chrome")) {
			setChromeBinary(caps);
		}
	}

	/**
	 * Method to set capabilities for Vista build server.
	 * 
	 * @param browser String
	 * @param caps DesiredCapabilities
	 */
	private void envSetupForVista(String browser, DesiredCapabilities caps) {

		caps.setPlatform(Platform.VISTA);
		caps.setBrowserName(browser);
		if (browser.equals("internet explorer")) {
			setIeBinary(caps);
		}
		if (browser.equals("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setEnableNativeEvents(true);
			caps = DesiredCapabilities.firefox();
			caps.setCapability(FirefoxDriver.PROFILE, profile);
		}
		if (browser.equals("chrome")) {
			setChromeBinary(caps);
		}
	}

	/**
	 * Method to set capabilities for XP build server.
	 * 
	 * @param browser String.
	 * @param caps DesiredCapabilities.
	 */
	private void envSetupForXp(String browser, DesiredCapabilities caps) {

		caps.setPlatform(Platform.XP);
		caps.setBrowserName(browser);
		if (browser.equals("internet explorer")) {
			setIeBinary(caps);
		}
		if (browser.equals("chrome")) {
			setChromeBinary(caps);
		}
		if (browser.equals("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setEnableNativeEvents(true);
			caps = DesiredCapabilities.firefox();
			caps.setCapability(FirefoxDriver.PROFILE, profile);
		}
	}

	/**
	 * Method to set capabilities for Win7 (machine one) build server.
	 * 
	 * @param browser String.
	 * @param caps DesiredCapabilities
	 */
	private void envSetupForWinMachine(String browser, DesiredCapabilities caps) {

		caps.setPlatform(Platform.WINDOWS);
		caps.setBrowserName(browser);
		if (browser.equals("internet explorer")) {
			setIeBinary(caps);
		}
		if (browser.equals("firefox")) {
			//Below line of code can be used if desired capabilities will not work to set binary path for Firefox browser.
			//System.setProperty("webdriver.firefox.bin", "C:\\CCB\\SeleniumGrid\\FF36.0.4\\firefox.exe");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setEnableNativeEvents(true);
			caps.setCapability("binary", SeleniumConstant.FIREFOX_PATH);
			caps.setCapability(FirefoxDriver.PROFILE, profile);
		}
		if (browser.equals("chrome")) {
			setChromeBinary(caps);
		}
	}

	/**
	 * Method to set the Chrome binary path, drvier.exe path for execution.
	 */
	private void setChromeBinary(DesiredCapabilities caps) {

		caps = DesiredCapabilities.chrome();
		caps.setBrowserName("chrome");
		System.setProperty(SeleniumConstant.CHROME_DRIVER_KEY, SeleniumConstant.CHROME_DRIVER_EXE_PATH);
		caps.setCapability(SeleniumConstant.CHROME_BROWSER_BINARY, SeleniumConstant.CHROME_BROWSER_EXE_PATH);
	}

	/**
	 * Method to set the Internet Explorer binary path, drvier.exe path for
	 * execution.
	 */
	private void setIeBinary(DesiredCapabilities caps) {

		System.setProperty(SeleniumConstant.IE_DRIVER_KEY, SeleniumConstant.IE_DRIVER_EXE_PATH);
	}

	/**
	 * Method to login into portal.
	 * 
	 * @param loginType
	 * @return
	 */
	public RemoteWebDriver login(String loginType) {

		Integer loginCount = 0;

		if ((userID != null) && !(userID.equals(loginType)) && !(driver.getCurrentUrl().contains("/lp/login"))
					&& !(driver.getCurrentUrl().contains("blank"))
					&& !((driver.getCurrentUrl().contains("/system/logout/")) || (driver.getCurrentUrl().contains("/logout")))) {
			logout(driver);
		}
		if (driver.getCurrentUrl().contains("blank") || !(driver.getCurrentUrl().contains(this.applicationUrl))) {
			driver.get(this.portalUrl);
		}
		while (loginCount != Integer.parseInt(commonTestData.getKey("LoginRetryCount")) && driver.getCurrentUrl().contains("/lp/login")
					|| driver.getCurrentUrl().contains(SeleniumConstant.MSB_LOGIN_CHECK) && !driver.getCurrentUrl().contains("/home.html")
					&& !(driver.getCurrentUrl().contains("fehlerseiten"))) {
			driver.findElement(By.id("teilnehmer")).clear();
			driver.findElement(By.id("teilnehmer")).sendKeys(commonTestData.getKey((SeleniumConstant.LOGIN_PREFIX).concat(loginType)));
			driver.findElement(By.id("pin")).clear();
			driver.findElement(By.id("pin")).sendKeys(commonTestData.getKey((SeleniumConstant.PASSWORD_PREFIX).concat(loginType)));

			driver.findElementByCssSelector("[class='b-01 b-a-04 b-g-01 login']").click();

			loginCount++;
		}
		if (!"MSB".equalsIgnoreCase(OsBrowserController.trackInfo)) {
			// Refresh page to close alert teaser, if is there.
			driver.navigate().refresh();
		}
		userID = loginType;
		return driver;
	}

	/**
	 * Method to logout from the portal.
	 * 
	 * @param driver
	 */
	public void logout(RemoteWebDriver driver) {

		if (isElementPresent(By.cssSelector("[href='/lp/logout']"))) {
			WebElement logout = driver.findElement(By.cssSelector("[href='/lp/logout']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logout);
			logout.click();
		} else
			driver.findElementByLinkText("Logout").click();
	}

	public void openPreviewServer() {

		driver.get(this.portalUrl);
	}

	/**
	 * Method to check element is present or not.
	 * 
	 * @param eId
	 * @return
	 */
	public boolean existsElement(String eId) {

		try {
			driver.findElement(By.id(eId));
		} catch (NoSuchElementException e) {
			LOG.error(SeleniumMessages.SE00008E, e);
			return false;
		}
		return true;
	}

	/**
	 * Method to set application url and testing environment to execute scripts.
	 * 
	 * @param applicationUrl
	 * @param testingOn
	 */
	protected void setTestEnvironment(String applicationUrl, String testingOn) {

		String environmentName = System.getProperty("environment");
		this.applicationUrl = applicationUrl;
		if (environmentName != null) {
			this.testingOn = environmentName.toLowerCase();
		} else {
			this.testingOn = testingOn.toLowerCase();
		}
	}

	/**
	 * Method to set application url and testing environment to execute scripts.
	 * 
	 * @param applicationUrl
	 * @param testingOn
	 */
	protected void setTestEnvironment(String applicationUrl, String testingOn, String postFix) {

		String environmentName = System.getProperty("environment");
		this.applicationUrl = applicationUrl;
		if (environmentName != null) {
			this.testingOn = environmentName.toLowerCase().concat(postFix);
		} else {
			this.testingOn = testingOn.toLowerCase().concat(postFix);
		}
	}

	/**
	 * Method to enter value in input field
	 * 
	 * @param elementLoc
	 * @param stringVal
	 */
	public void setText(By elementLoc, String stringVal) {

		driver.findElement(elementLoc).clear();
		driver.findElement(elementLoc).sendKeys(stringVal);
	}

	/**
	 * Method to wait for an element till it's not display .
	 * 
	 * @param by
	 */
	public void waitForElementDisplayed(By by) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * Method to wait for an element till it's not clickable.
	 * 
	 * @param by
	 */
	public void waitForElementToBeClickable(By by) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * Method to click on element using java script.
	 * 
	 * @param by
	 */
	public void javaScriptElementClick(By by) {

		waitForElementToBeClickable(by);
		JavascriptExecutor executer = driver;
		executer.executeScript("arguments[0].click();", driver.findElement(by));
	}

	/**
	 * Method to click on element.
	 * 
	 * @param by
	 */
	public void clickElement(By by) {

		waitForElementToBeClickable(by);
		driver.findElement(by).click();
	}

	/**
	 * Method to find out element by text.
	 * 
	 * @param by
	 * @param expectedString
	 */
	public void verifyElementText(By by, String expectedString) {

		assertTrue(driver.findElement(by).getText().trim().replace("\n", "").replace("\r", "").equalsIgnoreCase(expectedString.trim()));
	}

	/**
	 * This method is used to get a particular date in a particular format.
	 * 
	 * @param offsetFromToday
	 *        Number of days to be added or subtracted from today.
	 * @param format
	 *        Required format of the date example dd.MM.yyyy
	 * @return date in String format
	 */
	@SuppressWarnings("static-method")
	public String getDateInFormattedString(int offsetFromToday, String format) {

		DateFormat df = new SimpleDateFormat(format);
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, offsetFromToday);
		return df.format(today.getTime());

	}

	/**
	 * This method is used to get a particular date in a particular format.
	 * 
	 * @param offsetFromTodayInMonths
	 *        Number of months to be added or subtracted from today.
	 * @param format
	 *        Required format of the date example dd.MM.yyyy
	 * @return date in String format
	 */
	@SuppressWarnings("static-method")
	public String getDateInFormattedStringMonths(int offsetFromTodayInMonths, String format) {

		DateFormat df = new SimpleDateFormat(format);
		Calendar today = Calendar.getInstance();
		today.add(Calendar.MONTH, offsetFromTodayInMonths);
		return df.format(today.getTime());

	}

	/**
	 * Method to change application language
	 * 
	 * @param lang
	 *        EN/DE
	 * @throws InterruptedException
	 * 
	 */
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
					LOG.error(SeleniumMessages.SE00001E, e);
				}

				if (isElementPresent(By.xpath("//span[@class='ui-button-text']"))) {
					driver.findElement(By.xpath("//span[@class='ui-button-text']")).click();

				}
			}
		}
	}

	/**
	 * Method to change application language
	 * 
	 * @param lang
	 *        EN/DE
	 * @param navigationLink
	 *        navigation link to navigate.
	 * 
	 */
	public void switchInternationalization(String lang, String navigationLink) {

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
					LOG.error(SeleniumMessages.SE00001E, e);
				}

				if (isElementPresent(By.xpath("//span[@class='ui-button-text']"))) {
					driver.findElement(By.xpath("//span[@class='ui-button-text']")).click();
				}
			}
		}
		navigateToMenu(lang, navigationLink);
	}

	/**
	 * Navigate to desired portal eg: order book , CFD
	 * 
	 * @param language
	 *        EN/DE
	 * 
	 * @param navigationLink
	 *        navigationLink eg ,Order book
	 */
	protected void navigateToMenu(String language, String navigationLink) {

		// To navigate to desired portal.
		setSleepTime(2000);
		if (language.equalsIgnoreCase("EN") || language.equalsIgnoreCase("english")) {
			driver.findElement(By.linkText(SeleniumConstant.ONLINE_BANKING_EN)).click();

		} else {
			driver.findElement(By.linkText(SeleniumConstant.ONLINE_BANKING_DE)).click();

		}
		setSleepTime(5000);
		if (isElementPresent(By.linkText(navigationLink))) {
			driver.findElement(By.linkText(navigationLink)).click();
			setSleepTime(5000);
		} else {
			List<WebElement> li = driver.findElements(By.xpath("//div[@class='jspPane']//li//a"));
			for (WebElement wel : li) {
				if (wel.getText().equalsIgnoreCase(navigationLink)) {
					wel.click();
					break;
				}
			}
		}
		setSleepTime(5000);
	}

	/**
	 * Method to verify if element is present
	 * 
	 * @param by
	 * @return
	 */
	protected boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			LOG.error(SeleniumMessages.SE00008E, e);
			return false;
		}
	}

	/**
	 * Set focus and click on element
	 * 
	 * @param element
	 *        element on which we want to click
	 */
	public void setFocusAndClickElement(WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
		setSleepTime(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		setSleepTime(5000);
	}

	/**
	 * Scroll into view and click on element
	 * 
	 * @param element
	 *        Scroll into view a element
	 */
	public void scrollToIntoView(WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		setSleepTime(2000);
	}

	/**
	 * This method is used to do a explicit login without going to the login
	 * page
	 * 
	 * @param loginType
	 *        The user string
	 * 
	 */
	@SuppressWarnings("static-method")
	public void explicitLogin(String loginType) {

		driver.findElement(By.id("teilnehmer")).clear();
		driver.findElement(By.id("teilnehmer")).sendKeys(commonTestData.getKey((SeleniumConstant.LOGIN_PREFIX).concat(loginType)));
		driver.findElement(By.id("pin")).clear();
		driver.findElement(By.id("pin")).sendKeys(commonTestData.getKey((SeleniumConstant.PASSWORD_PREFIX).concat(loginType)));
		driver.findElement(By.id("headerLoginSubmit")).click();
	}

	/**
	 * Method to verify if element is present
	 * 
	 * @param by
	 * @return
	 */
	protected static boolean isElementPresent(WebElement element) {

		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			LOG.error(SeleniumMessages.SE00008E, e);
			return false;
		}
	}

	/**
	 * Utility method which retrieves TAN number from URL's (to which TAN number
	 * is appended) and then enters the same into the input box of the TAN. <br>
	 * Before calling this function you need to have this particular piece of
	 * code <br>
	 * <code>
	 * Date date = dateFormatConversion(); <br>
	 * </code><br>
	 * and then pass this 'date' object as a parameter to this method.
	 * 
	 * @param transactionType
	 *        Transaction type as per Track and the functionality
	 * @param messageTextTel
	 *        Unique participant number
	 * @param date
	 *        TAN request time
	 * @return true if TAN number was successfully entered into the input box
	 */
	protected boolean tanValidationPhotoTan(String transactionType, String messageTextTel, Date date) {

		String tanNumber = "";
		String mainWindow = driver.getWindowHandle();

		try {
			/**
			 * TAN server does not respond so fast while automation, thats why
			 * had to wait for some time
			 */
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			LOG.error(SeleniumMessages.SE00001E, e);
		}

		/** First URL from which mobile TAN is to be searched for */
		String url1 = SeleniumConstant.SMS_SERVER_ONE;

		/** open a new tab in the browser and load the URL in it */
		driver.findElement(By.cssSelector(HTML_BODY)).sendKeys(Keys.CONTROL, "t");
		driver.get(url1);
		try {
			/** let selenium open a new tab and hold for a second. */
			Thread.sleep(1000);
			// Refresh the tab to get updated values
			driver.navigate().refresh();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOG.error(SeleniumMessages.SE00001E, e);
		}

		// Generate a list for all elements related to Photo TAN table
		List<WebElement> lists = driver.findElements(By.xpath("html/body/table[2]/tbody/tr"));
		/**
		 * perform this action only when table has at least one entry for TAN
		 * and not just only headers
		 */
		if (lists.size() > 1) {
			tanNumber = tanSearchPhotoTan(lists, transactionType, messageTextTel, date);
		}
		try {
			/** let Selenium wait while switching tabs. */
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOG.error(SeleniumMessages.SE00001E, e);
		}
		changeTabs(mainWindow);
		if (tanNumber.isEmpty()) {
			driver.findElement(By.cssSelector(HTML_BODY)).sendKeys(Keys.CONTROL, "t");

			/** Second URL from which mobile TAN is to be searched for */
			String url2 = SeleniumConstant.SMS_SERVER_TWO;
			driver.get(url2);
			try {
				/** let selenium open a new tab and hold for a second. */
				Thread.sleep(1000);
				// refresh the tab to get updated values
				driver.navigate().refresh();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOG.error(SeleniumMessages.SE00001E, e);
			}
			lists.clear();
			lists = driver.findElements(By.xpath("html/body/table[2]/tbody/tr"));
			tanNumber = tanSearchPhotoTan(lists, transactionType, messageTextTel, date);
			changeTabs(mainWindow);
		}
		driver.findElementByName("tanPanel:dynamicTANPanel:form:tanInputField").clear();
		driver.findElementByName("tanPanel:dynamicTANPanel:form:tanInputField").sendKeys(tanNumber);
		if (tanNumber.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Utility method which retrieves TAN number from URL's (to which TAN number
	 * is appended) and then enters the same into the input box of the TAN. You
	 * need perform a click event on 'mobile TAN' button before calling this
	 * function. <br>
	 * Before calling this function you need to have this particular piece of
	 * code <br>
	 * <code>
	 * 		&nbsp &nbsp  Date date = dateFormatConversion(); <br>
			&nbsp &nbsp  driver.findElementById("requestTanButton").click();
	 * </code><br>
	 * and then pass this 'date' object as a parameter to this method.
	 * 
	 * @param tanMessageText
	 *        TAN Message as per Track and the functionality
	 * @param messageTextTel
	 *        Unique participant number
	 * @param date
	 *        TAN request time
	 * @return true if TAN number was successfully entered into the input box
	 */
	protected boolean tanValidationSMS(String tanMessageText, String messageTextTel, Date date) {

		String tanNumber = "";
		String mainWindow = driver.getWindowHandle();

		try {
			/**
			 * TAN server does not respond so fast while automation, thats why
			 * had to wait for some time
			 */
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			LOG.error(SeleniumMessages.SE00001E, e);
		}

		/** First URL from which mobile TAN is to be searched for */
		String url1 = SeleniumConstant.SMS_SERVER_ONE;

		/** open a new tab in the browser and load the URL in it */
		driver.findElement(By.cssSelector(HTML_BODY)).sendKeys(Keys.CONTROL, "t");
		driver.get(url1);
		try {
			/** let selenium open a new tab and hold for a second. */
			Thread.sleep(1000);
			// refresh the tab to get updated values
			driver.navigate().refresh();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOG.error(SeleniumMessages.SE00001E, e);
		}

		// generate a list for all elements related to mobile TAN table
		List<WebElement> lists = driver.findElements(By.xpath("html/body/table[1]/tbody/tr"));

		/**
		 * perform this action only when table has at least one entry for TAN
		 * and not just only headers
		 */
		if (lists.size() > 1) {
			tanNumber = tanSearch(lists, tanMessageText, messageTextTel, date);
		}
		try {
			/** let selenium wait while switching tabs. */
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOG.error(SeleniumMessages.SE00001E, e);
		}
		changeTabs(mainWindow);
		if (tanNumber.isEmpty()) {
			driver.findElement(By.cssSelector(HTML_BODY)).sendKeys(Keys.CONTROL, "t");

			/** Second URL from which mobile TAN is to be searched for */
			String url2 = SeleniumConstant.SMS_SERVER_TWO;
			driver.get(url2);
			try {
				/** let selenium open a new tab and hold for a second. */
				Thread.sleep(1000);
				// refresh the tab to get updated values
				driver.navigate().refresh();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOG.error(SeleniumMessages.SE00001E, e);
			}
			lists.clear();
			lists = driver.findElements(By.xpath("html/body/table[1]/tbody/tr"));
			tanNumber = tanSearch(lists, tanMessageText, messageTextTel, date);
			changeTabs(mainWindow);
		}
		if (tanMessageText.contains("Bitte prÃƒÂ¼fen Sie: Abmeldung von paydirekt, Benutzername")) {
			driver.findElementByName("cancellationConfirmationTAN:dynamicTANPanel:form:tanInputField").clear();
			driver.findElementByName("cancellationConfirmationTAN:dynamicTANPanel:form:tanInputField").sendKeys(tanNumber);

		} else if (tanMessageText.contains("Bitte prüfen Sie: Datenänderung für paydirekt Benutzername")) {
			driver.findElementByName("masterDataChangeConfirmationTAN:dynamicTANPanel:form:tanInputField").clear();
			driver.findElementByName("masterDataChangeConfirmationTAN:dynamicTANPanel:form:tanInputField").sendKeys(tanNumber);

		} else {
			driver.findElementByName(

			"tanPanel:dynamicTANPanel:form:tanInputField").clear();
			driver.findElementByName("tanPanel:dynamicTANPanel:form:tanInputField").sendKeys(tanNumber);
		}
		if (tanNumber.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Method which returns the TAN number from the list of WebElement.
	 * 
	 * @param lists
	 *        webElements of the table
	 * @param transactionType
	 *        Transaction Type as per Track and the functionality
	 * @param msgTextTel
	 *        Unique participant number
	 * @param date
	 *        TAN request time
	 * @return
	 */
	private String tanSearchPhotoTan(List<WebElement> lists, String transactionType, String msgTextTel, Date date) {

		String messageBody = "";
		/**
		 * Loop through web elements of the Photo TAN table, extract TAN number
		 * on the basis of transaction type and participant number. Time on which
		 * TAN was generated must be greater than the request time.
		 */
		for (int i = lists.size(); i > 1; i--) {
			/** 'xpath' value to the location for DATE string in table */
			String dateTAN = "/html/body/table[2]/tbody/tr[" + i + "]/td[1]";
			/** String that will have time of the generated TAN */
			String dateTANPageString = driver.findElement(By.xpath(dateTAN)).getText();
			int beginIndexTime = dateTANPageString.lastIndexOf(' ');
			dateTANPageString = dateTANPageString.substring(beginIndexTime + 1);
			Date dateTANPage = getCurrentDateFromTable(dateTANPageString);
			// when TAN page time value is less than request TAN time
			if (dateTANPage.compareTo(date) < 0) {
				break;
			}
			/** 'xpath' value to the location for TAN string in table */
			String xpathTAN = "html/body/table[2]/tbody/tr[" + i + "]/td[6]";
			/**
			 * 'xpath' to the location for for unique participant id in table
			 */
			String xpathTeilnehmer = "/html/body/table[2]/tbody/tr[" + i + "]/td[2]";
			/** String which has the telinumber */
			String teilnehmerNr = driver.findElement(By.xpath(xpathTeilnehmer)).getText();
			String xpathTransactionType = "html/body/table[2]/tbody/tr[" + i + "]/td[3]";
			String transactionTypeText = driver.findElement(By.xpath(xpathTransactionType)).getText();
			if (transactionType.contains(transactionTypeText) && teilnehmerNr.contains(msgTextTel)) {
				messageBody = driver.findElement(By.xpath(xpathTAN)).getText();
			}
		}
		return messageBody;

	}

	/**
	 * Method which returns the TAN number from the list of WebElement.
	 * 
	 * @param lists
	 *        webElements of the table
	 * @param messageText
	 *        TAN Message as per Track and the functionality
	 * @param msgTextTel
	 *        Unique participant number
	 * @param date
	 *        TAN request time
	 * @return
	 */
	private String tanSearch(List<WebElement> lists, String messageText, String msgTextTel, Date date) {

		String tanNumber = "";
		/**
		 * Loop through web elements of the mobile TAN table, extract TAN number
		 * on the basis of message text and participant number. Time on which
		 * TAN was generated must be greater than the request time.
		 */
		for (int i = lists.size(); i > 1; i--) {

			/** 'xpath' value to the location for DATE string in table */
			String dateTAN = "/html/body/table[1]/tbody/tr[" + i + "]/td[1]";

			/** String that will have time of the generated TAN */
			String dateTANPageString = driver.findElement(By.xpath(dateTAN)).getText();

			int beginIndexTime = dateTANPageString.lastIndexOf(' ');
			dateTANPageString = dateTANPageString.substring(beginIndexTime + 1);
			Date dateTANPage = getCurrentDateFromTable(dateTANPageString);

			// when TAN page time value is less than request TAN time
			if (dateTANPage.compareTo(date) < 0) {
				break;
			}

			/** 'xpath' value to the location for TAN string in table */
			String xpathTAN = "html/body/table[1]/tbody/tr[" + i + "]/td[4]";

			/**
			 * 'xpath' to the location for for unique particiapnt id in table
			 */
			String xpathTeilnehmer = "/html/body/table[1]/tbody/tr[" + i + "]/td[2]";

			/** String which has the teilnehmer */
			String teilnehmerNr = driver.findElement(By.xpath(xpathTeilnehmer)).getText();

			/** String which has the message text which contains TAN number */
			String messageBody = driver.findElement(By.xpath(xpathTAN)).getText();

			if (messageBody.contains(messageText) && teilnehmerNr.contains(msgTextTel)) {
				int beginIndex = messageBody.lastIndexOf(':');
				tanNumber = messageBody.substring(beginIndex + 2);
				break;
			}
		}
		return tanNumber;

	}

	/**
	 * Method which returns the date object from the String date passed. String
	 * date has to be in the format of 'hours:minutes:seconds'
	 * 
	 * @param date
	 *        String date which has to be converted
	 * @return formatted date object; used internally
	 */
	@SuppressWarnings("static-method")
	private Date getCurrentDateFromTable(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Date dateObj = null;
		try {
			dateObj = sdf.parse(date);
		} catch (ParseException e) {
			LOG.error(SeleniumMessages.SE000010E, e);
		}
		return dateObj;
	}

	/**
	 * Utility to switch between windows
	 * 
	 * @param mainWindow
	 *        parent window
	 */
	@SuppressWarnings("static-method")
	private void changeTabs(String mainWindow) {

		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		for (String currentTab : newTab) {
			driver.switchTo().window(currentTab);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
			driver.switchTo().window(mainWindow);
		}
	}

	/**
	 * Used internally to format system date as per the requirement.
	 * 
	 * @return formatted date as per the requirement
	 */
	protected Date dateFormatConversion() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String dateConvert = sdf.format(date);
		return getCurrentDateFromTable(dateConvert);
	}

	/**
	 * This is sleep method from java only use it when uttermost required
	 * 
	 * @param millis
	 *        time in mili seconds
	 */
	@SuppressWarnings("static-method")
	protected void setSleepTime(long millis) {

		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			LOG.error(SeleniumMessages.SE00001E, e);
		}
	}

	/**
	 * Method to execute scripts based on locale.
	 * 
	 * @param locales
	 *        - language name
	 * @return languages
	 */
	@SuppressWarnings("rawtypes")
	protected static Collection internationalization(String locales) {

		Object[] localeValues = locales.split("\\s*,\\s*");
		Object[] languages = new Object[localeValues.length];
		for (int i = 0; i < localeValues.length; i++) {
			languages[i] = new Object[] { localeValues[i] };
		}
		return Arrays.asList(languages);
	}

	/**
	 * Method to navigate to specific page. It can be used wherever explicit url
	 * is required to navigate on desired portal/page.
	 * 
	 * @param nvigationUrl
	 *        String.
	 */
	protected void setSpecificNavigation(String nvigationUrl) {

		driver.get(nvigationUrl);
	}

	/**
	 * @author Abhishek
	 * 
	 *         Method to verify string by using regular expression
	 * 
	 * @param str
	 *        string to be verified
	 * @param reqEx
	 *        regular expression
	 * @return true /false
	 */
	@SuppressWarnings("static-method")
	protected boolean verifyStringWithRegEx(String str, String reqEx) {

		@SuppressWarnings("unused")
		Pattern pattern, patternNull;
		Matcher matcher;

		pattern = Pattern.compile(reqEx);
		patternNull = Pattern.compile("^$");

		matcher = pattern.matcher(str);

		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * Method for fluent wait, it can be used to wait for particular element to
	 * load on page, based on given time unit. Using polling mechanism it will
	 * lookup an element on DOM in each and every 5 seconds (or provided time
	 * unit). Use this wait only if element is present in DOM and that is
	 * enabling based on some condition.
	 * 
	 * @param locator
	 *        By
	 * @return waitElement WebElement
	 */
	protected WebElement fluentWait(final By locator, long timeoutTime, long pollingTime) {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		Wait<WebDriver> wait = new FluentWait(driver).withTimeout(timeoutTime, TimeUnit.SECONDS).pollingEvery(pollingTime, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);

		WebElement waitElement = wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver input) {

				return driver.findElement(locator);
			}
		});
		return waitElement;
	}

	/**
	 * Method to refresh Page
	 */
	protected void refreshPage() {

		driver.navigate().refresh();

	}

	/**
	 * Abstract method to set the page in default mode.
	 */
	public abstract void resetPage();
}
