package productCreation;

import java.awt.AWTException;
import java.awt.Robot;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;

public class uploadProduct extends BaseTest {

	@Test(testName = "Upload a product")
	public void uploadProducts() throws InterruptedException, AWTException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement productMenu = driver.findElement(By.xpath(loc.getProperty("product_Menu_loc")));
		wait.until(ExpectedConditions.elementToBeClickable(productMenu)).click();
		log.info("Products menu clicked.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("upload_New_product_loc")))).click();
		log.info("Upload new product Clicked");

		// Select Brand Name
		WebElement brandNameDropDownElement = driver.findElement(By.name(loc.getProperty("brandNameDropDown")));
		// Create a Select instance
		Select brandDropdown = new Select(brandNameDropDownElement);
		// Select by visible text
		//brandDropdown.selectByVisibleText(loc.getProperty("brandOption"));
		

		// Alternatively, select by value
		// dropdown.selectByValue("optionValue");

	//	 Alternatively, select by index
		brandDropdown.selectByIndex(1);
		//log.info("Brand option selected.");
		// Select Main category
		WebElement mainCategoryDropDownElement = driver.findElement(By.name(loc.getProperty("mainCategoryDropDown")));
		Select mainCategorydropdown = new Select(mainCategoryDropDownElement);
		// Select by visible text
		mainCategorydropdown.selectByVisibleText(loc.getProperty("mainCategoryOption"));
		log.info("Main category selected");

		// Enter lot size
		driver.findElement(By.name(loc.getProperty("lotSize"))).clear();
		driver.findElement(By.name(loc.getProperty("lotSize"))).sendKeys(prop.getProperty("lotSize"));
		log.info("Lot size entered");

		// Select Sub category
		WebElement subCategoryDropDownElement = driver.findElement(By.name(loc.getProperty("subCategoryDropDown")));
		Select subCategorydropdown = new Select(subCategoryDropDownElement);
		// Select by visible text
		//subCategorydropdown.selectByVisibleText(loc.getProperty("subCategoryOption"));
		subCategorydropdown.selectByIndex(1);
		log.info("Sub category selected");

		// Select Set type
//		String setType=loc.getProperty("setType");
//		boolean check = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc.getProperty("setType")))).isSelected();
//		if(check) {
//			log.info("Set type "+setType+" already selected");
//		}else {
//			driver.findElement(By.id(loc.getProperty("setType"))).click();
//			}
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc.getProperty("setType")))).click();
//		log.info("Set type selected");
		Thread.sleep(3000);
		driver.findElement(By.xpath(loc.getProperty("saveAndNextButton_loc"))).click();
		log.info("Base Details has been saved.");

		Thread.sleep(3000);

		// Select packaging
		WebElement packagingDropDownElement = driver.findElement(By.name(loc.getProperty("packagingDropDown")));
		Select packagingdropdown = new Select(packagingDropDownElement);
		packagingdropdown.selectByVisibleText(loc.getProperty("packagingOption"));
		log.info("packaging type selected");

		// Select Product Weight
		WebElement productWeightDropDownElement = driver.findElement(By.name(loc.getProperty("productWeightDropDown")));
		Select productWeightdropdown = new Select(productWeightDropDownElement);
		productWeightdropdown.selectByVisibleText(loc.getProperty("productWeightOption"));
		log.info("Product weight selected");

		// Enter NRV
		driver.findElement(By.name(loc.getProperty("NRV"))).sendKeys(prop.getProperty("NRV"));
		log.info("NRV entered");

		// Enter hsnCode
		driver.findElement(By.name(loc.getProperty("hsnCode"))).sendKeys(prop.getProperty("hsnCode"));
		log.info("hsnCode entered");

		driver.findElement(By.xpath(loc.getProperty("saveAndNextButton_loc"))).click();
		log.info("Catalog Details has been saved.");

		Thread.sleep(2000);

		driver.findElement(By.xpath(loc.getProperty("createSet_loc"))).click();

		try {
			// Locate and click on the button to open the color picker
			WebElement colorPickerButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("color_loc")))); // Replace
																												// with
																												// actual
																												// locator
			colorPickerButton.click();

		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
		}

		driver.findElement(By.xpath(loc.getProperty("save_loc"))).click();
		log.info("Color has selected");

		wait.until(ExpectedConditions.elementToBeClickable(By.name(prop.getProperty("articleCode"))))
				.sendKeys(prop.getProperty("articleCode"));
		log.info("Article code has been entered as " + prop.getProperty("articleCode"));
		driver.findElement(By.xpath(loc.getProperty("uploadImage_loc"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("plusIconUpload_loc"))));

