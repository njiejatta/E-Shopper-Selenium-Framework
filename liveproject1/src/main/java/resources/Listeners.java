package resources;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BasePage1;
import base.ExtentManager;

public class Listeners extends BasePage1 implements ITestListener {

	public Listeners() {
		
		super();
	}
	
	public synchronized void onStart(ITestContext context) {
		ExtentManager.getReport();
		ExtentManager.createTest(context.getHost(), context.getName());
	}
	
	public synchronized void onTestFailure(ITestResult result) {
		ExtentManager.getTest().fail(result.getThrowable());
	    try {
	    	
	    	System.out.println("Test failed: " + result.getName());

	    	takeSnapShot(result.getMethod().getMethodName());
			ExtentManager.attachImage();

	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	  }
}
