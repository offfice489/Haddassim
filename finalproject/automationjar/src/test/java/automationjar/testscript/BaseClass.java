package automationjar.testscript;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	
	private WebDriver driver;
	protected HomePage homePage;
	@Before
	/*
	 * */
	public void setUp() {
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "//drivers//chromedriver.exe"); // path to webdriver.exe
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // open chrome on full screen
		driver.get("https://english.leumi.co.il/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		homePage = new HomePage(driver);
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	public String getSnapShotFolderPath() {
		String path = System.getProperty("user.dir");
		return path + "//snapShots//";
	}
	
	@After
	public void cleanUp() {
		this.driver.close();
	}
	
	public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination
		File DestFile = new File(getSnapShotFolderPath() + fileWithPath);

		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);

	}

	public HomePage getHomePage() {
		return homePage;
	}
}
