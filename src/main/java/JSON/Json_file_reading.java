package JSON;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json_file_reading {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		
		FileReader rdr = new FileReader("test.json");
		
		Object parserobj = parser.parse(rdr);
		
		JSONObject obj = (JSONObject) parserobj;
		
		String name = (String) obj.get("NAME");
		
		Long age = (Long) obj.get("Age");
		
		String qual = (String) obj.get("Qualification");
		
		System.out.println("Age is : "+ age+"\nName is : "+ name+"\nQualification is : "+ qual);
		
		//Iterating over the array
		JSONArray arr = (JSONArray) obj.get("Height_and_Weight");
		
		 Iterator iter = arr.iterator();
		 
		 while(iter.hasNext()) {
			 System.out.println("\nThe Height and weight is : " + iter.next());
		 }
		 
		 for(Object X : arr) {
			 System.out.println("\nThe Height and weight is : " + X);
		 }
		
		
		

	}

}
