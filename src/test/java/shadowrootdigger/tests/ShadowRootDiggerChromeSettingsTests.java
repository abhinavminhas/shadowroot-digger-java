package shadowrootdigger.tests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import helper.TestBase;
import io.github.abhinavminhas.ShadowRootAssist;

public class ShadowRootDiggerChromeSettingsTests extends TestBase {

	private String _tabRootElement = "body settings-ui > div#container settings-main#main > settings-basic-page.cr-centered-card-container > settings-privacy-page > settings-clear-browsing-data-dialog > cr-tabs[role=\"tablist\"]";
	private String _clearBrowsingDataDialogRootElement = "settings-ui > settings-main#main > settings-basic-page.cr-centered-card-container > settings-privacy-page > settings-clear-browsing-data-dialog";
    private String _settingsDropdownMenuRootElement = "settings-ui > settings-main#main > settings-basic-page.cr-centered-card-container > settings-privacy-page > settings-clear-browsing-data-dialog > settings-dropdown-menu#clearFromBasic";
	private String _divTabIdentifier = "div.tab";
	private String _selectTimeRangeIdentifier = "select#dropdownMenu";
    private String _basicTabCheckboxesIdentifier = "settings-checkbox[id*=\"CheckboxBasic\"]";
    private String _buttonClearDataIdentifier = "#clearBrowsingDataConfirm";
    private String _notExistsNestedShadowRootElement = "settings-ui > settings-main.main";
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
	public void test_getNestedShadowRootElement_ChromeSettings_ClearChromeData()
    {
        WebDriver.navigate().to("chrome://settings/clearBrowserData");
        WebElement clearBrowsingTab = ShadowRootAssist.getNestedShadowRootElement(WebDriver, _tabRootElement, 20 , 2000);
        new WebDriverWait(WebDriver, 20, 1000)
        .until(ExpectedConditions.elementToBeClickable(clearBrowsingTab.findElements(By.cssSelector(_divTabIdentifier)).iterator().next()));
        clearBrowsingTab.findElements(By.cssSelector(_divTabIdentifier)).iterator().next().click();
        WebElement settingsDropdownMenu = ShadowRootAssist.getNestedShadowRootElement(WebDriver, _settingsDropdownMenuRootElement, 20 , 2000);
        WebElement timeRangeSelect = settingsDropdownMenu.findElement(By.cssSelector(_selectTimeRangeIdentifier));
        new Select(timeRangeSelect).selectByVisibleText("Last hour");
        WebElement clearBrowsingDataDialog = ShadowRootAssist.getNestedShadowRootElement(WebDriver, _clearBrowsingDataDialogRootElement, 20 , 2000);
        List<WebElement> basicCheckboxes = clearBrowsingDataDialog.findElements(By.cssSelector(_basicTabCheckboxesIdentifier));
        for (WebElement checkbox : basicCheckboxes) {
        	String isCheckboxChecked = checkbox.getAttribute("checked");
            if (isCheckboxChecked == null)
            	checkbox.click();
		}
        clearBrowsingDataDialog.findElement(By.cssSelector(_buttonClearDataIdentifier)).click();
    }
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isShadowRootElementPresent_ChromeSettings_ShadowRootExists() {
		WebDriver.navigate().to("chrome://settings/clearBrowserData");
		Boolean exists = ShadowRootAssist.isShadowRootElementPresent(WebDriver, _existsShadowRootElement, false, 20, 2000);
		Assert.assertEquals(true, exists);
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_getNestedShadowRootElement_ChromeSettings_RootElementDoesNotExists() {
		String expectedErrorMessage = "getNestedShadowRootElement: Nested shadow root element for selector 'settings-main.main' in DOM hierarchy 'settings-ui > settings-main.main' Not Found.";
		WebDriver.navigate().to("chrome://settings/clearBrowserData");
		try {
			ShadowRootAssist.getNestedShadowRootElement(WebDriver, _notExistsNestedShadowRootElement, 20, 2000);
            Assert.fail("No Exception Thrown.");
		}
		catch (AssertionError ex) { throw ex; }
		catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
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
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isNestedShadowRootElementPresent_ChromeSettings_NestedShadowRootExists() {
		WebDriver.navigate().to("chrome://settings/clearBrowserData");
        Boolean exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, _tabRootElement, false, 20, 2000);
        Assert.assertEquals(true, exists);
        exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, _clearBrowsingDataDialogRootElement, false, 20, 2000);
        Assert.assertEquals(true, exists);
        exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, _tabRootElement, false, 20, 2000);
        Assert.assertEquals(true, exists);
	}
	
	@Test(description = CHROME_SETTINGS_TESTS)
	public void test_isNestedShadowRootElementPresent_ChromeSettings_NestedShadowRootNotExists () {
		String expectedErrorMessage = "isNestedShadowRootElementPresent: Nested shadow root element for selector 'settings-main.main' in DOM hierarchy 'settings-ui > settings-main.main' Not Found.";
        WebDriver.navigate().to("chrome://settings/clearBrowserData");
        Boolean exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, _notExistsNestedShadowRootElement, false, 20, 2000);
        Assert.assertEquals(false, exists);
        try {
        	ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, _notExistsNestedShadowRootElement, true, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
}