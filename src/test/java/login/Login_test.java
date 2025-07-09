package login;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.BaseTest;

public class Login_test extends BaseTest{
	
	@Test()
	public void login() {
		
	}
	
	@Test()
	public void logout() {
		driver.findElement(By.xpath(loc.getProperty("logout_button"))).click();
		log.info("Log out success");
		
	
		
	}
	

}
