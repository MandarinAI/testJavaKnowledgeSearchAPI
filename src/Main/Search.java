package Main;

import com.google.api.client.googleapis.auth.clientlogin.ClientLogin.Response;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.jayway.jsonpath.JsonPath;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.util.Properties;

public class Search {
	
	//public static Properties properties = new Properties();
	public static void main(String[] args){
		
		try{
			//properties.load(new FileInputStream("kgsearch.properties"));
			
			HttpTransport httpTransport = new NetHttpTransport();
			HttpRequestFactory factoryRequest = httpTransport.createRequestFactory();
			JSONParser parser = new JSONParser();
			GenericUrl url = new GenericUrl("https://kgsearch.googleapis.com/v1/entities:search");
			
			url.put("query", "Barack Obama");
			url.put("limit", "1");
			url.put("indent", "true");
			url.put("key", "AIzaSyCectN98JfoNUWE0GLiYCTkQ3r4xVaShHY");
			
			HttpRequest request = factoryRequest.buildGetRequest(url);
			HttpResponse httpResponse = request.execute();
			JSONObject response = (JSONObject) parser.parse(httpResponse.parseAsString());
			JSONArray elements = (JSONArray) response.get("itemListElement");
			
			for(Object element : elements){
				System.out.println(JsonPath.read(element, "$.result.name").toString());
				System.out.println(JsonPath.read(element, "$.result.@type").toString());
			}
			
			
		} catch (Exception x){
			x.printStackTrace();
		} 
		
		
	}

}
