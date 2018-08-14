package com.restassured.proxy.examples;

//static imports
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class ProxyExample{
	
	public static RequestSpecBuilder rspec;
	public static RequestSpecification rp;
	
	@BeforeClass
	public static void init(){
		ProxySpecification proxySpec = new ProxySpecification("localhost", 5555, "http");
		RestAssured.baseURI="http://localhost:8085";
				
		rspec = new RequestSpecBuilder();
		rspec.setProxy(proxySpec);
		rp = rspec.build();
		
	}
	
	@AfterClass
	public static void TearDown(){
		RestAssured.reset();
	}
	
	@Test
	public void sendRequestProxy(){
		/*
		ProxySpecification proxySpec = new ProxySpecification("localhost", 5555, "http");
		RestAssured.proxy(proxySpec); //initial parameter for all requests in the method or class
		*/
		RestAssured.proxy("localhost", 5555);
		
		RestAssured.given()
		/*
		.proxy(5555)
		.proxy("localhost",5555)
		.proxy(proxySpec)
		 */
		.when()
		.get("/student/list")
		.then()
		.log()
		.body();
	}
	
	//Proxy request using spec
	@Test
	public void sendRequestProxy2(){
		
		RestAssured.given()
		.spec(rp)
		.when()
		.get("/student/list")
		.then()
		.log()
		.body();
	}
}
