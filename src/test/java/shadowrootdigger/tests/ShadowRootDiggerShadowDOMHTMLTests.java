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
    
	@Test(description = SHADOW_DOM_HTML_TESTS)
	public void test_GetShadowRootElement_ShadowDOMHTML_ShadowRootElementExists() {
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
	public void test_GetShadowRootElement_ShadowDOMHTML_ShadowRootElementDoesNotExists() {
		String expectedErrorMessage = "getShadowRootElement: Shadow root element for selector '#non_host' Not Found.";
        WebDriver.navigate().to(getTestFilePath());
        try {
            ShadowRootAssist.getShadowRootElement(WebDriver, _notExistsShadowHostElement, 20 , 2000);
            Assert.fail("No Exception Thrown.");
        }
        catch (AssertionError ex) { throw ex; }
        catch (WebDriverException ex) { Assert.assertTrue(ex.getMessage().contains(expectedErrorMessage)); }
	}
}