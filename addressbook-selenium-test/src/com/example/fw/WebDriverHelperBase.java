package com.example.fw;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class WebDriverHelperBase extends HelperBase {
	

	private WebDriver driver;
	public boolean acceptNextAlert = true;
	
	public WebDriverHelperBase(ApplicationManager manager) {
		super(manager);
		this.manager = manager;	
		this.driver = manager.getDriver();
	}
	
	protected boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
	
	protected List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}
	
	protected WebElement findElemnt(By by) {
		return driver.findElement(by);
	}
	
	protected String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }

	protected boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	protected void type(By locator, String text) {
		if (text != null) {
			driver.findElement(locator).clear();
		    driver.findElement(locator).sendKeys(text);			
		}
	}

	protected void click(By locator) {
		driver.findElement(locator).click();
	}
	
	protected void selectByText(By locator, String text) {
		if (text != null) {
			new Select(driver.findElement(locator)).selectByVisibleText(text);			
		}
	}
	
	protected String getUrl() {
		return driver.getCurrentUrl();
	}
	
}
