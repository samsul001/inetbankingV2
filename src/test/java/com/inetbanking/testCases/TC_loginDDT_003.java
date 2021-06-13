package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_loginDDT_003 extends baseClass{
	
	@Test(dataProvider="TestData")
	public void loginDDT3(String user, String pwf) throws IOException {
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(user);
		lp.setPassword(pwf);
		lp.clickSubmit();
		
		if(isAlertPresent()==true) {
			captureScreen(driver, "loginDDT2_003");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			
		}
		else {
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(true);
		}
		
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	@DataProvider(name="TestData")
	public String[][] getData() throws IOException  {
		
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/TestData.xlsx";
		
		int rowCount = XLUtils.getRowCount(path, "Sheet1");
		int colCount = XLUtils.getColCount(path, "Sheet1", 1);
		
		String TestData[][] = new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				TestData[i-1][j]= XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return TestData;
			
	}
	

}
