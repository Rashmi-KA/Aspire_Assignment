
import org.testng.annotations.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Aspire_Assignment {
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");


	Random rand = new Random();
	Scanner keyboard = new Scanner(System.in);
	// Generate random integers in range 0 to 999
	int rand_int1 = rand.nextInt(1000);
	WebElement username,pwd,btn,InvenIcon,InvenTitle,ProdIcon,Proddrpdwn,PrdPageTitle,btnCreate,Prodname,btnSave
	,btnUpdatQunty,ValProdname,lstLocation,lstPackage,edtQuant,ManufactTitle,btnproductId,txtProdID,lnkAddline,lstProd
	,edtConsume,btnConfirm,btnmark_as_done,btnApply,btnOperations,btnManOrders;
	String prodname = "Test_"+rand_int1,workordernum;
	WebDriver driver;

	@Test
	//This function 
	public void LaunchURL() throws InterruptedException {
		driver = new ChromeDriver();

		WebDriverWait wait1 = new WebDriverWait(driver, 10);

		//Setting up the chrome driver exe, the second argument is the location where you have kept the driver in your system
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\santhosh\\Desktop\\Workspaces\\AspireProj\\AspireTask\\chromedriver.exe");

		//Setting the driver to chrome driver
		String url = "https://aspireapp.odoo.com";
		driver.get(url);
		//driver.wait(2);
		driver.manage().window().maximize();

		username = driver.findElement(By.xpath("//input[@id=\"login\"]"));
		pwd = driver.findElement(By.xpath("//input[@id=\"password\"]"));
		btn = driver.findElement(By.xpath("//button[@type=\"submit\"]"));	


		username.sendKeys("user@aspireapp.com");
		pwd.sendKeys("@sp1r3app");
		btn.click();
		InvenIcon =wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"result_app_1\"]/div[1]")));
		//InvenIcon = driver.findElement(By.xpath("//*[@id=\"result_app_1\"]/div[1]"));
		if(InvenIcon.isDisplayed()) {
			System.out.println("Logged in successfully");
			Reporter.log("Logged in successfully");
		}else {
			Reporter.log("Unable to login to the app");
		}
		InvenIcon.click();
		InvenTitle = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Inventory Overview']")));

		if(InvenTitle.isDisplayed()) {
			System.out.println("Inventory page loaded successfully");
			Reporter.log("Inventory page loaded successfully");
		}else {
			Reporter.log("Unable to load the Inventory page");
		}
		//Create a product
		ProdIcon = driver.findElement(By.xpath("//a[contains(text(),\"Products\")]"));
		ProdIcon.click();
		Proddrpdwn = driver.findElement(By.xpath("//span[contains(text(),\"Products\")]"));

		Proddrpdwn.click();
		PrdPageTitle  = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Products')]")));
		btnCreate= driver.findElement(By.xpath("//button[contains(text(),\"Create\")]"));
		if(PrdPageTitle.isDisplayed()) {
			Reporter.log("Product page loaded successfully");
		}else {
			Reporter.log("Unable to load the Product page");
		}
		btnCreate.click();

		Prodname  = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = \"Product Name\"]")));
		Prodname.sendKeys(prodname);
		btnSave = driver.findElement(By.xpath("//button[contains(text(),\"Save\")]"));
		btnSave.click();
		ValProdname =wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='o_field_char o_field_widget o_required_modifier']")));
		if(ValProdname.getText().equals(prodname)) {
			Reporter.log("Product name  successfully created"+prodname);
		}else {
			Reporter.log("Product name  successfully not  created"+prodname);

		}
		btnUpdatQunty = driver.findElement(By.xpath("//button[@name=\"action_update_quantity_on_hand\"]"));
		btnUpdatQunty.click();
		WebElement buttonCreate = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary o_list_button_add']")));
		buttonCreate.click();
		lstLocation =  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@class=\"o_input ui-autocomplete-input\"])[1]")));
		lstLocation.click();
		WebElement lst = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"WH/Stock\"]")));
		lst.click();

		//		loc.selectByVisibleText("WH/Stock");
		WebElement lstPackage = driver.findElement(By.xpath("(//input[@class=\"o_input ui-autocomplete-input\"])[2]"));
		lstPackage.click();
		WebElement lst2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"1\"]")));
		lst2.click();
		edtQuant = driver.findElement(By.xpath("//input[@name=\"inventory_quantity\"]"));

		edtQuant.click();
		edtQuant.sendKeys("14");
		WebElement btnSve = driver.findElement(By.xpath("//button[@class=\"btn btn-primary o_list_button_save\"]"));
		btnSve.click();

		//logout function	  
		//driver.close();	
	}


	@Test
	public void Manufacturing() {
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement App = driver.findElement(By.xpath("//a[@title='Applications']"));
		App.click();
		WebElement Manufactele = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"result_app_2\"]/div[1]")));
		Manufactele.click();
		ManufactTitle = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Manufacturing Orders')]")));
		if(ManufactTitle.isDisplayed()) {
			Reporter.log("Manufacturing page loaded successfully",true);
		}else
		{
			Reporter.log("Manufacturing page not loaded successfully", false);
		}
		btnCreate= driver.findElement(By.xpath("//button[contains(text(),\"Create\")]"));
		btnCreate.click();
		btnproductId = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@name=\"product_id\"]")));
		txtProdID = driver.findElement(By.xpath("(//input[@class='o_input ui-autocomplete-input'])[1]"));
		txtProdID.sendKeys(prodname);
		WebElement proddrp =  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='ui-menu-item'])[1]")));
		proddrp.click();

		lnkAddline = driver.findElement(By.xpath("(//a[text()=\"Add a line\"])[1]") );
		lnkAddline.click();
		lstProd = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div/input[@class=\"o_input ui-autocomplete-input\"])[6]")));
		lstProd.sendKeys(prodname);
		WebElement proddrpdwn =  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='ui-menu ui-widget ui-widget-content ui-autocomplete ui-front'][09]/li[1]")));
		proddrpdwn.click();
		//	lstProd.sendKeys(Keys.ENTER);
		edtConsume = driver.findElement(By.xpath("//input[@name='product_uom_qty']"));
		edtConsume.clear();
		edtConsume.sendKeys("10.00");
		btnConfirm =  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Confirm\"]")));
		btnConfirm.click();
		btnmark_as_done = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Mark as Done\"]")));
		if(btnmark_as_done.isDisplayed()) {
			Reporter.log("Order confirmed successfully",true);
		}else
		{
			Reporter.log("Order not confirmed ", false);
		}

		btnSave = driver.findElement(By.xpath("//button[@class=\"btn btn-primary o_form_button_save\"]"));
		btnmark_as_done.click();
		btnApply = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Apply\"]")));
		if(btnApply.isDisplayed()) {
			btnApply.click();
		}

	}
	@Test
	public void ValidateDone() {
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement WorkOrder = driver.findElement(By.xpath("//li[@class='breadcrumb-item active']"));
		workordernum = WorkOrder.getText();
		btnOperations = driver.findElement(By.xpath("//a[contains(text(),'Operations')]"));
		btnOperations.click();
		btnManOrders = driver.findElement(By.xpath("(//span[contains(text(),'Manufacturing Orders')])[1]"));
		btnManOrders.click();
		ManufactTitle = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Manufacturing Orders')]")));
		if(ManufactTitle.isDisplayed()) {
			Reporter.log("Manufacturing page loaded successfully",true);
		}else
		{
			Reporter.log("Manufacturing page not loaded successfully", false);
		}

		WebElement btndel = driver.findElement(By.xpath("//i[@title='Remove']"));

		btndel.click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search...']"));
		search.sendKeys(workordernum);
		search.sendKeys(Keys.ENTER);

		WebElement refNum = driver.findElement(By.xpath("//tr[@class='o_data_row']/td[3]"));
		WebElement state = driver.findElement(By.xpath("//tr[@class='o_data_row']/td[10]/span"));
		if(refNum.getText().equals(workordernum) && state.getText().equalsIgnoreCase("Done")) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
				File destFile = new File((String) reportDirectory+"/failure_screenshots/"+"_"+formater.format(calendar.getTime())+".png");
				FileUtils.copyFile(scrFile, destFile);
				Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");

				Reporter.log("Created product Id is updated with Done status "+ prodname +"with workorder "+ workordernum,true);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else
		{
			Reporter.log("Created product Id is updated with Done status "+ prodname +"with workorder "+ workordernum, false);
		}
	}

}