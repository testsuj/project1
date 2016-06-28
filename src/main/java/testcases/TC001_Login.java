package testcases;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.Reporter;
import wrappers.GenericWrappers;
import wrappers.LinkedInWrappers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;

public class TC001_Login extends LinkedInWrappers{

	@Test(dataProvider="fetchData")
	public void login(String userName, String passWord, 
			String vUser,String data) throws InterruptedException {
		
		
		new LoginPage(driver)
		.enterUserName(userName,driver)
		.enterPassword(passWord,driver)
		.clickLogin(driver)
		.verifyUserName(vUser,driver)
		.clickAdvanced(driver)
		.enterSearchKeyword(data,driver)
		.clickonSearchButton(driver)
		.numberOfResults(driver)
		.printFirstName(driver)
		.printConnectionNumber(driver)
		.mouseOverIcon(driver)
		.clickSignout(driver);
	}
	@BeforeClass
	public void beforeClass() {
		dataSheetName="TC001";
		browserName="chrome";
		testCaseName="Login to LinkedIn";
		testDescription="Login and Logout in LinkedIn";
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver=new ChromeDriver();
		
		
		
	}

	
	
	
	
	
	
}
