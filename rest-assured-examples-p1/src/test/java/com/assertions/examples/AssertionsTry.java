package com.assertions.examples;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;



public class AssertionsTry extends ReusableSpecifications{
	
	
	@BeforeClass
	public void init(){
		RestAssured.baseURI="http://api.walmartlabs.com";
		RestAssured.basePath="/v1";
	}
	
	//1) Verify if the number of items is equal to 10
	@Test
	public void VerifyNumberOfItems(){
		
	
				
	}
	
	
}
