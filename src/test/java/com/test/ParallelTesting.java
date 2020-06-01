package com.test;


import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ParallelTesting {

	AndroidDriver driver;
	
	public static String firstdeviceName="48b3b41d";
	public static String secondeviceName="emulator-5554";
	
	@BeforeClass
	@Parameters({"port","deviceID"})
	public void setUp(String port, String device) throws Exception
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		if(device.equalsIgnoreCase(firstdeviceName))
		{
			System.out.println(port+ " "+System.currentTimeMillis());
			System.out.println(device+ " "+System.currentTimeMillis());
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, firstdeviceName);
			cap.setCapability(MobileCapabilityType.UDID, firstdeviceName);
			cap.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
			
		}
		else if(device.equalsIgnoreCase(secondeviceName))
		{
			System.out.println(port+ " "+System.currentTimeMillis());
			System.out.println(device+ " "+System.currentTimeMillis());
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, secondeviceName);
			cap.setCapability(MobileCapabilityType.UDID, secondeviceName);
			cap.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		}

		driver= new AndroidDriver(new URL("http://localhost:4444/wd/hub"), cap);
		System.out.println("Appium Server Started");
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
	}
	
	@Test	
	public void test1()
	{
		driver.get("https://www.google.com");
		System.out.println("Session id "+driver.getSessionId());
		driver.findElement(By.id("q")).sendKeys("Hello Appium!!!");
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
}
