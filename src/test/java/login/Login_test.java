package login;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class Login_test extends BaseTest{
	
	@Test()
	public void verify_loginText() {
		
		String expectedtext = prop.getProperty("success_login_heading");
		
		String actualtext = driver.findElement(By.tagName(loc.getProperty("success_login_heading"))).getText();
		
		Assert.assertEquals(actualtext, expectedtext);
		
		log.info("Actual text is same as expected text : "+actualtext);
	}
	
	@Test()
	public void logout() {
		driver.findElement(By.xpath(loc.getProperty("logout_button"))).click();
		log.info("Log out success");
		
	
		
	}
	

}
