package ExampleBase;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.junit.BeforeClass;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.*;

public class WallMartTestBaseIO {
	
	protected static final String APIKEY="9esrkgbaukf5fjsk6sdrdb75";
	protected static RequestSpecBuilder builder;
	protected static RequestSpecification requestSpec;
	
	protected static ResponseSpecBuilder responseBuilder;
	protected static ResponseSpecification responseSpec;
	
	
	@BeforeClass
	public static void init(){
		RestAssured.baseURI="http://api.walmartlabs.com";
		RestAssured.basePath="/v1";
		
		//Request builder
		builder = new RequestSpecBuilder();
		builder.addQueryParam("query", "ipod");
		builder.addQueryParam("apiKey", APIKEY);
		builder.addQueryParam("format", "json");
		builder.addQueryParam("facet", "on");
		builder.addHeader("Accept","*/*");
		
		requestSpec = builder.build();
		
		//Response builder
		responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
		responseBuilder.expectHeader("Server", "Mashery Proxy");
		responseBuilder.expectStatusCode(200);
		
		responseBuilder.expectBody("query", equalTo("ipod"));
		responseBuilder.expectBody("numItems", equalTo(10));
		responseBuilder.expectBody("items.name", hasItem("Apple iPod touch 128GB"));
		responseBuilder.expectResponseTime(lessThan(3L),TimeUnit.SECONDS);
		
		responseSpec = responseBuilder.build();
	}
}
