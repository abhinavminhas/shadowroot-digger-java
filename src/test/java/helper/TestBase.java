package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class TestBase {

	protected WebDriver webDriver;
	private String chromeDriverVersion = "99.0.4844.51";
	protected static final String CHROME_SETTINGS_TESTS = "CHROME-SETTINGS-TESTS";
	protected static final String SHADOW_DOM_HTML_TESTS = "SHADOW-DOM-HTML-TESTS";
	protected static Properties config;
	private static FileInputStream fis;
	private int retry = 0;
	
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
	@BeforeMethod(alwaysRun = true)
	public void getChromeDriver() throws MalformedURLException {
		WebDriverManager.chromedriver().driverVersion(chromeDriverVersion).setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments("--no-sandbox");
		Boolean sessionCreated = false;
		do {
			retry++;
			try {
				if (config.getProperty("UseDocker").equals("true")) {
					webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), chromeOptions);
				} else {
					webDriver = new ChromeDriver(chromeOptions);
				}
				webDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				webDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
				webDriver.manage().window().maximize();
				sessionCreated = true;
				break; 
			} catch (WebDriverException ex) {
				/* Retry */
			}
		} while (retry <= 1);
		if (!sessionCreated)
			throw new WebDriverException("Failed to create webdriver session.");
	}
	
	/**
	 * Tear down test.
	 */
	@AfterMethod(alwaysRun = true)
	public void quitDriver() {
		if (webDriver != null)
			webDriver.quit();
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