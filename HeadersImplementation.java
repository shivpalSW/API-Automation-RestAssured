package sesionOnePackage;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersImplementation {
// Get all response headers it return
	@Test
	public void TC1() {

		String bodyPayLoad = "{ \n" + 
				"\"id\"     : \"225\" ,               \n" + 
				"\"name\"   : \"Shivpal-Header\",     \n" + 
				"\"email\"  : \"shivpal@header.com\", \n" + 
				"\"phone\"  : \"+25300021875\"        \n" + 
				"}";

		RestAssured.baseURI = "http://www.knowledgeware.in:9091/RestTutorial";

		Response resp = RestAssured.given()
				.header("Content-type","application/json")
				.and()
				.body(bodyPayLoad)
				.when()
				.post("/Signup")
				.then()
				.extract()
				.response();

		
		System.out.println(resp.asString());

		Headers headers = resp.headers();

		for (Header header : headers) {

			System.out.println("key --" + header.getName() + " value -- " + header.getValue());
		}

	}
}
