package testcases;


import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.Reporter;
import wrappers.GenericWrappers;
import wrappers.LinkedInWrappers;

public class TC003_AddSkill extends LinkedInWrappers {
	
	@Test(dataProvider ="fetchData")
	public void addskill(String username, String password,String vuser,String organization, String newskill) throws InterruptedException{
		
		new LoginPage(driver)
		.enterUserName(username,driver)
		.enterPassword(password,driver)
		.clickLogin(driver)
		.verifyUserName(vuser,driver)
		.clickProfile(driver)
		.verifyCurrentOrganization(organization,driver)
		.verifySkillExists(newskill,driver)
		.addAndSaveSkill(newskill,driver)
		.verifySkillExists(newskill,driver)
		.mouseOverIcon(driver)
		.clickSignout(driver);
		
			
	}
	
	@BeforeClass
	public void beforeclass(){
		dataSheetName="TC003";
		browserName="chrome";
		testCaseName="Edit Profile";
		testDescription="Edit profile and add new skill";
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver=new ChromeDriver();
	}
	

}
