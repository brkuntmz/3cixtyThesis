package com.webratio.units.custom.parseevent;
import java.util.Map;
import org.dom4j.Element;
import com.webratio.rtx.RTXException;
import com.webratio.rtx.RTXManager;
import com.webratio.rtx.RTXOperationUnitService;
import com.webratio.rtx.core.AbstractService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.dom4j.Element;
import com.webratio.rtx.RTXException;
import com.webratio.rtx.RTXManager;
import com.webratio.rtx.RTXOperationUnitService;
import com.webratio.rtx.core.AbstractService;
import com.webratio.units.custom.parseartist.ResultArtistParseObject;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webratio.units.custom.getevents.*;


public class ParseEventUnitService extends AbstractService implements RTXOperationUnitService{

    public ParseEventUnitService(String id, RTXManager mgr, Element descr) throws RTXException {
        super(id, mgr, descr);
        // TODO Auto-generated constructor stub
    }

    public Object execute(Map operationContext, Map sessionContext) throws RTXException {
        // TODO Auto-generated method stub
		// pageContext.get(getId() + ".foo");
		// ExtendedOperationUnitBean bean = new ExtendedOperationUnitBean();
		// bean.put("bar", "baz")
;		// return bean;

		ResultEventParseObject result = new ResultEventParseObject();
		/*try{
		
		ObjectMapper mapper = new ObjectMapper();
	    //JsonNode js = mapper.readTree(resultObject.getResponse());
	    //System.out.println(resultObject.getResponse());
		//ArrayNode data = (ArrayNode) js.get("results").get("bindings");
		//System.out.println(data.get(0).get("category").get("value").asText());
		
		if(data!=null){
			for (int i = 0; i < data.size(); i++) {
				
				String category = data.get(i).get("category").get("value").asText();
				String seeAlso = data.get(i).get("seeAlso").get("value").asText();
				String event = data.get(i).get("event").get("value").asText();
				String title = data.get(i).get("title").get("value").asText();
				String datetimeBegin = data.get(i).get("datetimeBegin").get("value").asText();
				String datetimeEnd = data.get(i).get("datetimeEnd").get("value").asText();
				String description = data.get(i).get("description").get("value").asText();
				String img = data.get(i).get("img").get("value").asText();
				String lng = data.get(i).get("long").get("value").asText();
				String lat = data.get(i).get("lat").get("value").asText();
				String placeName = data.get(i).get("placeName").get("value").asText();
				String position = data.get(i).get("lat").get("value").asText()+","+data.get(i).get("long").get("value").asText();
			
			
				result.getCategories().add(category);
				result.getSeeAlsos().add(seeAlso);
				result.getEvents().add(event);
				result.getTitles().add(title);
				result.getDateTimeBegins().add(datetimeBegin);
				result.getDateTimeEnds().add(datetimeEnd);
				result.getDescriptions().add(description);
				result.getImgs().add(img);
				result.getLngs().add(lng);
				result.getLats().add(lat);
				result.getPlaceNames().add(placeName);
				result.getPositions().add(position);
			}
		
		}
		}catch(Exception e){
			e.printStackTrace();
		}*/
		return result;
    }

    public void dispose() {
        // TODO Auto-generated method stub
    }

}