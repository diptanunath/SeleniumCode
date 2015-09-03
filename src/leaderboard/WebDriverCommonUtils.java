package leaderboard;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WebDriverCommonUtils {
	
 @Test
  public void waitForPageToLoad(WebDriver driver) {
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  public void waitForLinkPresent(String Xpath, WebDriver driver){
	  WebDriverWait wait=new WebDriverWait(driver,20);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("Xpath")));
	
  }
  
  public boolean VerifyTextPresent(String text,WebDriver driver){
	  String Pagesource=driver.getPageSource();
	  boolean flag=false;
	  if(Pagesource.contains(text)){
		  flag=true;
	  }
	  return flag;
  }
  public boolean getAndVerifyText(String WebElementXpath,String ExpectedText,WebDriver driver){
	  boolean flag=false;
	  String actual=driver.findElement(By.xpath(WebElementXpath)).getText();
	  if(ExpectedText.equalsIgnoreCase(actual)){
		  System.out.println(actual);
		  flag=true;
	  }
	  else{
		  System.out.println("not present");
	  }
	return flag;
	  
  }
  public boolean verifyElementPresent(String WebElementXpath,WebDriver driver) throws InterruptedException{
	
	  int count=100;
	  boolean flag=false;
	  while(count<=100){
		  try{
			 flag=driver.findElement(By.xpath(WebElementXpath)).isDisplayed();
		  System.out.println("Element Present");
		  flag=true;
		  break;
		  }
		  catch(NoSuchElementException t){
			  count++;
			  Thread.sleep(2000);
					  }
	  }
	return flag;
	  
  }
 
  public void ClickTagChatAdmin(String TagName,WebDriver driver) throws InterruptedException{
	  driver.findElement(By.cssSelector("span.hamburger-helper.ng-isolate-scope > span")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//a[span[contains(text(),'"+TagName+"')]]")).click();
	  
	  }
  
  public void ClearSearchBox(WebDriver driver) throws InterruptedException{
	  driver.findElement(By.cssSelector("span.hamburger-helper.ng-isolate-scope > span")).click();
	  
	  }
  
  
  public void LOGOUT(WebDriver driver) throws InterruptedException{
	  driver.findElement(By.cssSelector("span.hamburger-helper.ng-isolate-scope > span")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//a[span[contains(text(),'LOGOUT')]]")).click();
	  ////ul[@role='tablist']/li[5]/a
	  }
  
  public String getCurrentDate(WebDriver driver) throws InterruptedException{
	  DateFormat dateFormat = new SimpleDateFormat("dd");
      //get current date time with Date()
      Date date = new Date();
      String s=dateFormat.format(date);
   
      System.out.println(dateFormat.format(date));
      	return s;
	  }
  public String getCurrentMonth(WebDriver driver) throws InterruptedException{
	  DateFormat dateFormat = new SimpleDateFormat("MM");
      //get current date time with Date()
      Date date = new Date();
      String s=dateFormat.format(date);
   
      System.out.println(dateFormat.format(date));
      	return s;
	  }
  public String getCurrentYear(WebDriver driver) throws InterruptedException{
	  DateFormat dateFormat = new SimpleDateFormat("YYYY");
      //get current date time with Date()
      Date date = new Date();
      String s=dateFormat.format(date);
   
      System.out.println(dateFormat.format(date));
      	return s;
	  }
  
  public void GetTableData(String TableRowXpath,WebDriver driver) throws InterruptedException, InvalidFormatException, IOException{
	  
	  Thread.sleep(3000);
	  String s=driver.findElement( By.xpath("//div[@id='ldtable_info']")).getText();
	  String number=s.substring(s.lastIndexOf("of") + 3, s.lastIndexOf(" "));
	  int c=Integer.parseInt(number);
	  System.out.println("total entries"+c);
	  int g=1;
	  int RowIndex=1;
	  if(c>10)
	  {
		  try{
	  
		 WebElement nextPage=driver.findElement(By.xpath("//a[@id='ldtable_next' and @class='paginate_button next']"));
	  //WebElement nextPageNotAvailable=driver.findElement(By.xpath("//a[@id='ldtable_next' and @class='paginate_button next disabled']"));
	 
	while(nextPage.isDisplayed()){
		
	List<WebElement> Rowlist=driver.findElements(By.xpath(TableRowXpath));
		  
		/*  for(WebElement rowElement:Rowlist)
		  		{
		  	List<WebElement> columnlist=rowElement.findElements(By.xpath("td"));
		  int ColumnIndex=1;
		  int h=0;
		  for(WebElement colElement:columnlist)
		  			{
		  System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Data "+colElement.getText());
		  String j=colElement.getText();
		  ExcelLib e=new ExcelLib();
		  e.createRow("Sheet3", h); 
		  e.setExcelData("Sheet3",h, g, j);
		  ColumnIndex=ColumnIndex+1;
		  h++;
		  			}
		  g++;
		  RowIndex=RowIndex+1;
		  		}*/

		Thread.sleep(5000);
	     nextPage.click();
	     Thread.sleep(5000);
	
	  } 
		  }
		  catch(Exception ei){
			  ei.printStackTrace();
			  List<WebElement> Rowlist=driver.findElements(By.xpath(TableRowXpath));
			 
			  for(WebElement rowElement:Rowlist)
			  		{
			  	List<WebElement> columnlist=rowElement.findElements(By.xpath("td"));
			  int ColumnIndex=1;
			  int h=0;
			  for(WebElement colElement:columnlist)
			  			{
			  System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Data "+colElement.getText());
			  String j=colElement.getText();
			  ExcelLib e=new ExcelLib();
			  e.createRow("Sheet3", h); 
			  e.setExcelData("Sheet3",h, g, j);
			  ColumnIndex=ColumnIndex+1;
			  h++;
			  			}
			  g++;
			  RowIndex=RowIndex+1;
			  		}
           
		  }
	  }
	  else{
		  List<WebElement> Rowlist=driver.findElements(By.xpath(TableRowXpath));
		  
		  
		  for(WebElement rowElement:Rowlist)
		  		{
		  	List<WebElement> columnlist=rowElement.findElements(By.xpath("td"));
		  int ColumnIndex=1;
		  int h=0;
		  for(WebElement colElement:columnlist)
		  			{
		  System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Data "+colElement.getText());
		  String j=colElement.getText();
		  ExcelLib e=new ExcelLib();
		  e.createRow("Sheet3", h); 
		  e.setExcelData("Sheet3",h, g, j);
		  ColumnIndex=ColumnIndex+1;
		  h++;
		  			}
		  g++;
		  RowIndex=RowIndex+1;
		  		}

	  }
  }
	  public void Login(WebDriver driver) throws InterruptedException{
		    driver.get("http://app.helpchat.dev/");
			driver.findElement(By.xpath("//input[@id='userId']")).sendKeys("taken23231@gmail.com");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("buntyyyyy");
			driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			System.out.println("Login");
		
		  }	 
	   
  }

	 