//		upload images
		try {
			// Instantiate Robot class
			Robot rb = new Robot();

			// Add a delay to allow time for the file upload dialog to appear
			rb.delay(2000);

			WebElement fileInput = driver.findElement(By.name("name"));

			// Send the file path to the file input element
			fileInput.sendKeys(System.getProperty("user.dir") + prop.getProperty("imagePath1"));

			Thread.sleep(5000);

			// Send the file path to the file input element
			fileInput.sendKeys(System.getProperty("user.dir") + prop.getProperty("imagePath2"));
			log.info("images uploaded success!");

		} catch (Exception e) {
			log.error(e);
		}
		Thread.sleep(5000);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("save_loc")))).click();
		// save and next
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("saveAndNext_loc")))).click();
		log.info("variant has been saved");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("addSizeSet_loc")))).click();

		try {
			wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy(By.xpath(loc.getProperty("sizeSetCheckbox_loc"))));

			// Locate all checkboxes
			List<WebElement> checkboxes = driver.findElements(By.xpath(loc.getProperty("sizeSetCheckbox_loc")));

			// Check if there are at least two checkboxes
			if (checkboxes.size() >= 2) {
				// Click the first two checkboxes
				WebElement firstCheckbox = checkboxes.get(0);
				WebElement secondCheckbox = checkboxes.get(1);

				// Click the first checkbox if not already selected
				if (!firstCheckbox.isSelected()) {
					firstCheckbox.click();
				}

				// Click the second checkbox if not already selected
				if (!secondCheckbox.isSelected()) {
					secondCheckbox.click();
				}

			} else {
				System.out.println("Less than two checkboxes found.");
			}
		} catch (Exception e) {
			log.error(e);
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("chooseSizeSetButton_loc"))))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("saveAndNextButton_loc")))).click();
		log.info("Size set has selected");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("saveAndNext_loc")))).click();
		log.info("Stocks has saved.");

		// select material info
		try {
			Thread.sleep(3000);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, -1000);");
			// Locate all "Select" elements
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(loc.getProperty("select_loc"))));
			List<WebElement> selectElements = driver.findElements(By.xpath(loc.getProperty("select_loc")));

			for (int i = 0; i < selectElements.size(); i++) {
				// Click on the current "Select" element
				WebElement selectElement = selectElements.get(i);

				selectElement.click();

				// Wait for the dynamic radio buttons to appear
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("radioButtons_loc"))));

				// Locate all radio buttons for the current "Select"
				List<WebElement> radioButtons = driver.findElements(By.xpath(loc.getProperty("radioButtons_loc")));

				// Select the first radio button
				if (!radioButtons.isEmpty()) {
					WebElement firstRadioButton = radioButtons.get(0);
					if (!firstRadioButton.isSelected()) {
						firstRadioButton.click();
					}
				}

				// Click "Next" button
				WebElement nextButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("NEXT_button_loc"))));
				nextButton.click();

				// Wait for the next set of dynamic radio buttons to appear
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("radioButtons_loc"))));

				// Locate all radio buttons for the next set
				List<WebElement> nextRadioButtons = driver.findElements(By.xpath(loc.getProperty("radioButtons_loc")));

				// Select the first radio button from the next set
				if (!nextRadioButtons.isEmpty()) {
					WebElement firstNextRadioButton = nextRadioButtons.get(0);
					if (!firstNextRadioButton.isSelected()) {
						firstNextRadioButton.click();
					}
				}

				// Click "Select" button
				WebElement selectButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("SELECT_button_loc"))));
				selectButton.click();

			}

		} catch (Exception e) {
			log.error(e);
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("saveAndNextButton_loc")))).click();
		log.info("Material has saved.");

		
		
		
		// select attributes
		try {
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, -1000);");

			wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy(By.xpath(loc.getProperty("attributes_name_list_loc"))));
			List<WebElement> selectElements = driver
					.findElements(By.xpath(loc.getProperty("attributes_name_list_loc")));

			for (int i = 0; i < selectElements.size(); i++) {

				WebElement selectElement = selectElements.get(i);
				selectElement.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tab-pane.active")));

				// Find the first radio button in the active tab
				WebElement firstRadioButton = driver.findElement(By.cssSelector(
						".tab-pane.active .productUploadNew_AttributeListing__2ixIJ:first-of-type input[type='radio']"));

				// Click the first radio button
				firstRadioButton.click();

				// Optional: Pause for demonstration purposes
				Thread.sleep(1000);

				Thread.sleep(2000);

			}

		} catch (Exception e) {
			log.error(e);
			;
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("SAVE_loc")))).click();
		log.info("attributes has saved");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("submitForReview_loc")))).click();

	}

}

//Wait for the color picker form to be visible
//WebElement colorPickerForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("colorPickerForm"))); // Replace with actual locator
//
//// Example of selecting a color
//// Locate the color options; assuming they have a class name of 'color-option'
//WebElement colorOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".color-option[data-color='red']"))); // Replace with actual locator
//colorOption.click();
//
//// Optional: Verify the color selection
//WebElement selectedColor = driver.findElement(By.id("selectedColor")); // Replace with actual locator
//String selectedColorValue = selectedColor.getText();
//System.out.println("Selected Color: " + selectedColorValue);

