package Main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

public class Search {
	
	//public static Properties properties = new Properties();
	public static void submitQueryMethod(String query){
		
		String name = "", type = "", description = "";
		
		try{
			//properties.load(new FileInputStream("kgsearch.properties"));
			
			HttpTransport httpTransport = new NetHttpTransport();
			HttpRequestFactory factoryRequest = httpTransport.createRequestFactory();
			JSONParser parser = new JSONParser();
			GenericUrl url = new GenericUrl("https://kgsearch.googleapis.com/v1/entities:search");
			
			url.put("query", query);
			url.put("limit", "1");
			url.put("indent", "true");
			url.put("key", "AIzaSyCectN98JfoNUWE0GLiYCTkQ3r4xVaShHY");
			
			HttpRequest request = factoryRequest.buildGetRequest(url);
			HttpResponse httpResponse = request.execute();
			JSONObject response = (JSONObject) parser.parse(httpResponse.parseAsString());
			JSONArray elements = (JSONArray) response.get("itemListElement");
			
			for(Object element : elements){
				name = (JsonPath.read(element, "$.result.name").toString());
				type = (JsonPath.read(element, "$.result.@type").toString());
				description = (JsonPath.read(element, "$.result.description").toString());
			}
			
			if (name.equals("") || type.equals("") || description.equals("")){
				System.out.println("Object unavailable");
			} else{
				System.out.println(name + "\n" + type + "\n" + description);
			}
			
			
		} catch (Exception x){
			x.printStackTrace();
		} 
		
		
	}

}