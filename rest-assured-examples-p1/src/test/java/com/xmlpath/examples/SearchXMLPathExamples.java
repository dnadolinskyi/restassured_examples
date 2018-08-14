package com.xmlpath.examples;

import static io.restassured.RestAssured.*;
import static io.restassured.path.xml.XmlPath.*;
import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.internal.path.xml.NodeChildrenImpl;

import org.junit.Test;

import ExampleBase.TestXMLBase;

public class SearchXMLPathExamples extends TestXMLBase{
	
	//1)Extract numItems
	//@Test
	public void  test001(){
		String numItems = given().
			queryParam("query", "ipod").
			queryParam("apiKey", APIKEY).
			queryParam("format", "XML").
		when().
		get("/search").
		then().		
		extract().
		path("searchresponse.numItems");
		
		System.out.println("--------------------------Starting Test--------------------------");
		System.out.println("The value of numItems are: "+numItems);
		System.out.println("--------------------------End of Test--------------------------");
	}
	
	//2)Extract first productName by providing list index value
	//@Test
	public void  test002(){
		String productName = given().
			queryParam("query", "ipod").
			queryParam("apiKey", APIKEY).
			queryParam("format", "XML").
		when().
		get("/search").
		then().
		
		extract().
		path("searchresponse.items.item[0].name");
		
		System.out.println("--------------------------Starting Test--------------------------");
		System.out.println("The product name is: "+productName);
		System.out.println("--------------------------End of Test--------------------------");
	}
	
	//3)Get the gift options for the firstproduct
	//@Test
	public void  test003(){
		String xml  = given().
			queryParam("query", "ipod").
			queryParam("apiKey", APIKEY).
			queryParam("format", "XML").
		when().
		get("/search").
		asString();
		
		String giftOptions = with(xml).getString("searchresponse.items.item[0].giftOptions");
		
		System.out.println("--------------------------Starting Test--------------------------");
		System.out.println("The gift options are: "+giftOptions);
		System.out.println("--------------------------End of Test--------------------------");
	}


	//4)Get the size of items
	//@Test
	public void  test004(){
		NodeChildrenImpl sizeItems = given().
			queryParam("query", "ipod").
			queryParam("apiKey", APIKEY).
			queryParam("format", "XML").
		when().
		get("/search").
		then().		
		extract().
		path("searchresponse.items.item");
		
		System.out.println("--------------------------Starting Test--------------------------");
		System.out.println("The size of items is: "+sizeItems.size());
		System.out.println("--------------------------End of Test--------------------------");
	}
	
	//5) Get all the Names
	//@Test
	public void  test005(){
		String xml = given().
			queryParam("query", "ipod").
			queryParam("apiKey", APIKEY).
			queryParam("format", "XML").
		when().
		get("/search").asString();
		
		List<String> names = with(xml).getList("searchresponse.items.item.name");
		
		System.out.println("--------------------------Starting Test--------------------------");
		System.out.println("The names are: "+names);
		System.out.println("--------------------------End of Test--------------------------");
	}
	
	//6)Get the sale price for Name==Apple iPod touch 32GB
	@Test
	public void  test006(){
		String xml = given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "XML").
			when().
			get("/search").asString();
			
			List<String> prices = with(xml).getList("searchresponse.items.item.findAll{it.name=='Apple iPod touch 32GB'}.salePrice");
			
			System.out.println("--------------------------Starting Test--------------------------");
			System.out.println("The prices are: "+prices);
			System.out.println("--------------------------End of Test--------------------------");
		}
	
	//7)Deep search in XML Path
	//@Test
	public void  test007(){
		String xml = given().
				queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "XML").
			when().
			get("/search").asString();
			
			List<String> prices = with(xml).getList("**.findAll{it.name=='Apple iPod touch 32GB'}.salePrice");
			
			System.out.println("--------------------------Starting Test--------------------------");
			System.out.println("The prices are: "+prices);
			System.out.println("--------------------------End of Test--------------------------");
		}
}
