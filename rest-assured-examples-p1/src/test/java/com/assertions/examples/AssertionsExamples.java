package com.assertions.examples;

import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import ExampleBase.TestBase;

public class AssertionsExamples extends TestBase{

	//1) Verify if the number of items is equal to 10
	//@Test
	public void test001(){
		int numItems = 
				given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "JSON").
			when().
			get("/search").
			then().		
			extract().
			path("numItems");
			//Assertion with JUnitAssert
			assertEquals(10, numItems);
		
			//Assertion with Hamcrest
			given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "JSON").
			when().
			get("/search").
			then().
			body("numItems", equalTo(10));
				
			System.out.println("--------------------------Starting Test--------------------------");
			System.out.println("The value of numItems are: "+numItems);
			System.out.println("--------------------------End of Test--------------------------");
		}
	
	//2)Verify query
	//@Test
	public void test002(){
			//Assertion with Hamcrest
			given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "JSON").
			when().
			get("/search").
			then().
			body("query", equalTo("ipod"));
		}
	
	//3)Check Single Name in ArrayList
	//@Test
	public void test003(){
			//Assertion with Hamcrest
			given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "JSON").
			when().
			get("/search").
			then().
			body("items.name", hasItem("Apple iPod touch 16GB"));
		}
	
	//4)Check Multiple Names in ArrayList
	//@Test
	public void test004(){
			//Assertion with Hamcrest
			given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "JSON").
			when().
			get("/search").
			then().
			body("items.name", hasItems("Apple iPod touch 16GB","Apple iPod Touch 6th Generation 16GB Refurbished"));
		}
	
	//5)Verify the gift options for the first product (Checking Values inside Map using hasValue()
	//@Test
	public void test005(){
			given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "JSON").
			when().
			get("/search").
			then().
			body("items[0].giftOptions", hasKey("allowGiftWrap"));
		}
	
	//6)Check hashmap values inside a list
	//@Test
	public void test006(){
			given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "JSON").
			when().
			get("/search").
			then().
			body("items.findAll{it.name=='Apple iPod touch 16GB'}", hasItems(hasEntry("name", "Apple iPod touch 16GB")));
		}

	//7)Checking multiple values in the same statement
	//@Test
	public void test007(){
			given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "JSON").
			when().
			get("/search").
			then().
			body("items.findAll{it.name=='Apple iPod touch 16GB'}", hasItems(hasEntry("name", "Apple iPod touch 16GB"))).
			body("items[0].giftOptions", hasKey("allowGiftWrap")).
			body("query", equalTo("ipod")).
			statusCode(200);
		}
	
	//8)Checking multiple values in the same statement
	@Test
	public void test008(){
			given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "JSON").
			when().
			get("/search").
			then().
			body("items.size()", equalTo(10)).
			body("items.size()", greaterThan(5)).
			body("items.size()", greaterThanOrEqualTo(10)).
			body("items.size()", lessThan(11)).
			body("items.size()", lessThanOrEqualTo(10));
		}
}
