package RESTAssuredLogging;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.response.ResponseBody;

public class RESTLog {
  @Test
  public void log_all_details() {
	  given().baseUri("https://restcountries.eu/rest/v2")
	  .when().get("/alpha/USA")
	  .then().log().all();
  }
  
  @Test
  public void log_body() {
	 given().baseUri("https://restcountries.eu/rest/v2")
	  .when().get("/alpha/US")
	  .then().log().body();
  }
  
  @Test
  public void log_headers() {
	  given().baseUri("https://restcountries.eu/rest/v2")
	  .when().get("/alpha/US")
	  .then().log().headers();
  }
  @Test
  public void log_cookies() {
	  given().baseUri("https://restcountries.eu/rest/v2")
	  .when().get("/alpha/US")
	  .then().log().cookies();
  }
  
  @Test
  public void log_status() {
	  given().baseUri("https://restcountries.eu/rest/v2")
	  .when().get("/alpha/US")
	  .then().log().status();
  }
  
  @Test
  public void log_if_error() {    //it will log the error if the status code is from 400 to 599
	  given().baseUri("https://restcountries.eu/rest/v2")
	  .when().get("/alpha/UT")
	  .then().log().ifError();//.log().ifStatusCodeIsEqualTo(404);
  }
  @Test
  public void log_if_validation_fails() {    
  given().baseUri("https://restcountries.eu/rest/v2")
  .when().get("/alpha/USA")
  .then().log().ifValidationFails()				//this log if error should always preced the validation statement 
  .body("area",equalTo(9629091f),
		  		"alpha3Code",equalTo("India"),      //here we r intetionally failing to check the validation errot msg
		  		"altSpellings", hasItem("US"),
		  		"currencies[0].code", equalTo("USD"));
		  		
  }
}
