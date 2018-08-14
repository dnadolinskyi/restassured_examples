package com.filedownloads.examples;

//static imports
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.junit.Test;

public class FileDownloadExample {

	//Download a file & Compare it with an Expected file
	//Check the size of the file
	@Test
	public void verifyDownloaddedFile(){
		//Read the input file
		
		File inputFile = new File(System.getProperty("user.dir")+File.separator+"geckodriver-v0.20.1-win64.zip");
		
		//Length of the input file
		int expectedSize = (int)inputFile.length();
		
		System.out.println("The size of the expected file is "+expectedSize+" bytes");
		
		//https://github.com/mozilla/geckodriver/releases/download/v0.20.1/geckodriver-v0.20.1-win64.zip
		
		//Read the downloaded file
		byte[] actualdValue = given()
		.when()
		.get("https://github.com/mozilla/geckodriver/releases/download/v0.20.1/geckodriver-v0.20.1-win64.zip")
		.then()
		.extract()
		.asByteArray();
		
		System.out.println("The size of the actual file is "+actualdValue.length+" bytes");
		
		assertThat(expectedSize, equalTo(actualdValue.length));
	}
}
