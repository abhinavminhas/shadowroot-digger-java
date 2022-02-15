package shadowrootdigger.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import helper.TestBase;
import io.github.abhinavminhas.ShadowRootAssist;

@Test
public class ShadowRootDiggerShadowDOMHTMLTests extends TestBase {
	
	private String shadowHostElement = "#shadow_host";
	private String shadowRootEnclosedInput = "input[type='text']";
    private String shadowRootEnclosedCheckbox = "input[type='checkbox']";
    private String shadowRootEnclosedInputChooseFile = "input[type='file']";
    private String notExistsShadowHostElement = "#non_host";
    private String nestedShadowHostElement = "#shadow_host > #nested_shadow_host";
    private String nestedShadowHostShadowContent = "div[id='nested_shadow_content']";
    private String notExistsNestedShadowHostElement = "#shadow_host > #shadow_content";
    
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_getShadowRootElement_ShadowDOMHTML_ShadowRootElementExists() {
         WebDriver.navigate().to(getTestFilePath());
         WebElement shadowHost = ShadowRootAssist.getShadowRootElement(WebDriver, shadowHostElement, 20 , 2000);
         Assert.assertNotNull(shadowHost);
         WebElement shadowInput = shadowHost.findElement(By.cssSelector(shadowRootEnclosedInput));
         shadowInput.sendKeys("Input inside Shadow DOM");
         WebElement shadowCheckbox = shadowHost.findElement(By.cssSelector(shadowRootEnclosedCheckbox));
         shadowCheckbox.click();
         WebElement shadowInputFile = shadowHost.findElement(By.cssSelector(shadowRootEnclosedInputChooseFile));
         Assert.assertNotNull(shadowInputFile);
     }
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_getShadowRootElement_ShadowDOMHTML_ShadowRootElementDoesNotExists() {
		String expectedErrorMessage = "getShadowRootElement: Shadow root element for selector '#non_host' Not Found.";
        WebDriver.navigate().to(getTestFilePath());
        try {
            ShadowRootAssist.getShadowRootElement(WebDriver, notExistsShadowHostElement, 20 , 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_getNestedShadowRootElement_ShadowDOMHTML_RootElementExists() {
		WebDriver.navigate().to(getTestFilePath());
        WebElement nestedShadowHost = ShadowRootAssist.getNestedShadowRootElement(WebDriver, nestedShadowHostElement, 20 , 2000);
        WebElement nestedText = nestedShadowHost.findElement(By.cssSelector(nestedShadowHostShadowContent));
        Assert.assertEquals(nestedText.getText(), "nested text");
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_getNestedShadowRootElement_ShadowDOMHTML_RootElementDoesNotExists () {
		String expectedErrorMessage = "getNestedShadowRootElement: Nested shadow root element for selector '#shadow_content' in DOM hierarchy '#shadow_host > #shadow_content' Not Found.";
        WebDriver.navigate().to(getTestFilePath());
        try {
        	ShadowRootAssist.getNestedShadowRootElement(WebDriver, notExistsNestedShadowHostElement, 20 , 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_isShadowRootElementPresent_ShadowDOMHTML_ShadowRootExists() {
		WebDriver.navigate().to(getTestFilePath());
		Boolean exists = ShadowRootAssist.isShadowRootElementPresent(WebDriver, shadowHostElement, false, 20, 2000);
		Assert.assertEquals(exists, true);
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_isShadowRootElementPresent_ShadowDOMHTML_ShadowRootNotExists() {
		String expectedErrorMessage = "isShadowRootElementPresent: Shadow root element for selector '#non_host' Not Found.";
		WebDriver.navigate().to(getTestFilePath());
        Boolean exists = ShadowRootAssist.isShadowRootElementPresent(WebDriver, notExistsShadowHostElement, false, 20, 2000);
        Assert.assertEquals(exists, false);
        try {
        	ShadowRootAssist.isShadowRootElementPresent(WebDriver, notExistsShadowHostElement, true, 20, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex)  { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_isNestedShadowRootElementPresent_ShadowDOMHTML_NestedShadowRootExists() {
		WebDriver.navigate().to(getTestFilePath());
        Boolean exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, nestedShadowHostElement, false, 20, 2000);
        Assert.assertEquals(exists, true);
	}
	
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_isNestedShadowRootElementPresent_ShadowDOMHTML_NestedShadowRootNotExists() {
		String expectedErrorMessage = "isNestedShadowRootElementPresent: Nested shadow root element for selector '#shadow_content' in DOM hierarchy '#shadow_host > #shadow_content' Not Found.";
        WebDriver.navigate().to(getTestFilePath());
        Boolean exists = ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, notExistsNestedShadowHostElement, false, 10, 2000);
        Assert.assertEquals(exists, false);
        try  {
        	ShadowRootAssist.isNestedShadowRootElementPresent(WebDriver, notExistsNestedShadowHostElement, true, 10, 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
}