package schema_validator;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;

public class schema_validation {

	@Test
	public void json_schema_validation() {
				
		given().baseUri("http://data.fixer.io/api")
		.queryParam("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce")
		.queryParam("symbols", "USD,EUR,AUD,CAD,PLN,MXN,INR,AED,RN").
		when().get("/latest")
		.then().log().all()
		.statusCode(200)
		.body(matchesJsonSchema(new File("src/test/resources/json_schema_1.json")));
	}
	
	@Test
	public void xml_dtd_schema_validation() {
		Map<String,Object> params = new HashMap<String,Object>();
		  params.put("q", "Chennai");
		  params.put("appid", "e277d84e5b6964996ecf11b9d40c8164");
		  params.put("mode", "xml");
		  
		  given().baseUri("https://api.openweathermap.org/data/2.5")
			.queryParams(params)		
			.when().get("/weather")
			.then().log().all()
			.statusCode(200)
			.body(matchesDtd(new File("src/test/resources/xml_dtd_schema.dtd")));
	}
	
	@Test
	public void xml_xsd_schema_validation() {
		Map<String,Object> params = new HashMap<String,Object>();
		  params.put("q", "Chennai");
		  params.put("appid", "e277d84e5b6964996ecf11b9d40c8164");
		  params.put("mode", "xml");
		  
		  given().baseUri("https://api.openweathermap.org/data/2.5")
			.queryParams(params)		
			.when().get("/weather")
			.then().log().all()
			.statusCode(200)
			.body(matchesXsd(new File("src/test/resources/xml_xsd_schema.xsd")));
	}
}
