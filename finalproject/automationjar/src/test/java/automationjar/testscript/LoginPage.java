package automationjar.testscript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	protected WebDriver driver;

	private String loginPageUrl = "https://hb2.bankleumi.co.il/staticcontent/gate-keeper/en/?trackingCode=ca470ba4-390f-428e-7238-dcc9ae17d289&sysNum=23&langNum=2&proxyName=PRD_TLV";
	private By userName = By.xpath("/html/body/div[1]/main/div/section/div/div/div[1]/div/div[2]/form/div[1]/div/div/div[1]/input");
	private By passwd = By.xpath("/html/body/div[1]/main/div/section/div/div/div[1]/div/div[2]/form/div[2]/div/div/div[1]/input");
	private By loginBtn = By.xpath("/html/body/div[1]/main/div/section/div/div/div[1]/div/div[2]/form/div[3]/button");
	private By incorrectDetailsMassage = By.xpath("/html/body/div[1]/main/div/section/div/div/div[1]/div/div[2]/form/div[3]/div/div");

	public String getUrl() {
		return loginPageUrl;
	}
	public By getUserName() {
		return userName;
	}
	public By getPasswd() {
		return passwd;
	}
	public By getLoginBtn() {
		return loginBtn;
	}
	
	public void loginClick() {
	}
	public boolean verifyIncorrectDetailsMsg() {
		return driver.findElement(incorrectDetailsMassage).isDisplayed();
	}


}
