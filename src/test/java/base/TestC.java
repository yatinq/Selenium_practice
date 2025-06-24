package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestC extends BaseTest {
	
	@Test()
	public void test() {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement home_heading = driver.findElement(By.xpath(loc.getProperty("home_head_loc")));
		wait.until(ExpectedConditions.visibilityOf(home_heading));
		log.info("Home page is displaying.");
	}

}
