package TestPackage;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HandlingDynamicElements extends BaseClass{
	WebDriver driver = null;
	WebDriverWait wait=null;

	@BeforeMethod (groups = {"regression","smoke","sanity"})
	public void initialise_Driver() {
		this.driver=initialise_WebDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://www.qaclickacademy.com/practice.php");
	}

	@Test(groups= {"smoke"})
	public void getPageDetails() {
		System.out.println("URL : " + driver.getCurrentUrl());
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(false);
		sa.assertEquals(driver.getCurrentUrl(), "http://www.qaclickacadmy.com/practice.php");
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.qaclickacademy.com/practice.php");
		System.out.println("Page Title Assert Test :" + driver.getTitle());
		System.out.println("Page Source :\n" + driver.getPageSource());
	}

	@AfterMethod
	public void getScreenshot() throws IOException {
		String destination = "C:\\Users\\pravinutala\\Desktop\\Temp";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(destination + "\\temp.png"));
	}

	@Test(groups= {"sanity"})
	public void switchToFrame() throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//iframe[@id='courses-iframe']"))).build().perform();
		driver.switchTo().frame("courses-iframe");
		System.out.println("Now am in the Frame : " + driver.getWindowHandle());
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[@class='listbuilder-popup-scale']"))));
		driver.findElement(By.xpath(".//button[contains(text(),'NO THANKS')]")).click();

		List<WebElement> navBar = driver.findElements(By.xpath(".//ul[@class='nav navbar-nav navbar-right']//a"));
		for (int i = 0; i < navBar.size(); i++) {
			System.out.println(navBar.get(i).getText());
			if ((navBar.get(i).getText().trim()).equals("BLOG")) {
				navBar.get(i).click();
				break;
			}
		}
		System.out.println(driver.getTitle());
		driver.switchTo().defaultContent();
		System.out.println(driver.getTitle());
	}

	@Test(groups= {"sanity"})
	public void switchToTab() {
		String defaultWindowHandle = driver.getWindowHandle();
		System.out.println("Default window handle is : " + defaultWindowHandle);
		System.out.println("Page Title Assert Test :" + driver.getTitle());
		driver.findElement(By.id("opentab")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> itr = windowHandles.iterator();
		while (itr.hasNext()) {
			String str = itr.next();
			System.out.println(str);
			driver.switchTo().window(str);
			System.out.println("The Current window Handle is :" + driver.getWindowHandle());
			System.out.println("Page Title  :" + driver.getTitle());

		}
		driver.close();
		driver.switchTo().window(defaultWindowHandle);
		System.out.println("The Current window Handle is :" + driver.getWindowHandle());
		System.out.println("Page Title  :" + driver.getTitle());
	}

	@Test(groups= {"regression"})
	public void switchToAlert() {
		driver.findElement(By.id("alertbtn")).click();
		System.out.println("Text present in the Alert is :" + driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
	}

	@Test(groups= {"smoke"})
	public void verify_elementDisplayed() throws InterruptedException {
		driver.findElement(By.id("hide-textbox")).click();
		System.out.println("Element Displayed : " + driver.findElement(By.id("displayed-text")).isDisplayed());
		Thread.sleep(3000);
		driver.findElement(By.id("show-textbox")).click();
		System.out.println("Element Displayed : " + driver.findElement(By.id("displayed-text")).isDisplayed());
	}

	@Test(groups= {"smoke"})
	public void selectClassExample() throws InterruptedException {
		Select sel = new Select(driver.findElement(By.id("dropdown-class-example")));
		sel.selectByValue("option2");
		Thread.sleep(5000);
	}

	@Test(groups= {"sanity"})
	public void autoSuggestion() throws InterruptedException {
		driver.findElement(By.id("autocomplete")).sendKeys("India");
		List<WebElement> autoSug = driver
				.findElements(By.xpath(".//ul[contains(@class,'ui-autocomplete ui-front')]//div"));
		for (int i = 0; i < autoSug.size(); i++) {
			System.out.println(autoSug.get(i).getText());
			if ((autoSug.get(i).getText()).equalsIgnoreCase("India")) {
				autoSug.get(i).click();
				Thread.sleep(5000);
				break;
			}
		}
	}

	@Test
	public void handling_WebTable() {
		//print the rows which has the book value 25 and don't print the value
		List<WebElement> rows = driver.findElements(By.xpath(".//table[@id='product']//tr"));
		for (int i =0; i < rows.size(); i++) {
			if((rows.get(i).getText()).contains("25")) {
				List<WebElement> cols= driver.findElements(By.xpath(".//table[@id='product']//tr["+(i+1)+"]/td[3]/preceding-sibling::td"));
				for(int j=0;j<cols.size();j++) {
					System.out.print(cols.get(j).getText()+"\t");
				}
				System.out.println();
			}
		}
	}

	@AfterMethod(groups = {"regression","smoke","sanity"})
	public void tearDown() {
		driver.close();
	}
}
