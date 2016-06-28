package wrappers;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import utils.Reporter;

public class GenericWrappers {

	protected RemoteWebDriver driver;
	protected static Properties prop;
	public String sUrl,primaryWindowHandle,sHubUrl,sHubPort;
	WebDriverWait wait; 

	public GenericWrappers() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
		
		
	/**
	 * This method will launch only firefox and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Babu - TestLeaf
	 * @param url - The url with http or https
	 * 
	 */
	public boolean assign(RemoteWebDriver driver)
	{
		this.driver=driver;
		return true;
	}
	public boolean invokeApp(String browser,RemoteWebDriver driver) {
		boolean bReturn = false;
		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.get(sUrl);
			

			primaryWindowHandle = driver.getWindowHandle();		
			Reporter.reportStep("The browser:" + browser + " launched successfully", "PASS",driver);
			bReturn = true;

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The browser:" + browser + " could not be launched", "FAIL",driver);
		}
		return bReturn;
	}

	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Babu - TestLeaf
	 * @throws IOException 
	 * @throws COSVisitorException 
	 */
	public boolean enterById(String idValue, String data,RemoteWebDriver driver) {
		boolean bReturn = false;
		try {
			
			driver.findElement(By.id(idValue)).clear();
			driver.findElement(By.id(idValue)).sendKeys(data,Keys.TAB);	
			Reporter.reportStep("The data: "+data+" entered successfully in field :"+idValue, "PASS",driver);
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The data: "+data+" could not be entered in the field :"+idValue, "FAIL",driver);
		}
		return bReturn;
	}

	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTitle(String title){
		boolean bReturn = false;
		try{
			if (driver.getTitle().equalsIgnoreCase(title)){
				Reporter.reportStep("The title of the page matches with the value :"+title, "PASS",driver);
				bReturn = true;
			}else
				Reporter.reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "SUCCESS",driver);

		}catch (Exception e) {

			System.out.println(driver.getTitle());
			Reporter.reportStep("The title did not match", "FAIL",driver);
		}

		return bReturn;
	}
	public boolean verifyTitle(String title,RemoteWebDriver driver){
		boolean bReturn = false;
		try{
			if (driver.getTitle().equalsIgnoreCase(title)){
				Reporter.reportStep("The title of the page matches with the value :"+title, "PASS",driver);
				bReturn = true;
			}else
				Reporter.reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "SUCCESS",driver);

		}catch (Exception e) {

			System.out.println(driver.getTitle());
			Reporter.reportStep("The title did not match", "FAIL",driver);
		}

		return bReturn;
	}
	
	public boolean verifyCleared(String id,RemoteWebDriver driver){
		boolean bReturn = false;
		try{
			if (driver.findElement(By.id(id)).getText().equalsIgnoreCase("")){
				Reporter.reportStep("Value is null for the given id : "+id, "PASS",driver);
				bReturn = true;
			}else
				Reporter.reportStep("Value is not null for the given id : "+id, "SUCCESS",driver);

		}catch (Exception e) {
			Reporter.reportStep("Exception in verifyCleared Function", "FAIL",driver);
		}

		return bReturn;
	}


	/**
	 * This method will verify the title of the browser contains 
	 * @param title - The expected title of the browser
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTitleContains(String title,RemoteWebDriver driver){
		boolean bReturn = false;
		try{
			if (driver.getTitle().contains(title)){
				Reporter.reportStep("The title of the page matches with the value :"+title, "PASS",driver);
				bReturn = true;
			}else
				Reporter.reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "SUCCESS",driver);

		}catch (Exception e) {
			Reporter.reportStep("The title did not match", "FAIL",driver);
		}

		return bReturn;
	}
	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTextByXpath(String xpath, String text,RemoteWebDriver driver){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().equalsIgnoreCase(text)){
			Reporter.reportStep("The text: "+sText+" matches with the value :"+text, "PASS",driver);
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not match with the value :"+text, "FAIL",driver);
		}


		return bReturn;
	}
	
	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Babu - TestLeaf
	 */
	public boolean verifyTextContainsByXpath(String xpath, String text,RemoteWebDriver driver){
		boolean bReturn = false;
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().contains(text)){
			Reporter.reportStep("The text: "+sText+" contains the value :"+text, "PASS",driver);
			bReturn = true;
		}else{
			Reporter.reportStep("The text: "+sText+" did not contain the value :"+text, "FAIL",driver);
		}


		return bReturn;
	}

	/**
	 * This method will close all the browsers
	 * @author Babu - TestLeaf
	 */
	public void quitBrowser(RemoteWebDriver driver) {
		try {
			driver.close();
		} catch (Exception e) {
			Reporter.reportStep("The browser:"+driver.getCapabilities().getBrowserName()+" could not be closed.", "FAIL",driver);
		}

	}
	/**
	 * This method will move to frame using frame id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean moveToFrameByID(String Id) {
		boolean bReturn = false;
		try{
			driver.switchTo().frame(driver.findElementById(Id));
			

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element in the frame with ID : "+Id+" could not be clicked.", "FAIL",driver);
		}
		return bReturn;
	}
	
	
	
	
	//GetCSSValue By Xpath
		public String getCssValueByXpath(String xpathval, String cssprop,RemoteWebDriver driver){
			try{
			String cssvalue = driver.findElementByXPath(xpathval).getCssValue(cssprop);
			Reporter.reportStep("Required CssValue is = "+ cssvalue, "PASS",driver);
			return cssvalue;
			//cssprop = cssvalue;
			
			}
			catch(NoSuchElementException e){
				//System.out.println("No such element found");
				Reporter.reportStep("No such element found", "FAIL",driver);
				e.printStackTrace();
					}		
			catch (Exception e) {
			//	System.out.println("Some Error Occured while performing  method");
				Reporter.reportStep("Some Error Occured while performing the method", "FAIL",driver);
				e.printStackTrace();
					}
			return cssprop;
			
		//	System.out.println(cssprop);
		//	return cssprop;
		 	
		  }
	
	public int stringConvertsInt(String val)
	{
		int number=0;
	
		try
		{
			if(val!=null)
			{
				val=val.replaceAll("[^0-9]-", "");
			//	System.out.println(val);
				Reporter.reportStep("Result is : "+val, "PASS",driver);
				
			}
			if(val.contains("-"))
			{
				String[] ss2=val.split("-");
				number=Integer.parseInt(ss2[1]);
				Reporter.reportStep("Result is : "+number, "PASS",driver);
			}
			else
			{
				number=Integer.parseInt(val);
				Reporter.reportStep("Result is : "+number, "PASS",driver);
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception Encountered in String to int conversion !!!");
			Reporter.reportStep("Exception Encountered in String to int conversion !!!", "FAIL",driver);
			
		}
		return number;
	}
	public boolean verifyByColor(String linktext,String property,String colorval,RemoteWebDriver driver)

	{
		boolean flag=false;
		try
		{
		if(driver.findElementByLinkText(linktext).getCssValue(property).equalsIgnoreCase(colorval))
			flag=true;	
		}
		catch (NoSuchElementException e) {
			System.out.println("Element not found");
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		return flag;
	}
	
	public boolean matchCount(String xpath1,String xpath2,RemoteWebDriver driver) {
		boolean bReturn = false;
		try
		{
		int num=stringConvertsInt(getTextByXpath(xpath1,driver));
		int num2=stringConvertsInt(getTextByXpath(xpath2,driver));
		if(num==num2)
		{
			Reporter.reportStep("Count matches for First Connection", "PASS",driver);
			bReturn=true;
		}
		}
		catch(Exception e)
		{
			Reporter.reportStep("Error encountered during matching of count","FAIL",driver);
		}
		return bReturn;
		
	}





	

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickById(String id,RemoteWebDriver driver) {
		boolean bReturn = false;
		try{
			
			driver.findElement(By.id(id)).click();
			Reporter.reportStep("The element with id: "+id+" is clicked.", "PASS",driver);

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with id: "+id+" could not be clicked.", "FAIL",driver);
		}
		return bReturn;
	}
	public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
	public void scrollToUP() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
    }
	public boolean pageLoadComplete()
	{
		boolean flag=false;
		try
		{
		if(((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"))
			flag=true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Encountered in PageLoadComplete");
		}
		
		return flag;
		
	}

	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByClassName(String classVal,RemoteWebDriver driver) {
		boolean bReturn = false;
		try{
			
			driver.findElement(By.className(classVal)).click();
			Reporter.reportStep("The element with class Name: "+classVal+" is clicked.", "PASS",driver);

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with class Name: "+classVal+" could not be clicked.", "FAIL",driver);
		}
		return bReturn;
	}
/*	public boolean mouseOverByXpath(String xpath)
	{	boolean bReturn = false;
	try{
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElementByXPath(xpath));
		
		Reporter.reportStep("The element with class Name: "+xpath+" is clicked.", "PASS");

		bReturn = true;

	} catch (Exception e) {
		Reporter.reportStep("The element with class Name: "+xpath+" could not be clicked.", "FAIL");
	}
	return bReturn;
}*/
		
	
	/**
	 * This method will click the element using name as locator
	 * @param name  The name (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByName(String name,RemoteWebDriver driver) {
		boolean bReturn = false;
		try{
			
			driver.findElement(By.name(name)).click();
			Reporter.reportStep("The element with name: "+name+" is clicked.", "PASS",driver);

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with name: "+name+" could not be clicked.", "FAIL",driver);
		}
		return bReturn;
	}

	/**
	 * This method will click the element using link name as locator
	 * @param name  The link name (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByLink(String name,RemoteWebDriver driver) {
		boolean bReturn = false;
		try{
			
			driver.findElement(By.linkText(name)).click();
			Reporter.reportStep("The element with link name: "+name+" is clicked.", "PASS",driver);

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with link name: "+name+" could not be clicked.", "FAIL",driver);
		}
		return bReturn;
	}

	/**
	 * This method will click the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be clicked
	 * @author Babu - TestLeaf
	 */
	public boolean clickByXpath(String xpathVal,RemoteWebDriver driver) {
		boolean bReturn = false;
		try{
		
			driver.findElement(By.xpath(xpathVal)).click();
			Reporter.reportStep("The element : "+xpathVal+" is clicked.", "PASS",driver);

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be clicked.", "FAIL",driver);
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be moused over
	 * @author Babu - TestLeaf
	 */
	public boolean mouseOverByXpath(String xpathVal,RemoteWebDriver driver) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.xpath(xpathVal))).build().perform();
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" is performed.", "PASS",driver);

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by xpath : "+xpathVal+" could not be performed.", "FAIL",driver);
		}
		return bReturn;
	}

	/**
	 * This method will mouse over on the element using link name as locator
	 * @param xpathVal  The link name (locator) of the element to be moused over
	 * @author Babu - TestLeaf
	 */
	public boolean mouseOverByLinkText(String linkName,RemoteWebDriver driver) {
		boolean bReturn = false;
		try{
			new Actions(driver).moveToElement(driver.findElement(By.linkText(linkName))).build().perform();
			Reporter.reportStep("The mouse over by link : "+linkName+" is performed.", "PASS",driver);

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The mouse over by link : "+linkName+" could not be performed.", "FAIL",driver);
		}
		return bReturn;
	}

	public String getTextByXpath(String xpathVal,RemoteWebDriver driver){
		String bReturn = "";
		try{
			return driver.findElement(By.xpath(xpathVal)).getText();
			//Reporter.reportStep("First connection name is : "+text1, "PASS");
		} catch (Exception e) {
			Reporter.reportStep("The element with xpath: "+xpathVal+" could not be found.", "FAIL",driver);
		}
		return bReturn; 
	}

	/**
	 * This method will select the drop down value using id as locator
	 * @param id The id (locator) of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Babu - TestLeaf
	 */
	public boolean selectById(String id, String value,RemoteWebDriver driver) {
		boolean bReturn = false;
		try{
			new Select(driver.findElement(By.id(id)))
			.selectByVisibleText(value);;
			Reporter.reportStep("The element with id: "+id+" is selected with value :"+value, "PASS",driver);

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The value: "+value+" could not be selected.", "FAIL",driver);
		}
		return bReturn;
	}
	
	public void loadObjects() throws FileNotFoundException, IOException{
		prop = new Properties();
		prop.load(new FileInputStream(new File("./object.properties")));
	
	}


}

