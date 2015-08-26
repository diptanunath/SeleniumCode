package leaderboard;





import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ExcelLib {

	String excelpath="D:\\TestData.xlsx";
  	public String getExcelData(String SheetName,int RowNum,int cellNum) throws IOException, InvalidFormatException, IOException{
  		FileInputStream f1s=new FileInputStream(new File(excelpath));
  		Workbook wb=WorkbookFactory.create(f1s);
  		Sheet sh=wb.getSheet(SheetName);
  		Row row=sh.getRow(RowNum);
  		String data=row.getCell(cellNum).getStringCellValue();
  		return data;
  		
  	}
  	public int RowCount(String SheetName) throws IOException,InvalidFormatException{
  		FileInputStream f1s=new FileInputStream(excelpath);
  		Workbook wb=WorkbookFactory.create(f1s);
  		Sheet sh=wb.getSheet(SheetName);
  	    int rowCount=sh.getLastRowNum();
		return rowCount;
  		
  		
  	}

  	public void setExcelData(String SheetName,int RowNum,int cellNum,String data) throws  InvalidFormatException, IOException{
  		FileInputStream f1s=new FileInputStream(excelpath);
  		Workbook wb=WorkbookFactory.create(f1s);
  		Sheet sh=wb.getSheet(SheetName);
  	    Row row=sh.getRow(RowNum);
  		Cell cell=row.createCell(cellNum);
  	    cell.setCellType(cell.CELL_TYPE_STRING);
  		cell.setCellValue(data);
  		FileOutputStream fo=new FileOutputStream(excelpath);
  		wb.write(fo);
  	}
		
  	

  	public void createRow(String SheetName,int RowNum) throws  InvalidFormatException, IOException{
  		FileInputStream f1s=new FileInputStream(excelpath);
  		Workbook wb=WorkbookFactory.create(f1s);
  		Sheet sh=wb.getSheet(SheetName);
  	    Row row=sh.createRow(RowNum);
  		
  	}
  	
  	void excuteandwritequery(String sCurrentLine,Statement stmt,String Sheet) throws SQLException, InvalidFormatException, IOException {
		// TODO Auto-generated method stub
		      ExcelLib Lib=new ExcelLib();
		      ResultSet resultSet = stmt.executeQuery(sCurrentLine);
		      //STEP 5: Extract data from result set
		      
		     ResultSetMetaData rsmd = resultSet.getMetaData();
		      int columnsNumber = rsmd.getColumnCount();
		      System.out.println("column Number is"+columnsNumber);
		      int j=1;
		    //STEP 6: Extract data column wise
		     while (resultSet.next()) {
		 
		    	
		          for (int i = 1; i <= columnsNumber; i++) {
		             // if (i > 1) System.out.print(",  ");
		              String columnValue = resultSet.getString(i);
		            Lib.createRow(Sheet, i); 
		             
		             Lib.setExcelData(Sheet,i, j, columnValue);
		              System.out.print(columnValue );
		            // array1[i]=resultSet.getString(i);
		            // System.out.println(array1[i]);	
		           
		              
		          }
		          j++;
		       
		      }resultSet.close();
	}

	
	static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
            sb.append(line);
            sb.append("\n");
//	            line = line + "\n";
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }

	  
	  
	
	    
	}
}



