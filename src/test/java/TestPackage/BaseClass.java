package TestPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {
	WebDriver driver;

	public WebDriver initialise_WebDriver() {
		WebDriver driver;
		ChromeOptions opt = new ChromeOptions();
		opt.setAcceptInsecureCerts(true);
		opt.addArguments("disable-infobars");
		driver = new ChromeDriver(opt);
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.qaclickacademy.com/practice.php");
		return driver;
	}

}
