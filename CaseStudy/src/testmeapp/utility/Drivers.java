package testmeapp.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Drivers{

	static public WebDriver getDriver(String browser) {
		if(browser == "chrome") {
			System.setProperty("webdriver.chrome.driver","resources/drivers/chromedriver.exe");
			return new ChromeDriver();
		}else if(browser =="firefox") {
			System.setProperty("webdriver.gecko.driver", "resources/drivers/geckodriver.exe");
			return new FirefoxDriver();
		}
		else {
			return null;
		}
	}

}
