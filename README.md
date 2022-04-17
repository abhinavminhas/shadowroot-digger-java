# shadowroot-digger-java
*DOM shadow root elements finder using Selenium solution in JAVA*. </br></br>
![shadowroot-digger (Build)](https://github.com/abhinavminhas/shadowroot-digger-java/actions/workflows/build.yml/badge.svg)
[![codecov](https://codecov.io/gh/abhinavminhas/shadowroot-digger-java/branch/main/graph/badge.svg?token=8LXZL9ZLZR)](https://codecov.io/gh/abhinavminhas/shadowroot-digger-java)
![maintainer](https://img.shields.io/badge/Creator/Maintainer-abhinavminhas-e65c00)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.abhinavminhas/ShadowRoot.Digger?label=Maven%20Central)](https://search.maven.org/search?q=a:ShadowRoot.Digger)
[![Maven Repository](https://img.shields.io/maven-central/v/io.github.abhinavminhas/ShadowRoot.Digger?label=Maven%20Repository)](https://mvnrepository.com/artifact/io.github.abhinavminhas/ShadowRoot.Digger)  

One of the important aspect of web components is encapsulation and [Shadow DOM API](https://developer.mozilla.org/en-US/docs/Web/Web_Components/Using_shadow_DOM) is a key part of this, allowing hidden DOM trees to be attached to elements in the regular DOM tree. This shadow DOM tree starts with a shadow root, underneath which any elements can be attached, in the same way as the normal DOM. The solution combines the power of [Document Query Selector API](https://developer.mozilla.org/en-US/docs/Web/API/Document/querySelector)  with [Selenium](https://www.selenium.dev/) to grab such shadow root DOM trees and interact with any elements encapsulated within it.

Selenium 4.x.x - Current supported package/solution version.  
Selenium 3 - Check support [here](https://github.com/abhinavminhas/shadowroot-digger-java/tree/Selenium-3-v1.x.x).  

## Download
The package is available and can be downloaded using [Maven Central Repository Search](https://search.maven.org/)/[MVNRepository](https://mvnrepository.com/) package manager.  
- Package Name (Maven Central Repository Search) - [ShadowRoot.Digger](https://search.maven.org/search?q=a:ShadowRoot.Digger).
- Package Name (MVNRepository) - [ShadowRoot.Digger](https://mvnrepository.com/artifact/io.github.abhinavminhas/ShadowRoot.Digger).

## Features
1. Returns shadow root or nested shadow root from DOM.
2. Checks if shadow root or nested shadow root is present or not in the DOM.  
   **NOTE:** *Supports Selenium 3, 4.x.x (Check Selenium Dependency Before Use)*  
   &emsp;&emsp;&nbsp;&nbsp;&nbsp;&nbsp;For Selenium 3 - Use versions [1.x.x](https://search.maven.org/artifact/io.github.abhinavminhas/ShadowRoot.Digger/1.0.0/jar).

## JAVA Supported Versions
The solution is built on Java 8.

## Usage Guidelines
1. Install the maven package [ShadowRoot.Digger (Maven Central)](https://search.maven.org/search?q=a:ShadowRoot.Digger)/[ShadowRoot.Digger (MVNRepository)](https://mvnrepository.com/artifact/io.github.abhinavminhas/ShadowRoot.Digger).  
2. Use below methods to get shadow root or nested shadow root.  
   Required parameters - webdriver instance, shadow root host selector identifier/s, wait time in seconds & polling interval in milliseconds.
    ```
    ShadowRootAssist.getShadowRootElement()
    ShadowRootAssist.getNestedShadowRootElement()
    ```
    The returned shadow root element from above methods can be used to find element/s encapsulated within it.  
    **NOTE:** *Use **[jQuery Selectors](https://www.w3schools.com/jquery/jquery_ref_selectors.asp)** or **[CSS Selectors](https://www.w3schools.com/cssref/css_selectors.asp)** for shadow root host identifications.*
3. Use below methods for checking if shadow root or nested shadow root exists or not.  
   Required parameters - webdriver instance, shadow root host selector identifier/s, flag to throw error if shadow root element not found, wait time in seconds & polling interval in milliseconds.
    ```
    ShadowRootAssist.isShadowRootElementPresent()
    ShadowRootAssist.isNestedShadowRootElementPresent()
    ```
    **NOTE:** *Use **[jQuery Selectors](https://www.w3schools.com/jquery/jquery_ref_selectors.asp)** or **[CSS Selectors](https://www.w3schools.com/cssref/css_selectors.asp)** for shadow root host identifications.*

 Check the solution tests for more information.  
**NOTE:** *Google Chrome & shadow DOM in Chrome settings along with [ShadowDOM.html](/src/test/resources/testfiles/ShadowDOM.html) have been used for testing the solution.*

## Verified Versions

   | Google Chrome | Chrome Driver | Microsoft Edge | Edge Driver |
   | ----------- | ----------- | ----------- | ----------- |
   | 100.0.4896.75 | 100.0.4896.60 | 100.0.1185.29 | 100.0.1185.29 |
   | 99.0.4844.74 | 99.0.4844.51 | 99.0.1150.46 | 99.0.1150.46 |
   | 98.0.4758.102 | 98.0.4758.102 | 98.0.1108.56 | 98.0.1108.56 |
   | 97.0.4692.71 | 97.0.4692.71 | 97.0.1072.69 | 97.0.1072.69 |
   | 96.0.4664.110 | 96.0.4664.45 | 96.0.1054.62 | 96.0.1054.62 |
   | 95.0.4638.69 | 95.0.4638.54 | 95.0.1020.53 | 95.0.1020.53 |
   | 94.0.4606.81 | 94.0.4606.61 | 94.0.992.23 | 94.0.992.23 |
   | 93.0.4577.82 | 93.0.4577.63 | 93.0.961.27 | 93.0.961.27 |
   | 92.0.4515.131 | 92.0.4515.107 | 92.0.902.45 | 92.0.902.45 |
