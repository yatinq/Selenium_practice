package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentsReportsConfig {
	
	
	
	public static ExtentReports getReportObject() {
	String path=System.getProperty("user.dir")+"/src/test/resources/reports/index.html";
	//Extents Reports, ExtentSparkReporter
	ExtentSparkReporter reporter=new ExtentSparkReporter(path);  //responsible to create html file and do some configuration
	reporter.config().setReportName("Infinito Automation");
	reporter.config().setDocumentTitle("Test Results");
	ExtentReports extent=new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Yatin Sharma");
	return extent;
	
	}
	
	
}
