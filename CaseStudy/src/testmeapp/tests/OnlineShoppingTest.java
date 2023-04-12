package testmeapp.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testmeapp.utility.Drivers;


public class OnlineShoppingTest {
	WebDriver driver;
	ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;

	@BeforeTest
	public void startReportBeforeTest() {
		
		String filePath = System.getProperty("user.dir") +"/extent-reports/result.html";
		htmlReporter = new 	ExtentSparkReporter(filePath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		driver = Drivers.getDriver("chrome");
		driver.navigate().to("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
	}

	@Test
	public void testCart() {

	}

	public void testLogin() {

	}

	public void testPayment() {

	}
	@Test(priority=1)
	public void testRegistration() {
		driver.findElement(By.xpath("//a[@href='login.htm']")).click();
		driver.findElement(By.id("userName")).sendKeys("QETester4");
		driver.findElement(By.id("password")).sendKeys("Pass@1234$$");
		driver.findElement(By.name("Login")).click();

	}
	@AfterMethod
	public void getResultAfterMethod(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			logger.log(Status.PASS,"This is in " + result.getMethod().getMethodName());
		}
		else if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, "This is in " + result.getMethod().getMethodName());
		}
	}

	@AfterTest 
	public void endReportAfterTest() {
		extent.flush();
		driver.close();
	}

}
