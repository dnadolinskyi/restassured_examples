package com.specification.examples;

import org.junit.Test;

import static io.restassured.RestAssured.given;

import ExampleBase.WallMartTestBaseIO;
import static org.hamcrest.Matchers.hasItem;

public class ResponseSpecificationExample extends WallMartTestBaseIO {
	
	//Re-use of the code by implementing requestSpecification
	@Test
	public void test001(){
		given()
		.spec(requestSpec)
		.when()
		.get("/search")
		.then()
		.spec(responseSpec);
		
	}
	
	@Test
	public void test002(){
		given()
		.spec(requestSpec)
		.when()
		.get("/search")
		.then()
		.spec(responseSpec)
		.body("items.name", hasItem("Refurbished B-Grade Apple iPod Touch 32GB 5th Generation Blue FD717LL/A"));
		
	}
	
}
