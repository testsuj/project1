package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class JobDetailsPage extends LinkedInWrappers
{
	protected String jobTitle ="";
	protected String companyName="";
	protected String companyLocation="";
	
	public JobDetailsPage(RemoteWebDriver driver)
	{
		String title = getCurrentPageTitle(driver);
		
			if(!verifyTitleContains(title,driver))
			{
					Reporter.reportStep("This is not Job Details Page", "FAIL",driver);
			}			
	}
	
	public JobDetailsPage(String jobName, String compName, String compLoc)
	{
		this.jobTitle	= jobName;
		this.companyName = compName;
		this.companyLocation = compLoc;
	
	if(!verifyTitle(jobName + " at " + compName + " in " + compLoc+ " | LinkedIn"))
			{
					Reporter.reportStep("This is not Job Details Page", "FAIL",driver);
			}			
	}

	public JobDetailsPage printCompanyName(RemoteWebDriver driver) 
	{
		companyName=getTextByXpath(prop.getProperty("JobDetailsPage.PrintCompanyName.Xpath"),driver);
		return this;		
	}

	public CompanyDetailsPage clickCompanyLink(RemoteWebDriver driver) throws InterruptedException {
        
		clickByXpath(prop.getProperty("JobDetailsPage.ClickCompanyLink.Xpath"),driver);
		return new CompanyDetailsPage(companyName,driver);
	}
}


