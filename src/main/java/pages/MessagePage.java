package pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class MessagePage extends LinkedInWrappers {

	public MessagePage(RemoteWebDriver driver) {
	
		if (!verifyTitle("Messaging | LinkedIn",driver))
			Reporter.reportStep("This is not Job Result Page", "FAIL",driver);
	}
	
	public MessagePage enterSenderName(String data,RemoteWebDriver driver)
	{
		enterByIdLink(prop.getProperty("MessagePage.Name.Id"), data,driver);
		return this;
	}
	
	public MessagePage enterMessage(String data,RemoteWebDriver driver)
	{
		enterByIdLink(prop.getProperty("MessagePage.Message.Id"), data,driver);
		return this;
	}
	public MessagePage unCheckEnter(RemoteWebDriver driver)
	{
		clickById(prop.getProperty("MessagePage.UnCheck.Id"),driver);
		return this;
	}
	public MessagePage clickSendMessage(RemoteWebDriver driver)
	{
		clickByXpath(prop.getProperty("MessagePage.clickSend.Xpath"),driver);
		return this;
	}
	public MessagePage mouseOverIcon(RemoteWebDriver driver){
		mouseOverByXpath(prop.getProperty("HomePage.Signout.Xpath"),driver);
		return this;
	}
	
	public LogoutPage clickSignout(RemoteWebDriver driver) throws InterruptedException {
		clickByLink(prop.getProperty("HomePage.Signout.Linktext"),driver);
		Thread.sleep(3000);
		return new LogoutPage(driver) ;
	}

	public MessagePage verifyMessage2(String classval,String message,RemoteWebDriver driver){
		List<WebElement> allitems = null;
		try{
		allitems = driver.findElementsByClassName(classval);
		for (WebElement web : allitems) {
			if(web.getText().equalsIgnoreCase(message))
			{
				Reporter.reportStep("Message is Sent : "+message, "PASS",driver);
				break;
			}
			
		}
		}
		catch(NoSuchElementException e){
			//System.out.println("No such element found");
			e.printStackTrace();
			Reporter.reportStep("No such element found", "FAIL",driver);
		}		
		catch (Exception e) {
			//System.out.println("Some Error Occured while performing listOfAllElementsByClassName() method");
			e.printStackTrace();
			Reporter.reportStep("Error occured for listOfAllElementsByClassName() ", "FAIL",driver);
		}
		
		return this;
		
	}
	
	
}
