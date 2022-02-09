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
	public void test_GetShadowRootElement_ChromeSettings_ShadowRootElementExists() {
        WebDriver.navigate().to("chrome://settings/clearBrowserData");
        WebElement clearBrowsingTab = ShadowRootAssist.getShadowRootElement(WebDriver, _existsShadowRootElement, 20 , 2000);
        Assert.assertNotNull(clearBrowsingTab);
    }
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_GetShadowRootElement_ChromeSettings_ShadowRootElementDoesNotExists() {
		String expectedErrorMessage = "getShadowRootElement: Shadow root element for selector 'not-exists' Not Found.";
        WebDriver.navigate().to("chrome://settings/clearBrowserData");
        try {
            ShadowRootAssist.getShadowRootElement(WebDriver, _notExistsShadowRootElement, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
    }
}