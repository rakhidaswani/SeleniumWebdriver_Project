package com.test.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.beans.FB_Login_IO;
import com.test.excel_RW.Excel_RW;

public class FB_Driver {
	
	
	public static void main(String []arg) throws IOException
	{
		
        System.setProperty("webdriver.chrome.driver", "utilities//browser_drivers//chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		Excel_RW excel_RW=new Excel_RW();
		List<FB_Login_IO> lstInpBean = new ArrayList<FB_Login_IO>();
		List<FB_Login_IO> lstOutBean = new ArrayList<FB_Login_IO>();		
		lstInpBean=	excel_RW.getRequestData();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
	
		for (FB_Login_IO inpBean : lstInpBean){
			
			String status=null;
			driver.get("https://www.facebook.com/");
			driver.manage().window().maximize();
			driver.findElement(By.id("email")).sendKeys(inpBean.getUserName());
			driver.findElement(By.id("pass")).sendKeys(inpBean.getUserName());
			driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
			
			if(driver.findElement(By.id("loginbutton")).isDisplayed())
            {
					status="passed";
	 
            }
            else
            {
            		status="failed";
	 
            }

			inpBean.setLogin_status(status);
			
			
			
			lstOutBean.add(inpBean);
			
		}
		
		excel_RW.setLoginStatus(lstOutBean);
		System.out.println("Login Results Updated");
		driver.close();
	}

}
