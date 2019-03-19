package com.practice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practice.pagemodel.UiTestPageModel;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * UI test util methods
 * 
 * @author ramandeep.singh.nagi
 *
 */
public class UiTestUtil {

	/******************* Creating the reference Objects of classes *********/

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

	/**
	 * Declaring Select Objects
	 */
	protected Select select;

	/**
	 * Method to get current driver.
	 * 
	 * @return
	 */
	public RemoteWebDriver getDriver() {

		return driver;
	}

	protected String EMAIL = "";
	protected String PASSWORD = "";

	/********************************* Util methods ***********************/

	/**
	 * Method to open browser
	 */
	public void openBrowserWindow(String browserName) {

		if (properties.getProperty("Grid").equalsIgnoreCase("Y")) {
			DesiredCapabilities caps = null;
			if (browserName.equalsIgnoreCase(Constants.CHROME)) {
				caps = DesiredCapabilities.chrome();
				caps.setBrowserName(Constants.CHROME);
				// caps.setJavascriptEnabled(true);
				caps.setPlatform(Platform.ANY);
			} else if (browserName.equalsIgnoreCase(Constants.FIREFOX)) {
				caps = DesiredCapabilities.firefox();
				caps.setJavascriptEnabled(true);
				caps.setPlatform(Platform.ANY);
			} else if (browserName.equalsIgnoreCase(Constants.IE)) {
				caps = DesiredCapabilities.internetExplorer();
				caps.setJavascriptEnabled(true);
				caps.setPlatform(Platform.ANY);
				caps.setVersion("11");
			} else if (browserName.equalsIgnoreCase(Constants.CHROME)) {
				caps = DesiredCapabilities.edge();
				caps.setJavascriptEnabled(true);
				caps.setPlatform(Platform.ANY);
			}
			try {
				driver = new RemoteWebDriver(new URL("http://192.168.0.101:4444/wd/hub"), caps);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (browserName.equalsIgnoreCase(Constants.CHROME)) {
				// setBrowserBinaries();
				setDesiredBrowser(browserName);

			} else if (browserName.equalsIgnoreCase(Constants.FIREFOX)) {
				// setBrowserBinaries();
				setDesiredBrowser(browserName);

			} else if (browserName.equalsIgnoreCase(Constants.IE)) {
				// setBrowserBinaries();
				setDesiredBrowser(browserName);

			} else if (browserName.equalsIgnoreCase(Constants.EDGE)) {
				// setBrowserBinaries();
				setDesiredBrowser(browserName);

			}
		}
		setDefaultWaitTime();
		initializeMethods();
	}

	/**
	 * Method to load all binaries & drivers for supported browsers
	 */
	public void setBrowserBinaries() {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.edgedriver().setup();
		WebDriverManager.iedriver().setup();
	}

	/**
	 * Method to handle ChromeOptions
	 */
	public void setDesiredBrowser(String desiredBrowser) {
		if (desiredBrowser.equalsIgnoreCase(Constants.CHROME)) {

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + Constants.CHROME_DRIVER);

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
	 * Method to navigate to the desired portal
	 * 
	 * @param url
	 */
	public void navigateToPortal(String url) {
		driver.get(url);
	}

	/**
	 * load properties file
	 * 
	 * @throws IOException
	 */
	public void loadProperties() {
		if (properties == null) {
			try {
				properties = new Properties();
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + Constants.UI_TEST_FULL_PATH);
				properties.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
				// report
			}
		}
	}

	/**
	 * Method to initialize page model class
	 */
	public void initializeMethods() {

		this.uiTestPageModel = PageFactory.initElements(driver, UiTestPageModel.class);
		loadProperties();
		// System.out.println("util: " +
		// properties.getProperty("ExtentReportPath"));
	}

	/**
	 * Method to append random nos to email
	 * 
	 * @return
	 */
	public String generateRandomEmail() {

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999);
		EMAIL = "username" + Integer.toString(randomInt) + "@gmail.com";
		System.out.println(EMAIL);
		return EMAIL;

	}

	/**
	 * Method to verify if element is present
	 * 
	 * @param by
	 * @return
	 */
	protected boolean isElementPresent(WebElement element) {

		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			reportFailure("Elelemnt is not present");
			return false;
		}
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

	// public WebElement getObject(WebElement we) {
	// try {
	// return we;
	// } catch (Exception e) {
	// e.printStackTrace();
	// reportFailure("Unable to extract Object: "+ we);
	// }
	// return we;
	//
	// }

	/******************* Methods to handle waits *************************/

	/**
	 * Method to set default wait and execution time, and maximize window size.
	 */
	private void setDefaultWaitTime() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
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

	/************************************* Screenshot ***********************************/

	/**
	 * Method to: - Log failure to reports - Calling the takeScreenshot method -
	 * failing the cucumber assertions
	 */
	public void reportFailure(String errMsg) {
		// fail in extent reports
		exScenario.log(Status.FAIL, errMsg);
		// take screenshot and put in repots
		takeSceenShot();
		// fail in cucumber as well
		Assertions.assertThat(false);
	}

	/**
	 * Method to capture the screenshot
	 */
	public void takeSceenShot() {
		// fileName of the screenshot
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		// take screenshot
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath + screenshotFile));
			// put screenshot file in reports
			exScenario.log(Status.FAIL, "Screenshot-> "
					+ exScenario.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath + screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*************************** Extent report ***************************/

	/**
	 * /** Method to generate the Extent Reports
	 */
	public void initReports(String scenarioName) {

		exReport = ExtentManager.getInstance("C:\\Reports\\");
		exScenario = exReport.createTest(scenarioName);
		exScenario.log(Status.INFO, "Starting scenario " + scenarioName);
	}

	/**
	 * Method to close the reporting
	 */
	public void quit() {
		if (exReport != null)
			exReport.flush();
		if (driver != null)
			driver.quit();
	}

	/************************** Extent logs *************************/

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
