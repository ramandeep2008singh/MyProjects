package com.practice.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
	 * Object to hold RemoteWebDriver value.
	 */
	public static RemoteWebDriver driver;

	/**
	 * Adding logger
	 */
	// Logger logs;

	/**
	 * Initializing object of Page model class
	 */
	protected UiTestPageModel uiTestPageModel;

	/** Instantiating the logger. */
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

		} else if (desiredBrowser.equalsIgnoreCase(Constants.FIREFOX)) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + Constants.FIREFOX_DRIVER);
			driver = new FirefoxDriver();

		} else if (desiredBrowser.equalsIgnoreCase(Constants.IE)) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + Constants.IE_DRIVER);
			driver = new InternetExplorerDriver();

		} else if (desiredBrowser.equalsIgnoreCase(Constants.EDGE)) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + Constants.EDGE_DRIVER);
			driver = new EdgeDriver();

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
	 */
	public void loadProperties() {

		try {
			FileReader reader = new FileReader(System.getProperty("user.dir") + Constants.UI_TEST_FULL_PATH);
			properties = new Properties();
			properties.load(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// else if (giveProperty.equalsIgnoreCase(Constants.API_TEST)) {
	// properties = new Properties();
	// try {
	// fis = new FileInputStream(System.getProperty("user.dir") +
	// Constants.API_TEST_FULL_PATH);
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//

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

	// /**
	// * screenShot method is invoked whenever the Testcase is Failed.
	// *
	// * @param name
	// * @param driver
	// * @return
	// */
	// @Attachment(value = "Screenshot of {0}", type = "image/png")
	// public byte[] saveScreenshot(String name, WebDriver driver) {
	// return (byte[]) ((TakesScreenshot)
	// driver).getScreenshotAs(OutputType.BYTES);
	// }
	//
	// public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
	// iHookCallBack.runTestMethod(iTestResult);
	// if (iTestResult.getThrowable() != null) {
	// this.saveScreenshot(iTestResult.getName(), driver);
	// }
	// }

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

}
