package com.webratio.units.custom.parseplace;
import java.util.Map;
import org.dom4j.Element;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.webratio.rtx.RTXException;
import com.webratio.rtx.RTXManager;
import com.webratio.rtx.RTXOperationUnitService;
import com.webratio.rtx.core.AbstractService;



public class ParsePlaceUnitService extends AbstractService implements RTXOperationUnitService{

    public ParsePlaceUnitService(String id, RTXManager mgr, Element descr) throws RTXException {
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
		/*ResultPlaceObject resultObject =new ResultPlaceObject();
		try{
			
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode js = mapper.readTree(resultObject.getResponse());
			ArrayNode data = (ArrayNode) js.get("results").get("bindings");
			
			
			if(data!=null){
				for (int i = 0; i < data.size(); i++) {
					
					String image = data.get(i).get("img").get("value").asText();
					String telephone = data.get(i).get("telephone").get("value").asText();
					String rating = data.get(i).get("rating").get("value").asText();
					String name = data.get(i).get("title").get("value").asText();
					String description = data.get(i).get("descriptions").get("value").asText();
					String lng = data.get(i).get("long").get("value").asText();
					String lat = data.get(i).get("lat").get("value").asText();
					String uri = data.get(i).get("venue").get("value").asText();
					String category = data.get(i).get("businessType").get("value").asText();
					String url = data.get(i).get("url").get("value").asText();
					String position = data.get(i).get("lat").get("value").asText()+","+data.get(i).get("long").get("value").asText();
				
				
					result.getImages().add(image);
					result.getTelephones().add(telephone);
					result.getRatings().add(rating);
					result.getNames().add(name);
					result.getDescriptions().add(description);
					result.getLons().add(lng);
					result.getLats().add(lat);
					result.getUris().add(uri);
					result.getCategories().add(category);
					result.getUrls().add(url);
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