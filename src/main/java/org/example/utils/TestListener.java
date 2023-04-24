package org.example.utils;

import org.example.core.drivers.DriverManager;
import org.example.report.AllureManager;
import org.testng.ITestListener;
import org.testng.ITestResult;
public class TestListener implements ITestListener {

    public String getTestName(ITestResult result){
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }
    @Override
    public void onTestFailure(ITestResult result){
        Log4j.error("Test case: "+ getTestName(result)+ " is failed.");
        CaptureUtils.captureScreen(DriverManager.getDriver(), getTestName(result));
        AllureManager.takeScreenshotToAttachOnAllureReport();
    }
    @Override
    public void onTestSkipped(ITestResult result){
        Log4j.warn("Test case: "+ getTestName(result)+ " is Skipped.");
    }

    @Override
    public void onTestStart(ITestResult result){
        Log4j.warn("Test case: "+ getTestName(result)+ " is starting.");
    }

}