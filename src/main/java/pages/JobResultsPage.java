package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;


public class JobResultsPage extends LinkedInWrappers{

	String jobTitle="",companyName="",companyLocation="";
	// Verify the Job Result Page Title
	public JobResultsPage (RemoteWebDriver driver){
		if (!verifyTitle("Search | LinkedIn"))
			Reporter.reportStep("This is not Job Result Page", "FAIL",driver);
	}
	
	// Verify the color of the First View button is blue
	public JobResultsPage checkColorValue(RemoteWebDriver driver) throws InterruptedException{
		Thread.sleep(2000);
		getFirstLinkAndVerifyCss(prop.getProperty("JobResultsPage.FirstView.Xpath"),"background-color",driver);
		return this;		
	}

	//Click View on the second Job
	public JobDetailsPage clickSecondViewButton(RemoteWebDriver driver) throws InterruptedException{
		clickByXpath(prop.getProperty("JobResultsPage.SecondView.Xpath"),driver);
		Thread.sleep(3000);
		//return new JobDetailsPage(jobTitle,companyName,companyLocation);
		return new JobDetailsPage(driver);
			
	}
	public JobResultsPage getJobTitle(RemoteWebDriver driver)
	{
		jobTitle=getTextByXpath(prop.getProperty("//li[@data-li-position='1']//h3/a"),driver);
		return this;
	}
	public JobResultsPage getCompanyName(RemoteWebDriver driver)
	{
		companyName=getTextByXpath(prop.getProperty("//li[@data-li-position='1']//bdi/a"),driver);
		return this;
	}
	public JobResultsPage getCompanyLocation(RemoteWebDriver driver)
	{
		companyLocation=getTextByXpath(prop.getProperty("//li[@data-li-position='1']//dd/bdi"),driver);
		return this;
	}
	
}
