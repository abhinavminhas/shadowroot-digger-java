package io.github.abhinavminhas;

import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

/** 
 * 'ShadowRootAssist' class to support digging of shadow roots in DOM.
 * 
 * @author 	Abhinav Minhas
 * @version 1.0.0
 * @since 	01-01-2022
 */
public class ShadowRootAssist {
	
	/** 
	 * Returns shadow root element for provided selector.
	 * 
	 * @param webDriver 					Selenium web driver instance.
	 * @param shadowRootSelector 			Shadow root element selectors (probably jQuery or CssSelectors).
	 * @param timeInSeconds 				Wait time in seconds.
	 * @param pollingIntervalInMilliseconds	Polling interval time in milliseconds.
	 * @return								Shadow root web element.
	 * @exception WebDriverException		Throws - 'WebDriverException' in case any shadow root element is not found.
	 */
	public static WebElement getShadowRootElement(WebDriver webDriver, String shadowRootSelector, int timeInSeconds, int pollingIntervalInMilliseconds) {
		WebElement requiredShadowRoot = null;
		String shadowRootQuerySelector = "return document.querySelector('%s').shadowRoot";
 		String shadowRootElement = String.format(shadowRootQuerySelector, shadowRootSelector);
		try {
			WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeInSeconds, pollingIntervalInMilliseconds);
			webDriverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor)webDriver).executeScript(shadowRootElement) != null);
			Object returnedObject = ((JavascriptExecutor)webDriver).executeScript(shadowRootElement);
			if (returnedObject instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>)returnedObject;
				RemoteWebElement remoteWebElement = new RemoteWebElement();
				remoteWebElement.setParent((RemoteWebDriver)webDriver);
				remoteWebElement.setId((String)map.values().iterator().next());
				requiredShadowRoot = remoteWebElement;
			} else
				requiredShadowRoot = (WebElement)returnedObject;
		} catch(WebDriverException ex) {
			throw new WebDriverException(String.format("%s: Shadow root element for selector '%s' Not Found.", new Object(){}.getClass().getEnclosingMethod().getName(), shadowRootSelector));
		}
		return requiredShadowRoot;
	}
	
	/**
	 * Returns nested shadow root element from DOM hierarchy of shadow root elements with selectors separated by '>'.
	 * 
	 * @param webDriver						Selenium web driver instance.
	 * @param shadowRootSelectors			List of shadow root element selectors (probably jQuery or CssSelectors) separated by '>'.
	 * @param timeInSeconds					Wait time in seconds.
	 * @param pollingIntervalInMilliseconds Polling interval time in milliseconds.
	 * @return								Nested shadow root web element.
	 * @exception WebDriverException		Throws - 'WebDriverException' in case any shadow root element is not found in the nested hierarchy.
	 */
	public static WebElement getNestedShadowRootElement(WebDriver webDriver, String shadowRootSelectors, int timeInSeconds, int pollingIntervalInMilliseconds) {
		WebElement requiredShadowRoot = null;
        String[] listShadowRootSelectors = shadowRootSelectors.split(">");
        for (int i = 0; i < listShadowRootSelectors.length ; i++) { listShadowRootSelectors[i] = listShadowRootSelectors[i].trim(); }
        String shadowRootQuerySelector = ".querySelector('%s').shadowRoot";
        String shadowRootQueryString = "";
        for (String shadowRoot : listShadowRootSelectors) {
        	String documentReturn = "return document%s;";
        	String tempQueryString = String.format(shadowRootQuerySelector, shadowRoot);
            shadowRootQueryString += tempQueryString;
            String shadowRootElement = String.format(documentReturn, shadowRootQueryString);
            try {
            	WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeInSeconds, pollingIntervalInMilliseconds);
                webDriverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor)webDriver).executeScript(shadowRootElement) != null);
    			Object returnedObject = ((JavascriptExecutor)webDriver).executeScript(shadowRootElement);
    			if (returnedObject instanceof Map) {
    				@SuppressWarnings("unchecked")
    				Map<String, Object> map = (Map<String, Object>)returnedObject;
    				RemoteWebElement remoteWebElement = new RemoteWebElement();
    				remoteWebElement.setParent((RemoteWebDriver)webDriver);
    				remoteWebElement.setId((String)map.values().iterator().next());
    				requiredShadowRoot = remoteWebElement;
    			} else
    				requiredShadowRoot = (WebElement)returnedObject;
            } catch (WebDriverException ex) {
            	throw new WebDriverException(String.format("%s: Nested shadow root element for selector '%s' in DOM hierarchy '%s' Not Found.", new Object(){}.getClass().getEnclosingMethod().getName(), shadowRoot, shadowRootSelectors));
            }
		}
        return requiredShadowRoot;
	}
	
	/**
	 * Checks if the shadow root element exists or not.
	 * 
	 * @param webDriver						Selenium web driver instance.
	 * @param shadowRootSelector			Shadow root element selectors (probably jQuery or CssSelectors).
	 * @param throwError					Boolean value to throw error if shadow root element hierarchy does not exists.
	 * @param timeInSeconds					Wait time in seconds.
	 * @param pollingIntervalInMilliseconds Polling interval time in milliseconds.
	 * @return								Boolean value shadow root element exists or not.
	 * @exception WebDriverException		Throws - 'WebDriverException' in case any shadow root element is not found when @param 'throwError' is set to 'true'.
	 */
	public static Boolean isShadowRootElementPresent(WebDriver webDriver, String shadowRootSelector, Boolean throwError, int timeInSeconds, int pollingIntervalInMilliseconds) {
		Boolean isPresent = false;
		String shadowRootQuerySelector = "return document.querySelector('%s').shadowRoot";
		String shadowRootElement = String.format(shadowRootQuerySelector, shadowRootSelector);
        try {
        	WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeInSeconds, pollingIntervalInMilliseconds);
			webDriverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor)webDriver).executeScript(shadowRootElement) != null); 
			Object returnedObject = ((JavascriptExecutor)webDriver).executeScript(shadowRootElement);
			if (returnedObject instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>)returnedObject;
				RemoteWebElement remoteWebElement = new RemoteWebElement();
				remoteWebElement.setParent((RemoteWebDriver)webDriver);
				remoteWebElement.setId((String)map.values().iterator().next());
				Object requiredShadowRoot = remoteWebElement;
				webDriverWait.until(item -> requiredShadowRoot instanceof WebElement);
				isPresent = true;
			} else {
				webDriverWait.until(item -> returnedObject instanceof WebElement);
				isPresent = true;
			}
        } catch (WebDriverException ex) {
        	if (throwError)
                throw new WebDriverException(String.format("%s: Shadow root element for selector '%s' Not Found.", new Object(){}.getClass().getEnclosingMethod().getName(), shadowRootSelector));
            else
                isPresent = false;
        }
        return isPresent;
	}
	
	/**
	 * Checks if the nested shadow root element hierarchy exists or not.
	 * 
	 * @param webDriver						Selenium web driver instance.
	 * @param shadowRootSelectors			List of shadow root element selectors (probably jQuery or CssSelectors) separated by '>'.
	 * @param throwError					Boolean value to throw error if nested shadow root element hierarchy does not exists.
	 * @param timeInSeconds					Wait time in seconds.
	 * @param pollingIntervalInMilliseconds Polling interval time in milliseconds.
	 * @return								Boolean value if nested shadow root element hierarchy exists or not.
	 * @exception WebDriverException		Throws - 'WebDriverException' in case any shadow root element is not found in the nested hierarchy when @param 'throwError' is set to 'true'.
	 */
	public static Boolean isNestedShadowRootElementPresent(WebDriver webDriver, String shadowRootSelectors, Boolean throwError, int timeInSeconds, int pollingIntervalInMilliseconds) {
		Boolean isPresent = false;
        String[] listShadowRootSelectors = shadowRootSelectors.split(">");
        for (int i = 0; i < listShadowRootSelectors.length ; i++) { listShadowRootSelectors[i] = listShadowRootSelectors[i].trim(); }
        String shadowRootQuerySelector = ".querySelector('%s').shadowRoot";
        String shadowRootQueryString = "";
        for (String shadowRoot : listShadowRootSelectors) {
        	String documentReturn = "return document%s;";
        	String tempQueryString = String.format(shadowRootQuerySelector, shadowRoot);
            shadowRootQueryString += tempQueryString;
            String shadowRootElement = String.format(documentReturn, shadowRootQueryString);
            try {
            	WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeInSeconds, pollingIntervalInMilliseconds);
                webDriverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor)webDriver).executeScript(shadowRootElement) != null);
    			Object returnedObject = ((JavascriptExecutor)webDriver).executeScript(shadowRootElement);
    			if (returnedObject instanceof Map) {
    				@SuppressWarnings("unchecked")
    				Map<String, Object> map = (Map<String, Object>)returnedObject;
    				RemoteWebElement remoteWebElement = new RemoteWebElement();
    				remoteWebElement.setParent((RemoteWebDriver)webDriver);
    				remoteWebElement.setId((String)map.values().iterator().next());
    				Object requiredShadowRoot = remoteWebElement;
    				webDriverWait.until(item -> requiredShadowRoot instanceof WebElement);
    				isPresent = true;
    			} else {
    				webDriverWait.until(item -> returnedObject instanceof WebElement);
    				isPresent = true;
    			}
            } catch (WebDriverException ex) {
            	if (throwError)
            		throw new WebDriverException(String.format("%s: Nested shadow root element for selector '%s' in DOM hierarchy '%s' Not Found.", new Object(){}.getClass().getEnclosingMethod().getName(), shadowRoot, shadowRootSelectors));
            	else
                    isPresent = false;
            }
		}
        return isPresent;
	}
	
}