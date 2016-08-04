package com.webratio.units.custom.geteventcategories;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.webratio.rtx.RTXConstants;
import com.webratio.rtx.RTXContentUnitService;
import com.webratio.rtx.RTXException;
import com.webratio.rtx.RTXManager;
import com.webratio.rtx.RTXOperationUnitService;
import com.webratio.rtx.application.RTXApplication;
import com.webratio.rtx.core.AbstractService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class GetEventCategoriesUnitService extends AbstractService implements RTXOperationUnitService{

	/*private String url = "https://api.3cixty.com/v2/executeQuery";
	private String key = "78710ad5-6a97-4b1e-94d3-571ba508e6b7";
	private String query = "SELECT DISTINCT (?category AS ?uri) ?category (COUNT(DISTINCT ?event) " +
			"AS ?count) WHERE { GRAPH <{graph}> { ?event a lode:Event . } " +
			"?event lode:hasCategory ?category . } GROUP BY ?category " +
			"ORDER BY DESC (?count) LIMIT 100 OFFSET 0";
	private String graph = "http://3cixty.com/milan/events";*/
	private String endpoint = "http://alzir.dia.fi.upm.es/resource/event/city/milan.json?api_key=25ec8816c67c4693963aa089ab78ec25&_view=list&_sort=label&_pageSize=10&_page=0";

	
    public GetEventCategoriesUnitService(String id, RTXManager mgr, Element descr) throws RTXException {
        super(id, mgr, descr);
        // TODO Auto-generated constructor stub
    }

    public Object execute(Map operationContext, Map sessionContext) throws RTXException {
        // TODO Auto-generated method stub
		// pageContext.get(getId() + ".foo");
		// ExtendedOperationUnitBean bean = new ExtendedOperationUnitBean();
		// bean.put("bar", "baz")
;		// return bean;

		/*query = query.replace("{graph}",graph);
		
		 String urlWithParams = url+"?format=json&query="+URLEncoder.encode(query);
		
		GetMethod get = new GetMethod(urlWithParams);*/
		ResultEventCategoriesParseObject result = new ResultEventCategoriesParseObject();
		try{
			URL url = new URL(endpoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
				
		    	
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
	
			String output;
			StringBuilder sb = new StringBuilder();
			
			
			while ((output = br.readLine()) != null) {
				
	    	    sb.append(output);
			}
			
			JSONObject response = new JSONObject(sb.toString()); 
			JSONObject body= new JSONObject(response.get("body").toString());
			JSONArray items = body.getJSONObject("result").getJSONArray("items");
			
			
			if(items!=null){
	    		for (int i = 0; i < items.length(); i++) {
	    			
	    			String category = (String)items.getJSONObject(i).get("prefLabel");
	    			//String count = items.get(i).get("count").get("value").asText();
	    			String uri = (String)items.getJSONObject(i).get("_about");
	    			
	    			result.getCategories().add(category);
	    			//result.getCounts().add(count);
	    			result.getUris().add(uri);
	    		
	    		}
	    		
    		}
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
		/*try{
		    
		    HttpClient client = new HttpClient();
		    
		    if (key != null && key != "") {
		    	get.addRequestHeader("key", key);
		    }
		    int status = client.executeMethod(get);
		    
		    String response = get.getResponseBodyAsString();
		    
		    if(status!=200){
		    	result.setResultCode("error");
		    	result.setResponse(response);
		    	result.setCode(status);
		     
		    }else{
		    	result.setResultCode("success");
		    	result.setResponse(response);
		    	result.setCode(status);
		    }
		    
		    
		    
		}catch(Exception e){
		    e.printStackTrace();
		    result.setResultCode("error");
		} finally {
		    if (get != null) {
		        get.releaseConnection();
		    }
		}*/
		
		return result;

        
    }

    public void dispose() {
        // TODO Auto-generated method stub
    }

}