package shadowrootdigger.tests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import helper.TestBase;
import io.github.abhinavminhas.ShadowRootAssist;

@Test
public class ShadowRootDiggerChromeSettingsTests extends TestBase {

	private String tabRootElement = "body settings-ui > div#container settings-main#main > settings-basic-page.cr-centered-card-container > settings-privacy-page > settings-clear-browsing-data-dialog > cr-tabs[role=\"tablist\"]";
	private String clearBrowsingDataDialogRootElement = "settings-ui > settings-main#main > settings-basic-page.cr-centered-card-container > settings-privacy-page > settings-clear-browsing-data-dialog";
    private String settingsDropdownMenuRootElement = "settings-ui > settings-main#main > settings-basic-page.cr-centered-card-container > settings-privacy-page > settings-clear-browsing-data-dialog > settings-dropdown-menu#clearFromBasic";
	private String divTabIdentifier = "div.tab";
	private String selectTimeRangeIdentifier = "select#dropdownMenu";
    private String basicTabCheckboxesIdentifier = "settings-checkbox[id*=\"CheckboxBasic\"]";
    private String buttonClearDataIdentifier = "#clearBrowsingDataConfirm";
    private String notExistsNestedShadowRootElement = "settings-ui > settings-main.main";
	private String existsShadowRootElement = "settings-ui";
    private String notExistsShadowRootElement = "not-exists";
    
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_getShadowRootElement_ChromeSettings_ShadowRootElementExists() {
        webDriver.navigate().to("chrome://settings/clearBrowserData");
        WebElement clearBrowsingTab = ShadowRootAssist.getShadowRootElement(webDriver, existsShadowRootElement, 20 , 2000);
        Assert.assertNotNull(clearBrowsingTab);
    }
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_getShadowRootElement_ChromeSettings_ShadowRootElementDoesNotExists() {
		String expectedErrorMessage = "getShadowRootElement: Shadow root element for selector 'not-exists' Not Found.";
        webDriver.navigate().to("chrome://settings/clearBrowserData");
        try {
            ShadowRootAssist.getShadowRootElement(webDriver, notExistsShadowRootElement, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
    }
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_getNestedShadowRootElement_ChromeSettings_ClearChromeData()
    {
        webDriver.navigate().to("chrome://settings/clearBrowserData");
        WebElement clearBrowsingTab = ShadowRootAssist.getNestedShadowRootElement(webDriver, tabRootElement, 20 , 2000);
        int count = 0;
        do {
        	count++;
        	try {
        		WebElement requiredElement = clearBrowsingTab.findElements(By.cssSelector(divTabIdentifier)).iterator().next();
        		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10, 1000);
        		webDriverWait.until((ExpectedCondition<Boolean>) wd -> requiredElement.getText().equals("Basic") && requiredElement.isDisplayed() && requiredElement.isEnabled());
        		requiredElement.click();
        		break;
        	}
        	catch (TimeoutException ex) {
        		/* Retry */
        	}
        	catch (ElementNotInteractableException ex) {
        		/* Retry */
        	}
        } while (count <= 1);
        WebElement settingsDropdownMenu = ShadowRootAssist.getNestedShadowRootElement(webDriver, settingsDropdownMenuRootElement, 20 , 2000);
        WebElement timeRangeSelect = settingsDropdownMenu.findElement(By.cssSelector(selectTimeRangeIdentifier));
        new Select(timeRangeSelect).selectByVisibleText("Last hour");
        WebElement clearBrowsingDataDialog = ShadowRootAssist.getNestedShadowRootElement(webDriver, clearBrowsingDataDialogRootElement, 20 , 2000);
        List<WebElement> basicCheckboxes = clearBrowsingDataDialog.findElements(By.cssSelector(basicTabCheckboxesIdentifier));
        for (WebElement checkbox : basicCheckboxes) {
        	String isCheckboxChecked = checkbox.getAttribute("checked");
            if (isCheckboxChecked == null)
            	checkbox.click();
		}
        clearBrowsingDataDialog.findElement(By.cssSelector(buttonClearDataIdentifier)).click();
    }
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_getNestedShadowRootElement_ChromeSettings_RootElementDoesNotExists() {
		String expectedErrorMessage = "getNestedShadowRootElement: Nested shadow root element for selector 'settings-main.main' in DOM hierarchy 'settings-ui > settings-main.main' Not Found.";
		webDriver.navigate().to("chrome://settings/clearBrowserData");
		try {
			ShadowRootAssist.getNestedShadowRootElement(webDriver, notExistsNestedShadowRootElement, 20, 2000);
            Assert.fail("No Exception Thrown.");
		}
		catch (AssertionError ex) { throw ex; }
		catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isShadowRootElementPresent_ChromeSettings_ShadowRootExists() {
		webDriver.navigate().to("chrome://settings/clearBrowserData");
		Boolean exists = ShadowRootAssist.isShadowRootElementPresent(webDriver, existsShadowRootElement, false, 20, 2000);
		Assert.assertEquals(exists, true);
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isShadowRootElementPresent_ChromeSettings_ShadowRootNotExists() {
		String expectedErrorMessage = "isShadowRootElementPresent: Shadow root element for selector 'not-exists' Not Found.";
		webDriver.navigate().to("chrome://settings/clearBrowserData");
        Boolean exists = ShadowRootAssist.isShadowRootElementPresent(webDriver, notExistsShadowRootElement, false, 20, 2000);
        Assert.assertEquals(exists, false);
        try {
        	ShadowRootAssist.isShadowRootElementPresent(webDriver, notExistsShadowRootElement, true, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex)  { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isNestedShadowRootElementPresent_ChromeSettings_NestedShadowRootExists() {
		webDriver.navigate().to("chrome://settings/clearBrowserData");
        Boolean exists = ShadowRootAssist.isNestedShadowRootElementPresent(webDriver, tabRootElement, false, 20, 2000);
        Assert.assertEquals(exists, true);
        exists = ShadowRootAssist.isNestedShadowRootElementPresent(webDriver, clearBrowsingDataDialogRootElement, false, 20, 2000);
        Assert.assertEquals(exists, true);
        exists = ShadowRootAssist.isNestedShadowRootElementPresent(webDriver, tabRootElement, false, 20, 2000);
        Assert.assertEquals(exists, true);
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isNestedShadowRootElementPresent_ChromeSettings_NestedShadowRootNotExists () {
		String expectedErrorMessage = "isNestedShadowRootElementPresent: Nested shadow root element for selector 'settings-main.main' in DOM hierarchy 'settings-ui > settings-main.main' Not Found.";
        webDriver.navigate().to("chrome://settings/clearBrowserData");
        Boolean exists = ShadowRootAssist.isNestedShadowRootElementPresent(webDriver, notExistsNestedShadowRootElement, false, 20, 2000);
        Assert.assertEquals(exists, false);
        try {
        	ShadowRootAssist.isNestedShadowRootElementPresent(webDriver, notExistsNestedShadowRootElement, true, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
}