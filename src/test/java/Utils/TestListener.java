package Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import BaseClasses.PageBaseClass;

public class TestListener extends PageBaseClass implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentReportManager.getReportInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.log(Status.FAIL, result.getThrowable());

        if (driver != null) {
            String screenshotPath =
                    captureScreenshot(result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
