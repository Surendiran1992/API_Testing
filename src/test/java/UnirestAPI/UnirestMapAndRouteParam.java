package UnirestAPI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
//To pass multiple headers or fields, we can create a map and pass them to .headers(Map<String, Object> headers) and .fields(Map<String, String> fields) respectively:
import org.testng.annotations.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestMapAndRouteParam {
	@Test
	public void UsingMAP() throws UnirestException {
		Map<String,String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Bearer 5a9ce37b3100004f00ab5154");

		Map<String,Object> body = new HashMap<>();
		body.put("name", "Suren");
		body.put("id", "PYT567");

		HttpResponse<JsonNode> reposne = Unirest.put("http://www.mocky.io/v2/5a9ce7853100002a00ab515e")
				.headers(headers).fields(body).asJson(); 

		Assert.assertEquals(202, reposne.getStatus());
	}

	//For passing any URL parameters, we can use the routeParam() method:
	@Test
	public void UsingParam() throws UnirestException {
		HttpResponse<JsonNode> reposne = Unirest.get("http://www.mocky.io/v2/5a9ce37b3100004f00ab5154/{userId}")
				.routeParam("userId", "123").asJson();
		Assert.assertEquals(200, reposne.getStatus());

	}


		@Test
		public void UploadindFiles() throws UnirestException {
			HttpResponse<JsonNode> response = Unirest.post("http://www.mocky.io/v2/5a9ce7663100006800ab515d")
					.field("file", new File("C:\\Users\\dell 7570\\OneDrive\\Documents\\Suren\\Java\\Work_Space\\APITesting\\Notes.docx"))
					.asJson();

			Assert.assertEquals(201, response.getStatus());

		}
		
		@Test
		public void givenByteStreamWhenUploadedThenCorrect() throws FileNotFoundException, IOException, UnirestException {
			try (InputStream inputStream = new FileInputStream(
					new File("C:\\Users\\dell 7570\\OneDrive\\Documents\\Suren\\Java\\Work_Space\\APITesting\\Notes.docx"))) {
				byte[] bytes = new byte[inputStream.available()];
				inputStream.read(bytes);
				HttpResponse<JsonNode> jsonResponse = Unirest.post(
						"http://www.mocky.io/v2/5a9ce7663100006800ab515d")
						.field("file", bytes, "article.txt")
						.asJson();

				Assert.assertEquals(201, jsonResponse.getStatus());
			}
		}
	}
