package leaderboard;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.testng.annotations.Test;



public class SQL {
	   // JDBC driver name and database URL
	  
	  // static String[] array1=new String[13];
  @Test
  public void sql1() {
	  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   final String DB_URL = "jdbc:mysql://172.16.1.215/helpchat";

	   //  Database credentials
	   final String USER = "appuser";
	   final String PASS = "appuser";
	
		   Connection conn = null;
		   Statement stmt = null;
		  
		   try{
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
			  //elib2.excuteandwritequery(sCurrentLine,stmt,"Sheet2");
			  elib2.resultSetToArrayList2(sCurrentLine, stmt);
			  
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		  
		   //System.out.println("Goodbye!");
		}//end main
		
}
