package RESTAssured;
/*Given - setting precondition or background, used for setting headers,query,cookies
When - this doesnt have much functionality, this actuallly precedes the actual get,post,put,delete methods
Then- this is used for validating the result or for logging purposes*/
import org.testng.annotations.Test;

import java.io.File;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
//import io.restassured.response.ValidatableResponse;

public class SimpleTest {
	@Test
	public void getRequest() {
		
		Response response =  RestAssured.get("https://reqres.in/api/users?page=2")
										.then().extract().response();
							

		System.out.println(response.body().asPrettyString());
	}

	@Test
	public void nextgetRequest() {
		//RestAssured.baseURI = "https://reqres.in/";

		ValidatableResponse response = RestAssured
				.given().baseUri("https://reqres.in/").accept(ContentType.JSON).param("page", "2")
				.when()
				.get("/api/users")
				.then().statusCode(200).statusLine("HTTP/1.1 200 OK");

		System.out.println(response);
		//it just validates the response

	}
	@SuppressWarnings("unchecked")
	@Test
	public void postRequest() {
		JSONObject obj = new JSONObject();
		obj.put("name", "suren");
		obj.put("job", "Engineer");
		
		//RestAssured.baseURI = "https://reqres.in/";
		
		Response response = RestAssured
		.given()
		.baseUri("https://reqres.in/").
		 contentType(ContentType.JSON).accept(ContentType.JSON).body(obj.toJSONString())   //this line is to convert json object to json string
		.when()
		.post("/api/users")
		.then().statusCode(201).statusLine("HTTP/1.1 201 Created").extract().response();
		System.out.println(response.body().asPrettyString());
	}
	
	@Test
	public void filepostReq() {
		String response = RestAssured
		.given().baseUri("https://reqres.in/").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(new File("test.json"))   //this line is add the json file to the post request
		.when()
		.post("/api/users")
		.then().statusCode(201).statusLine("HTTP/1.1 201 Created").extract().statusLine(); // response();
	
		System.out.println(response);
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void putRequest() {
		JSONObject obj = new JSONObject();    //this object was created from simpleJSON library
		obj.put("name", "suren");
		obj.put("job", "Tester");
		
		RestAssured.baseURI = "https://reqres.in/";
		
		Response response = RestAssured
		.given().contentType(ContentType.JSON).body(obj.toJSONString())   //this line is to convert json object to json string
		.when()
		.put("/api/users/2")
		.then().statusCode(200).statusLine("HTTP/1.1 200 OK").extract().response();
		
		System.out.println(response.body().asPrettyString());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void patchRequest() {
		JSONObject obj = new JSONObject();
		obj.put("name", "suren");
		obj.put("job", "Engineer");
		
		RestAssured.baseURI = "https://reqres.in/";
		
		Response response = RestAssured
		.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(obj.toJSONString())   //this line is to convert json object to json string
		.when()
		.patch("/api/users/2")
		.then().statusCode(200).statusLine("HTTP/1.1 200 OK").extract().response();
		
		System.out.println(response.body().asPrettyString());
	}
	
	@Test
	public void deleteRequest() {
				
		RestAssured.baseURI = "https://reqres.in/";
		
		RestAssured
		.given().contentType(ContentType.JSON).accept(ContentType.JSON)   //this line is to convert json object to json string
		.when()
		.delete("/api/users/2")
		.then().statusCode(204).statusLine("HTTP/1.1 204 No Content");
	}
}
