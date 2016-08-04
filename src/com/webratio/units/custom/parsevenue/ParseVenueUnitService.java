package com.webratio.units.custom.parsevenue;
import java.util.Map;
import org.dom4j.Element;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.webratio.rtx.RTXException;
import com.webratio.rtx.RTXManager;
import com.webratio.rtx.RTXOperationUnitService;
import com.webratio.rtx.core.AbstractService;
import com.webratio.units.custom.getvenues.ResultVenueObject;


public class ParseVenueUnitService extends AbstractService implements RTXOperationUnitService{

    public ParseVenueUnitService(String id, RTXManager mgr, Element descr) throws RTXException {
        super(id, mgr, descr);
        // TODO Auto-generated constructor stub
    }

    public Object execute(Map operationContext, Map sessionContext) throws RTXException {
        // TODO Auto-generated method stub
		// pageContext.get(getId() + ".foo");
		// ExtendedOperationUnitBean bean = new ExtendedOperationUnitBean();
		// bean.put("bar", "baz")
;		// return bean;
		ResultVenueObject resultObject = new ResultVenueObject();
		ResultVenueParseObject result = new ResultVenueParseObject();
		try{
			
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode js = mapper.readTree(resultObject.getResponse());
			ArrayNode data = (ArrayNode) js.get("results").get("bindings");
			
			
			
			if(data!=null){
				for (int i = 0; i < data.size(); i++) {
					
					String name = data.get(i).get("placeName").get("value").asText();
					String count = data.get(i).get("count").get("value").asText();
					String uri = data.get(i).get("uri").get("value").asText();
					
					result.getNames().add(name);
					result.getCounts().add(count);
					result.getUris().add(uri);
				
				}
			}
			
			
			
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
    }

    public void dispose() {
        // TODO Auto-generated method stub
    }

}