package com.aspiresys.codes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class Runnable {
	
	public static WebDriver driver;
	public static Workbook workbook;
	public static int startsteprownumber;
	public static int laststeprownumber=0;
	public static Properties properties=new Properties();
	
	@BeforeMethod
	public void beforeMethod() {
		
		try {
		properties.load(new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\com\\aspiresys\\resources\\Environment.txt"));
		switch (properties.get("Browsertype").toString()){
		case "ie":
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\com\\aspiresys\\resources\\iedriver.exe");
			driver=new InternetExplorerDriver();
		case "chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\com\\aspiresys\\resources\\chromedriver.exe");
			ChromeOptions chromeOptions=new ChromeOptions();
			List<String> list=Arrays.asList("start-maximized","disable-infobars");
			chromeOptions.addArguments(list);
			driver=new ChromeDriver(chromeOptions);
		case "firefox":
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\com\\aspiresys\\resources\\geckodriver.exe");
			driver=new FirefoxDriver();
		}		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void initiateTests() throws IOException {
		
		try {
			
			workbook=new XSSFWorkbook(new FileInputStream(new File("src\\main\\resources\\com\\aspiresys\\resources\\Github.xlsx")));
			Sheet sheet=workbook.getSheetAt(0);			
			startsteprownumber=laststeprownumber+1;			
			while(!(ExcelUtils.getCellData(sheet, startsteprownumber, 0).equalsIgnoreCase("End"))) {			
			laststeprownumber = startsteprownumber+1;			
			while(!(ExcelUtils.getCellData(sheet, laststeprownumber, 0).contains("TC")) && !(ExcelUtils.getCellData(sheet, laststeprownumber, 0).contains("End"))) {				
				laststeprownumber++;			
			}		
			laststeprownumber=laststeprownumber-1;			
			for(int i=startsteprownumber;i<=laststeprownumber;i++) {
			Class<?> c=Class.forName("com.aspiresys.codes.CustomFunctions");
			Method m=c.getDeclaredMethod(ExcelUtils.getCellData(sheet, i, 2),WebDriver.class,By.class,String.class);
			Object indicator=m.invoke(c.newInstance(), driver,CustomFunctions.returnElement(driver, sheet, i, 3),ExcelUtils.getCellData(sheet, i, 4));
			System.out.println(indicator);
			}
			startsteprownumber=laststeprownumber+1;
			
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	

	@AfterMethod
	public void afterMethod() {
		
		try {
			
			driver.quit();
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		
	}

}
