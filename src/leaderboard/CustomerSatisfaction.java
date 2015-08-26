package leaderboard;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomerSatisfaction {
	WebDriver driver;
	@BeforeClass	
	public void Chrome(){
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	@Test
  public void f() throws InterruptedException {
		WebDriverCommonUtils wlib=new WebDriverCommonUtils();

		 
		//////Login///////////////
		wlib.Login(driver);
		wlib.waitForPageToLoad(driver);
		
		//Select Customer Satisfaction
		wlib.ClickTagChatAdmin("CUSTOMER SATISFACTION", driver);
		Thread.sleep(2000);
		
		
		
	    //SendKeys("Ako");
	 

		driver.findElement(By.xpath("//input[@id='_value' and @placeholder='Search group']")).sendKeys("Ak");
		Thread.sleep(2000);
		
		//****Get Searchbox data////////////////////////
		
		WebElement wb1=driver.findElement(By.xpath("//div[@id='_dropdown']/div"));
		List<WebElement> wb2=driver.findElements(By.xpath("//div[@id='_dropdown']/div"));
		for(WebElement ls:wb2){
			System.out.println(ls.getText());
						}
		driver.findElement(By.xpath("//input[@id='_value' and @placeholder='Search group']")).clear();
		
		
		//click on searchbox data for a selected group
		driver.findElement(By.xpath("//input[@id='_value' and @placeholder='Search group']")).sendKeys("Akosha");
		WebElement wb3=driver.findElement(By.xpath("//div[@id='_dropdown']/div[3]"));

		Actions act1=new Actions(driver);
		act1.moveToElement(wb1).click().perform();
		Thread.sleep(2000);
		
	/////Select current date//////////
		String currentdate=wlib.getCurrentDate(driver);
		int cdate = Integer.parseInt(currentdate);
		cdate=cdate-7;
		///////////////////////////Click on Date From //////////////////////////
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='dp1439733782209']")).click();
		//driver.findElement(By.xpath("//span[@class='date-from-leaderboard']//input")).click();
		driver.findElement(By.xpath("//div[@id='ui-datepicker-div']"));


		WebElement wb4=driver.findElement(By.xpath("//a[contains(text(),'"+cdate+"')]"));
		Actions act=new Actions(driver);
		act.moveToElement(wb4).click().perform();
		Thread.sleep(2000);

		
		Thread.sleep(2000);
		wlib.LOGOUT(driver);
		Thread.sleep(2000);
		driver.quit();
	  }
  }

