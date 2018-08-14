package com.specification.examples;

import org.junit.Test;

import static io.restassured.RestAssured.given;

import ExampleBase.WallMartTestBaseIO;

public class RequestSpecificationExample extends WallMartTestBaseIO {
	
	//Re-use of the code by implementing requestSpecification
	@Test
	public void test001(){
		given()
		.spec(requestSpec)
		.when()
		.get("/search")
		.then()
		.log()
		.all();
		
	}
	
}
