package ExampleBase;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class TestXMLBase {
		
		protected static final String APIKEY="9esrkgbaukf5fjsk6sdrdb75";
		
		@BeforeClass
		public static void init(){
			RestAssured.baseURI="http://api.walmartlabs.com";
			RestAssured.basePath="/v1";
		}
}
