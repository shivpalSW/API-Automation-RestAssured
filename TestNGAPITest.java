package sesionOnePackage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TestNGAPITest {
	
	int userID = 5;
	int id = 44;

	// @Test
	public void verifyStatusCode() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";

		RequestSpecification httpReq = RestAssured.given();

		Response respData = httpReq.request(Method.GET, "/api/v1/Activities");

		System.out.println(respData.getStatusCode());

		Assert.assertEquals(respData.getStatusCode(), 200);

	}

	// @Test
	public void verifyBDDForm() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";

		RestAssured.given().when().get("c").then().statusCode(200);

	}

	//@Test
	public void testActivitySpecific(){
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
	

		RequestSpecification httpR= RestAssured.given();
		
		Response respData= httpR.request(Method.GET,"/api/v1/Activities/19");
		
		System.out.println(respData.asString());
 

	}
	//@Test
	public void specificPlaceHolder() {
		
		RestAssured.baseURI= "https://jsonplaceholder.typicode.com/posts";
		
		RequestSpecification resSpec = RestAssured.given();
		
		Response resData= resSpec.request(Method.GET,"?userId=5&id=44");
		
		System.out.println(resData.asString());
	
			
	}
	
	//@Test  //Parameterized 
	public void specificPlaceHolder2() {
	 
	RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	
	RequestSpecification resSpecData= RestAssured.given();
	
	Response res = resSpecData.request(Method.GET,"/posts?userID="+userID+"&id="+id);

	System.out.println(res.asString());	

	
	}
	
	//@Test
	public void specificPlaceHolder3() {
		 
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		//RequestSpecification resSpecData= RestAssured.given();

		System.out.println(RestAssured.given().get("/posts?id=1").getBody().asString());
		
	}
	 //collection Method:- Matcher [WAY-1]
	//@Test 
	public void TC1() {
		
		RestAssured.baseURI= "https://jsonplaceholder.typicode.com";
		RestAssured.given().when().get("/posts?id=44").then().
		body("id", hasItems(44));
		
	} 
	// By using containsString to validate a "title"  [WAY-2]
	//@Test
	public void TC2() {
		RestAssured.baseURI= "https://jsonplaceholder.typicode.com";
		
		RestAssured.given().when().get("/posts?id=44").then().body(containsString("optio dolor molestias sit"));
		//Need containsString implementation library here
		// optio dolor molestias sit= this is a title for a id no 44
		
	}
	//[WAY-3] Using equalTo method to verify the title
	// import this package= import static org.hamcrest.CoreMatchers.equalTo;
	//It is mostly used method in an industry
	//@Test 
	public void TC3() {
	
	RestAssured.baseURI= "https://jsonplaceholder.typicode.com";	
	
	RestAssured.given().when().get("/posts?id=44").then().body("title",equalTo("optio dolor molestias sit"));
		
	}
	//[WAY-4]
	@Test
	public void TC4 () {
		
		RestAssured.baseURI= "https://jsonplaceholder.typicode.com";
		
		ValidatableResponse responce= RestAssured.given().when().get("/posts?userID=10").then();
		
		responce.statusCode(HttpStatus.SC_OK);

	}

}
