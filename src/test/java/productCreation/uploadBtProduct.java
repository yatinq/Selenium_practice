package productCreation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;

public class uploadBtProduct extends BaseTest {
	@Test()
	public void upload_Bt_product() {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("selectproductType_loc")))).click();
		log.info("menu clicked");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("brandDistribution_loc")))).click();
		log.info("BT clicked");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("purchase_Order_loc")))).isDisplayed();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("product_Menu_loc")))).click();
		log.info("product clicked");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("productBT_loc")))).click();
		String lastProductname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("lastProductname_loc")))).getText();
		log.info("last product name is : "+lastProductname);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("addproductButton_loc")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("uploadProductButton_loc")))).click();
		
		
		
		
		
		
	}

}
