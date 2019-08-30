package testng;

import org.junit.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(TestNGListeners.class)

public class TestNg_Annotations {
	
static int count=1;
	@BeforeSuite
	public void before_Suite() {
		System.out.println("Before Suite : "+count++);
	}
	@BeforeTest
	public void before_Test() {
		System.out.println("Before Test : "+count++);
	}
	@BeforeClass
	public void before_Class() {
		System.out.println("Before Class : "+count++);
	}
	@BeforeMethod
	public void before_Method() {
		System.out.println("Before Method : "+count++);
	}
	@AfterSuite
	public void after_Suite() {
		System.out.println("After Suite : "+count++);
	}
	@AfterTest
	public void after_Test() {
		System.out.println("After Test : "+count++);
	}
	@AfterClass
	public void after_Class() {
		System.out.println("After Class : "+count++);
	}
	@AfterMethod
	public void  after_Method() {
		System.out.println("After Method : "+count++);
	}
	@Test(retryAnalyzer = Retry.class)
	void test2() {
		System.out.println("I Failed this intentionally : Count - "+count);
		Assert.fail();
		//System.out.println("I am from test2()");
	}
	
	@Test(dependsOnMethods = {"test2"},retryAnalyzer = Retry.class)
	void test1() {
		System.out.println("I am from test1(),I am dependent on test2(), hence it needs to execute first");
		System.out.println("just added this in Testing1 branch");
		System.out.println("just added this in Testing1 branch");
		System.out.println("just added this in Testing1 branch");
		System.out.println("just added this in Testing1 branch");
		System.out.println("just added this in Testing1 branch");
		System.out.println("just added this in Testing1 branch");
		
	}
	
	@Test(groups = {"smoke","regression"},retryAnalyzer = Retry.class)
	void smokeTest1() {
		System.out.println("smoke test 1");
	}
	
	@Test(groups = {"smoke","regression"},retryAnalyzer = Retry.class)
	void smokeTest2() {
		System.out.println("smoke test 2");
	}
	
	@Test(groups = {"sanity","regression"},retryAnalyzer = Retry.class)
	void sanityTest() {
		System.out.println("sanity Test");
	}
	
	@Test(groups = {"regression"},retryAnalyzer = Retry.class)
	void ReTest() {
		System.out.println("Re Test");
	}
}
