package automationjar.testscript;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

public class Bank extends BaseClass {
	
	/*
	 * This test takes a screen shots, scrolling and resizing the page
	 * */
	@Test
	public void taksFullPageSnapShot() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) getDriver(); // take screenshot
		takeSnapShot(getDriver(), "mainPage1.png");
		// scroll down page
		js.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(20000);
		// take screenshot again
		takeSnapShot(getDriver(), "mainPage2.png");
		Thread.sleep(20000);
		// change window size
		getDriver().manage().window().setSize(new org.openqa.selenium.Dimension(500, 500));
		Thread.sleep(20000);
		takeSnapShot(getDriver(), "mainPage3.png");
		assertTrue("ScrennShots has been takes successfully", true);

	}
	/*
	 * This test search valid value, 
	 * Expected result - search result contains the searched value/s
	 * */
	@Test
	public void search() throws Exception {
		
		String searchStr = "Accounts";
		// insert string in search box
		getDriver().findElement(homePage.getSearchBox()).sendKeys(searchStr);
		// click on search img
		getDriver().findElement(By.id("searchImg")).click();
		Thread.sleep(3000);
		// check URl is updated to search result
		assertEquals("https://english.leumi.co.il/SearchResults/", getDriver().getCurrentUrl());
		Thread.sleep(3000);
		// check the search result contains the string
		boolean IsFoundRes = getDriver().findElement(By.id("GSAsearchText_div")).getText().contains(searchStr);
		assertTrue(IsFoundRes);
	}
	
	/*
	 * This test insert invalid concept in search box
	 * expected result  - get massage of "string not found"
	 * */
	@Test
	public void searchIncorrectStr() throws Exception {
		String searchStr = "blablabla";
		// insert invalid value
		getDriver().findElement(homePage.getSearchBox()).sendKeys(searchStr);
		getDriver().findElement(By.id("searchImg")).click();
		Thread.sleep(3000);
		assertEquals("https://english.leumi.co.il/SearchResults/", getDriver().getCurrentUrl());
		Thread.sleep(3000);
		// check if massage appears 
		boolean IsResAsExpected = getDriver().findElement(By.id("GSAsearchText")).getText().contains("did not match any document");
		assertTrue(IsResAsExpected);		
	}

	/*
	 * This test create new google account
	 * */
	@Test
	public void createAccount() throws InterruptedException {
		// navigate to page
		getDriver().get("https://accounts.google.com/signup/v2/webcreateaccount?biz=false&cc=IL&continue=https%3A%2F%2Fmail.google.com&dsh=S1258467296%3A1678791772632534&flowEntry=SignUp&flowName=GlifWebSignIn&hl=iw&service=mail&authuser=0");
		// fill all fields
		getDriver().findElement(By.id("firstName")).sendKeys("first name");
		getDriver().findElement(By.id("lastName")).sendKeys("last name");
		getDriver().findElement(By.id("username")).sendKeys("mymavenpr");
		getDriver().findElement(By.name("Passwd")).sendKeys("123qwert!");
		getDriver().findElement(By.name("ConfirmPasswd")).sendKeys("123qwert!");
		//click next - to create account
		getDriver().findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div/div/button/span")).click();
		Thread.sleep(10000);
		boolean isAccountCreated = getDriver().getCurrentUrl().startsWith("https://accounts.google.com/signup/v2/webgradsidvphone?biz=false");
		assertTrue("Account has been created succesfully", isAccountCreated);
		Thread.sleep(10000);
	}
	
	/*
	 * This test using check-box
	 * */
	@Test
	public void useComboBox() throws InterruptedException {
		//combobox object
		Select options = new Select(getDriver().findElement(By.name("selObj")));
		options.selectByVisibleText("Leumi Israel Online");
		Thread.sleep(10000);		
		// check if combobox selection navigate to expected page
		boolean IsUrlUpdated = getDriver().getCurrentUrl().startsWith("https://hb2.bankleumi.co.il/staticcontent/gate-keeper/en/");
		assertTrue("using combobox options", IsUrlUpdated);
	}
	
	/*
	 * This test login to bank account with incorrect details
	 * */
	@Test
	public void logIn() {
		LoginPage logPage = new LoginPage(getDriver());

		getDriver().get(logPage.getUrl());
		getDriver().findElement(logPage.getUserName()).sendKeys("usename");
		getDriver().findElement(logPage.getPasswd()).sendKeys("123456789aaa");
		getDriver().findElement(logPage.getLoginBtn()).click();
		
		assertTrue("login with incorrect details - as expected", logPage.verifyIncorrectDetailsMsg());
	}
	

	/*
	 * This test verify the date is updated
	 * */
	@Test
	public void verifyDate() {
		// get current date and format it 
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    Date date = new Date();
	    String timeStr = format.format(date);

		System.out.println(timeStr);
		getDriver().get(homePage.getUrl());
		// compare with the site value
		boolean IsDateUpdated = getDriver().findElement(homePage.getDateAndT()).getText().startsWith(timeStr);
		
		Assert.assertTrue("Date is updated", IsDateUpdated);
	}
	
	/*
	 * This test navigate between pages
	 * */
	@Test
	public void siteNavigation() throws InterruptedException {
		getDriver().get(homePage.getUrl());
		Thread.sleep(3000);
		getDriver().findElement(homePage.getPrivateTab()).click();
		Thread.sleep(3000);
		getDriver().navigate().back();
		Assert.assertEquals(getDriver().getCurrentUrl(), homePage.getUrl());
		// refresh page
		getDriver().navigate().refresh();
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getCurrentUrl(), homePage.getUrl());

		// navigate forward
		getDriver().navigate().forward();
		Thread.sleep(3000);
		Assert.assertEquals("https://english.leumi.co.il/FigBusiness/Private_Banking/3238/", getDriver().getCurrentUrl());
	}
	
	/*
	 * This test navigate between main tabs
	 * */
	@Test
	public void tabsNavigation() throws InterruptedException {
		getDriver().findElement(homePage.getPrivateTab()).click();
		Thread.sleep(5000);
		Assert.assertEquals("https://english.leumi.co.il/FigBusiness/Private_Banking/3238/", getDriver().getCurrentUrl());
		
		// navigate to global tab
		getDriver().findElement(homePage.getGlobalTab()).click();
		Thread.sleep(5000);
		Assert.assertEquals("https://english.leumi.co.il/LEWins/Global_Services/3289/", getDriver().getCurrentUrl());
		
		// navigate to business tab

		getDriver().findElement(homePage.getBussinessTab()).click();
		Thread.sleep(5000);
		Assert.assertEquals("https://english.leumi.co.il/WnnnWn/Business_Banking/21985/", getDriver().getCurrentUrl());		
	}
	
	/*
	 * This test check the home page title is correct*/
	@Test
	public void verifyHomePageTitle() {
		
		Assert.assertTrue("Home page title doesn't match", homePage.verifyHomePageTitle());
	}
	/*
	 * This test copy paragraph from wiki site
	 * */
	@Test
	public void copyParagraph() {
		//navigate to site
		getDriver().get("https://www.hamichlol.org.il/%D7%A1%D7%9C%D7%A0%D7%99%D7%95%D7%9D");
		String paragraph = getDriver().findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[6]")).getText();
		try {
			// copy paragraph to file
			FileWriter writer = new FileWriter(System.getProperty("user.dir")+"//selenium_paragraph.txt");
			writer.write(paragraph);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
