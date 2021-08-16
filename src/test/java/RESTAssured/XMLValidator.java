package RESTAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


//https://api.openweathermap.org/data/2.5/weather?q=Chennai&appid=e277d84e5b6964996ecf11b9d40c8164&mode=xml
public class XMLValidator {
	@Test
	public void xml_validation_weatherapp() {

		given().baseUri("https://api.openweathermap.org/data/2.5")
		.queryParam("q", "Chennai")
		.queryParam("appid", "e277d84e5b6964996ecf11b9d40c8164")
		.queryParam("mode", "xml")
		.when().get("/weather")
		.then().statusCode(200).log().all()
		.body("current.city.@id", equalTo("1264527"),
				"current.city.country",equalTo("IN"),
				"current.wind.speed.@value", equalTo("0.89")			  
				);
	}

	@Test
	public void extract_single_value() {
	String feelslike =	given().baseUri("https://api.openweathermap.org/data/2.5")
		.queryParam("q", "Chennai")
		.queryParam("appid", "e277d84e5b6964996ecf11b9d40c8164")
		.queryParam("mode", "xml")
		.when().get("/weather")
		.then().statusCode(200).log().all()
		.extract().path("current.feels_like.@value");
	System.out.println(feelslike);
	}
}
