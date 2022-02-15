package shadowrootdigger.tests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
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
        WebDriver.navigate().to("chrome://settings/clearBrowserData");
        WebElement clearBrowsingTab = ShadowRootAssist.getShadowRootElement(WebDriver, existsShadowRootElement, 20 , 2000);
        Assert.assertNotNull(clearBrowsingTab);
    }
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_getShadowRootElement_ChromeSettings_ShadowRootElementDoesNotExists() {
		String expectedErrorMessage = "getShadowRootElement: Shadow root element for selector 'not-exists' Not Found.";
        WebDriver.navigate().to("chrome://settings/clearBrowserData");
        try {
            ShadowRootAssist.getShadowRootElement(WebDriver, notExistsShadowRootElement, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
    }
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_getNestedShadowRootElement_ChromeSettings_ClearChromeData()
    {
        WebDriver.navigate().to("chrome://settings/clearBrowserData");
        WebElement clearBrowsingTab = ShadowRootAssist.getNestedShadowRootElement(WebDriver, tabRootElement, 20 , 2000);
        int count = 0;
        do {
        	count++;
        	try {
        		WebElement requiredElement = clearBrowsingTab.findElements(By.cssSelector(divTabIdentifier)).iterator().next();
        		WebDriverWait webDriverWait = new WebDriverWait(WebDriver, 10, 1000);
        		webDriverWait.until((ExpectedCondition<Boolean>) wd -> requiredElement.getText().equals("Basic") && requiredElement.isDisplayed() && requiredElement.isEnabled());
        		requiredElement.click();
        		break;
        	}
        	catch (ElementNotInteractableException ex) {
        		/* Retry */
        	}
        } while (count <= 1);
        WebElement settingsDropdownMenu = ShadowRootAssist.getNestedShadowRootElement(WebDriver, settingsDropdownMenuRootElement, 20 , 2000);
        WebElement timeRangeSelect = settingsDropdownMenu.findElement(By.cssSelector(selectTimeRangeIdentifier));
        new Select(timeRangeSelect).selectByVisibleText("Last hour");
        WebElement clearBrowsingDataDialog = ShadowRootAssist.getNestedShadowRootElement(WebDriver, clearBrowsingDataDialogRootElement, 20 , 2000);
        List<WebElement> basicCheckboxes = clearBrowsingDataDialog.findElements(By.cssSelector(basicTabCheckboxesIdentifier));
        for (WebElement checkbox : basicCheckboxes) {
        	String isCheckboxChecked = checkbox.getAttribute("checked");
            if (isCheckboxChecked == null)
            	checkbox.click();
		}
        clearBrowsingDataDialog.findElement(By.cssSelector(buttonClearDataIdentifier)).click();
    }
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isShadowRootElementPresent_ChromeSettings_ShadowRootExists() {
		WebDriver.navigate().to("chrome://settings/clearBrowserData");
		Boolean exists = ShadowRootAssist.isShadowRootElementPresent(WebDriver, existsShadowRootElement, false, 20, 2000);
		Assert.assertEquals(exists, true);
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_getNestedShadowRootElement_ChromeSettings_RootElementDoesNotExists() {
		String expectedErrorMessage = "getNestedShadowRootElement: Nested shadow root element for selector 'settings-main.main' in DOM hierarchy 'settings-ui > settings-main.main' Not Found.";
		WebDriver.navigate().to("chrome://settings/clearBrowserData");
		try {
			ShadowRootAssist.getNestedShadowRootElement(WebDriver, notExistsNestedShadowRootElement, 20, 2000);
            Assert.fail("No Exception Thrown.");
		}
		catch (AssertionError ex) { throw ex; }
		catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isShadowRootElementPresent_ChromeSettings_ShadowRootNotExists() {
		String expectedErrorMessage = "isShadowRootElementPresent: Shadow root element for selector 'not-exists' Not Found.";
		WebDriver.navigate().to("chrome://settings/clearBrowserData");
        Boolean exists = ShadowRootAssist.isShadowRootElementPresent(WebDriver, notExistsShadowRootElement, false, 20, 2000);
        Assert.assertEquals(exists, false);
        try {
        	ShadowRootAssist.isShadowRootElementPresent(WebDriver, notExistsShadowRootElement, true, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex)  { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isNestedShadowRootElementPresent_ChromeSettings_NestedShadowRootExists() {
		WebDriver.navigate().to("chrome://settings/clearBrowserData");
        Boolean exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, tabRootElement, false, 20, 2000);
        Assert.assertEquals(exists, true);
        exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, clearBrowsingDataDialogRootElement, false, 20, 2000);
        Assert.assertEquals(exists, true);
        exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, tabRootElement, false, 20, 2000);
        Assert.assertEquals(exists, true);
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isNestedShadowRootElementPresent_ChromeSettings_NestedShadowRootNotExists () {
		String expectedErrorMessage = "isNestedShadowRootElementPresent: Nested shadow root element for selector 'settings-main.main' in DOM hierarchy 'settings-ui > settings-main.main' Not Found.";
        WebDriver.navigate().to("chrome://settings/clearBrowserData");
        Boolean exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, notExistsNestedShadowRootElement, false, 20, 2000);
        Assert.assertEquals(exists, false);
        try {
        	ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, notExistsNestedShadowRootElement, true, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
}