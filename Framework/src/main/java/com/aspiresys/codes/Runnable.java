package com.aspiresys.codes;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;


public class Runnable {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver.exe");
		
		ChromeOptions chromeOptions=new ChromeOptions();
		
		List<String> list=Arrays.asList("start-maximized","disable-infobars");
 		
		chromeOptions.addArguments(list);
		
		WebDriver driver=new ChromeDriver(chromeOptions);
		
		driver.navigate().to("https://github.com/");
		
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://github.com/login");
		
		driver.findElement(By.xpath("//*[@id='login_field']")).sendKeys("rajkumar");
		
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("rajkumar");
		
		driver.findElement(By.xpath("//*[@value='Sign in']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='container']")).getText().toString(), "Incorrect username or password.");

		driver.quit();
	}

}
