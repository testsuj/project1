package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class LogoutPage extends LinkedInWrappers {
	public LogoutPage(RemoteWebDriver driver){
	
		if(!verifyTitle("Signed Out | LinkedIn",driver))
			Reporter.reportStep("This is not Home Page", "FAIL",driver);
		else
			Reporter.reportStep("This is Signout Page", "PASS",driver);
		
	}	
}
