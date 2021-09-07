package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends baseClass {

	@Test
	public void logintest() throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);		
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")) {

			Assert.assertTrue(true);
		}

		else {
			captureScreen(driver, "logintest");
			Assert.assertTrue(false);	

		}
	}
	public void takeScreenshots(WebDriver driver ,String tname) throws IOException {
		/*String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); 
			String screenShotName = tname+"-"+timeStamp;*/
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("screenshot taken");
	}

}
