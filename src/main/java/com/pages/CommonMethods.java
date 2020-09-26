package com.pages;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.util.TimeUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CommonMethods {

	public static AndroidDriver<MobileElement> driver = null;
	
	static FileInputStream fis;
	static Properties prop = new Properties();

	public static String readConfigProperty(String key) throws IOException {
		String property = System.getProperty("user.dir") + "/src/main/java/configfiles/config.properties";

		FileInputStream fis = new FileInputStream(property);
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		System.out.println(" Entered Value is " + value);
		return value;
	}

	public static void click(By element) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();
	}

	public static void input(By element, String input) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(input);
	}

	public boolean isElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		boolean ElementPresent = false;
		try {
			if (wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed()) {
				ElementPresent = true;
				System.out.println("----------- Element displayed --------------" + gettext(locator));
			}
		} catch (Exception e) {
			System.out.println("Element is not present " + e);
		}
		return ElementPresent;
	}

	public String gettext(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
		return text;
	}

	public static String dateandtime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date date = new Date();
		String dateandtime = formatter.format(date);
		System.out.println(dateandtime);
		return dateandtime;
	}

	public String screenshot(String screenshotname, AndroidDriver<MobileElement> driver2) {
		String destination = null;
		File srcFile = ((TakesScreenshot) driver2).getScreenshotAs(OutputType.FILE);
		try {
			destination = System.getProperty("user.dir") + "/Screenshot/" + screenshotname + dateandtime() + ".png";
			FileHandler.copy(srcFile, new File(destination));
			// FileUtils.copyFile(srcFile, new File(destination));
			System.out.println("Screenshot Taken");
		} catch (IOException e) {
			System.out.println("Screenshot Failed");
			e.printStackTrace();
		}
		return destination;
	}
	
// Below method is used to swipe from bottom to top 
	public void verticalSwipe() {
		Dimension dim = driver.manage().window().getSize();
		System.out.println(" Size of the screen is " + dim);
		int height = dim.getHeight();
		System.out.println(" Height of the screen is " + height);
		int width = dim.getWidth();
		System.out.println(" Width of the screen is " + width);
		Dimension size = driver.manage().window().getSize();
		// Here x is constant . It means x axis is contant. 
		int x = (int) (width * 0.20);
		System.out.println(x);
		// We are swipping from bottom to top so starty and endy are used to swipe from where it should start and end 
		int starty = (int) (height * 0.75);
		System.out.println(starty);
		int endy = (int) (height * 0.35);
		System.out.println(endy);

		// Previously driver.swipe() is there. Now it is deprecated. To overcome this, used touchaction class
		new TouchAction(driver).press(point(x, starty)).waitAction(waitOptions(ofMillis(1000))).moveTo(point(x, endy))
				.release().perform();

	}
	
	public void verticalSwipeUntillElementPresent(By element) {
		Dimension dim = driver.manage().window().getSize();
		System.out.println(" Size of the screen is " + dim);
		int height = dim.getHeight();
		System.out.println(" Height of the screen is " + height);
		int width = dim.getWidth();
		System.out.println(" Width of the screen is " + width);
		Dimension size = driver.manage().window().getSize();
		// Here x is constant . It means x axis is contant. 
		int x = (int) (width * 0.20);
		System.out.println(x);
		// We are swipping from bottom to top so starty and endy are used to swipe from where it should start and end 
		int starty = (int) (height * 0.75);
		System.out.println(starty);
		int endy = (int) (height * 0.35);
		System.out.println(endy);

		// Previously driver.swipe() is there. Now it is deprecated. To overcome this, used touchaction class
		new TouchAction(driver).press(point(x, starty)).waitAction(waitOptions(ofMillis(1000))).moveTo(point(x, endy))
				.release().perform();

	}

	public void horizontalSwipe() {
		Dimension dim = driver.manage().window().getSize();
		System.out.println(" Size of the screen is " + dim);
		int height = dim.getHeight();
		System.out.println(" Height of the screen is " + height);
		int width = dim.getWidth();
		System.out.println(" Width of the screen is " + width);
		Dimension size = driver.manage().window().getSize();
		int y = (int) (height * 0.30);
		System.out.println(y);
		int startx = (int) (width * 0.90);
		System.out.println(startx);
		int endx = (int) (width * 0.10);
		System.out.println(endx);

		new TouchAction(driver).press(point(startx, y)).waitAction(waitOptions(ofMillis(1000))).moveTo(point(endx, y))
				.release().perform();

	}
	
	public void locatorWait(By element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

}
