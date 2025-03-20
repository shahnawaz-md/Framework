package com.automation.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	
	
	
	public static String captureScreenshot(WebDriver driver) 
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotpath = System.getProperty("user.dir")+"/Screenshots/DemoTestScreenshot_" + getCurrentDateTime() + ".png";
		try {
			FileHandler.copy(src, new File(screenshotpath));
			
			System.out.println("Screenshot captured");
			
		} catch (IOException e) {

			System.out.println("Unbale to capture screenshot" +e.getMessage());
		}
		return screenshotpath;
	}
	
	public static String getCurrentDateTime()
	{
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		
		Date currentDate = new Date();
		
		return customFormat.format(currentDate);
		
	}
	
	public static String acceptAlert(WebDriver driver) {
		String message = null;
		try {
		Alert alert = driver.switchTo().alert();
		message = alert.getText();
		alert.accept();
		} catch (Exception e) {
			System.out.println("Unable to accept alert" +e.getMessage());
		}
		return message;
		}
	
	public static String cancelAlert(WebDriver driver) {
		String message = null;
		try {
		Alert alert = driver.switchTo().alert();
		message = alert.getText();
		alert.dismiss();
		} catch (Exception e) {
			System.out.println("Unable to cancel alert" +e.getMessage());
		}
		return message;
		}
	
	public void switchToDefaultFrame(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			
		} catch (Exception Ex) {
			System.out.println("Exception occured");
		}
	}
	
	public static void dropDownSelectionByText(WebDriver driver, By dropDownID, String dropDownValue) {
		try {
			WebElement element = null;
			new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(dropDownID));
			element = driver.findElement(dropDownID);
			Select dropDown = new Select(element);
			dropDown.selectByVisibleText(dropDownValue);
		}
		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
		}
	}
	
	
	

}
