package leaderboard;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataFromToDate {
	WebDriver driver;
	@BeforeClass	
	public void Chrome(){
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
  @Test
  public void DataFromToDate() throws InterruptedException, InvalidFormatException, IOException {

	WebDriverCommonUtils wlib1=new WebDriverCommonUtils();
	ExcelLib elib=new ExcelLib();
	ReadPropertiesFile rd=new ReadPropertiesFile();
	
	
    wlib1.Login(driver);
	
	wlib1.waitForPageToLoad(driver);
	
	
	
	//////////////////////////Select Leaderboard///////////////////////
	wlib1.ClickTagChatAdmin("LEADERBOARD", driver);

	/////Select current date//////////
	String currentdate=wlib1.getCurrentDate(driver);
	int cdate = Integer.parseInt(currentdate);
	cdate=cdate-12;
	///////////////////////////Click on Date From //////////////////////////
	Thread.sleep(2000);

	driver.findElement(By.xpath(rd.getValue("DateFromLeaderboard"))).click();
	driver.findElement(By.xpath(rd.getValue("ClickOnCalender")));


	WebElement wb=driver.findElement(By.xpath("//a[contains(text(),'"+cdate+"')]"));
	Actions act=new Actions(driver);
	act.moveToElement(wb).click().perform();
	Thread.sleep(2000);


///////////////////////////Click on Date TO //////////////////////////

driver.findElement(By.xpath(rd.getValue("DateToLeaderboard"))).click();
driver.findElement(By.xpath(rd.getValue("ClickOnCalender")));
cdate=cdate+12;
WebElement wb2=driver.findElement(By.xpath("//a[contains(text(),'"+cdate+"')]"));
Actions act2=new Actions(driver);
act2.moveToElement(wb2).click().perform();


/////////////////////Grab Table Data/////////////////////////
Thread.sleep(2000);
//wlib1.waitForPageToLoad(driver);
wlib1.GetTableData("//table[@id='ldtable']/tbody/tr",driver);

////////////////////////LOGOUT/////////////////////
 wlib1.LOGOUT(driver);
 driver.quit();

}
}
