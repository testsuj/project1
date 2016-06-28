package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;


public class AdvancedSearch extends LinkedInWrappers{

	public AdvancedSearch(RemoteWebDriver driver) throws InterruptedException {
	
		
	}	
	
	public AdvancedSearch enterSearchKeyword(String data,RemoteWebDriver driver) {
		enterById(prop.getProperty("AdvancedSearchPage.SearchKeyword.Id"), data,driver);
		return this;
	}

	public AdvSearchResult clickonSearchButton(RemoteWebDriver driver) throws InterruptedException {
		clickByClassName(prop.getProperty("AdvancedSearchPage.ClickSearch.Classname"),driver);
		Thread.sleep(1000);
		return new AdvSearchResult(driver);
	}
	
	public AdvSearchResult clickonCloseButton(RemoteWebDriver driver) throws InterruptedException {
	
		clickByXpath(prop.getProperty("AdvancedSearchPage.ClickClose.Xpath"),driver);
		
		
		return new AdvSearchResult(driver);
	}
	
	public AdvancedSearch enterCompanyName(String data,RemoteWebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		enterById(prop.getProperty("AdvancedSearchPage.EnterCompany.Id"), data,driver);
		return this;
	}
	
	public AdvancedSearch select(String value,RemoteWebDriver driver)
	{
		selectById(prop.getProperty("AdvancedSearchPage.SelectValue.Id"), value,driver);
		return this;
	}
	public AdvancedSearch clickReset(RemoteWebDriver driver)
	{
		clickByXpath(prop.getProperty("AdvancedSearchPage.ClickReset.Xpath"),driver);
		return this;
	}
	public AdvancedSearch companyNameClear(RemoteWebDriver driver) throws InterruptedException
	{
		Thread.sleep(1000);
		verifyCleared(prop.getProperty("AdvancedSearchPage.EnterCompany.Id"),driver);
		return this;
	}
	public AdvancedSearch checkFirstConn(RemoteWebDriver driver) throws InterruptedException
	{
		Thread.sleep(1000);
		clickById(prop.getProperty("AdvancedSearchPage.CheckFirst.Id"),driver);
		return this;
	}
	public AdvancedSearch scrollToUP2(RemoteWebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
        return this;
    }
	
	public AdvancedSearch moouseOverIcon(RemoteWebDriver driver){
		mouseOverByXpath(prop.getProperty("HomePage.Signout.Xpath"),driver);
		return this;
	}
	
	public LogoutPage clickSignout(RemoteWebDriver driver) {
		clickByLink(prop.getProperty("HomePage.Signout.Linktext"),driver);
		return new LogoutPage(driver) ;
		
	
	}
	
	
	
	
}


