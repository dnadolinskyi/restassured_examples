package com.fileupload.example;

//static imports
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.junit.Test;

public class FileUploadLoadExample {
	
	/**
	 * Upload a gif file to zamzar.com and convert it into a png file
	 */
	@Test
	public void uploadFileExample(){
		String apiKey="d056d28adf60cd4675d0a269ad8b77eb2ee9310c";
		String endpoint = "https://sandbox.zamzar.com/v1/jobs";
		
		File inputFile = new File(System.getProperty("user.dir")+File.separator+"dancing_banana.gif");
		
		System.out.println(inputFile.length());
		
		given()
		.auth()
		.basic(apiKey, "")
		.multiPart("source_file", inputFile)
		.multiPart("target_format", "png")
		.when()
		.post(endpoint)
		.then()
		.log()
		.all();
	}
}
