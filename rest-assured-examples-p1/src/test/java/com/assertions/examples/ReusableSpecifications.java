package com.assertions.examples;

import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class ReusableSpecifications {

	protected static final String APIKEY="9esrkgbaukf5fjsk6sdrdb75";
	
	public RequestSpecBuilder rspec;
	public RequestSpecification requestSpecification;
	
	public ResponseSpecBuilder respec;
	public ResponseSpecification responseSpecification;
	
	public RequestSpecification getGenericRequestSpec() {
		rspec = new RequestSpecBuilder();
		rspec.addQueryParameter("query", "ipod");
		rspec.addQueryParameter("apiKey", APIKEY);
		rspec.addQueryParameter("format", "JSON");
		requestSpecification = rspec.build();
		
		return requestSpecification;
		
	}
	
	public ResponseSpecification getGenericResponseSpec() {
		respec = new ResponseSpecBuilder();
		respec.expectHeader("Content-Type", "application/json;charset=UTF-8");
		respec.expectHeader("Transfer-Encoding", "chunked");
		respec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);	
		responseSpecification = respec.build();
		
		return responseSpecification;
		
	}

}