//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[text()='Select']")));
//
//// Locate all "Select" elements
//List<WebElement> selectElements = driver.findElements(By.xpath("//div[text()='Select']"));
//
//// Check if there are at least three "Select" elements
//WebElement selectElement = selectElements.get(0);
//selectElement.click();

// Thread.sleep(2000);
//// Wait for the unique container to be present
//   WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loc.getProperty("active_attribute_tab_loc"))));
//   
//   // Now find all radio buttons within this container
//   List<WebElement> radioButtons = container.findElements(By.xpath(loc.getProperty("radioButtons_loc")));
//
//   if (!radioButtons.isEmpty()) {
//       // Select the first radio button if it's not already selected
//       WebElement firstRadioButton = radioButtons.get(0);
//       if (!firstRadioButton.isSelected()) {
//       	System.out.println(firstRadioButton.getText());
//           firstRadioButton.click();
//       }
//   }

//Optionally wait for any confirmation or changes to complete before proceeding
// For example, wait until the next "Select" button or any other element appears
//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@type='radio']")));

//// Find all tab links and iterate over them
// List<WebElement> tabs = driver.findElements(By.cssSelector(".nav-item"));
// 
// for (WebElement tab : tabs) {
//     // Click on each tab
//     tab.click();
//     
//     // Wait for the content to be loaded
//     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tab-pane.active")));
//     
//     // Find the first radio button in the active tab
//     WebElement firstRadioButton = driver.findElement(By.cssSelector(".tab-pane.active .productUploadNew_AttributeListing__2ixIJ:first-of-type input[type='radio']"));
//     
//     // Click the first radio button
//     firstRadioButton.click();
//     
//     // Optional: Pause for demonstration purposes
//     Thread.sleep(1000);

//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(loc.getProperty("attributes_name_list_loc"))));
//List<WebElement> selectElements = driver.findElements(By.xpath(loc.getProperty("attributes_name_list_loc")));
//
//for (int i = 0; i < selectElements.size(); i++) {
//  
//  WebElement selectElement = selectElements.get(i);
//  selectElement.click();
//  
//  Thread.sleep(2000);
//// Wait for the unique container to be present
//  WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loc.getProperty("active_attribute_tab_loc"))));
//  
//  // Now find all radio buttons within this container
//  List<WebElement> radioButtons = container.findElements(By.xpath(loc.getProperty("radioButtons_loc")));
//
//  if (!radioButtons.isEmpty()) {
//      // Select the first radio button if it's not already selected
//      WebElement firstRadioButton = radioButtons.get(0);
//      if (!firstRadioButton.isSelected()) {
//      	System.out.println(firstRadioButton.getText());
//          firstRadioButton.click();
//      }
//  }
//  Thread.sleep(2000);
//
//}	
//}
//catch(Exception e) {
//log.error(e);
//}

///Users/bijnis/Documents/images/
//try {
////upload images
//WebElement imageInput = driver.findElement(By.xpath(loc.getProperty("plusIconUpload_loc")));		
//String firstImage = "/Users/bijnis/Documents/images/img1.png";
//String secongImage = "/Users/bijnis/Documents/images/img2.png";
////String filePaths = firstImage + "\n" + secongImage;
////imageInput.sendKeys(filePaths);
//imageInput.sendKeys(firstImage);
//
//}
//catch (Exception e) {
//    log.error("failed");
//}
//
//Robot rb=new Robot();
//rb.delay(2000);
////StringSelection ss=new StringSelection(prop.getProperty("html_file_name"));
//StringSelection ss=new StringSelection("/Users/bijnis/Documents/images/img1.png");
//Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
//log.info("file name copied!");
//rb.keyPress(KeyEvent.VK_CONTROL);
//rb.keyPress(KeyEvent.VK_V);
//
//rb.keyRelease(KeyEvent.VK_CONTROL);
//rb.keyRelease(KeyEvent.VK_V);
//rb.keyPress(KeyEvent.VK_ENTER);
//rb.keyRelease(KeyEvent.VK_ENTER);

//StringSelection ss = new StringSelection(filePath);
//
//// Set the file path to the clipboard
//Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//clipboard.setContents(ss, null);

// Simulate Ctrl+V (paste) to paste the file path
//rb.delay(5000);
//rb.keyPress(KeyEvent.VK_SLASH);
//rb.keyRelease(KeyEvent.VK_SLASH);
//Thread.sleep(4000);
//rb.keyPress(KeyEvent.VK_META);
//rb.keyPress(KeyEvent.VK_V);
//rb.keyRelease(KeyEvent.VK_V);
//rb.keyRelease(KeyEvent.VK_META);
//rb.delay(2000);
//// Simulate pressing Enter to confirm the file upload
//rb.keyPress(KeyEvent.VK_ENTER);
//rb.keyRelease(KeyEvent.VK_ENTER);
//log.info("value entered.");
