package sesionOnePackage;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Collections;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.containsString;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndAutomation {
	
	//@Test
	public void TC1 () {  //[way-1 to use PUT operation]
		//validating data Before creation
		
		RequestSpecification reqSpec = new RequestSpecBuilder().build();
		reqSpec.baseUri("http://www.knowledgeware.in:9091/RestTutorial");
		reqSpec.basePath("/employee");
		
		RestAssured.given()
		.spec(reqSpec)
		.get("/12")
		.then()
		.statusCode(200)
		.body("",Matchers.hasSize(0))
		.body("isEmpty()" , Matchers.is(true))
		.body("",equalTo(Collections.emptyList()));		
	}
	
	//@Test  // [Way-2 to use PUT operation] :-Using JSON Object
	public void TC2 () {
		RestAssured.baseURI = "http://www.knowledgeware.in:9091/RestTutorial/employee";
		RequestSpecification reqSpec = RestAssured.given();
		
		//for creating a jsonObject we need to configure a json simple 1.1.1 jar
		JSONObject jsonObject = new JSONObject ();
		
		jsonObject.put("id","11");
		jsonObject.put ("name","Shivpal-JSONObject");
		jsonObject.put("email","ab@gmail.com");
		jsonObject.put("phone","+12346816");
		
		reqSpec.contentType(ContentType.JSON).body(jsonObject.toJSONString());
		
		Response response = reqSpec.post("/Signup") ;
		
		System.out.println(response.getStatusCode());
		//Note:- Cross-check through postman whether data is created or not
	}
	
	// [Way-3] Method To convert JSON Node/body into the string and pass as a string(Very imp Question)
	
	String bodyPayLoad = "{ \n" +
		       "\"id\"     : \"222\" ,                      \n" +
		       "\"name\"   : \"Shivpal-JSONToString\",      \n" +
		       "\"email\"  : \"shivpal@jasontostring.com\", \n" +
		       "\"phone\"  : \"+17300021875\"               \n" +
		    "}";
	
	//@Test
	public void TC3 () {
		
		System.out.println(bodyPayLoad);
		
		RestAssured.baseURI= "http://www.knowledgeware.in:9091/RestTutorial/employee";
		
		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.contentType(ContentType.JSON).body(bodyPayLoad);
		
		Response response = reqSpec.post("/signup");
		
		System.out.println(response.getStatusCode());
		
	}
	//[Way -4] :- Extracting POST Response and validating a same response 
	String bodyPayLoad2 = "{ \n" +
		       "\"id\"     : \"223\" ,                      \n" +
		       "\"name\"   : \"Shivpal-POSTResponce\",      \n" +
		       "\"email\"  : \"shivpal@postresponce.com\",  \n" +
		       "\"phone\"  : \"+25300021875\"               \n" +
		    "}";
	
	//@Test
	public void TC4 () {
		
		RestAssured.baseURI = "http://www.knowledgeware.in:9091/RestTutorial/employee";
		
		RequestSpecification reqSpec = RestAssured.given();
		
		Response resp = reqSpec.contentType(ContentType.JSON)
				.body(bodyPayLoad2)
				.post("/Signup")
				.then()
				.statusCode(200)
				.extract()
				.response();
		System.out.println(bodyPayLoad);
		System.out.println("\n");
		//System.out.println(resp.asString());
		
		//Validation part for checking whether data get added or not
		Assert.assertEquals(resp.statusCode(), 200);
		
		//Validating a previously added data/value is correctly added or not
		Assert.assertEquals(resp.jsonPath().getString("id[20]"),"223");
		//If above validation fails then we can say user/data not added. and if it passed it means user is added.				
	}	
	
//*****************************************************************************************************//
	
	// MethOD:- 3 ,UPDATE Operation using "PUT" Method
	
	String bodyPayLoadPut = "{ \n" +
		       "\"id\"     : \"224\" ,                       \n" +
		       "\"name\"   : \"Shivpal-PUTResponceUpdated\", \n" +
		       "\"email\"  : \"shivpal@putresponce.com\",    \n" +
		       "\"phone\"  : \"+25300021875\"                \n" +
		       "}";
	
	//@Test
	public void TC5 () {
		
		RestAssured.baseURI = "http://www.knowledgeware.in:9091/RestTutorial";
		
		//RequestSpecification reqSpec = RestAssured.given();
		//Above line code can be eliminated as it can play a major role here
		
		Response resp =  RestAssured.given()
				.header("Content-type","application/json")
				.and()
				.body(bodyPayLoadPut)
				.when()
				.put("/employee/224")
				.then()
				.extract()
				.response();
		 
		 Assert.assertEquals(resp.statusCode(), 200);//Validation
		 
		 System.out.println(resp.asString());
	}
	//Now we are going to validate whether record gets updated or not by using below steps 
	//@Test
	public void TC6() {
		
		RequestSpecification resp = new RequestSpecBuilder().build();
		
		resp.baseUri("http://www.knowledgeware.in:9091/RestTutorial");
		resp.basePath(" /employee ");
		
		RestAssured.given().
		spec(resp)
		.get("/224")
		.then()
		.statusCode(200)
		.body(containsString("Shivpal-PUTResponceUpdated"));
	
	}

//******************************************************************************************************
	//DELETE Operation using "delete" method
	
	//@Test
	public void TC7 () {
		
		RestAssured.baseURI = "http://www.knowledgeware.in:9091/RestTutorial";
		
		Response resp = RestAssured.given()
				.header("Content-type","application/json")
				.when()
				.delete("/employee/112")
				.then()
				.extract()
				.response();
		
		Assert.assertEquals(resp.statusCode(),200);
		
	}	
	
	
	//Now we have to validate that whether Record gets deleted successfully or not
	@Test
	public void TC8 () {
		
		RequestSpecification reqSpec = new RequestSpecBuilder().build();
		reqSpec.baseUri("http://www.knowledgeware.in:9091/RestTutorial");
		reqSpec.basePath("/employee");
		
		RestAssured.given()
		.spec(reqSpec)
		.get("/112")
		.then()
		.statusCode(200)
		.body("",Matchers.hasSize(0))
		.body("isEmpty()" , Matchers.is(true))
		.body("",equalTo(Collections.emptyList()));	
		
	}
}
