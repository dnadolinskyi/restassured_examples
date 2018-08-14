package com.restassured.examples.soapws;

//static imports
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

import java.io.File;

import org.junit.Test;

public class HolidayWsExample {
	
	//Request to SOAP services usingRestAssured
	@Test
	public void getHolidays(){
		String wsdlUrl="http://www.holidaywebservice.com/HolidayService_v2/HolidayService2.asmx?wsdl";
		String payload="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hs=\"http://www.holidaywebservice.com/HolidayService_v2/\"><soapenv:Body><hs:GetHolidaysAvailable><hs:countryCode>Canada</hs:countryCode></hs:GetHolidaysAvailable></soapenv:Body></soapenv:Envelope>";
		
		RestAssured.given()
		.contentType("text/xml")
		.body(payload)
		.when()
		.post(wsdlUrl)
		.then()
		.log()
		.all();
	}
}
