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
		captureScreen(driver, "TC_addNewCustomer_004");
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		
	}
	
	Thread.sleep(3000);
	
	AddCustomerPage ac = new AddCustomerPage(driver);
	ac.clickAddNewCutomer();
	ac.CustName("daniel");
	ac.custGender();
	ac.custDob("05", "12", "1995");
	ac.custAddr("new street");
	ac.custCity("kadayanallur");
	ac.custState("Tamilnadu");
	ac.custPinNo("231451");
	ac.custTelephone("1234567890");
	String email = randomString()+"@gmail.com";
	ac.custEmailId(email);
	ac.custPassword("asdfg1234");
	ac.clickSubBtn();
	Thread.sleep(10000);
	
	boolean res=ac.verifySuccessText("Customer Registered Successfully!!!");
	if(res==true) {
		Assert.assertTrue(true);
	}
	else {
		captureScreen(driver, "TC_addNewCustomer_004");
		Assert.assertTrue(false);
		
	}
	
	}
	
	
	


}
