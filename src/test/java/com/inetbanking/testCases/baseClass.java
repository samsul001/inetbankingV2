package com.inetbanking.testCases;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class baseClass {

	ReadConfig readconfig = new ReadConfig();
	public String baseUrl = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	

	public static WebDriver driver;

	//public static Logger logger;
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {

		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
		}

		if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		}

		if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readconfig.getIePath());
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		}		
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}


	public void captureScreen(WebDriver driver, String tname) throws IOException { 
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date()); 
		String screenShotName = tname+"-"+timeStamp;
		TakesScreenshot ts =(TakesScreenshot) driver; 
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"\\Screenshots\\"+screenShotName+".png");
		FileUtils.copyFile(source, target); 	
		 
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			return true;
		}catch(NoAlertPresentException e) {
			return false;
		}
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(8);
		return generatedString;
	}

	public String randomNumeric() {
		String generateNumeric = RandomStringUtils.randomNumeric(5);
		return generateNumeric;
	}


}
