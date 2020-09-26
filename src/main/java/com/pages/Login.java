package com.pages;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.util.TimeUtils;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Login extends CommonMethods {

	By number = By.id("com.mfino.goodmoney:id/edit_text");
	By continu = By.id("com.mfino.goodmoney:id/btn");
	By device_binding = By.id("com.mfino.goodmoney:id/we_dnt_recognize");
	By pin = By.id("com.mfino.goodmoney:id/txt_pin_entry");
	By createaccount_pin = By.xpath("//android.widget.TextView[contains(@text,'You already have an account')]");
	By servererror = By.xpath(
			"//android.widget.TextView[contains(@text,'Unable to connect to server. Please try again in 5 minutes. If error persists, contact us via Help or help@goodmoney.com.')]");
	By skip = By.xpath("//android.widget.TextView[@text='Skip']");
	public static By available_balance = By.id("com.mfino.goodmoney:id/tv_available_balance");
	public static By available_spend = By.id("com.mfino.goodmoney:id/tv_text_available_balance");
	By invalidPassword = By.xpath("//android.widget.TextView[contains(@text,'left before we lock you out.')]");
	By p1= By.xpath("//android.widget.TextView[@text,'Enter your 6-digit passcode to log in.']");
	

	boolean b;
	boolean s;
	boolean inv ;
	boolean server;
	boolean device;
	boolean p;
	boolean pintext;
	public void validLogin(String PhoneNumber, String password) throws IOException, Exception {
	Thread.sleep(30000);
		try
		{
			click(number);
			driver.getKeyboard().sendKeys(PhoneNumber);
			click(continu);
			Thread.sleep(5000);
			try
			{
				device= driver.findElement(device_binding).isDisplayed();
				if(device==true)
				{
					System.out.println(" Device binding screen ");
					devicebinding();
					click(pin);
					driver.getKeyboard().sendKeys(password);
				}
				
			}
			catch(Exception e)
			{
				System.out.println(" Device Binding screen is not displayed");
			}
		}
		catch(Exception e)
		{
			System.out.println(" User already logged in. Please continue with the pin");
		}
		
		try {
		   pintext= driver.findElement(pin).isDisplayed();
			if(pintext==true)
			{
				System.out.println(" Displayed Pin Screen ");
				click(pin);
				driver.getKeyboard().sendKeys(password);
			}
		}
		catch(Exception e)
		{
			System.out.println(" Pin Screen is not displayed ");
		}
		try
		{
			inv = driver.findElement(invalidPassword).isDisplayed();
			if(inv==true)
			{
				System.out.println(" Entered invalid password"+ driver.findElement(invalidPassword).getText());
			}
		}
		catch(Exception e)
		{
			System.out.println(" Entered valid password ");
		}
		
		try
		{
			s =driver.findElement(skip).isDisplayed();
			if(s==true)
				System.out.println(" Displayed Skip screen ");
			click(skip);
		}
		catch(Exception e)
		{
			System.out.println(" Skip screen is not displayed ");
		}
		try
		{
			locatorWait(available_balance);
			boolean b = driver.findElement(available_balance).isDisplayed();
			b=true;
			System.out.println(" Server works fine ");
			
		}
		catch(Exception e)
		{
			System.out.println(" Server Issue or entered invalid number");
		}
	
		
		isElementPresent(available_balance);
		Thread.sleep(5000);
		
	}

	public void invalidlogin(String invalidPhoneNumber) throws InterruptedException, IOException {
		click(number);
		driver.findElement(number).clear();
		driver.getKeyboard().sendKeys(invalidPhoneNumber);
	}

	public String devicebinding() throws IOException, Exception {
		GmailLogin gmail = new GmailLogin();
		String s = null;
		System.out.println(" Displayed Device Binding Screen ");
		click(continu);
		Thread.sleep(10000);
		int otp = gmail.getEmail(CommonMethods.readConfigProperty("email"),
				CommonMethods.readConfigProperty("password"), "Your Good Money MFA Code.");
		s = Integer.toString(otp);
		input(number, s);
		Thread.sleep(5000);
		boolean invalidpassword = false;
		try
		{
			invalidpassword = driver.findElement(invalidPassword).isDisplayed();
			invalidpassword = true;
			System.out.println(" Entered Invalid Password");
		}
		catch(Exception e)
		{
			
		}
		return s;
	}

}
