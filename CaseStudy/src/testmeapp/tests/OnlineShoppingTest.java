package testmeapp.tests;


import java.sql.Time;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	String user = "Tester30";
	String passwd = "Pass1234";

	@BeforeTest
	public void startReportBeforeTest() {

		String filePath = System.getProperty("user.dir") +"/extent-reports/result.html";
		htmlReporter = new 	ExtentSparkReporter(filePath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		driver = Drivers.getDriver("chrome");
		driver.navigate().to("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");


	}
	@Test(priority=2,enabled=true)
	public void testCart() {
		
		driver.manage().window().maximize();
		Actions actions = new Actions(driver);
		logger = extent.createTest("Add to Cart Test");
		driver.findElement(By.id("myInput")).sendKeys("Headphone");
		actions.keyDown(Keys.ENTER).perform();
		
	}
	
	@Test(priority=1,enabled=true)
	public void testLogin() {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		By homeEle = By.xpath("//a[@href='login.htm']");
		wait.until(ExpectedConditions.presenceOfElementLocated(homeEle));
		logger = extent.createTest("Login Test");
		driver.findElement(By.xpath("//a[@href='login.htm']")).click();
		driver.findElement(By.id("userName")).sendKeys(user);
		driver.findElement(By.id("password")).sendKeys(passwd);
		driver.findElement(By.name("Login")).click();
		
	}
	@Test(priority=3)
	public void testPayment() {
		logger = extent.createTest("Test Payement");
		driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();
		driver.findElement(By.xpath("//a[@href='displayCart.htm']")).click();
		driver.findElement(By.xpath("//a[@href='checkout.htm']")).click();
		driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
		driver.findElement(By.xpath(("/html/body/div/div/div/div/div[2]/form/div/div/div/div[2]/div[1]/div/label/input"))).click();
		driver.findElement(By.id("btn")).click();
	}
	@Test(priority=0,enabled=true)
	public void testRegistration() {
		
		logger = extent.createTest("Registration Test");
		driver.findElement(By.xpath("//a[@href='RegisterUser.htm']")).click();
		driver.findElement(By.name("userName")).sendKeys(user);
		driver.findElement(By.name("firstName")).sendKeys(("Erica"));
		driver.findElement(By.name("lastName")).sendKeys("Ember");
		driver.findElement(By.name("password")).sendKeys(passwd);
		driver.findElement(By.name("confirmPassword")).sendKeys(passwd);;
		driver.findElement(By.xpath("//*[@id=\"gender\"]")).click();
		driver.findElement(By.name("emailAddress")).sendKeys("ericaember@gmail.com");
		driver.findElement(By.name("mobileNumber")).sendKeys("0123456789");
		driver.findElement(By.name("dob")).sendKeys("10/10/2001");
		driver.findElement(By.name("address")).sendKeys("New Town,Opposite to Hifi Mall,New York");
		Select ques = new Select(driver.findElement(By.name("securityQuestion")));
		ques.selectByIndex(0);
		driver.findElement(By.name("answer")).sendKeys("New");
		driver.findElement(By.xpath("//input[@name='Submit']")).click();
		driver.findElement(By.name("userName")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(passwd);
		driver.findElement(By.name("Login")).click();
		driver.findElement(By.xpath("//a[@href='logout.htm']")).click();

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

	}

}
