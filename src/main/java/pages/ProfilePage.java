package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrappers.LinkedInWrappers;

public class ProfilePage extends LinkedInWrappers {


	int count = 0; //A temp variable to check if skill is already added


	public ProfilePage(RemoteWebDriver driver) throws InterruptedException{
       Thread.sleep(3000);
		//Verify the page landed in Linkedin Profile page. Verify title of the Profile page
		if(!(verifyTitleContains("Edit Profile | LinkedIn",driver))){
			Reporter.reportStep("This is not the edit profile page", "FAIL",driver);
		}
	}

	//Verify current organization in the profile page
	public ProfilePage verifyCurrentOrganization(String organization,RemoteWebDriver driver){
		if(!verifyTextContainsByXpath(prop.getProperty("ProfilePage.Organization.Xpath"), organization,driver)){
		Reporter.reportStep("Given text is not present", "FAIL",driver);
		}
		else{
			Reporter.reportStep("Given text is present", "PASS",driver);
		}
		return this;
	}

	//Verify if skill exists. Get the list of all skills and compare with new skill
	public ProfilePage verifySkillExists(String newskill,RemoteWebDriver driver){

		List<WebElement> skills = listOfAllElementsByClassName(prop.getProperty("ProfilePage.listofskills.Classname"),driver);
		for (WebElement item : skills) {
			String skill = item.getText();
			if(skill.equalsIgnoreCase(newskill)){
				//System.out.println("Skill is added");
				count++;//if atleast one skill matches counter will be incremented. A temp variable.
				Reporter.reportStep("Skill is added", "INFO",driver);
			}
		}
		return this;
	}
	public ProfilePage mouseOverIcon(RemoteWebDriver driver){
		mouseOverByXpath(prop.getProperty("HomePage.Signout.Xpath"),driver);
		return this;
	}
	
	public LogoutPage clickSignout(RemoteWebDriver driver) {
		clickByLink(prop.getProperty("HomePage.Signout.Linktext"),driver);
		return new LogoutPage(driver) ;
	}
	


public ProfilePage addAndSaveSkill(String newskill,RemoteWebDriver driver) throws InterruptedException{
		if(count==0){
		clickByXpath(prop.getProperty("ProfilePage.AddSkill.Xpath"),driver);
		enterById(prop.getProperty("ProfilePage.Skill.Id"), newskill,driver);
		clickByXpath(prop.getProperty("ProfilePage.clickAdd.Xpath"),driver);
		clickByXpath(prop.getProperty("ProfilePage.clicksave.Xpath"),driver);
		Reporter.reportStep("New skill is added and saved successfully", "PASS",driver);
		}
		Thread.sleep(1000);
		return this;
	}


	/*//Click on Add Skill in the profile page
	public ProfilePage clickAddSkill(){

		clickByXpath("//div[@id='skills-item']/following-sibling::button");
		return this;
	}
	
	public ProfilePage enterSkillName(){
		
		enterById(prop.getProperty("ProfilePage.Skill.Id"), "Selenium");
		return this;
	}
	
	public ProfilePage clickAdd(){
		
		clickById(prop.getProperty("ProfilePage.ClickAdd.Id"));
		return this;
	}
public ProfilePage clickSave(){
		
		clickByXpath(prop.getProperty("ProfilePage.clicksave.Xpath"));
		return this;
	
}*/
}
