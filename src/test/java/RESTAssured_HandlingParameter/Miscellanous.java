package RESTAssured_HandlingParameter;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

/* Wiremock mocks the server api so we can create similar API from the Req docs and write test 
 * cases using it before the real API arrives and when we get hands on in real API we can test it
 * rightaway without having to write testing codes,it saves time.
 * One more advantage is we can record the API request and response using the Wiremock recorder 
 * and store it our local dir as stubs and utilize it when we are having to run load testing and 
 * other testing without really having to put load on the server and other third party server
 * */
public class Miscellanous {

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI ="http://localhost:9999";
		RestAssured.port=9999;
		
	}
	
	@Test
	public void specify_port() {
		given()
		.queryParam("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce")
		.queryParam("symbols", "USD,EUR,AUD,CAD,PLN,MXN,INR,AED,RN").
		when().get("/latest")
		.then().log().all()
		.statusCode(200);
	}
	
	@Test
	public void response_time() {
		given()
		.queryParam("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce")
		.queryParam("symbols", "INR").
		when().get("/latest")
		.then().time(lessThan(200L), TimeUnit.MILLISECONDS);
	}
	
	/*XML namespace parsing is a way to use similar xml tags,
	 * if the same tags need to be used, then namespaces are used in xml we need config the xml 
	 * name space parser first
	 * and then run the test.To do that we need to create and "xmlConfig" object and use 
	 * that obj in given().config() and we are good to go */

	/*Here we r checking if the concat of two values is the result of other value in the body
	 * To determine it we can use 2 ways one is we can get the response and store it and then 
	 * fetching the req value from it and asserting it using conventional Assert or else we 
	 * use the below responseAwareMatchers */
	@Test
	public void response_aware_matcher() {
		given()
		.queryParam("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce")
		.queryParam("symbols", "USD,EUR,AUD,CAD,PLN,MXN,INR,AED,RN").
		when().get("/latest")
		.then().body("rates.PLN", response -> equalTo(response.path("rates.AUD").toString()+
				response.path("rates.CAD").toString()));
		
		}
}
