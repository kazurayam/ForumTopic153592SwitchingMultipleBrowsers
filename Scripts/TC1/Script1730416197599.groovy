import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import my.TestHelper 

// Sample URL used in the Selenium offical doc at https://www.selenium.dev/documentation/webdriver/interactions/windows/
String url = "https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html"

TestObject anchorOpenNewWindow = TestHelper.makeTestObject('Open new window', "//a[@id='a-link-that-opens-a-new-window']")

WebUI.openBrowser('')
WebDriver driver1 = DriverFactory.getWebDriver()
TestHelper.layoutWindow(driver1, 1024, 400, 20, 20)
WebUI.navigateToUrl(url)
WebUI.click(anchorOpenNewWindow)
WebUI.delay(1)
WebUI.switchToWindowIndex(1)
// make sure that we are looking at the 2nd tab, not the 1st tab
WebUI.verifyElementNotPresent(anchorOpenNewWindow, 10)

ChromeOptions options = new ChromeOptions()
options.setExperimentalOption("excludeSwitches", ["enable-automation"]);
WebDriver driver2 = new ChromeDriver(options)
TestHelper.layoutWindow(driver2, 1024, 400, 60, 440)
DriverFactory.changeWebDriver(driver2)
WebUI.navigateToUrl(url)
WebUI.click(anchorOpenNewWindow)
WebUI.delay(1)
WebUI.switchToWindowIndex(1)
// make sure that we are looking at the 2nd tab, not the 1st tab
WebUI.verifyElementNotPresent(anchorOpenNewWindow, 10)

driver1.quit()
driver2.quit()
