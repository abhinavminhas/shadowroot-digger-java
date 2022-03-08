# Changelog
All notable changes to this project documented here.

## [Released]

## [1.0.0](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/1.0.0/jar) - 2021-02-22
### Added
- Shadow root digger implementation.
  - ShadowRootAssist.getShadowRootElement()
  - ShadowRootAssist.getNestedShadowRootElement()
  - ShadowRootAssist.isShadowRootElementPresent()
  - ShadowRootAssist.isNestedShadowRootElementPresent()

### Notes
- This version supports change in shadow root return values for Selenium in Chromium browsers (Google Chrome and Microsoft Edge) v96 and is backward compatible.

## [2.0.0](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.0.0/jar) - 2021-##-##
### Changed
- **ShadowRootAssist** methods **getShadowRootElement()** & **getNestedShadowRootElement()** now return **'SearchContext'** instead of **'WebElement'** which can be used to search elements encapsulated within.
- Changed use of web driver wait *```WebDriverWait(WebDriver driver, long timeOutInSeconds, long sleepInMillis)```* to *```WebDriverWait(WebDriver driver, Duration timeout, Duration sleep)```* due to deprecation of former in the new Selenium version.

### Notes
- This version supports change in shadow root return values for Selenium in Chromium browsers (Google Chrome and Microsoft Edge) v96 and is backward compatible.