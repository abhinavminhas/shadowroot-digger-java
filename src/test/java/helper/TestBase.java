package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class TestBase {

	protected WebDriver WebDriver;
	private String ChromeDriverVersion = "97.0.4692.71";
	protected static final String CHROME_SETTINGS_TESTS = "CHROME-SETTINGS-TESTS";
	protected static final String SHADOW_DOM_HTML_TESTS = "SHADOW-DOM-HTML-TESTS";
	protected static Properties config;
	private static FileInputStream fis;
	
	/**
	 * Gets configuration properties.
	 * 
	 * @throws IOException 
	 */
	@BeforeSuite
	public static void getProperties() throws IOException {
		config = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
		config.load(fis);
	}
	
	/**
	 * Initialize test.
	 * 
	 * @throws MalformedURLException
	 */
	@BeforeTest
	public void getChromeDriver() throws MalformedURLException {
		WebDriverManager.chromedriver().driverVersion(ChromeDriverVersion).setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments("--no-sandbox");
		if (config.getProperty("UseDocker").equals("true")) {
			WebDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), chromeOptions);
		} else {
			WebDriver = new ChromeDriver(chromeOptions);
		}
		WebDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		WebDriver.manage().window().maximize();
	}
	
	/**
	 * Tear down test.
	 */
	@AfterTest
	public void quitDriver() {
		WebDriver.quit();
	}
	
	/**
	 * Gets test files directory path.
	 * 
	 * @return Test files directory path.
	 */
	public String getTestFilePath() {
		String path = "file:///" + System.getProperty("user.dir") + "/src/test/resources/testfiles/ShadowDOM.html";
		return path;
	}
	
}