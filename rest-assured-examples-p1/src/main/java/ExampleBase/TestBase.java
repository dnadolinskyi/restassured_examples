package ExampleBase;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.RestAssured.*;

import org.junit.BeforeClass;

public class TestBase {
	
	protected static final String APIKEY="9esrkgbaukf5fjsk6sdrdb75";
	
	@BeforeClass
	public static void init(){
		RestAssured.baseURI="http://api.walmartlabs.com";
		RestAssured.basePath="/v1";
	}
}
