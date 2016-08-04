package com.webratio.units.custom.bikeplatforminfo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Element;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.maps.model.LatLng;
import com.webratio.rtx.RTXContentUnitService;
import com.webratio.rtx.RTXException;
import com.webratio.rtx.RTXManager;
import com.webratio.rtx.RTXOperationUnitService;
import com.webratio.rtx.core.AbstractService;
import com.webratio.rtx.core.BeanHelper;


public class BikePlatformInfoUnitService extends AbstractService implements RTXContentUnitService, RTXOperationUnitService{

	
	private HttpURLConnection conn;
	private String endpoint;
	private Map<String,Object> resultMap = new HashMap<String, Object>();
	private ResultBikeObject result = new ResultBikeObject();
	private String city;
	
	
    public BikePlatformInfoUnitService(String id, RTXManager mgr, Element descr) throws RTXException {
        super(id, mgr, descr);
        // TODO Auto-generated constructor stub
    }

    public Object computeParameterValue(String outputParamName, Map pageContext, Map sessionContext) throws RTXException {
        // TODO Auto-generated method stub
        return null;
    }

    public Object execute(Map localContext, Map sessionContext) throws RTXException {
        // TODO Auto-generated method stub
		// pageContext.get(getId() + ".foo");
		// ExtendedOperationUnitBean bean = new ExtendedOperationUnitBean();
		// bean.put("bar", "baz")
;		// return bean;

		city=BeanHelper.asString(localContext.get("city"));
		if(city.equalsIgnoreCase("Milan")){	

			getbikePlatformInfo("http://api.citybik.es/v2/networks/bikemi","Milan");
		}else if(city.equalsIgnoreCase("Nice")){	

			getbikePlatformInfo("http://api.citybik.es/v2/networks/velobleu","Nice");
		}else{	

			getbikePlatformInfo("http://api.citybik.es/v2/networks/barclays-cycle-hire","London");
		}

		resultMap.put("emptySlots", result.getEmptySlots().toArray());
	    resultMap.put("freeBikes", result.getFreeBikes().toArray());
	    resultMap.put("lats", result.getLats().toArray());
	    resultMap.put("lons", result.getLons().toArray());
	    resultMap.put("latlons", result.getLatlons().toArray());
	    resultMap.put("names", result.getNames().toArray());
	    resultMap.put("city",result.getCities().toArray());
	    resultMap.put("resultCode", result.getCode());
	    
	    
				
				
        return resultMap;
    }
    
    public void getbikePlatformInfo(String endpoint,String city){
    	
    	try{
			URL url = new URL(endpoint);
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
			//System.out.println(sb.toString());
			
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode js = mapper.readTree(sb.toString());
		    JsonNode network = js.get("network");
		    ArrayNode items = (ArrayNode)network.get("stations");
		     
		    
		    if(items!=null){
				for (int i = 0; i < items.size(); i++) {
					
					//System.out.println("Deneme");
					String nOfSlots= items.get(i).get("empty_slots").asText();
					String freeBikes= items.get(i).get("free_bikes").asText();
					String lat= items.get(i).get("latitude").asText();
					String lon= items.get(i).get("longitude").asText();
					String name;
					
					if(city.equals("London")){
						name= items.get(i).get("extra").get("name").asText();
					}else{
						name=items.get(i).get("name").asText();
					}
						
					String latlon = lat+","+lon; 
					//System.out.println("latlons: "+ latlon);
					result.getEmptySlots().add(nOfSlots);
					result.getFreeBikes().add(freeBikes);
					result.getLats().add(lat);
					result.getLons().add(lon);
					result.getLatlons().add(latlon);
					result.getNames().add(name);
					result.getCities().add(city);
					result.setCode("success");
					
					//System.out.println("name: "+ result.getNames());
					//System.out.println("free bikes: "+ result.getFreeBikes());
					
					
				}
		    }else{
		    	
		    	System.out.println("Item is null");
			}
		   //System.out.println("latlons: "+ result.getLatlons());
		    /*System.out.println("cities: "+ result.getCities());
		    System.out.println("cities: "+ result.getCities().get(0).getClass());*/
			}catch(Exception e){
				e.printStackTrace();
			}
    }
    
    

    public void dispose() {
        // TODO Auto-generated method stub
    }

}