package com.test;


import static org.testng.Assert.assertEquals;

import java.io.IOException;
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
import com.pages.CommonMethods;
import com.pages.Login;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ParallelTesting extends CommonMethods{

	
	
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
			//cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, firstdeviceName);
			cap.setCapability(MobileCapabilityType.UDID, firstdeviceName);
			cap.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
			
		}
		else if(device.equalsIgnoreCase(secondeviceName))
		{
			System.out.println(port+ " "+System.currentTimeMillis());
			System.out.println(device+ " "+System.currentTimeMillis());
			//cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, secondeviceName);
			cap.setCapability(MobileCapabilityType.UDID, secondeviceName);
			cap.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		}
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		//	cap.setCapability(MobileCapabilityType.APP, f.getAbsolutePath()); /*--> Used to install apk from system to android machine*/
			//below properties used when apk already installed in a device. We will launch the app without installing.
			/*cap.setCapability("noReset", true);
			cap.setCapability("fullReset", false);*/
			cap.setCapability("appActivity", "com.mfino.goodmoney.ui.welcome_story.activities.Splash");	
			cap.setCapability("appPackage", "com.mfino.goodmoney");
			/*cap.setCapability("unicodeKeyboard", true); 
			cap.setCapability("resetKeyboard", true);*/
			//Below property is used to accept the permission
			 cap.setCapability("autoGrantPermissions", true);

		driver= new AndroidDriver(new URL("http://localhost:4444/wd/hub"), cap);
		System.out.println("Appium Server Started");
		
		
	}
	
	@Test(enabled=false)
	public void test1()
	{
		driver.get("https://www.google.com");
		System.out.println("Session id "+driver.getSessionId());
		driver.findElement(By.id("q")).sendKeys("Hello Appium!!!");
	}
	Login create;
	@Test
	public void login() throws IOException, Exception {
		
		create = new Login();
		create.validLogin("8058718068","147258");
		
		String actual = driver.findElement(create.available_spend).getText();
		String expected = "Available to spend";
		assertEquals(actual, expected);
		System.out.println("----------- Login Success -----------");

	}
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
}
