

package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static FileReader fr;
	public static Properties loc = new Properties();
	public static FileReader fr1;

	public static Logger log = LogManager.getLogger(BaseTest.class);

	@BeforeMethod

	public void setUp() throws IOException {
		if (driver == null) {
//			System.out.println("The path is "+System.getProperty("user.dir"));//we use this to remove the hardcode of the path and this will print the path till project location.
			FileReader fr = new FileReader(
					System.getProperty("user.dir") + "/src/test/resources/configfiles/config.properties");
			prop.load(fr);
			FileReader fr1 = new FileReader(
					System.getProperty("user.dir") + "/src/test/resources/configfiles/locators.properties");
			loc.load(fr1);


		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			
			 // Create ChromeOptions instance
	        ChromeOptions options = new ChromeOptions();
	        
	        // Add argument to disable notifications
	        options.addArguments("--disable-notifications");
	        
	        // Create a new instance of the Chrome driver with options
	         driver = new ChromeDriver(options);
			// WebDriverManager.chromedriver().setup();
			// log.info("setup successful!");
			//driver = new ChromeDriver();
			log.info("browser has opened!");
			driver.manage().window().maximize();
			//driver.manage().window().fullscreen();
			log.info("window has been maximised!");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			log.info("Implicit wait.....");
			driver.get(prop.getProperty("stagingurl"));
			log.info("URL has entered!");

			driver.findElement(By.xpath(loc.getProperty("username_loc"))).sendKeys(prop.getProperty("username"));
			driver.findElement(By.xpath(loc.getProperty("next_loc"))).click();
			driver.findElement(By.xpath(loc.getProperty("password_loc"))).sendKeys(prop.getProperty("password"));
			driver.findElement(By.xpath(loc.getProperty("login_loc"))).click();
			log.info("Login Successful!");
			
			try {
				
			}
			catch (Exception e) {
				
			}

		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		//	driver.manage().window().maximize();
			driver.manage().window().fullscreen();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(prop.getProperty("stagingurl"));
			driver.findElement(By.xpath(loc.getProperty("username_loc"))).sendKeys(prop.getProperty("username"));
			driver.findElement(By.xpath(loc.getProperty("password_loc"))).sendKeys(prop.getProperty("password"));
			driver.findElement(By.xpath(loc.getProperty("login_loc"))).click();
			log.info("Login Successful!");
		}
		// here you can define more browsers like edge....

	}
//	Below code used for take screenshot
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"/src/test/resources/reports/"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"/src/test/resources/reports/"+testCaseName+".png";
		
	}
	
	
	
	
	
	
	
//	WebElement contacts = driver.findElement(By.xpath(list.getProperty("contacts_loc")));
//	File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	FileUtils.copyFile(f,new  File("/home/yatinsharma/work/infinito_auto/selenium_auto/src/test/resources/reports/screenshots.png"));
//	log.info("screenshot captured!");
//	@AfterMethod
//	
//	public void tearDown() throws InterruptedException {
//		Thread.sleep(3000);
//		driver.close();
//		System.out.println("Teardown Successful.");
//	}

}
