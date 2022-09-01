# Changelog
All notable changes to this project documented here.

## [Released]

## [2.1.6](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.1.6/jar) - 2022-09-01
### Changed
- Bumped Selenium version from '4.3.0' to '4.4.0'.

## [2.1.5](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.1.5/jar) - 2022-07-08
### Changed
- Bumped Selenium version from '4.2.2' to '4.3.0'.

## [2.1.4](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.1.4/jar) - 2022-07-07
### Changed
- Bumped Selenium version from '4.2.1' to '4.2.2'.

## [2.1.3](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.1.3/jar) - 2022-07-07
### Changed
- Bumped Selenium version from '4.2.0' to '4.2.1'.

## [2.1.2](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.1.2/jar) - 2022-07-06
### Changed
- Bumped Selenium version from '4.1.4' to '4.2.0'.

## [2.1.1](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.1.1/jar) - 2022-07-05
### Changed
- Bumped Selenium version from '4.1.3' to '4.1.4'.

## [2.1.0](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.1.0/jar) - 2022-06-28
### Added
- Addition of 'StaleElementReferenceException' handler.

## [2.0.4](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.0.4/jar) - 2022-04-18
### Changed
- Bumped Selenium version from '4.1.2' to '4.1.3'.
- Bumped WebDriverManager version from '5.1.0' to '5.1.1'.

## [2.0.3](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.0.3/jar) - 2022-03-16
### Changed
- Bumped Selenium version from '4.1.1' to '4.1.2'.

### Notes
- This version supports change in shadow root return values for Selenium in Chromium browsers (Google Chrome and Microsoft Edge) v96 and is backward compatible.

## [2.0.2](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.0.2/jar) - 2022-03-14
### Changed
- Bumped Selenium version from '4.1.0' to '4.1.1'.

### Notes
- This version supports change in shadow root return values for Selenium in Chromium browsers (Google Chrome and Microsoft Edge) v96 and is backward compatible.

## [2.0.1](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.0.1/jar) - 2022-03-12
### Changed
- Bumped Selenium version from '4.0.0' to '4.1.0'.

### Notes
- This version supports change in shadow root return values for Selenium in Chromium browsers (Google Chrome and Microsoft Edge) v96 and is backward compatible.

## [2.0.0](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/2.0.0/jar) - 2022-03-08
### Changed
- ShadowRootAssist methods *getShadowRootElement()* & *getNestedShadowRootElement()* now return '*SearchContext*' instead of '*WebElement*' which can be used to search elements encapsulated within.
- Changed use of web driver wait *```WebDriverWait(WebDriver driver, long timeOutInSeconds, long sleepInMillis)```* to *```WebDriverWait(WebDriver driver, Duration timeout, Duration sleep)```* due to deprecation of former in the new Selenium version.

### Notes
- This version supports change in shadow root return values for Selenium in Chromium browsers (Google Chrome and Microsoft Edge) v96 and is backward compatible.

## [1.0.0](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/1.0.0/jar) - 2022-02-22
### Added
- Shadow root digger implementation.
  - ShadowRootAssist.getShadowRootElement()
  - ShadowRootAssist.getNestedShadowRootElement()
  - ShadowRootAssist.isShadowRootElementPresent()
  - ShadowRootAssist.isNestedShadowRootElementPresent()

### Notes
- This version supports change in shadow root return values for Selenium in Chromium browsers (Google Chrome and Microsoft Edge) v96 and is backward compatible.