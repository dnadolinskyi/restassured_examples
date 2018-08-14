package com.softassertions.examples;

import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

import org.junit.Test;

import ExampleBase.LocalTestBase;

public class SoftAssertionsExamples extends LocalTestBase{
	//1) Hard Assertions example mean that if assertion fails then other assertions won't be executed
	@Test
	public void hardAsserts(){
		RestAssured.basePath="/student/list";
		
		RestAssured.given()
		.when()
		.get()
		.then()
		.root("[0]")
		.body("firstName", equalTo("Vernon"))
		.body("lastName", equalTo("Harper"))
		.body("email", equalTo("egestas.rhoncus.Proin@massaQuisqueporttitor.org"))
		.body("programme", equalTo("Financial Analysis"));
	}

	//2) Soft Assertions example mean that all Assertions will be executed even if any of them fails
	@Test
	public void softAsserts(){
		RestAssured.basePath="/student/list";
		
		RestAssured.given()
		.when()
		.get()
		.then()
		.root("[0]") //rootPath in the Class as local variable. Check LocalTestBase as global variable//RestAssured.rootPath="[0]";
		.body("firstName", equalTo("Vernon"),
				"lastName", equalTo("Harper"),
				"email", equalTo("egestas.rhoncus.Proin@massaQuisqueporttitor.org"),
				"programme", equalTo("Financial Analysis"))
	
		.root("[2]") 
		.body("firstName", equalTo("Reece"),
				"lastName", equalTo("Jason"),
				"email", equalTo("tincidunt.dui@ultricessit.co.uk"),
				"programme", equalTo("Computer Science"));
	}
}
