package com.aspiresys.codes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Runnable {
	
	public static WebDriver driver;
	public static Workbook workbook;
	public static int startsteprownumber;
	public static int laststeprownumber=0;
	
	@BeforeMethod
	public void beforeMethod() {
		
		try {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver.exe");
		
		ChromeOptions chromeOptions=new ChromeOptions();
		
		List<String> list=Arrays.asList("start-maximized","disable-infobars");
 		
		chromeOptions.addArguments(list);
		
		driver=new ChromeDriver(chromeOptions);
		
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void initiateTests() throws IOException {
		
		try {
			
			workbook=new XSSFWorkbook(new FileInputStream(new File("src\\main\\resources\\com\\aspiresys\\resources\\Github.xlsx")));
			
			Sheet sheet=workbook.getSheetAt(0);
			
			startsteprownumber=laststeprownumber+1;
			
			while(!(sheet.getRow(startsteprownumber).getCell(0).getStringCellValue().equalsIgnoreCase("End"))) {
			
			laststeprownumber = startsteprownumber+1;
			
			while(!(sheet.getRow(laststeprownumber).getCell(0).getStringCellValue().contains("TC")) && !(sheet.getRow(laststeprownumber).getCell(0).getStringCellValue().contains("End"))) {
				
				laststeprownumber++;
				
			}
			
			laststeprownumber=laststeprownumber-1;
					
			System.out.println(sheet.getRow(startsteprownumber).getCell(0).getStringCellValue());
			
			startsteprownumber=laststeprownumber+1;
			
			}
			
		} catch (Exception e) {
			
			e.getMessage();
			
		}
		
		/*driver.navigate().to("https://github.com/");
		
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://github.com/login");
		
		driver.findElement(By.xpath("//*[@id='login_field']")).sendKeys("rajkumar");
		
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("rajkumar");
		
		driver.findElement(By.xpath("//*[@value='Sign in']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='container']")).getText().toString(), "Incorrect username or password.");
*/
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
