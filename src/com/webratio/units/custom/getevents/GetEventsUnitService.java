package com.webratio.units.custom.getevents;
import groovyjarjarantlr.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/*import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;*/
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;

import javassist.bytecode.Descriptor;

import org.apache.commons.io.IOUtils;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.Request;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.webratio.ide.units.editors.InputParametersEditor;
import com.webratio.ide.units.internal.core.InputDescription;
import com.webratio.rtx.RTXBLOBData;
import com.webratio.rtx.RTXException;
import com.webratio.rtx.RTXManager;
import com.webratio.rtx.RTXOperationUnitService;
import com.webratio.rtx.blob.ExternalBLOBData;
import com.webratio.rtx.core.AbstractService;
import com.webratio.rtx.core.BeanHelper;
import com.webratio.rtx.core.DescriptorHelper;
/*import com.webratio.rtx.core.DescriptorHelper;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

*/
public class GetEventsUnitService extends AbstractService implements RTXOperationUnitService{

	private String city;
	private String type;
	private String api;
	private String lat;
	private String lon;
	private String endpoint;
	private String endpoint2;
	private String eventDate;
	private RTXManager rtx;
	private String searchType="10";
	private String[] latlong;
	
		//"http://alzir.dia.fi.upm.es/resource/event/city/milan.json?api_key=25ec8816c67c4693963aa089ab78ec25&_view=list&_sort=label&_pageSize=10&_page=0"; 
	private HttpURLConnection conn;
    public GetEventsUnitService(String id, RTXManager mgr, Element descr) throws RTXException {
        super(id, mgr, descr);
        // TODO Auto-generated constructor stub
    }

