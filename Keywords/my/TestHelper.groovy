package my

import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

public class TestHelper {
	
	static TestObject makeTestObject(String id, String xpath) {
		TestObject tObj = new TestObject(id)
		tObj.addProperty('xpath', ConditionType.EQUALS, xpath)
		return tObj
	}
	
	static void layoutWindow(WebDriver driver, int windowWidth, int windowHeight, int positionX, int positionY) {
		driver.manage().window().setSize(new Dimension(windowWidth, windowHeight))
		driver.manage().window().setPosition(new Point(positionX, positionY))
	}
	
}
