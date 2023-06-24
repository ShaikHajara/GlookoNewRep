package com.glooko.tests.home;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.glooko.tests.basetest.BaseTest;
import com.glooko.utility.ReadExcelData;
import com.glooko.views.home.HomeView;
import com.glooko.views.login.LoginView;
import com.glooko.views.welcome.WelcomeView;

public class HomeTest extends BaseTest {
	LoginView loginview;
	WelcomeView welcomeview;
	HomeView homeview;

	@Test
	public void addEvents() {
		waitForSeconds(10);
		System.out.print("message");
		System.out.print("message");
		homeview.addMultipleEvents();
		homeview.logout();
	}

	@Test
	public void checkAndtapNotification() {
		homeview.checkNotification();
		homeview.logout();
	}

	@Test
	public void getInsulinListText() {
		waitForElement(homeview.startElement, 30);
		homeview.clickFloatIconFromHomeScreen();
		assertEquals(homeview.titleInAddEventScreen, "Add Event");
		homeview.tapInsulinDropDown();
		assertEquals(homeview.startElementInMedicationList, "Insulin");
		homeview.getMedicationTextFromInsulinList();
		homeview.logout();
	}

	@BeforeMethod
	public void init() {
		loginview = new LoginView(driver);
		welcomeview = new WelcomeView(driver);
		homeview = new HomeView(driver);
		welcomeview.clickLogin();
		try {
			ReadExcelData.getDataFromExcel("C:\\Users\\Shaik.Hajara\\eclipse-workspace\\Glooko-master\\src\\main\\resources\\excelfiles\\LoginFile.xlsx", "Sheet1");
		} catch (final InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginview.loginToGlooko(ReadExcelData.validValueslist.get(0), ReadExcelData.validValueslist.get(1));
	}
}
