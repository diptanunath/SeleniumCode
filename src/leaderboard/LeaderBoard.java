package leaderboard;



import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class LeaderBoard {
	private static final List<WebElement> WebElement = null;
	WebDriver driver;
@Test
public void Chrome(){
	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
	driver = new ChromeDriver();
}

@BeforeClass
public void sql1() throws ClassNotFoundException, SQLException, IOException, InvalidFormatException {
	  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   final String DB_URL = "jdbc:mysql://172.16.1.215/helpchat";

	   //  Database credentials
	   final String USER = "appuser";
	   final String PASS = "appuser";
	
		   Connection conn = null;
		   Statement stmt = null;
		  
		
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      //Step5:read .sql file through readFile method
		      ExcelLib elib2=new ExcelLib();
		     
		      String sCurrentLine=elib2.readFile("D:\\DBtesting\\Agent_data.sql");
			 
		      
		      //Step6:Execute and write query in an excel file
			 // elib2.excuteandwritequery(sCurrentLine,stmt,"Sheet2");
		     
			    	ResultSet resultSet = stmt.executeQuery(sCurrentLine);
			    	ResultSetMetaData md = resultSet .getMetaData();
			        int columns = md.getColumnCount();
			        //india
			        Map<String, List<String>> map = new HashMap<>(columns);
			        for (int i = 1; i <= columns; ++i) {
			            map.put(md.getColumnName(i), new ArrayList<>());
			        }
			        while (resultSet .next()) {
			            for (int i = 1; i <= columns; ++i) {
			                map.get(md.getColumnName(i)).add(resultSet.getString(i));
			              // System.out.println( String.valueOf(map.get(md.getColumnName(i))));
			               System.out.println(resultSet .getString(i));
			               
			              
			            }
			        }
			        System.out.println(map.get("TeamLead"));
			        for (Entry<String, List<String>> entry : map.entrySet()) {
			   
						System.out.println("Key: "+entry.getKey()+" value: "+entry.getValue());
						
					}

			 
			
			// elib2.resultSetToArrayList2(sCurrentLine, stmt);
		
}
			  
			  
  @Test
  public void leaderboard() throws InterruptedException, InvalidFormatException, IOException {
	
			//static String[] array2=new String[13];

		WebDriverCommonUtils wlib=new WebDriverCommonUtils();
		ExcelLib elib=new ExcelLib();
        
		//////Login///////////////
		wlib.Login(driver);
		wlib.waitForPageToLoad(driver);
		Thread.sleep(3000);
		
		//Select Leaderboard
		wlib.ClickTagChatAdmin("LEADERBOARD", driver);
	
		///////////////////////////Click on Date From //////////////////////////
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='date-from-leaderboard']//input")).click();
		driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[1]/span")).click();
		driver.findElement(By.xpath("//div[@id='ui-datepicker-div']"));
	
     
		WebElement wb=driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/table//tr[2]/td[5]/a"));
		Actions act=new Actions(driver);
		act.moveToElement(wb).click().perform();
		Thread.sleep(2000);

	
		//eSendKeys("AG1232");
        

		driver.findElement(By.xpath("//div[@id='leaderboard']//div/input")).sendKeys("AG1232");
		Thread.sleep(2000);
		
		//****Get Searchbox data////////////////////////
		
		WebElement wb1=driver.findElement(By.xpath("//div[@id='_dropdown']/div[3]"));
		List<WebElement> wb2=driver.findElements(By.xpath("//div[@id='_dropdown']/div[3]"));
		for(WebElement ls:wb2){
			System.out.println(ls.getText());
						}

		Actions act1=new Actions(driver);
		act1.moveToElement(wb1).click().perform();

		Thread.sleep(2000);
		
		/////Get Table Header Data
		List<WebElement> lst1=driver.findElements(By.xpath("//table[@id='ldtable']/thead/tr/th"));
		///Get Column Data
		List<WebElement> lst=driver.findElements(By.xpath("//table[@id='ldtable']/tbody/tr[1]/td"));
        Map<String,String> mMap=new HashMap<String,String>();
		
		//Preparing hashMap
		for(int i=0;i<lst1.size();i++)
		{
			mMap.put(lst1.get(i).getText(), lst.get(i).getText());
		}
		/////Get Value of hasmap by passing key
		
		System.out.println(mMap.get("Team Lead"));
		
		//Printing out hashmap
		for (Map.Entry<String, String> entry : mMap.entrySet()) {
			System.out.println("Key: "+entry.getKey()+" value: "+entry.getValue());
			
		}
		

			
		/////Export to Excel\\\\\\\
		
		WebElement w1=driver.findElement(By.xpath("//span[contains(text(),'Export')]"));
		Actions act4=new Actions(driver);
		act4.moveToElement(w1).click().perform();
		Runtime.getRuntime().exec("C:\\Users\\LP-531\\Desktop\\window.exe");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@placeholder='Search expert name']")).clear();
		Thread.sleep(2000);
		wlib.LOGOUT(driver);
		driver.quit();
			}
		
	
  }

