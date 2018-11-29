package com.aspiresys.codes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CustomFunctions {
	
	public static Boolean clickElement(WebDriver driver,By element,String value) {
		driver.findElement(element).click();
		return true;
	}
	public static Boolean navigateToURL(WebDriver driver,By element,String value) {
		driver.navigate().to(value);
		return true;
	}
	public static Boolean verifyURL(WebDriver driver,By element,String value) {
		Assert.assertEquals(driver.getCurrentUrl(), value);
		return true;
	}
	public static Boolean sendKeys(WebDriver driver,By element,String value) {
		driver.findElement(element).sendKeys(value);
		return true;
	}
}
