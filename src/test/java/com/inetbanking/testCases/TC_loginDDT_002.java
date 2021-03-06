package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_loginDDT_002 extends baseClass{
	

	@Test(dataProvider="LoginData")
	public void loginDDT(String uname, String pwd) throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(uname);
		lp.setPassword(pwd);
		lp.clickSubmit();
		
		if(isAlertPresent()==true) {
			captureScreen(driver, "loginDDT");
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
			driver.switchTo().alert();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
		
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rowNum = XLUtils.getRowCount(path, "Sheet1");
		int colNum = XLUtils.getColCount(path, "Sheet1", 0);
		System.out.println(rowNum);
		System.out.println(colNum);
		
		String loginData[][] = new String[rowNum][colNum];
		
		for(int i=1;i<=rowNum;i++) {
			for(int j=0;j<colNum;j++) {
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return loginData;
	}
	
}
