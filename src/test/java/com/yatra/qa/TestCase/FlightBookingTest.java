package com.yatra.qa.TestCase;



	import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.yatra.qa.base.TestBase;
import com.yatra.qa.pages.FlightBooking;
import com.yatra.qa.pages.SignUpPage;
import com.yatra.qa.util.TestUtil;

	public class FlightBookingTest extends TestBase {

		FlightBooking fl;
		TestUtil testUtil;
		//SignUpPage signup;

		public FlightBookingTest() {
			super();
		}

		@BeforeClass
		public void setUp() throws InterruptedException {
			initialization();
			fl = new FlightBooking();
			testUtil = new TestUtil();
			TestUtil.runTimeInfo("error", "login successful");

		}

		@Test(priority = 1)
		public void flightsrc() {
			fl.oneWayFlightsrc();
			fl.selcF1(dCity);
			fl.selcF2(aCity);
			fl.selcDate(tDate);
		}

		@Test(priority = 2)
		public void flightSelec() {

			fl.oneWayFlightsel();
		}

		@Test(priority = 3)
		public void flightUserDetails() {

			fl.oneWayFlightuserD(); 
		}

		@Test(priority = 4)
		public void paymentPg() {

			Assert.assertTrue(fl.verifyUPIpayment(), "UPI Payment Tab missing on the page");
			Assert.assertTrue(fl.verifyCCpayment(), "CC Payment Tab missing on the page");
			Assert.assertTrue(fl.verifyPayNow(), "PayNow Button missing on the page");

		}

		@AfterClass
		public void tearDown() {
			
			driver.close();

		}

	}



