package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class CompanyDetailsPage extends LinkedInWrappers{
	
	protected String companyName="";
	
	
	public CompanyDetailsPage(String compName,RemoteWebDriver driver) {
		companyName=compName+": Careers & Employment | LinkedIn";
	}
	
	public CompanyDetailsPage(RemoteWebDriver driver) {
		if(!verifyTitle(companyName,driver))
			Reporter.reportStep("This is not Company Details Page", "FAIL",driver);
	}	
	
	public CompanyDetailsPage getEmployeeCount(RemoteWebDriver driver){
	
	String EmpCount=getTextByXpath(prop.getProperty("Company.EmployeesCount.Xpath"),driver);
	String rangeEmp=stringRange(EmpCount);
	Reporter.reportStep("Employee Count : "+ rangeEmp,"PASS",driver);
	
	return this;
	}

	public CompanyDetailsPage moouseOverIcon(RemoteWebDriver driver){
		mouseOverByXpath(prop.getProperty("HomePage.Signout.Xpath"),driver);
		return this;
	}
	
	public LogoutPage clickSignout(RemoteWebDriver driver) {
		clickByLink(prop.getProperty("HomePage.Signout.Linktext"),driver);
		return new LogoutPage(driver) ;
	}

	
}
