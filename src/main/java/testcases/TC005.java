package testcases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.LinkedInWrappers;

public class TC005 extends LinkedInWrappers{
	@Test(dataProvider="fetchData")
	public void login(String userName, String passWord, 
			String vUser,String data) throws InterruptedException {

		new LoginPage(driver)
		.enterUserName(userName,driver)
		.enterPassword(passWord,driver)
		.clickLogin(driver)
		.verifyUserName(vUser,driver)
		.clickAdvanced(driver).
		enterCompanyName(data,driver).
		select("Located in or near:",driver)
		.clickReset(driver).
		companyNameClear(driver).scrollToUP2(driver)
		.checkFirstConn(driver)
		.clickonSearchButton(driver)
		.mouseOverIcon(driver)
		.clickSignout(driver);
		
	}
	@BeforeClass
	public void beforeClass() {
		dataSheetName="TC005";
		browserName="chrome";
		testCaseName="Job Search in LinkedIn";
		testDescription="Login and Jobsearch in LinkedIn";
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver=new ChromeDriver();
	}

	
	
	
	
	
	
}
