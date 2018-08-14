package com.jsonpath.examples;

import static org.hamcrest.Matchers.*;
import static com.jayway.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import ExampleBase.TestBase;

import javax.management.StringValueExp;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class SearchJsonPathExample extends TestBase{
	
	//1)Extract numItems
	@Test
	public void test001(){
		int numItems = given().
			queryParam("query","ipod").
			queryParam("apiKey",APIKEY).
			queryParam("format","json").
		when().
		get("/search").
		then().
		extract().
		path("numItems");
		System.out.println("-------------------Starting Test-------------------");
		System.out.println("The total number of items are: "+numItems);
		System.out.println("-------------------End of Test-------------------");
	}
	
	//2)Extract query
	//@Test
	public void test002(){
		String query = given().
			queryParam("query","ipod").
			queryParam("apiKey",APIKEY).
			queryParam("format","json").
		when().
		get("/search").
		then().
		extract().
		path("query");
		System.out.println("-------------------Starting Test-------------------");
		System.out.println("The query is set to: "+query);
		System.out.println("-------------------End of Test-------------------");
	}
	
	//3)Extract first productionName by providing list index value
	//@Test
	public void test003(){
		String productName = given().
			queryParam("query","ipod").
			queryParam("apiKey",APIKEY).
			queryParam("format","json").
		when().
		get("/search").
		then().
		contentType(ContentType.JSON).
		extract().
		path("items[0].name");
		
		System.out.println("-------------------Starting Test-------------------");
		System.out.println("The Product Name is: "+productName);
		System.out.println("-------------------End of Test-------------------");
	}
	
	//4)Get the gift options for the firstproduct
		//@Test
		public void test004(){
			HashMap<String,String> giftOptions = 
				given().
				queryParam("query","ipod").
				queryParam("apiKey",APIKEY).
				queryParam("format","json").
			when().
			get("/search").
			then().
			contentType(ContentType.JSON).
			extract().
			path("items[0].giftOptions");
			
			System.out.println("-------------------Starting Test-------------------");
			System.out.println("The gift options are: "+giftOptions);
			System.out.println("-------------------End of Test-------------------");
		}
	//5)Print the size of items
		//@Test
		public void test005(){
			int size = given().
				queryParam("query","ipod").
				queryParam("apiKey",APIKEY).
				queryParam("format","json").
			when().
			get("/search").
			then().
			extract().
			path("items.size()");
			
			System.out.println("-------------------Starting Test-------------------");
			System.out.println("The size of items: "+size);
			System.out.println("-------------------End of Test-------------------");
		}
		
		//6)Get all the names
		//@Test
		public void test006(){
			List<String> names = given().
				queryParam("query","ipod").
				queryParam("apiKey",APIKEY).
				queryParam("format","json").
			when().
			get("/search").
			then().
			extract().
			path("items.name");
			
			System.out.println("-------------------Starting Test-------------------");
			System.out.println("The names are: "+names);
			System.out.println("-------------------End of Test-------------------");
		}
		//7)Get all the values for name==Apple iPod Touch 32Gb
		//@Test
		public void test007(){
			List<HashMap<String,Object>> values = given().
				queryParam("query","ipod").
				queryParam("apiKey",APIKEY).
				queryParam("format","json").
			when().
			get("/search").
			then().
			extract().
			path("items.findAll{it.name=='Apple iPod touch 32GB'}"); 
			
			System.out.println("-------------------Starting Test-------------------");
			System.out.println("The names are: "+values);
			System.out.println("-------------------End of Test-------------------");
		}
		//8)Get the sale price for  name==Apple iPod Touch 32Gb
		//@Test
		public void test008(){
			List<Float> salePrice = given().
				queryParam("query","ipod").
				queryParam("apiKey",APIKEY).
				queryParam("format","json").
			when().
			get("/search").
			then().
			extract().
			path("items.findAll{it.name=='Apple iPod touch 32GB'}.salePrice"); 
			
			System.out.println("-------------------Starting Test-------------------");
			System.out.println("The sale prices are: "+salePrice);
			System.out.println("-------------------End of Test-------------------");
		}
		
		//9)Get the names which have salePrice less than 150
		//@Test
		public void test009(){
			List<String> names = given().
				queryParam("query","ipod").
				queryParam("apiKey",APIKEY).
				queryParam("format","json").
			when().
			get("/search").
			then().
			extract().
			path("items.findAll{it.salePrice<150}.name"); 
			
			System.out.println("-------------------Starting Test-------------------");
			System.out.println("The items that have the price less than 150 are: "+names);
			System.out.println("-------------------End of Test-------------------");
		}
		
		//10)Get the upc of items that Start with name=Ref
		//@Test
		public void test0010(){
			List<String> upcValues = given().
				queryParam("query","ipod").
				queryParam("apiKey",APIKEY).
				queryParam("format","json").
			when().
			get("/search").
			then().
			extract().
			path("items.findAll{it.name==~/Ref.*/}.upc"); 
			
			System.out.println("-------------------Starting Test-------------------");
			System.out.println("The upc of items that Start with name=Ref: "+upcValues);
			System.out.println("-------------------End of Test-------------------");
		}
		
		//11)Get the salePrice of items that end with name=ed
				@Test
				public void test0011(){
					List<String> salePrice = given().
						queryParam("query","ipod").
						queryParam("apiKey",APIKEY).
						queryParam("format","json").
					when().
					get("/search").
					then().
					extract().
					path("items.findAll{it.name==~/.*ed/}.salePrice"); 
					
					System.out.println("-------------------Starting Test-------------------");
					System.out.println("The salePrice of items that end with name=ed: "+salePrice);
					System.out.println("-------------------End of Test-------------------");
				}
}
