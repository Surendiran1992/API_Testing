package RESTAssured_HandlingParameter;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;

public class Parameter {
  @Test
  public void Handling_single_param() {
	  given().baseUri("https://restcountries.eu/rest/v2")
	  .param("fullText", true)
	  .when().get("/name/india")
	  .then().log().ifValidationFails()
	  .statusCode(200);
  }
  
  @Test
  public void Multiple_params() {
	  Map<String,Object> params = new HashMap<String,Object>();
	  params.put("q", "Chennai");
	  params.put("appid", "e277d84e5b6964996ecf11b9d40c8164");
	  params.put("mode", "xml");
	  
	  given().baseUri("https://api.openweathermap.org/data/2.5")
		.queryParams(params)		
		.when().get("/weather")
		.then().log().all()
		.statusCode(200);
  }
  @Test
  public void Handling_MutliValue_params() {
	  given().baseUri("https://restcountries.eu/rest/v2").
	  param("codes", "col;no;in;ee").
	  when().get("/alpha").
	  then().log().everything().
	  statusCode(200);
  }
  
  //to generate random currency code in path parameter
  //https://restcountries.eu/rest/v2/currency/{{$randomCurrencyCode}}
  
  //https://restcountries.eu/rest/v2/currency/{currency}
  @Test
  public void Handling_Path_Param() {  //multipath param can also be handled https://restcountries.eu/rest/v2/currency/{currency}/name/{name}
	  given().baseUri("https://restcountries.eu/rest/v2").
	  pathParam("currency", "eur").
	  when().get("/currency/{currency}").
	  then().log().everything().
	  statusCode(200);
  }
  
	/*
	 * For Form Parameters: types of form parameters we need to specify it in
	 * contentType
	 * multipart/form-data 
	 * application/x-www-form-urlencoded
	 * application/json //https://postman-echo.com/post
	 */  
  @Test
  public void Handling_Form_Param() { 
	  given().baseUri("https://postman-echo.com").
	  contentType("application/x-www-form-urlencoded;charset=UTF-8")
	  .formParam("First Name", "Suren")
	  .formParam("Last Name", "diran")
	  .when().post("/post")
	  .then().log().headers().log().all()
	  .statusCode(200);
  }
}
