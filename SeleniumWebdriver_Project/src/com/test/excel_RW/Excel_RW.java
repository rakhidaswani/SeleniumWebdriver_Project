package com.test.excel_RW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.test.beans.FB_Login_IO;


public class Excel_RW {
	
	
	XSSFWorkbook workbook = null;
	XSSFSheet sheet = null;	
	String ExcelPath = "";
	List<FB_Login_IO> lstInpBean = new ArrayList<FB_Login_IO>();
	
	public List<FB_Login_IO> getRequestData() throws IOException {
		
		FileInputStream inputStream = new FileInputStream(new File("utilities//excel//FB_Details.xlsx"));
        
        workbook = new XSSFWorkbook(inputStream);
        
        sheet = workbook.getSheet("FB_LoginDetails");
        
        Iterator<Row> rowIterator = sheet.iterator();
		
		int rowCntr=0;
		
		
        while (rowIterator.hasNext())
		{ 
			Row row = rowIterator.next();
			rowCntr = rowCntr + 1;
			
			if(rowCntr == 1) continue;
			
			FB_Login_IO inpBean = new FB_Login_IO();
			
			if(row.getCell(0) == null) {inpBean.setUserName("");} else {inpBean.setUserName(row.getCell(0).getStringCellValue());}
			if(row.getCell(1) == null) {inpBean.setPassword("");} else {inpBean.setPassword(row.getCell(1).getStringCellValue());}
            
			
			lstInpBean.add(inpBean);
			
			
		}
        
		return lstInpBean;
		
	}

	public void setLoginStatus(List<FB_Login_IO> fsOuputlist) throws IOException {
		
		// TODO Auto-generated method stub
		
		Sheet sheet = workbook.getSheet("FB_LoginDetails");
		
		 int rowNum = 1;
	        for(FB_Login_IO fbOuput: fsOuputlist) {
	            Row row = sheet.getRow(rowNum);
	          
	            row.createCell(2).setCellValue(fbOuput.getLogin_status());
	   
	            rowNum++;
	        }
		
	        FileOutputStream fileOut = new FileOutputStream("utilities//excel//FB_Details.xlsx");
			 workbook.write(fileOut);
			 workbook.close();
		
	}

}
