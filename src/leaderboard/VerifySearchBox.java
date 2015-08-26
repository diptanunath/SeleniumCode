package leaderboard;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifySearchBox {
	
	WebDriver driver;
	@BeforeClass	
	public void Chrome(){
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
  @Test
  public void SearchBox() throws InterruptedException {

	WebDriverCommonUtils wlib=new WebDriverCommonUtils();

 
	//////Login///////////////
	wlib.Login(driver);
	wlib.waitForPageToLoad(driver);
	
	//Select Leaderboard
	wlib.ClickTagChatAdmin("LEADERBOARD", driver);
	Thread.sleep(2000);
	wlib.getAndVerifyText("//span[contains(text(),'Leaderboard')]", "Leaderboard", driver);
	
	
    //SendKeys("AG123");
 

	driver.findElement(By.xpath("//div[@id='leaderboard']//div/input")).sendKeys("AG123");
	Thread.sleep(2000);
	
	//****Get Searchbox data////////////////////////
	
	WebElement wb1=driver.findElement(By.xpath("//div[@id='_dropdown']/div"));
	List<WebElement> wb2=driver.findElements(By.xpath("//div[@id='_dropdown']/div"));
	for(WebElement ls:wb2){
		System.out.println(ls.getText());
					}
	driver.findElement(By.xpath("//input[@placeholder='Search expert name']")).clear();
	Thread.sleep(2000);
	wlib.LOGOUT(driver);
	Thread.sleep(2000);
	driver.quit();
  }
}
