package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	
	WebDriver ldriver;
	public AddCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath = "/html/body/div[3]/div/ul/li[2]/a")
	WebElement lnkAddNewCustomer;
	
	@FindBy(name = "name")
	WebElement txtCustName;
	
	@FindBy(name = "rad1")
	WebElement rdGender;
	
	@FindBy(id = "dob")
	WebElement txtDob;
	
	@FindBy(name = "addr")
	WebElement txtAddress;
	
	@FindBy(name = "city")
	WebElement txtCity;
	
	@FindBy(name = "state")
	WebElement txtState;
	
	@FindBy(name = "pinno")
	WebElement txtPinNo;
	
	@FindBy(name = "telephoneno")
	WebElement txtTelephNo;
	
	@FindBy(name = "emailid")
	WebElement emailId;
	
	@FindBy(name = "password")
	WebElement txtPassword;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[14]/td[2]/input[1]")
	WebElement btnSubmit;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")
	WebElement txtSuccess;
	
	public void clickAddNewCutomer() {
		lnkAddNewCustomer.click();
	}
	
	public void CustName(String cname) {
		txtCustName.sendKeys(cname);
	}
	
	public void custGender() {
		rdGender.click();
	}
	
	public void custDob(String dd, String mm, String yyyy) {
		txtDob.sendKeys(dd);
		txtDob.sendKeys(mm);
		txtDob.sendKeys(yyyy);
		
	}
	
	public void custAddr(String caddress) {
		txtAddress.sendKeys(caddress);
	}
	
	public void custCity(String cCity) {
		txtCity.sendKeys(cCity);
	}
	
	public void custState(String cState) {
		txtState.sendKeys(cState);
	}	
	
	public void custPinNo(String cpinno) {
		txtPinNo.sendKeys(cpinno);
	}
	
	public void custTelephone(String cTelePhone) {
		txtTelephNo.sendKeys(cTelePhone);
	}
	
	public void custEmailId(String cEmailId) {
		emailId.sendKeys(cEmailId);
	}
	
	public void custPassword(String cPassword) {
		txtPassword.sendKeys(cPassword);
	}
	
	public void clickSubBtn() {
		btnSubmit.click();
	}
	
	public boolean verifySuccessText(String custSuccess) {
		boolean successMes = txtSuccess.getText().contains(custSuccess);
		return successMes;
	}

}
