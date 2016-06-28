package pages;



import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;


public class LoginPage extends LinkedInWrappers{

	public LoginPage(){

		if(!verifyTitle("World’s Largest Professional Network | LinkedIn"))
			Reporter.reportStep("This is not Login Page", "FAIL",driver);
	}
	public LoginPage(RemoteWebDriver driver){

		if(!verifyTitle("World’s Largest Professional Network | LinkedIn",driver))
			Reporter.reportStep("This is not Login Page", "FAIL",driver);
	}

	public LoginPage enterUserName(String username,RemoteWebDriver driver) {
		enterById(prop.getProperty("Login.UserName.Id"), username,driver);
		return this;
	}

	public LoginPage enterPassword(String password,RemoteWebDriver driver) throws InterruptedException {
		enterById(prop.getProperty("Login.Password.Id"), password,driver);
		Thread.sleep(3000);
		return this;
	}

	public HomePage clickLogin(RemoteWebDriver driver) throws InterruptedException  {
		
		clickByXpath(prop.getProperty("Login.LoginButton.Xpath"),driver);
		
		return new HomePage(driver);
	}
	

	public LoginPage verifyErrorMessage(String data,RemoteWebDriver driver) {
		verifyTextContainsByXpath(("//*[@id='global-alert-queue']/div/p/strong"), data,driver);
		
	
		return this;
	}

	public LoginPage verifyPasswrdError(String data,RemoteWebDriver driver) {
		
		verifyTextContainsByXpath(("//*[@id='session_password-login-error']"), data,driver);
		
	
		return this;
	}
	public LoginPage mouseOverIcon(RemoteWebDriver driver){
		mouseOverByXpath(prop.getProperty("HomePage.Signout.Xpath"),driver);
		return this;
	}
	
	public LogoutPage clickSignout(RemoteWebDriver driver) {
		clickByLink(prop.getProperty("HomePage.Signout.Linktext"),driver);
		return new LogoutPage(driver) ;
	}
	
















}
