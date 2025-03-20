package com.automation.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.automation.pages.BaseClass;
import com.automation.pages.LoginPage;

public class LoginTestDemoApp extends BaseClass {
	
	
	@Test
	public void loginDemoApp()
	{		
		logger = report.createTest("Login to Demo App");
						
		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		
		/*
		 * String username = excel.getStringData("LoginCredential", 0, 0); String
		 * password = excel.getStringData("LoginCredential", 0, 1);
		 */
		logger.info("Starting Application");
		
		String userDetail = excel.getStringData("LoginCredential", 0, 0);
		System.out.println(userDetail);
		
		String passwordDetail = excel.getStringData("LoginCredential", 0, 1);
		System.out.println(passwordDetail);
		
		loginpage.loginDemo(userDetail, passwordDetail);		
		
		logger.pass("Logged in successfully");
		
		
	}

}
