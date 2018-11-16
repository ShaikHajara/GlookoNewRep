package com.glooko.tests.login;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.glooko.tests.basetest.BaseTest;
import com.glooko.utility.ReadExcelData;
import com.glooko.views.login.LoginView;
import com.glooko.views.welcome.WelcomeView;

public class LoginTest extends BaseTest {
	LoginView loginview;
	WelcomeView welcomeview;
	List<String> returnedValues;
	
	//@Test
	public void doLogin() {
		try {
			welcomeview.clickLogin();
			Reporter.log("Reading excel data");
			ReadExcelData.getDataFromExcel("C:\\Users\\Shaik.Hajara\\eclipse-workspace\\Glooko-master\\src\\main\\resources\\excelfiles\\LoginFile.xlsx", "Sheet1");
			loginview.loginToGlooko(ReadExcelData.validValueslist.get(0), ReadExcelData.validValueslist.get(1));
		} catch (final InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void logintest() throws IOException {	
		System.out.println(returnedValues.size());
		for(int i=0;i<returnedValues.size();i+=2) {
			welcomeview.clickLogin();
			loginview.loginToGlooko(returnedValues.get(i),returnedValues.get(i+1));	
		}
		takeScreenshotAtEndOfTest();
	}
	
	
	@BeforeMethod
	public void init() throws IOException {
	    returnedValues=ReadExcelData.getLoginData("C:\\Users\\Shaik.Hajara\\eclipse-workspace\\Glooko-master\\src\\main\\resources\\excelfiles\\LoginFile.xlsx", "Sheet2");
		ReadExcelData.getExpectedData("C:\\Users\\Shaik.Hajara\\eclipse-workspace\\Glooko-master\\src\\main\\resources\\excelfiles\\LoginFile.xlsx", "Sheet2");
	    loginview = new LoginView(driver);
		welcomeview = new WelcomeView(driver);
	}
}
