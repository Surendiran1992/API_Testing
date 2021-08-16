package JSON;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Json_Creation {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("NAME", "sUREN");
		obj.put("Qualification", "M.S");
		obj.put("Age", 28);
		
		//creating json array here
		JSONArray arr= new JSONArray();
		arr.add(5.8f);
		arr.add(78);
		
		obj.put("Height_and_Weight", arr);
		
		FileWriter wrt = new FileWriter("test.json");
		wrt.write(obj.toJSONString());
		wrt.close();		

	}

}
