package com.elearn.core;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.elearn.utils.ConfigProperties;

public class DriverMaster {

	private static String chromeDriverProperty = ConfigProperties
			.getSystemProperties("chromeDriverProperty");
	private static String chromeDriverLocation = ConfigProperties
			.getSystemProperties("chromeDriverLocation");
	private static String chromeDriverMode = ConfigProperties
			.getSystemProperties("chromeDriverMode");

	private static HashMap<Long, WebDriver> map = new HashMap<Long, WebDriver>();

	private DriverMaster() {};

	public static void startDriverInstance(String driverKey) {
		BrowserType browser = BrowserType.get(driverKey);
		WebDriver driver;
		switch (browser) {
		case FIREFOX:
			driver = new FirefoxDriver();
			map.put(Thread.currentThread().getId(), driver);
			break;
		case CHROME:
			System.setProperty(chromeDriverProperty, chromeDriverLocation);
			ChromeOptions option = new ChromeOptions();
			option.addArguments(chromeDriverMode);
			driver = new ChromeDriver(option);
			map.put(Thread.currentThread().getId(), driver);
			break;
		default:
			driver = new FirefoxDriver();
			break;
		}
	}

	public static WebDriver getDriverInstance() {
		WebDriver driver = map.get(Thread.currentThread().getId());
		return driver;
	}

	public static void stopDriverInstance() {
		WebDriver driver = map.get(Thread.currentThread().getId());
		driver.quit();
	}

}
