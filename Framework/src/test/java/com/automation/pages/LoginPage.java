package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.utility.Helper;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	@FindBy(how = How.NAME, using = "uid")
	WebElement userName;
	
	@FindBy(how = How.NAME, using = "password")
	WebElement passWord;
	
	@FindBy(how = How.NAME, using = "btnLogin")
	WebElement btnLogin;
	
	@FindBy(how = How.NAME, using = "btnReset")
	WebElement btnReset;
	
	public void loginDemo(String username, String password)
	{
		
		try 
		{
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			
		}
		userName.sendKeys(username);
		passWord.sendKeys(password);
		btnLogin.click();
		
	}
	

}
