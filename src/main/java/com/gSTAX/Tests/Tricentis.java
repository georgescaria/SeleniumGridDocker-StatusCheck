package com.gSTAX.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gSTAX.ElementXPaths.ElementXPaths;


public class Tricentis extends BaseTest {

	
	@Test
	public void WebsiteTest1() throws Exception {

		try {
			driver.get("http://demowebshop.tricentis.com/");
			driver.navigate().refresh();
			driver.manage().window().maximize();
			
			for (int i = 0; i < 3; i++) {
				waitUntilDisplayed(ElementXPaths.register);
				click(ElementXPaths.register);
				waitUntilDisplayed(ElementXPaths.login);
				click(ElementXPaths.login);
				waitUntilDisplayed(ElementXPaths.cart);
				click(ElementXPaths.cart);
				waitUntilDisplayed(ElementXPaths.wishlist);
				click(ElementXPaths.wishlist);
				System.out.println("Test Passed");
			}
		} catch (Exception e) {
			System.out.println("Test Failed");
			Assert.assertTrue(false);
			e.printStackTrace();
		}

	}

	public void waitUntilDisplayed(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			//driver = null;
			//wait = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void click(String xpath)
	{
	 	WebElement element = driver.findElement(By.xpath(xpath));

		try {
				JavascriptExecutor executor = (JavascriptExecutor)driver; 
				executor.executeScript("arguments[0].click();", element);
				executor = null;
			}
		catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//driver = null;
		element = null;
	}
}
