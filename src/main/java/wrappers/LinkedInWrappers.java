package wrappers;

import java.io.FileNotFoundException;
import pages.LogoutPage;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import utils.DataInputProvider;
import utils.Reporter;

public class LinkedInWrappers extends GenericWrappers {
	
	protected String browserName;
	protected String dataSheetName;
	protected static String testCaseName;
	protected static String testDescription;
	protected RemoteWebDriver driver;

	protected String expectedvalue = "rgba(0, 140, 201, 1)";
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, IOException{
		Reporter.startResult();
		loadObjects();
	}

	@BeforeTest
	public void beforeTest(){
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		Reporter.startTestCase();
		//assign(driver);
		invokeApp(browserName,driver);

	}
		
	@AfterSuite
	public void afterSuite(){
		Reporter.endResult();
	}

	@AfterTest
	public void afterTest(){
		
	}
	
	@AfterMethod
	public void afterMethod(){
		quitBrowser(driver);
	}
	public List<WebElement> listOfAllElementsByClassName(String classval,RemoteWebDriver driver){
		List<WebElement> allitems = null;
		try{
		allitems = driver.findElementsByClassName(classval);
		int count = allitems.size();
		System.out.println("Count of all skills : "+count);
		Reporter.reportStep("List of all Skills", "PASS",driver);
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
		
		return allitems;
		
	}
	
	public List<WebElement> verifyMessage(String classval,String message,RemoteWebDriver driver){
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
		
		return allitems;
		
	}
	public boolean enterByIdLink(String idValue, String data,RemoteWebDriver driver) {
		boolean bReturn = false;
		try {
			
			driver.findElement(By.id(idValue)).clear();
			driver.findElement(By.id(idValue)).sendKeys(data);	
			Thread.sleep(3000);
			driver.findElement(By.id(idValue)).sendKeys(Keys.TAB);	
				
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+idValue, "PASS",driver);
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+idValue, "FAIL",driver);
		}
		return bReturn;
	}

	
	@DataProvider(name="fetchData")
	public Object[][] getData(){
		return DataInputProvider.getSheet(dataSheetName);		
	}	
	
	/*public LogoutPage Logout() throws InterruptedException
	{
		Thread.sleep(5000);
		mouseOverByXpath(prop.getProperty("HomePage.Signout.Xpath"));
		Thread.sleep(500);
		clickByLink(prop.getProperty("Home.Signout.Linktext"));
		return new LogoutPage();
	}*/
	
	
	
	public String stringRange(String val)
	{
		try
		{
			if(val!=null)
			{
				val=val.replaceAll("[^0-9-]", "");
				System.out.println(val);
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Encountered in String to int conversion !!!");
			
		}
		return val;
	}
	
	public boolean getFirstLinkAndVerifyCss(String xpath,String cssval,RemoteWebDriver driver){
		try{
		String actvalue = getCssValueByXpath(xpath, cssval,driver);
		if(expectedvalue.equals(actvalue)){
		//	System.out.println("Color of the first view button is blue");
			Reporter.reportStep("Color of the first view button is blue", "PASS",driver);
		}
		else{
			//System.out.println("Color of the first view button is not blue");
			Reporter.reportStep("Color of the first view button is not blue", "FAIL",driver);
		}
		return true;
		}
		catch(Exception e){
			Reporter.reportStep("Some exception has occured", "FAIL",driver);
			return false;
		}
		
		
		
	}
	
	public String getCurrentPageTitle(RemoteWebDriver driver){
		
		String currenttitle = driver.getTitle();
		if(currenttitle!=null)
		Reporter.reportStep("Obtained current page's title", "PASS",driver);
		return currenttitle;
	}
	

public void checkCount(String xpath1, String xpath2,RemoteWebDriver driver){
	String res1 = getTextByXpath(xpath1,driver);
	res1 = res1.replaceAll("[^0-9-]", "");
	String res2 = getTextByXpath(xpath2,driver);
	res2 = res2.replaceAll("[^0-9-]", "");
	if(res1.equals(res2)){
		Reporter.reportStep("Search count matches", "PASS",driver);
	}
		
	}
}


