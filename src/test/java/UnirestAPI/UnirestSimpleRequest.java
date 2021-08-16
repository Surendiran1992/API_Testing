package UnirestAPI;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestSimpleRequest {
	
	@Test
	public void getrequest() throws UnirestException {
		HttpResponse<JsonNode> response =  Unirest.get("http://dummy.restapiexample.com/api/v1/employees")
				.header("Content-Type", "application/json").asJson();
				
		Assert.assertEquals(200,response.getStatus());
		Assert.assertNotNull(response.getBody());
		System.out.println("The status code is : "+ response.getStatus()+
				"\nThe status message is : "+ response.getStatusText()+
				"\nThe body is : " + response.getBody());
	}
	
	@Test
	public void postrequest() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.post("http://www.mocky.io/v2/5a9ce7663100006800ab515d")
		.header("Content-Type", "application.json")
		.body("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}")
		.asJson();
		
		Assert.assertEquals(201,response.getStatus());
		Assert.assertNotNull(response.getBody());
		System.out.println("\nThe status code is : "+ response.getStatus()+
				"\nThe status message is : "+ response.getStatusText()+
				"\nThe body is : " + response.getBody());
	}
	@Test(dependsOnMethods = {"postrequest"})
	public void putrequest() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.put("http://dummy.restapiexample.com/api/v1/update/2")
		.header("Content_Type", "application.json")
		.body("{\"name\":\"Suren\",\"salary\":\"7879787\",\"age\":\"28\"}").asJson();
		
		System.out.println("\nThe status code is : "+ response.getStatus()
							+"\nThe body is : " + response.getBody());
	}
	
	@Test
	public void deleterequest() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.delete("http://dummy.restapiexample.com/api/v1/delete/2")
		.header("Content_Type", "application.json").asJson();
		
		System.out.println("\nThe status code is : "+ response.getStatus());
		System.out.println("The body is : "+ response.getBody());
	}

}
