package RESTAssured;
//changing the import to static will let us use the methods inside the class without classname or object
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
// static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class hamrestmatcher {
  @Test
  public void equalsfloatCheck() {
	 baseURI="http://localhost:3000/";
	 
	 given().contentType(ContentType.JSON).accept(ContentType.JSON)
	 .when()
		.get("team")
		.then()
		.body("id", equalTo(3)).body("data.height", equalTo(6.2f))
		.body("data.weight", equalTo(67.6f));
  }
  
  @Test
  public void TimeCheck() {
	 baseURI="http://localhost:3000/";
	 
	 given().contentType(ContentType.JSON).accept(ContentType.JSON)
	 .when()
		.get("team")
		.then()
		.time(lessThan(100L),TimeUnit.MILLISECONDS);
  }
  
  @Test
  public void ArrayCheck() {
	 baseURI="http://localhost:3000/";
	 
	 given().contentType(ContentType.JSON).accept(ContentType.JSON)
	 .when()
		.get("team")
		.then()
		.body("odds",hasSize(2)).body("odds",empty());
  }
  
  @Test
  public void ItemsCheck() {
	 baseURI="http://localhost:3000/";
	 
	 given().contentType(ContentType.JSON).accept(ContentType.JSON)
	 .when()
		.get("team")
		.then()
		.body("odds.price",hasItems(4.7f,5.7f))
		.body("data.weight",is(67.6f));
  }
  
  @Test
  public void validation() {
	  given().baseUri("https://restcountries.eu/rest/v2")
	  .when().get("/alpha/USA")
	  .then().log().all().body("area",equalTo(9629091f),
			  		"alpha3Code",equalTo("USA"),
			  		"altSpellings", hasItem("US"),
			  		"currencies[0].code", equalTo("USD")
			  );
  }
}
