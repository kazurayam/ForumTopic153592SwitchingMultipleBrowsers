import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import my.TestHelper

// Sample URL used in the Selenium offical doc at https://www.selenium.dev/documentation/webdriver/interactions/windows/
String url = "https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html"

TestObject anchorOpenNewTab = TestHelper.makeTestObject('Open new window', "//a[@id='a-link-that-opens-a-new-window']")
TestObject divSimple = TestHelper.makeTestObject('div simple', "/html/body/div[text()='Simple page with simple test.']")

/**
 * work on the 1st Chrome window
 */
// Let's work on the 1st Chrme Browser
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(url)
// click the anchor tag to open the 2nd tab
WebUI.click(anchorOpenNewTab)
WebUI.delay(1)
// we want to look at the 2nd tab
WebUI.switchToWindowIndex(1)
// slide the Browser windout out of view
WebDriver driver = DriverFactory.getWebDriver()
driver.manage().window().setPosition(new Point(-2000, 1000));
WebUI.delay(1)
// make sure that we are looking at the 2nd tab
WebUI.waitForElementVisible(divSimple, 5, FailureHandling.STOP_ON_FAILURE)
// make sure that we are NOT looking at the 1st tab
WebUI.verifyElementNotPresent(anchorOpenNewTab, 5, FailureHandling.STOP_ON_FAILURE)

/**
 * work on the 2nd Chrome window
 */
// open the 2nd Chrome window
ChromeOptions options = new ChromeOptions()
options.setExperimentalOption("excludeSwitches", ["enable-automation"]);
WebDriver driver2 = new ChromeDriver(options)
DriverFactory.changeWebDriver(driver2)
WebUI.maximizeWindow()
WebUI.navigateToUrl(url)
// click the anchor tag to open the 2nd tab
WebUI.click(anchorOpenNewTab)
WebUI.delay(1)
// we want to look at the 2nd tab
WebUI.switchToWindowIndex(1)
// slide the Browser windout out of view
driver2.manage().window().setPosition(new Point(-2000, 1000));
WebUI.delay(1)
// make sure that we are looking at the 2nd tab of the 2nd window
WebUI.waitForElementVisible(divSimple, 5, FailureHandling.STOP_ON_FAILURE)
// make sure that we are NOT looking at the 1st tab of the 2nd window
WebUI.verifyElementNotPresent(anchorOpenNewTab, 5, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()