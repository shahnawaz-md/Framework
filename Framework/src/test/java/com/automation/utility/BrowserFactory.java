package com.automation.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	WebDriver driver;
	
	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) 
	{
		
		if(browserName.equals("Chrome"))
		{
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().clearDriverCache().setup();
			driver = new ChromeDriver(chromeOptions);
			
		}
		else if(browserName.equals("Firefox"))
		{
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			WebDriverManager.firefoxdriver().clearDriverCache().setup();
			driver = new FirefoxDriver(firefoxOptions);
			
		}
		else if(browserName.equals("IE"))
		{
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			WebDriverManager.iedriver().clearDriverCache().setup();
			driver = new InternetExplorerDriver(ieOptions);
		}
		else
		{
			System.out.println("We do not support this browser");
		}
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
		
	}
	
	
	public static void quitBrowser(WebDriver driver)
	{
		
		driver.close();
		driver.quit();
		
	}

}
