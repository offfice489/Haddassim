package automationjar.testscript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object encapsulates the Home Page
 */
public class HomePage {
	protected WebDriver driver;

	private By privateTab = By.id("m3238");
	private By bussinessTab = By.id("m21985");
	private By globalTab = By.id("m3289");
	private String Url = "https://english.leumi.co.il/";
	private By dateAndT = By.id("dateTime");
	private By searchBox = By.id("GSAsearchInput");
	
	public By getSearchBox() {
		return searchBox;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public By getPrivateTab() {
		return privateTab;
	}

	public By getBussinessTab() {
		return bussinessTab;
	}

	public By getGlobalTab() {
		return globalTab;
	}
	
	public String getUrl() {
		return Url;
	}
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPageTitle(){
		String title = driver.getTitle();
		return title;
	}
	
	public boolean verifyHomePageTitle() {
		String expectedPageTitle="Leumi International";
		System.out.println(getPageTitle());
		return getPageTitle().contains(expectedPageTitle);
	}

	public By getDateAndT() {
		return dateAndT;
	}
}
