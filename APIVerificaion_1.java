package sesionOnePackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIVerificaion_1 {
	
	public static void main(String[] args) {
		
//		RestAssured = It is a class and it is used to set up a Rest-full Request.
//      BaseURI = Its a Static Data member from Rest Assured class and which is used to set our Root URL.
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	 	
		/*BDD Cucumber
		 * GWT - Given, When, Then
		 * Given = Always says our Prerequisites
		 * When = Always says our Action
		 * Then = Always says our Results
		 * Request Specification= It is an Interface.It helps you to modify the request,
		 * like adding headers or adding authentication details.
		 */
		
		RequestSpecification httpRequest = RestAssured.given();
		/*
		 * Response = It is an Interface,Which represents Response returned from the server
		 * response object = It will contain all the data send by the server.
		 * toString() Method= Use to convert response into a string.
		 */

		Response respData = httpRequest.get("/posts");
		
		System.out.println(respData.toString());		
	
	}
		
	}
		 

