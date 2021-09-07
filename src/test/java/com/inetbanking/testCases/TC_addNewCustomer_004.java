package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_addNewCustomer_004 extends baseClass {
	
	@Test
	public void addNewCustTest() throws InterruptedException, IOException {
	LoginPage lp = new LoginPage(driver);
	lp.setUserName(username);
	lp.setPassword(password);
	lp.clickSubmit();
	
	if(isAlertPresent()==true) {
		captureScreen(driver, "addNewCustTest");
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		
	}
	
	AddCustomerPage ac = new AddCustomerPage(driver);
	ac.clickAddNewCutomer();
	ac.CustName("pavan");
	ac.custGender();
	ac.custDob("05", "13", "1975");
	ac.custAddr("big street");
	ac.custCity("tenkasi");
	ac.custState("Tamilnadu");
	ac.custPinNo("231451");
	ac.custTelephone("1234567890");
	String email = randomString()+"@gmail.com";
	ac.custEmailId(email);
	ac.custPassword("adnnbgtsapan4833");
	ac.clickSubBtn();
	Thread.sleep(10000);
	
	boolean res=ac.verifySuccessText("Customer Registered Successfully!!!");
	if(res==true) {
		Assert.assertTrue(true);
	}
	else {
		captureScreen(driver, "addNewCustTest");
		Assert.assertTrue(false);
		
	}
	
	}
	
	
	


}
