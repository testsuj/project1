package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class JobPage extends LinkedInWrappers {

	
	public JobPage(RemoteWebDriver driver){
		if(!verifyTitleContains("Jobs Home | LinkedIn", driver))
			Reporter.reportStep("This is not Job Page", "FAIL",driver);
	}
	
	public JobPage closeArea(RemoteWebDriver driver)
	{
		clickByXpath(prop.getProperty("Job.LocationClose.Xpath"),driver);
		return this;
	}
	
	
	public JobPage enterKeyWord(String data,RemoteWebDriver driver) {
		enterById(prop.getProperty("Job.KeyWord.Id"), data,driver);
		return this;
	}
	public JobPage sleepJobPage()
	{
		try
		{
			Thread.sleep(1000);
		
		}
		catch(Exception e)
		{
			System.out.println("Thread.sleep exception");
		
		}
		return this;
	}
	
	public JobResultsPage clickSearch(RemoteWebDriver driver) {
		clickByXpath(prop.getProperty("Job.SearchButton.Xpath"),driver);
		try
		{
			Thread.sleep(1000);
		
		}
		catch(Exception e)
		{
			System.out.println("Thread.sleep exception");
		
		}
		return new JobResultsPage(driver);
	}
	
	
	

}
