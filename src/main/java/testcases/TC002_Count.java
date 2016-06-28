package testcases;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.Reporter;
import wrappers.GenericWrappers;
import wrappers.LinkedInWrappers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class TC002_Count extends LinkedInWrappers{
	@Test(dataProvider="fetchData")
	
	public void verifyCount(String userName, String passWord, 
			String vUser,String data) throws InterruptedException{
		
		new LoginPage(driver)
		.enterUserName(userName,driver)
		.enterPassword(passWord,driver)
		.clickLogin(driver)
		.verifyUserName(vUser,driver)
		.clickAdvanced(driver)
		.clickonCloseButton(driver)
		.closeSecondTab(driver)
		.closeSecondTab(driver)
		.matchCountConn(driver)
		.mouseOverIcon(driver)
		.clickSignout(driver);
	
	}
	
	@BeforeClass
	public void beforeClass() {
		dataSheetName="TC002";
		browserName="chrome";
		testCaseName="verify the first connection count";
		testDescription="Login and Logout in LinkedIn";
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver=new ChromeDriver();
	}

	
	
	
	
	
	
}
