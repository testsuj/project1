package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class AdvSearchResult extends LinkedInWrappers  {
	
	public AdvSearchResult(RemoteWebDriver driver) throws InterruptedException{
		
		if(!verifyTitle("Search | LinkedIn",driver))
			Reporter.reportStep("This is not advance search Page", "FAIL",driver);
		Thread.sleep(2000);
	}
	
	public AdvSearchResult numberOfResults(RemoteWebDriver driver){
		String text =getTextByXpath(prop.getProperty("AdvancedResults.SearchResults.Xpath"),driver);
		text=text.replaceAll("[^0-9]","");
		Reporter.reportStep("No of Results : "+text, "PASS",driver);
		return this;
	}
	
	public AdvSearchResult printFirstName(RemoteWebDriver driver){
		String text1 =getTextByXpath(prop.getProperty("AdvancedResults.FirstName.Xpath"),driver);	
	//	System.out.println(text1);
		Reporter.reportStep("First connection name is : "+text1, "PASS",driver);
		return this;
	}


	public AdvSearchResult mouseOverIcon(RemoteWebDriver driver){
		mouseOverByXpath(prop.getProperty("HomePage.Signout.Xpath"),driver);
		return this;
	}
	
	public LogoutPage clickSignout(RemoteWebDriver driver) throws InterruptedException {
		clickByLink(prop.getProperty("HomePage.Signout.Linktext"),driver);
		Thread.sleep(5000);
		return new LogoutPage(driver) ;
	}
	public AdvSearchResult printConnectionNumber(RemoteWebDriver driver) {

		String number = getTextByXpath(prop.getProperty("AdvancedResults.ConnectionNum.Xpath"),driver);
		String conn = getTextByXpath(prop.getProperty("AdvancedResults.ConnectionConn.Xpath"),driver);
		String contact = number+conn;
		if(contact!=null){
		Reporter.reportStep("First connection  is : "+contact, "PASS",driver);
		}
		return this;
	}
	
	
	public AdvSearchResult closeSecondTab(RemoteWebDriver driver) throws InterruptedException {

		clickByXpath(prop.getProperty("AdvancedResults.SecondConnButton.Xpath"),driver);
		Thread.sleep(5000);
		return this;
	}
	
	public AdvSearchResult closeThirdTab(RemoteWebDriver driver) {

		clickByXpath(prop.getProperty("AdvancedResults.GroupMembersButton.Xpath"),driver);
		return this;
	}
	
	
	public AdvSearchResult matchCountConn(RemoteWebDriver driver) throws InterruptedException
	{
		Thread.sleep(3000);
		checkCount(prop.getProperty("AdvancedResults.FirstConnCount.Xpath"), prop.getProperty("AdvancedResults.SideBarFirstConnCount.Xpath"),driver);
		return this;
	}
	
}

	
	
	
