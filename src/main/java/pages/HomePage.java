package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class HomePage extends LinkedInWrappers{

	public HomePage(RemoteWebDriver driver){
		if(!verifyTitle("Welcome! | LinkedIn",driver))
		Reporter.reportStep("This is not Home Page", "FAIL",driver);
	}

	public HomePage verifyUserName(String data,RemoteWebDriver driver){
		verifyTextContainsByXpath(prop.getProperty("HomePage.UserName.Xpath"), data,driver);
		return this;
	}

	public HomePage mouseOverMessageIcon(RemoteWebDriver driver) throws InterruptedException{
		Thread.sleep(3000);
		mouseOverByXpath(prop.getProperty("HomePage.Message.Xpath"),driver);
		Thread.sleep(3000);
		return this;
	}
	
	public MessagePage clickNewMessage(RemoteWebDriver driver)
	{
		clickByXpath(prop.getProperty("HomePage.Message.New"),driver);
		return new MessagePage(driver);
	}
	

	public ProfilePage clickProfile(RemoteWebDriver driver) throws InterruptedException {
		clickByLink(prop.getProperty("HomePage.Profile.Linktext"),driver);
		return new ProfilePage(driver);
	}
	
	public JobPage clickJobs(RemoteWebDriver driver) throws InterruptedException {
		clickByLink(prop.getProperty("HomePage.Jobs.Linktext"),driver);
		Thread.sleep(5000);
		return new JobPage(driver);
	}
	
	public AdvancedSearch clickAdvanced(RemoteWebDriver driver) throws InterruptedException {
		clickByLink(prop.getProperty("HomePage.Advanced.Linktext"),driver);
		return new AdvancedSearch(driver) ;
	}
	
	public HomePage moouseOverIcon(RemoteWebDriver driver){
		mouseOverByXpath(prop.getProperty("HomePage.Signout.Xpath"),driver);
		return this;
	}
	
	public LogoutPage clickSignout(RemoteWebDriver driver) {
		clickByLink(prop.getProperty("HomePage.Signout.Linktext"),driver);
		return new LogoutPage(driver) ;
	}
	
}
