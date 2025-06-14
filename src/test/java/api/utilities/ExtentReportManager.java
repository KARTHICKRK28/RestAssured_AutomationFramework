package api.utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Desktop;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		repName = "Test-Report-" + timestamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the report
		sparkReporter.config().setDocumentTitle("RestAssured Automation Project"); // Title of the report
		sparkReporter.config().setReportName("Pet Stores Users API"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimelineEnabled(true);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// System info
		extent.setSystemInfo("Application", "Pet Stores Users API");
		extent.setSystemInfo("Module", "Users");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("User Name", System.getProperty("os.name"));
		extent.setSystemInfo("Environment", "QA");

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // Declare and initialize test
		test.assignCategory(result.getMethod().getGroups());// Optional group info
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();

	}

}
