package sesionOnePackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIVerificaion_2 {

	public static void main(String[] args) {
		
		RestAssured.baseURI= "https://fakerestapi.azurewebsites.net/";
		
		RequestSpecification httpsRequest = RestAssured.given();
		
		Response respData= httpsRequest.get("/api/v1/Activities");
		
		System.out.println(respData.asString());
		
	}

}