    public Object execute(Map operationContext, Map sessionContext) throws RTXException {
        // TODO Auto-generated method stub
		
		ResultEventParseObject result = new ResultEventParseObject();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		api=BeanHelper.asString(operationContext.get("apiKey"));
    	type=BeanHelper.asString(operationContext.get("type"));
    	city=BeanHelper.asString(operationContext.get("city"));
    	eventDate=BeanHelper.asString(operationContext.get("eventDate"));
    	searchType=BeanHelper.asString(operationContext.get("searchType"));
    	
    	
    	
    	if(type.equals("near")){
    		latlong =  BeanHelper.asString(operationContext.get("mapCoordinates")).split(",");
    		lat = latlong[0];
    		lon = latlong[1];
    		
    		//endpoint="http://alzir.dia.fi.upm.es/resource/event/near/lat/"+lat+"/lon/"+lon+"/dist/0.5.json?api_key="+api+"&_view=list&_sort=label&_pageSize=30&_page=0";
    		endpoint2="http://aplicaciones.localidata.com/eldaSuit/event/near/city/"+city.toLowerCase()+"/lat/"+lat+"/lon/"+lon+"/dist/2.5/lang/en.json?_view=3cixty&_sort=label&_pageSize=20&_page=0";
    	}
    	else{
    		
        
        	System.out.println("city EVENT print: "+city);
    		endpoint="http://alzir.dia.fi.upm.es/resource/event/city/"+city.toLowerCase()+".json?api_key="+api+"&_view=list&_sort=label&_pageSize=30&_page=0";
    	}
    	
    	System.out.println("city EVENT print: "+city);
		try{
			URL url = new URL(endpoint2);
			conn = (HttpURLConnection) url.openConnection();
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
			System.out.println(sb.toString());
			
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode js = mapper.readTree(sb.toString());
		    ArrayNode items = (ArrayNode) js.get("result").get("items");
			
			/*JSONObject response = new JSONObject(sb.toString()); 
			JSONObject body= new JSONObject(response.get("body").toString());
			JSONArray items = body.getJSONObject("result").getJSONArray("items");
			*/
		    
		    String distance;
			String category; 
			String position;
			String eventName;
			String seeAlso;
			String title;
			String description;
			String publisher;
			String dateTimeBeginning;
			String dateTimeEnd="";
			String lat;
			String lon;
			String date;
			String timeStart="";
			String timeEnd="";
			Date dBegin=new Date();
			Date dEnd=new Date();
			
			
			SimpleDateFormat formatterInput = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("formatter: "+formatterInput );
			System.out.println("eventDate: "+eventDate );
			Date dInput = formatterInput.parse(eventDate);
			
			if(items!=null){
				for (int i = 0; i < items.size(); i++) {
					
					
					if(items.get(i).get("timeBeginning")==null){
						
						if(items.get(i).get("atTime") ==null){
						
							dateTimeBeginning="No begin time is provided";
						}else{
							
							dateTimeBeginning=items.get(i).get("atTime").get("hasBeginning").
							get("inXSDDateTime").get(1).get("_value").asText();
							
							date =dateTimeBeginning.substring(0, 10);
							timeStart =dateTimeBeginning.substring(11,dateTimeBeginning.length()-1);
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							 dBegin= formatter.parse(date);
							 System.out.println("dBegin: "+dBegin);
						}
					}else{
						
						
						dateTimeBeginning= items.get(i).get("timeBeginning").asText();
						date =dateTimeBeginning.substring(0, 10);
						timeStart =dateTimeBeginning.substring(11,dateTimeBeginning.length()-1);
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						dBegin = formatter.parse(date);
						System.out.println("dBegin: "+dBegin);
				
						/*
						Calendar c= Calendar.getInstance();
						c.setTime(d);
						int day=c.get(Calendar.DAY_OF_MONTH);
						int month=c.get(Calendar.MONTH);
						int year=c.get(Calendar.YEAR);
						
						System.out.println("day: "+day+" month: "+month+" year: "+year);*/
						
					}
					
					if(items.get(i).get("timeEnd")==null){
						
						if(items.get(i).get("atTime") ==null){
							
							dateTimeEnd="No begin time is provided";
						}else{
							
							dateTimeEnd=items.get(i).get("atTime").get("hasEnd").
							get("inXSDDateTime").get(1).get("_value").asText();
							
							date =dateTimeEnd.substring(0, 10);
							timeEnd =dateTimeEnd.substring(11,dateTimeEnd.length()-1);
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							dEnd = formatter.parse(date);
							System.out.println("dEnd: "+dEnd);
						}
					}else{
						dateTimeEnd= items.get(i).get("timeEnd").asText();

						date =dateTimeEnd.substring(0, 10);
						timeEnd =dateTimeEnd.substring(11,dateTimeEnd.length()-1);
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						dEnd = formatter.parse(date);
						System.out.println("dEnd: "+dEnd);
					}
					
					System.out.println("before if");
					System.out.println("result: "+!((dInput.before(dEnd) && dInput.after(dBegin)) || (dInput.equals(dBegin)) && dInput.equals(dEnd)));
					System.out.println("searchType: "+searchType);
					if(!((dInput.before(dEnd) && dInput.after(dBegin)) || (dInput.equals(dBegin)) && dInput.equals(dEnd)) && searchType.equals("1")){
						System.out.println("inside");
						continue;
						
					}
					
					if(items.get(i).get("category")==null){
						
						category="No category is provided";
					}else{
						category= items.get(i).get("category").get(0).asText();
					}
					
					if(items.get(i).get("atPlace")==null && items.get(i).get("address")==null){
						
						position="No place info is provided";
					}else{
						if(items.get(i).get("address")==null){
							if(items.get(i).get("atPlace").get(0)!=null){
							
								position= items.get(i).get("atPlace").get(0).get("location").get("streetAddress").asText();
							}else{
								position= items.get(i).get("atPlace").asText();
							}
						}else{
							position= items.get(i).get("address").asText();
						}
					}

					if(items.get(i).get("name")==null){
						
						eventName="Event name is not provided";
						title="No title is provided";
					}else{
						eventName= items.get(i).get("name").asText();
						title= items.get(i).get("name").asText();
					}
					
					if(items.get(i).get("additionalUrls")==null){
						
						seeAlso="No additional info is provided";
					}else{
						seeAlso= items.get(i).get("additionalUrls").asText();
					}
					
					if(items.get(i).get("description")==null){
						
						description="No description is provided";
					}else{
						description= items.get(i).get("description").get("text").asText();
					}
					
					
					if(items.get(i).get("source")==null){
						
						publisher="No publisher is provided";
					}else{
						publisher= items.get(i).get("source").asText();
					}
					
					
					if(items.get(i).get("lat")==null){
						
						lat="No lat is provided";
					}else{
						lat= items.get(i).get("lat").asText();
					}

					if(items.get(i).get("lon")==null){
						
						lon="No publisher is provided";
					}else{
						lon= items.get(i).get("lon").asText();
					}
					
					
					/*URL urlImage;
					RTXBLOBData blobFile=null;
					InputStream is;
					if(items.get(i).get("imageUrl")==null){
						
						urlImage=new URL("https://d30y9cdsu7xlg0.cloudfront.net/png/56110-200.png");
					}else{
						
						 urlImage= new URL(items.get(i).get("imageUrl").asText()); 
						HttpURLConnection urlCon= (HttpURLConnection)urlImage.openConnection();
						
						if(urlCon.getResponseCode()==200){
							
							is = urlImage.openConnection().getInputStream();
														
						}else{
							urlImage=new URL("https://d30y9cdsu7xlg0.cloudfront.net/png/56110-200.png");
							is = urlImage.openConnection().getInputStream();
						}
						
						File tempFile = File.createTempFile("image", "." + null);
						 
						tempFile.deleteOnExit();
						
						OutputStream out = null;
						 
						try{
						 
						  is = url.openConnection().getInputStream();
						 
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
						}*/
					
					if(items.get(i).get("distance")==null){
						
						distance="Distance is not provided";
					}else{
						distance= items.get(i).get("distance").asText().substring(0,4)+" km";
					}
					
					
					
				
					result.getCategories().add(category);
					result.getSeeAlsos().add(seeAlso);
					result.getTitles().add(title);
					result.getDescriptions().add(description);
					result.getEventNames().add(eventName);
					result.getPositions().add(position);
					result.getPublishers().add(publisher);
					result.getDistances().add(distance);
					//result.getImages().add(blobFile);
					result.getDateTimeBegins().add(dateTimeBeginning);
					result.getDateTimeEnds().add(dateTimeEnd);
					result.getTimeStart().add(timeStart);
					result.getTimeEnd().add(timeEnd);
					result.getLats().add(lat);
					result.getLons().add(lon);
					result.setCode("success");
					
				}
				
			}
			
			/*System.out.println("Cats: "+result.getCategories());
			System.out.println("See: "+result.getSeeAlsos());
			System.out.println("Title: "+result.getTitles());
			System.out.println("Desc: "+result.getDescriptions());
			System.out.println("place: "+result.getPlaceNames());
			System.out.println("Pos: "+result.getPositions());
			System.out.println("Pubs: "+result.getPublishers());*/
			//System.out.println("D: "+result.getDistances());
			
			
			System.out.println("New Event Date: "+eventDate);
			System.out.println("mapCoord: "+latlong);
			System.out.println("type: "+type);
			System.out.println("Begin Date: "+result.getDateTimeBegins());
			System.out.println("End Date: "+result.getDateTimeEnds());
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		resultMap.put("category", result.getCategories().toArray());
		resultMap.put("seeAlso", result.getSeeAlsos().toArray());
		resultMap.put("title", result.getTitles().toArray());
		resultMap.put("description", result.getDescriptions().toArray());
		resultMap.put("eventName", result.getEventNames().toArray());
		resultMap.put("position", result.getPositions().toArray());
		resultMap.put("publisher", result.getPublishers().toArray());
		resultMap.put("distance", result.getDistances().toArray());
		//resultMap.put("image", result.getImages().toArray());
		resultMap.put("dateTimeBeginning", result.getDateTimeBegins().toArray());
		resultMap.put("dateTimeEnd", result.getDateTimeEnds().toArray());
		resultMap.put("timeStart", result.getTimeStart().toArray());
		resultMap.put("timeEnd", result.getTimeEnd().toArray());
		resultMap.put("lat", result.getLats().toArray());
		resultMap.put("lon", result.getLons().toArray());
		resultMap.put("resultCode",result.getCode());
		
		System.out.println("Map: "+resultMap.toString());
		
		return resultMap;
        
    }

    public void dispose() {
        // TODO Auto-generated method stub
    	conn.disconnect();
    }

}