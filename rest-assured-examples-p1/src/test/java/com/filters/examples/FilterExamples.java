package com.filters.examples;

import java.io.PrintStream;
import java.io.StringWriter;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FilterExamples {
	
	public static StringWriter requestWriter;
	public static PrintStream requestCapture;
	
	public static StringWriter responseWriter;
	public static PrintStream responseCapture;
	
	public static StringWriter errorWriter;
	public static PrintStream errorCapture;
	
	@BeforeClass
	public static void init(){
		RestAssured.baseURI="http://localhost:8085";
		RestAssured.basePath="/student";
		//RestAssured.rootPath="[0]";
	}
	
	@Before
	public void beforeEachTest(){
		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
		
		responseWriter = new StringWriter();
		responseCapture = new PrintStream(new WriterOutputStream(responseWriter), true);
		
		errorWriter = new StringWriter();
		errorCapture = new PrintStream(new WriterOutputStream(errorWriter), true);
		
	}
	
	@AfterClass
	public static void TearDown(){
		RestAssured.reset();
	}
	//Logging simple
	//@Test
	public void getStudent(){
	String response =
		RestAssured.given()
		.log()
		.all()
		.when()
		.get("/list") 
		.asString();
	System.out.println(response);
	
	}
	
	//Logging advanced
	@Test
	public void getStudentAdvance(){
	String response =
		RestAssured.given()
		.when()
		.get("/list")
		.asString();
	
	//System.out.println(response);
	
	
	RestAssured
	.given()
	.filter(new RequestLoggingFilter(requestCapture)) //request capture
	.filter(new ResponseLoggingFilter(requestCapture)) //response capture
	.when()
	.get("/list"); 
	System.err.println(requestWriter.toString());
	
	
	RestAssured
	.given()
	.filter(new ErrorLoggingFilter(errorCapture)) //error capture
	.when()
	.get("/listsww21&sa:1"); //lists for error THOSE ERRORS BERWEEN 400 AND 500
	
	System.err.println(errorWriter.toString().toUpperCase());
	}
}
