package ExampleBase;

import io.restassured.RestAssured;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.*;

public class LocalTestBase {
	
	@BeforeClass
	public static void init(){
		RestAssured.baseURI="http://localhost:8085";
		//RestAssured.rootPath="[0]";
	}
	
	@AfterClass
	public static void TearDown(){
		RestAssured.reset();
	}
}
