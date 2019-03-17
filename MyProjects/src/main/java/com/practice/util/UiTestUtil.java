package com.practice.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practice.pagemodel.UiTestPageModel;
import com.practice.reports.ExtentManager;

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
	public Properties properties;

	/**
	 * variable for file input stream
	 */
	FileInputStream fis;

	/**
	 * Object to hold RemoteWebDriver value.
	 */
	public static RemoteWebDriver driver;

	/**
	 * Adding Extent classes object
	 */
	public ExtentReports exReport;
	public ExtentTest exScenario;

	/**
	 * Initializing object of Page model class
	 */
	public UiTestPageModel uiTestPageModel;

	/** Instantiating the logger. */

	/**
	 * WebDriver wait object
	 */
	public WebDriverWait wait;

	// private static final TestLogger LOG =
	// TestLogger.getLogger(UiTestUtil.class);

	/**
	 * Method to login into portal
	 */
	public void login() {

		// logs = Logger.getLogger("devpinoylogger");
		// logs.debug("starting");
		// logs.debug("executing");
		// logs.debug("ending");

	}

	/**
	 * Method to open browser
	 */
	public void openBrowserWindow(String browserName) {

		if (browserName.equalsIgnoreCase(Constants.CHROME)) {
			setDesiredBrowser(browserName);

		} else if (browserName.equalsIgnoreCase(Constants.FIREFOX)) {
			setDesiredBrowser(browserName);

		} else if (browserName.equalsIgnoreCase(Constants.IE)) {
			setDesiredBrowser(browserName);

		} else if (browserName.equalsIgnoreCase(Constants.EDGE)) {
			setDesiredBrowser(browserName);

		}
		setDefaultWaitTime();
		initializeMethods();
	}

	/**
	 * Method to handle ChromeOptions
	 */
	public void setDesiredBrowser(String desiredBrowser) {

		if (desiredBrowser.equalsIgnoreCase(Constants.CHROME)) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + Constants.CHROME_DRIVER);
			// To enable browser level logs
			System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "D://chrome.log");
			// To stop browser level logs printing to the console
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--disable-notifications");
			ops.addArguments("disable-infobars");
			ops.addArguments("--start-maximized");

			driver = new ChromeDriver(ops);
			infoLog("browser opened: " + desiredBrowser);

		} else if (desiredBrowser.equalsIgnoreCase(Constants.FIREFOX)) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + Constants.FIREFOX_DRIVER);
			driver = new FirefoxDriver();
			infoLog("browser opened: " + desiredBrowser);

		} else if (desiredBrowser.equalsIgnoreCase(Constants.IE)) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + Constants.IE_DRIVER);
			driver = new InternetExplorerDriver();
			infoLog("browser opened: " + desiredBrowser);

		} else if (desiredBrowser.equalsIgnoreCase(Constants.EDGE)) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + Constants.EDGE_DRIVER);
			driver = new EdgeDriver();
			infoLog("browser opened: " + desiredBrowser);

		}

	}

	/**
	 * Method to set default wait and execution time, and maximize window size.
	 */
	private void setDefaultWaitTime() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	/**
	 * Method to wait for an element till it's display .
	 * 
	 * @param by
	 */
	public void waitForElementDisplayed(By by) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	/**
	 * Method to wait for an element till it's clickable.
	 * 
	 * @param by
	 */
	public void waitForElementToBeClickable(By by) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(by));
	}

	public void navigateToPortal(String url) {
		driver.get(url);
	}

	/**
	 * Method to setup the browser, properties file ...
	 */
	public void setup(String browser) {

	}

	/**
	 * load properties file
	 * 
	 * @throws IOException
	 */
	public void loadProperties() {

		properties = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + Constants.UI_TEST_FULL_PATH);
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method to initialize page model class
	 */
	public void initializeMethods() {

		this.uiTestPageModel = PageFactory.initElements(driver, UiTestPageModel.class);
		loadProperties();

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
	 * Method to append random nos to email
	 * 
	 * @return
	 */
	public int generateRandomEmail() {

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		return randomInt;

	}

	/**
	 * Method to close the browser
	 */
	public void tearDown() {

	}
	
	/*****************Extent report****************
	
	/**
	 * Method to generate the Extent Reports
	 */
	public void initReports(String scenarioName) {
		exReport = ExtentManager.getInstance(this.properties.getProperty("ExtentReportPath"));
		exScenario = exReport.createTest(scenarioName);
		exScenario.log(Status.INFO, "Starting scenario " + scenarioName);
	}
	
	//***********Extent logs****************
	
	/**
	 * Method for INFO log
	 */
	public void infoLog(String msg) {
		exScenario.log(Status.INFO, msg);
	}
	
	/**
	 * Method for FAILURE log
	 */
	public void failurerLog(String msg) {
		exScenario.log(Status.FAIL, msg);
	}
	
	/**
	 * Method for ERROR log
	 */
	public void errorrLog(String msg) {
		exScenario.log(Status.ERROR, msg);
	}
	
	/**
	 * Method for DEBUG log
	 */
	public void debugLog(String msg) {
		exScenario.log(Status.DEBUG, msg);
	}
	
	/**
	 * Method for WARNING log
	 */
	public void warningLog(String msg) {
		exScenario.log(Status.WARNING, msg);
	}
}
