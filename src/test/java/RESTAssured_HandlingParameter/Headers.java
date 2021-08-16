package RESTAssured_HandlingParameter;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.http.Cookie;

public class Headers {
	/*
	 * What are Headers?
	 * 
	 * Headers contain important information in the form of meta-data associated with:
	 * 
	 * 		1. Request Body
	 * 		2. Response Body
	 * 		3. Caching of Response
	 * 		4. Authentication
	 * 		5. Cookies	
	 */
	//http://data.fixer.io/api/latest 
	//my access key = e1f68bb2cb7ab59ef333d0599a7830ce
	//symbols=USD,AUD,CAD,PLN,MXN,INR,AED,RN
	@Test
	public void Handling_Access() {
		given().baseUri("http://data.fixer.io/api")
		.queryParam("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce")
		.queryParam("symbols", "USD,EUR,AUD,CAD,PLN,MXN,INR,AED,RN").
		when().get("/latest")
		.then().log().all()
		.statusCode(200);
	}

	//If-None-Match: "7d66ba82c2aa525285346e332298bf30"  
	//If-Modified-Since: Thu, 01 Jul 2021 01:28:22 GMT
	//this will return 304 – Not Modified if the data is not changed since our last request
	//they find it using the etag and date assigned to us on every inception 
	@Test
	public void Handling_Headers() {
		given().baseUri("http://data.fixer.io/api")
		.queryParam("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce")
		.queryParam("symbols", "USD,EUR,AUD,CAD,PLN,MXN,INR,AED,RN")
		.header("If-None-Match","3a7b7b084218cec92f0a86b89dec467a")
		.header("If-Modified-Since","Wed, 14 Jul 2021 00:32:04 GMT")
		.when().get("/latest")
		.then().log().all();
		//.statusCode(200);
	}
	@Test
	public void Sending__Headers_Object() {
		Map<String,Object> headers = new HashMap<String,Object>();
		headers.put("If-None-Match","ba367c8af0177ab1f7fa410420cce78f");
		headers.put("If-Modified-Since","Mon, 05 Jul 2021 01:19:03 GMT");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce");
		param.put("symbols", "USD,EUR,AUD,CAD,PLN,MXN,INR,AED,RN");
		
		given().baseUri("http://data.fixer.io/api")
		.queryParams(param)		
		.headers(headers)
		.when().get("/latest")
		.then().log().all();
		//.statusCode(200);
	}
	
	@Test
	public void Sending__Cookies_Builder() {
		
		Cookie cookie = new Cookie.Builder("usertype","values").setDomain("domain")
						.setComment("testing cookie").setPath("/path").build();
		
		Map<String,Object> headers = new HashMap<String,Object>();
		headers.put("If-None-Match","ba367c8af0177ab1f7fa410420cce78f");
		headers.put("If-Modified-Since","Mon, 05 Jul 2021 01:19:03 GMT");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce");
		param.put("symbols", "USD,EUR,AUD,CAD,PLN,MXN,INR,AED,RN");
		
		given().baseUri("http://data.fixer.io/api")
		.queryParams(param)		
		.headers(headers)
		.cookie(cookie)
		.when().get("/latest")
		.then().log().all()
		.statusCode(200);
	}
	@Test
	public void Validating_Headers() {
		given().baseUri("http://data.fixer.io/api")
		.queryParam("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce")
		.queryParam("symbols", "USD,EUR,AUD,CAD,PLN,MXN,INR,AED,RN").
		when().get("/latest")
		.then().log().all()
		.header("transfer-encoding", "chunk")
		.statusCode(200);
	}
	
	@Test
	public void Extracting_Headers() {
		io.restassured.http.Headers header = given().baseUri("http://data.fixer.io/api")
		.queryParam("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce")
		.queryParam("symbols", "USD,EUR,AUD,CAD,PLN,MXN,INR,AED,RN").
		 when().get("/latest")
		.then().extract().headers();
		
		System.out.println(header.getValue("access-control-allow-methods"));
		System.out.println(header.getValue("last-modified"));
	}
	
	@Test
	public void Extracting_Cookies() { //we dont have actual cookie in site so we cannot test it
		Map<String, String> cookies = given().baseUri("http://data.fixer.io/api")
		.queryParam("access_key", "e1f68bb2cb7ab59ef333d0599a7830ce")
		.queryParam("symbols", "USD,EUR,AUD,CAD,PLN,MXN,INR,AED,RN").
		 when().get("/latest")
		.then().extract().cookies();
		
		System.out.println(cookies.get("key"));
		System.out.println(cookies.get("key"));
	}
	
}
