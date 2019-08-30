package testng;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	private int count = 0;
	private static int maxTry = 10;

	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {                      //Check if test not succeed
			if (count < maxTry) {                            //Check if maxtry count is reached
				count++;                                     //Increase the maxTry count by 1
				result.setStatus(ITestResult.FAILURE);  //Mark test as failed
				return true; 
				}                               //Tells TestNG to re-run the test
		} else {
			result.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
		}
		return false;
	}

}