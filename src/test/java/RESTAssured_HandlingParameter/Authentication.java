package RESTAssured_HandlingParameter;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
public class Authentication {
	/*
	 * Authentication:
	 *  1. Basic VS Digest 
	 *  Basic auth is just encoded but not encrypted wherease disgest are enocoded as well as encrypted
	 *  2. Preemptive VS Challenged
	 *  Preemptive auth is give user cerenditials before the server request for it whereas
	 *  in challenged it provides only when it is asked.
	 *  By default both digest and basic are challenged
	 *  
	 *  Example:
	 *   URI: https://postman-echo.com
	 *   End-point: /digest-auth
	 */
	@Test
	public void digest_auth() {
		given().baseUri("https://postman-echo.com")
		.auth().digest("postman", "password")
		.when().get("/digest-auth")
		.then().log().all().statusCode(200);
	}
	/*
	 * oAuth 1.0:
	 * Consumer Key (API Key)
	 * Consumer Secret (API Secret Key)
	 * Access Token
	 * Access Token Secret*/
	//API for posting the tweet :https://api.twitter.com/1.1/statuses/update.json
	//Here we r simulating how other apps which ask for permission to post something in our social-website wall
	//We created a developer account and created an app for doing this,all token are retrived.
	//We give our API&secret key to 3rd party app to post something on our behalf
	@Test
	public void Oauth1_auth() {
		String tweet = "This is simple tweet to test the REST API using Oauth";
		Response response =	given().baseUri("https://api.twitter.com/1.1")
				.auth().oauth("5x7fEWwXHBk9gpiNAkCtHxh0a",
						"iiKezwIsfxQ76Qk5i86YwkNZLiemzuS8HSSSeXTPGygq9RW81Z", 
						"893137199079636992-oyx2z54aY1ag99OOGXWuiMKqdp148YH", 
						"vlUwApGqqLDJhYgKgCOCzCvGvoPoQHU9UopGB5isTffvE")
				.param("status", tweet)
				.when().post("/statuses/update.json")
				.then().extract().response();
				
		String res=response.asString();
		JsonPath js=new JsonPath(res);

		System.out.println("id is"+js.get("id"));

		String Tweetid=(js.get("id")).toString();

		System.out.println("Id of newly Created Tweet is \t"+Tweetid);
	}
	
	/*
	 * oAuth 2.0
	 * 
	 * Consumer Key (API Key)
	 * Consumer Secret (API Secret Key)
	 * Access Token - These are generated only when sending request to server then the server
	 * will generate the token on the fly and we can use the token until specified expiry time
	 * to perform API requests(Customer keys are not required once bearer token is generated,
	 * customer keys are needed only when acquiring the access token)
	 * 
	 */
	
	@Test
	public void Oauth2_auth() {
		
		String accessToken = given()
			.baseUri("https://api.sandbox.paypal.com/v1")
			.contentType("application/x-www-form-urlencoded;charset=UTF-8")
			.header("Accept-Language","en_US")
			.param("grant_type", "client_credentials")
			.auth().preemptive().basic(
						"Afq8Vt73m98ez_xXDGaDIwXueTXcTvGSizt4SkOcM5J9ZeWoxFKuCI4x12kqYPsZis6mCPQLeuHhiMRg",
						"EBlgaZ_4UKOo6i22KAcQjVyo8hFYqySaRuawMrUmPlmHSbQJLjLOIRDdnKvQ3W5lnc-d6iulVsc9OR-z").
		when()
			.post("/oauth2/token").
		then()
			.log().all()
			.statusCode(200)
			.extract().path("access_token");
		
		//Generate next invoice
		
		given()
			.baseUri("https://api.sandbox.paypal.com/v2")
			.contentType("application/json")
			.auth().oauth2(accessToken).
		when()
			.post("/invoicing/generate-next-invoice-number").
		then()
			.log().all()
			.statusCode(200);

	}

}
