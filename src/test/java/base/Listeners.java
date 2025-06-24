package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent=ExtentsReportsConfig.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();   //Thread safe
	
	@Override
	public void onTestStart(ITestResult result) {
	
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);  //Assign a unique thread id  of error validation test
		//we use this to remove concurrency
		//(If many test cases are executing at the same time then if any test case failed , we will get actual failed test case in reporyt.)
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// this will log the message
		extentTest.get().log(Status.PASS,"Test has been passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// this will return the failed reason in report
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		} 
		
		//On failure it will capture the Screenshot and attach it in report
		String filePath=null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// use to flush so that html report will generate.
		extent.flush();
		
		
	}
	

	
	}
	
