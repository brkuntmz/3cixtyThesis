package com.webratio.units.custom.getplaces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.webratio.rtx.RTXBLOBData;
import com.webratio.rtx.RTXException;
import com.webratio.rtx.RTXManager;
import com.webratio.rtx.RTXOperationUnitService;
import com.webratio.rtx.blob.ExternalBLOBData;
import com.webratio.rtx.core.AbstractService;
import com.webratio.rtx.core.BeanHelper;


public class GetPlacesUnitService extends AbstractService implements RTXOperationUnitService{

	private String endpoint;
	private String endpoint2;
	private String api;
	private String lat;
	private String lon;
	private String type;
	private String city;
	private RTXManager rtx;
    public GetPlacesUnitService(String id, RTXManager mgr, Element descr) throws RTXException {
        super(id, mgr, descr);
        // TODO Auto-generated constructor stub
    }

    public Object execute(Map operationContext, Map sessionContext) throws RTXException {
        // TODO Auto-generated method stub
		// pageContext.get(getId() + ".foo");
		// ExtendedOperationUnitBean bean = new ExtendedOperationUnitBean();
		// bean.put("bar", "baz")
;		// return bean;


		ResultPlaceParseObject result = new ResultPlaceParseObject();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
    	api=BeanHelper.asString(operationContext.get("apiKey"));
    	type=BeanHelper.asString(operationContext.get("type"));
    	city=BeanHelper.asString(operationContext.get("city"));
    	
    	if(type.equals("near")){
    		String[] latlong =  BeanHelper.asString(operationContext.get("mapCoordinatesPlace")).split(",");
    		lat = latlong[0];
    		lon = latlong[1];
    		endpoint="http://alzir.dia.fi.upm.es/resource/place/near/lat/"+lat+"/lon/"+lon+"/dist/0.5.json?api_key="+api+"&_view=list&_sort=label&_pageSize=30&_page=0";
    		endpoint2="http://aplicaciones.localidata.com/eldaSuit/place/near/city/"+city+"/lat/"+lat+"/lon/"+lon+"/dist/2.5/lang/en.json?_view=3cixty&_sort=label&_pageSize=20&_page=0";
    	}
    	else{
    		
        	
        	//System.out.println("type: "+type);
    		endpoint="http://alzir.dia.fi.upm.es/resource/place/city/"+city.toLowerCase()+".json?api_key="+api+"&_view=list&_sort=label&_pageSize=30&_page=0";
    	}
    	//System.out.println("end: "+endpoint2);
		try{
			URL url1 = new URL(endpoint2);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
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
			
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode js = mapper.readTree(sb.toString());
		    ArrayNode items = (ArrayNode) js.get("result").get("items");
			
			
			if(items!=null){
	    		for (int i = 0; i < items.size(); i++) {
	    			
	    			String category;
	    			//List<String> category = new ArrayList<String>();
	    			
	    			if(items.get(i).get("category")==null){
	    				category="No category is provided";
	    			}
	    			else{
	    				category=items.get(i).get("category").get(0).asText();
	    			}
	    			
	    			String telephone;
	    			if(items.get(i).get("telephone")==null){
	    				telephone="No telephone number is provided";
	    			}else{
	    				telephone = items.get(i).get("telephone").asText();
	    			}
	    			
	    			String rating;	
					if(items.get(i).get("aggregateRating")==null){
						rating="No rating is provided";
					}else{
	    				rating = items.get(i).get("aggregateRating").asText();
					}
					
					String name = items.get(i).get("name").asText(); 
					
					String image;
					RTXBLOBData blobFile= null ;
					URL urlImage;
					
					if(items.get(i).get("imageUrl")==null){
						 urlImage= new URL("https://d30y9cdsu7xlg0.cloudfront.net/png/56110-200.png");
						
					}else{
					
						urlImage = new URL(items.get(i).get("imageUrl").asText()); 
						System.out.println("URL: "+urlImage);
						
						InputStream	is = urlImage.openConnection().getInputStream();
				
						 
						File tempFile = File.createTempFile("image", "." + null);
						 
						tempFile.deleteOnExit();
						
						OutputStream out = null;
						 
						try{
						 
						  is = urlImage.openConnection().getInputStream();
						 
						  out = new FileOutputStream(tempFile);
						 
						  byte[] buf = new byte[1024];
						 
						  int length = 0;
						 
						  while((length = is.read(buf)) > 0 ){
						 
						    out.write(buf, 0, length);
						 
						  }
						 
						}finally{
						 
						  IOUtils.closeQuietly(out);
						 
						  IOUtils.closeQuietly(is);
						 
						}
						 
						//instantiate the RTXBLOBData
						 
						blobFile= new ExternalBLOBData("image", tempFile, rtx);
						
						//}
					}
					
					String seeAlso;
					if(items.get(i).get("additionalUrls")==null){
						seeAlso="No url is provided";
					}else{
						seeAlso = items.get(i).get("additionalUrls").asText();
					}
					
					
					String distance; 
					
					if(items.get(i).get("distance")==null){
						distance="No position is provided";
					}else{
						
						distance=items.get(i).get("distance").asText().substring(0,4)+" km";
					}
					String publisher = items.get(i).get("source").asText();
					
					String address;
					if(items.get(i).get("address")==null){
						address="No url is provided";
					}else{
						address = items.get(i).get("address").asText();
					}
					
				
					result.getCategories().add(category);
					result.getImages().add(blobFile);
					result.getTelephones().add(telephone);
					result.getRatings().add(rating);
					result.getNames().add(name);
					result.getAddresses().add(address);
					result.getSeeAlsos().add(seeAlso);
					result.getDistances().add(distance);
					result.getPublishers().add(publisher);
					result.setCode("success");
	    		
	    		}
	    		
    		}
			/*
			System.out.println("Cats: "+result.getCategories());
			System.out.println("Tel: "+result.getTelephones());
			System.out.println("Img: "+result.getImages());
			System.out.println("Desc: "+result.getDescriptions());
			System.out.println("names: "+result.getNames());
			System.out.println("Pos: "+result.getPositions());
			System.out.println("Pubs: "+result.getPublishers());
			System.out.println("URLs: "+result.getUrls());
			System.out.println("Rats: "+result.getRatings());
			*/
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			resultMap.put("category", result.getCategories().toArray());
			resultMap.put("image", result.getImages().toArray());
			resultMap.put("telephone", result.getTelephones().toArray());
			resultMap.put("rating", result.getRatings().toArray());
			resultMap.put("address", result.getAddresses().toArray());
			resultMap.put("distance", result.getDistances().toArray());
			resultMap.put("publisher", result.getPublishers().toArray());
			resultMap.put("name", result.getNames().toArray());
			resultMap.put("seeAlso", result.getSeeAlsos().toArray());
			resultMap.put("resultCode",result.getCode());
        return resultMap;
    }

    public void dispose() {
        // TODO Auto-generated method stub
    }

}