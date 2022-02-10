package shadowrootdigger.tests;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import helper.TestBase;
import io.github.abhinavminhas.ShadowRootAssist;

public class ShadowRootDiggerChromeSettingsTests extends TestBase {

    private String _existsShadowRootElement = "settings-ui";
    private String _notExistsShadowRootElement = "not-exists";
    
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_getShadowRootElement_ChromeSettings_ShadowRootElementExists() {
        WebDriver.navigate().to("chrome://settings/clearBrowserData");
        WebElement clearBrowsingTab = ShadowRootAssist.getShadowRootElement(WebDriver, _existsShadowRootElement, 20 , 2000);
        Assert.assertNotNull(clearBrowsingTab);
    }
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_getShadowRootElement_ChromeSettings_ShadowRootElementDoesNotExists() {
		String expectedErrorMessage = "getShadowRootElement: Shadow root element for selector 'not-exists' Not Found.";
        WebDriver.navigate().to("chrome://settings/clearBrowserData");
        try {
            ShadowRootAssist.getShadowRootElement(WebDriver, _notExistsShadowRootElement, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
    }
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isShadowRootElementPresent_ChromeSettings_ShadowRootExists() {
		WebDriver.navigate().to("chrome://settings/clearBrowserData");
		Boolean exists = ShadowRootAssist.isShadowRootElementPresent(WebDriver, _existsShadowRootElement, false, 20, 2000);
		Assert.assertEquals(true, exists);
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isShadowRootElementPresent_ChromeSettings_ShadowRootNotExists() {
		String expectedErrorMessage = "isShadowRootElementPresent: Shadow root element for selector 'not-exists' Not Found.";
		WebDriver.navigate().to("chrome://settings/clearBrowserData");
        Boolean exists = ShadowRootAssist.isShadowRootElementPresent(WebDriver, _notExistsShadowRootElement, false, 20, 2000);
        Assert.assertEquals(false, exists);
        try {
        	ShadowRootAssist.isShadowRootElementPresent(WebDriver, _notExistsShadowRootElement, true, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex)  { throw ex; }
        catch (WebDriverException ex) { System.out.println(ex.getMessage()); Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
}