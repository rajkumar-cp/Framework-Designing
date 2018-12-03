package com.aspiresys.codes;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CustomFunctions {
	public static Properties properties=new Properties();
	
	public static By returnElement(WebDriver driver,Sheet sheet,int rowno,int columnno) {
		try {
		String elem=ExcelUtils.getCellData(Runnable.workbook,0, rowno, columnno);
		properties.load(new FileReader("src\\main\\resources\\com\\aspiresys\\resources\\Github Object Repository.txt"));
		if(elem!="") {
		String[] s=properties.getProperty(elem).split("\\|");
		switch(s[0]) {
		case "xpath":
			return By.xpath(s[1]);
		case "id":
			return By.id(s[1]);
		case "linktext":
			return By.linkText(s[1]);
		case "partiallinktext":
			return By.partialLinkText(s[1]);
		case "cssselector":
			return By.cssSelector(s[1]);
		case "name":
			return By.name(s[1]);
		case "classname":
			return By.className(s[1]);
		case "tagname":
			return By.tagName(s[1]);
		default:
			return null;
		}}
		else return null;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static Boolean clickElement(WebDriver driver,By element,String value) {
		try {
		driver.findElement(element).click();
		takeScreenshot(driver, element, value);
		return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public static Boolean navigateToURL(WebDriver driver,By element,String value) {
		try {
		driver.navigate().to(value);
		takeScreenshot(driver, element, value);
		return true;
	}catch (Exception e) {
		System.out.println(e.getMessage());
		return false;
	}
	}
	public static Boolean verifyURL(WebDriver driver,By element,String value) {
		try {
		Assert.assertEquals(driver.getCurrentUrl(), value);
		takeScreenshot(driver, element, value);
		return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public static Boolean sendKeys(WebDriver driver,By element,String value) {
		try {
		driver.findElement(element).sendKeys(value);
		takeScreenshot(driver, element, value);
		return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public static void takeScreenshot(WebDriver driver,By element,String value) {
		try {
			if(element!=null) {
			 if(driver.findElements(element).size()!=0) {
				((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", driver.findElement(element));
				TakesScreenshot scrShot =((TakesScreenshot)driver);
		        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		        File DestFile=new File(Runnable.outputDirectory+"//Step"+Runnable.currentStep+".png");
		        FileUtils.copyFile(SrcFile, DestFile);
			}}
			else {
				TakesScreenshot scrShot =((TakesScreenshot)driver);
		        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		        File DestFile=new File(Runnable.outputDirectory+"//Step"+Runnable.currentStep+".png");
		        FileUtils.copyFile(SrcFile, DestFile);
			}
			}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}
}
