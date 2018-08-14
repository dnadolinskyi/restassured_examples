package com.checkresponsetime.example;

//static imports
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import ExampleBase.TestXMLBase;


public class VerifyTimeExample extends TestXMLBase{

	//)Verify the time of the response
	@Test
	public void test001(){
		Response resp = RestAssured.given()
			.queryParam("query","ipod")
			.queryParam("apiKey",APIKEY)
			.queryParam("format","json")
		.log()
		.all()
		.when()
		.get("/search");
		
		//Get the time of the response
		Long responseTime = resp
				.time(); //In ms
		Long responseTime2 = resp.timeIn(TimeUnit.SECONDS);//in seconds
		
		resp.then().time(lessThan(3L),TimeUnit.SECONDS); //Assertion. Also check response specification, it was added there as a part of assertions.
		
		System.out.println("-------------------Starting Test-------------------");
		System.out.println("The time of the response is: "+responseTime+" ms");
		System.out.println("The time of the response is: "+responseTime2+" seconds");
		System.out.println("-------------------End of Test-------------------");
	}
	
}
