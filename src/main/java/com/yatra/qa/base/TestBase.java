package com.yatra.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.yatra.qa.util.TestUtil;
import com.yatra.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public String dCity = "Pune";
	public String aCity = "Chennai";
	public String tDate = "28/08/2023";
	
	public TestBase() {	
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/yatra" + "/qa/config/config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		}catch(NullPointerException e) {
		e.printStackTrace();
		}
	}

	public void initialization(){
		String browserName = prop.getProperty("browser");
			WebDriverManager.chromedriver().setup();
			

		if(browserName.equals("chrome")){

				WebDriverManager.chromedriver().setup();
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("disable-notifications");
				opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				driver = new ChromeDriver(opt);
				 // driver = new ChromeDriver();
				  
				  
				//System.setProperty("webdriver.chrome.driver", "/Users/naveenkhunteta/Downloads/chromedriver");	
				//driver = new ChromeDriver(); 
			}

			else if(browserName.equals("edge")){
				WebDriverManager.edgedriver().setup();	
				driver = new EdgeDriver(); 
			}

			
		
			e_driver = new EventFiringWebDriver(driver);
			// Now create object of EventListerHandler to register it with EventFiringWebDriver
			eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
		
		
	}
}
