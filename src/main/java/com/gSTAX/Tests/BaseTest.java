package com.gSTAX.Tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;

	@BeforeTest
	public void setupDriver() throws MalformedURLException {

		try {
			DesiredCapabilities dc;
			String host = "localhost";
			System.out.println(System.getProperty("BROWSER"));
			if ((System.getProperty("BROWSER") != null)
					&& (System.getProperty("BROWSER").equalsIgnoreCase("firefox"))) {
				dc = DesiredCapabilities.firefox();
			}
			else {
				dc = DesiredCapabilities.chrome();
			}

			if (System.getProperty("HUB_HOST") != null) {
				host = System.getProperty("HUB_HOST");
			}

			String completeUrl = "http://" + host + ":4444/wd/hub";

			driver = new RemoteWebDriver(new URL(completeUrl), dc);

		} catch (Exception e) {
			e.printStackTrace();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

	}

	@AfterTest
	public void quitDriver() {
		driver.quit();
	}

}
