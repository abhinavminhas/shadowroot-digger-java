package shadowrootdigger.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import helper.TestBase;
import io.github.abhinavminhas.ShadowRootAssist;

public class ShadowRootDiggerShadowDOMHTMLTests extends TestBase {
	
	private String _shadowHostElement = "#shadow_host";
	private String _shadowRootEnclosedInput = "input[type='text']";
    private String _shadowRootEnclosedCheckbox = "input[type='checkbox']";
    private String _shadowRootEnclosedInputChooseFile = "input[type='file']";
    private String _notExistsShadowHostElement = "#non_host";
    private String _nestedShadowHostElement = "#shadow_host > #nested_shadow_host";
    private String _nestedShadowHostShadowContent = "div[id='nested_shadow_content']";
    private String _notExistsNestedShadowHostElement = "#shadow_host > #shadow_content";
    
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_getShadowRootElement_ShadowDOMHTML_ShadowRootElementExists() {
         WebDriver.navigate().to(getTestFilePath());
         WebElement shadowHost = ShadowRootAssist.getShadowRootElement(WebDriver, _shadowHostElement, 20 , 2000);
         Assert.assertNotNull(shadowHost);
         WebElement shadowInput = shadowHost.findElement(By.cssSelector(_shadowRootEnclosedInput));
         shadowInput.sendKeys("Input inside Shadow DOM");
         WebElement shadowCheckbox = shadowHost.findElement(By.cssSelector(_shadowRootEnclosedCheckbox));
         shadowCheckbox.click();
         WebElement shadowInputFile = shadowHost.findElement(By.cssSelector(_shadowRootEnclosedInputChooseFile));
         Assert.assertNotNull(shadowInputFile);
     }
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_getShadowRootElement_ShadowDOMHTML_ShadowRootElementDoesNotExists() {
		String expectedErrorMessage = "getShadowRootElement: Shadow root element for selector '#non_host' Not Found.";
        WebDriver.navigate().to(getTestFilePath());
        try {
            ShadowRootAssist.getShadowRootElement(WebDriver, _notExistsShadowHostElement, 20 , 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_getNestedShadowRootElement_ShadowDOMHTML_RootElementExists() {
		WebDriver.navigate().to(getTestFilePath());
        WebElement nestedShadowHost = ShadowRootAssist.getNestedShadowRootElement(WebDriver, _nestedShadowHostElement, 20 , 2000);
        WebElement nestedText = nestedShadowHost.findElement(By.cssSelector(_nestedShadowHostShadowContent));
        Assert.assertEquals("nested text", nestedText.getText());
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_getNestedShadowRootElement_ShadowDOMHTML_RootElementDoesNotExists () {
		String expectedErrorMessage = "getNestedShadowRootElement: Nested shadow root element for selector '#shadow_content' in DOM hierarchy '#shadow_host > #shadow_content' Not Found.";
        WebDriver.navigate().to(getTestFilePath());
        try {
        	ShadowRootAssist.getNestedShadowRootElement(WebDriver, _notExistsNestedShadowHostElement, 20 , 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_isShadowRootElementPresent_ShadowDOMHTML_ShadowRootExists() {
		WebDriver.navigate().to(getTestFilePath());
		Boolean exists = ShadowRootAssist.isShadowRootElementPresent(WebDriver, _shadowHostElement, false, 20, 2000);
		Assert.assertEquals(true, exists);
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_isShadowRootElementPresent_ShadowDOMHTML_ShadowRootNotExists() {
		String expectedErrorMessage = "isShadowRootElementPresent: Shadow root element for selector '#non_host' Not Found.";
		WebDriver.navigate().to(getTestFilePath());
        Boolean exists = ShadowRootAssist.isShadowRootElementPresent(WebDriver, _notExistsShadowHostElement, false, 20, 2000);
        Assert.assertEquals(false, exists);
        try {
        	ShadowRootAssist.isShadowRootElementPresent(WebDriver, _notExistsShadowHostElement, true, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex)  { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_isNestedShadowRootElementPresent_ShadowDOMHTML_NestedShadowRootExists() {
		WebDriver.navigate().to(getTestFilePath());
        Boolean exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, _nestedShadowHostElement, false, 20, 2000);
        Assert.assertEquals(true, exists);
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_isNestedShadowRootElementPresent_ShadowDOMHTML_NestedShadowRootNotExists() {
		String expectedErrorMessage = "isNestedShadowRootElementPresent: Nested shadow root element for selector '#shadow_content' in DOM hierarchy '#shadow_host > #shadow_content' Not Found.";
        WebDriver.navigate().to(getTestFilePath());
        Boolean exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, _notExistsNestedShadowHostElement, false, 10, 2000);
        Assert.assertEquals(false, exists);
        try  {
        	ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, _notExistsNestedShadowHostElement, true, 10, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
}