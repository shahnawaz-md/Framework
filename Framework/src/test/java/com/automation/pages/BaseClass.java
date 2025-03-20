package com.automation.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.automation.utility.BrowserFactory;
import com.automation.utility.ConfigDataProvider;
import com.automation.utility.ExcelDataProvider;
import com.automation.utility.Helper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {
	

	 public WebDriver driver;
	 
	 public ExcelDataProvider excel;
	 public ConfigDataProvider config;
	 public ExtentReports report;
	 public ExtentTest logger;
	 
	 
	 @BeforeSuite
	 public void setUpSuite()
	 {
		 Reporter.log("Setting up reports and Test is getting ready", true);
		  excel = new ExcelDataProvider();
		  config = new ConfigDataProvider();
		  
		  ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/DemoTest_"+Helper.getCurrentDateTime()+".html"));
		  
		  report = new ExtentReports();
		  report.attachReporter(extent);
		  
		  Reporter.log("Setting done - Test can be started", true);
	 }
	
	
	@BeforeClass
	public void setup()
	{
		Reporter.log("Trying to start Browser and getting application ready", true);
		//String Url = "https://demo.guru99.com/V4/";
		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingUrl());
		
		Reporter.log("Browser and Application is up and running", true);
	}
	
	@AfterClass
	public void tearDown()
	{
		BrowserFactory.quitBrowser(driver);
		
		Reporter.log("Browser closed successfully", true);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException
	{
		Reporter.log("Test is about to end", true);
		
		if(result.getStatus()== ITestResult.FAILURE)
		{
			//Helper.captureScreenshot(driver);
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus()== ITestResult.SUCCESS)
		{
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus()== ITestResult.SKIP)
		{
			logger.skip("Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		
		report.flush();
		
		Reporter.log("Test Completed >>> Reports Generated", true);
	}
}
