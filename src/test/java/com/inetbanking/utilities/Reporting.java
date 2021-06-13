package com.inetbanking.utilities;

//Listener class used to generate Extent reports and capture failed test screenshots
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	
	WebDriver driver = null;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-"+timeStamp+".html";
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"./extent-config.xml");
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("hostname", "localhost");
		extent.setSystemInfo("Env", "QA");
		extent.setSystemInfo("user", "samsul");
		
		htmlReporter.config().setDocumentTitle("Inet Banking Test Project"); //Title of Report
		htmlReporter.config().setReportName("Functional Automation Test Report"); //Name of Report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //Chart Location of Report
		htmlReporter.config().setTheme(Theme.DARK); //Teme of Report
		
	}
	
	public void onTestSuccess(ITestResult tr) {
		logger=extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr) {
		logger=extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String ScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(ScreenshotPath);
		
		if(f.exists()) {
			try {
				logger.fail("Screenshot is below: " + logger.addScreenCaptureFromPath(ScreenshotPath));
				
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onTestSkipped(ITestResult tr) {
		logger=extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
public void captureScreen(WebDriver driver, String tname) throws IOException { 
		
		/*String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); 
		String screenShotName = tname+"-"+timeStamp;*/
		
		TakesScreenshot ts =(TakesScreenshot) driver; 
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"/Screenshots/"+tname+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination); 
		
		 
	}
	
}
