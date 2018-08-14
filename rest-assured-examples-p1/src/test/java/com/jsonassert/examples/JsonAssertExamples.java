package com.jsonassert.examples;

import io.restassured.RestAssured;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import ExampleBase.LocalTestBase;

public class JsonAssertExamples extends LocalTestBase{
	
	//LENIENT - soft check
	//@Test
	public void getStudents()throws IOException, JSONException {
		String expectedValue = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+
				File.separator+"file.txt")));
		
		RestAssured.basePath="/student/list";
		String actualValue = RestAssured.given()
				.when()
				.get()
				.asString();
		
		System.out.println(expectedValue);
		System.out.println(actualValue);
		
		JSONAssert.assertEquals(expectedValue, actualValue, JSONCompareMode.LENIENT);
	}
	
	//Strict - checks the order as well as data
	//@Test
	public void getStudentsStrict()throws IOException, JSONException {
		String expectedValue = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+
				File.separator+"difforder.txt")));
		
		RestAssured.basePath="/student/list";
		String actualValue = RestAssured.given()
				.when()
				.get()
				.asString();
		
		System.out.println(expectedValue);
		System.out.println(actualValue);
		
		JSONAssert.assertEquals(expectedValue, actualValue, JSONCompareMode.STRICT);
	}
}
