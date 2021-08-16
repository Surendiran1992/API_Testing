package RESTAssured;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class staticTest {
  @Test
  public void eqaulscheck() {
	 baseURI="http://localhost:3000/";
	 
	 given().contentType(ContentType.JSON).accept(ContentType.JSON)
	 .when()
		.get("lotto")
		.then()
		.body("lottoId", equalTo(5));
  }
  
}
