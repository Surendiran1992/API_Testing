package JavaAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class HttpUrlConnectionEx {

	public static void getURLMethod() throws IOException{
		System.out.println("THis is GET Method Execution");
		URL url = new URL("https://reqres.in/api/users");
		HttpURLConnection conn =   (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();   //optional (equivalnet to clicking send in postman)

		int statuscode= conn.getResponseCode();
		System.out.println("The response status code is : "+ statuscode);

		String statusmsg = conn.getResponseMessage();
		System.out.println("The response message is : "+ statusmsg);

		InputStream instream = conn.getInputStream();
		InputStreamReader rdr = new InputStreamReader(instream);
		BufferedReader brdr = new BufferedReader(rdr);     // we are using buffered reader here to read line by line

		String content; 
		StringBuilder bld = new StringBuilder();  //converting string to stringbld bcoz strbuilder is mutable

		while((content= brdr.readLine())!=null) {
			bld.append(content);
		}
		System.out.println(bld);

		rdr.close();
		brdr.close(); 
		instream.close();
	}

	public static void postMethod() throws IOException {
		System.out.println("\n\nTHis is POST Method Execution");
		URL url = new URL("http://dummy.restapiexample.com/api/v1/create");
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");

		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		conn.connect();

		String jsonData= "{\"name\":\"Suren\",\"salary\":\"50000\",\"age\":\"28\"}"; //slashes are escape sequences 
		byte[] jsonbytes = jsonData.getBytes();  //we cannot send string over HTTP, so we r getting the bytes of string and then writing it  

		OutputStream out = conn.getOutputStream();
		out.write(jsonbytes);

		System.out.println("The response status code is : "+ conn.getResponseCode()+"\n"+ conn.getResponseMessage());

		InputStream input = conn.getInputStream();
		InputStreamReader rdr = new InputStreamReader(input);
		BufferedReader brdr = new BufferedReader(rdr);

		String content;
		StringBuffer buff = new StringBuffer();

		while((content = brdr.readLine())!= null){
			buff.append(content);
						
		}
		System.out.println(buff);

	}

	public static void putMethod() throws IOException {
		System.out.println("\n\nTHis is PUT Method Execution");
		URL url = new URL("https://dummy.restapiexample.com/api/v1/update/2250");
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");

		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		conn.connect();

		String jsonData= "{\"name\":\"Suren\",\"salary\":\"1000000\",\"age\":\"28\"}"; //slashes are escape sequences 
		byte[] jsonbytes = jsonData.getBytes();  //we cannot send string over HTTP, so we r getting the bytes of string and then writing it  

		OutputStream out = conn.getOutputStream();
		out.write(jsonbytes);

		System.out.println("The response status code is : "+ conn.getResponseCode()+"\n"+ conn.getResponseMessage());

		InputStream input = conn.getInputStream();
		InputStreamReader rdr = new InputStreamReader(input);
		BufferedReader brdr = new BufferedReader(rdr);

		String content;
		StringBuffer buff = new StringBuffer();

		while((content = brdr.readLine())!= null){
			buff.append(content);
		}
		System.out.println(buff);

	}
	public static void deleteMethod() throws IOException {
		System.out.println("\n\nTHis is DELETE Method Execution");
		
		URL url = new URL("https://dummy.restapiexample.com/api/v1/delete/2250");
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("DELETE");

		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		conn.connect();

	
		System.out.println("The response status code is : "+ conn.getResponseCode()+"\n"+ conn.getResponseMessage());

		InputStream input = conn.getInputStream();
		InputStreamReader rdr = new InputStreamReader(input);
		BufferedReader brdr = new BufferedReader(rdr);

		String content;
		StringBuffer buff = new StringBuffer();

		while((content = brdr.readLine())!= null){
			buff.append(content);
		}
		System.out.println(buff);

	}
	public static void main(String[] args) throws IOException {

		getURLMethod(); 
		postMethod();
		putMethod();
		deleteMethod();
	}

}
