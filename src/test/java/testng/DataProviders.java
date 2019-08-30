package testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataProviders {

	@Test(dataProvider = "getData")
	public void loginTest(String uid, String pwd) {
		System.out.println("User Name : "+uid);
		System.out.println("Password : "+pwd);
		System.out.println("just added this in Master branch");
		System.out.println("just added this in testing2 branch");
	
		System.out.println("just added this in testing2 branch");
	}

	@DataProvider
	public Object[][] getData(){
		Object[][] data=new Object[2][2];
		data[0][0]="pradeep";
		data[0][1]="password";
		data[1][0]="test";
		data[1][1]="test";

		return data;
	}
	
	@Test
	@Parameters({"url","test"})
	public void parameter(String url, String test) {
		
		System.out.println("Parameter Value : "+url);
		System.out.println("Parameter Value : "+test);
		System.out.println("just added this in Master branch");
		System.out.println("just added this in testing2 branch");
		System.out.println("Added from Testing1 branch");
		System.out.println("Added from Testing1 branch");
		System.out.println("just added this in testing2 branch");
	}
}