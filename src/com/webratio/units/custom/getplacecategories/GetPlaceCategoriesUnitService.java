package com.webratio.units.custom.getplacecategories;
import java.net.URLEncoder;
import java.util.Map;
import org.dom4j.Element;

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

public class GetPlaceCategoriesUnitService extends AbstractService implements RTXOperationUnitService{

	private String url = "https://api.3cixty.com/v2/executeQuery";
	private String key = "78710ad5-6a97-4b1e-94d3-571ba508e6b7";
	private String query = "SELECT DISTINCT (?catRead AS ?category) (?cat AS ?uri) ?type (COUNT(DISTINCT ?venue) " +
			"AS ?count) " +
			"WHERE { GRAPH <{graph}> { ?venue a dul:Place . } " +
			"?venue (locationOnt:businessType|locationOnt:businessTypeTop) ?cat . ?venue ?type ?cat . " +
			"FILTER(?type = locationOnt:businessType || ?type = locationOnt:businessTypeTop) " +
			"OPTIONAL { ?cat skos:broader ?catGeneral . } " +
			"FILTER(?type = locationOnt:businessTypeTop && !bound(?catGeneral) || bound(?catGeneral)) " +
			"?cat skos:inScheme <http://data.linkedevents.org/kos/3cixtyScheme> . ?cat skos:prefLabel ?catRead . " +
			"BIND(STR(bound(?catGeneral)) AS ?boolean) } " +
			"GROUP BY ?boolean ?cat ?catRead ?type " +
			"ORDER BY ASC (?boolean) DESC (?count) LIMIT 100 OFFSET 0";
	private String graph = "http://3cixty.com/milan/events";

	
	
    public GetPlaceCategoriesUnitService(String id, RTXManager mgr, Element descr) throws RTXException {
        super(id, mgr, descr);
        // TODO Auto-generated constructor stub
    }

    public Object execute(Map operationContext, Map sessionContext) throws RTXException {
        // TODO Auto-generated method stub
		// pageContext.get(getId() + ".foo");
		// ExtendedOperationUnitBean bean = new ExtendedOperationUnitBean();
		// bean.put("bar", "baz")
;		// return bean;

		query = query.replace("{graph}",graph);
		 String urlWithParams = url+"?format=json&query="+URLEncoder.encode(query);
		
		GetMethod get = new GetMethod(urlWithParams);
		ResultObject result = new ResultObject();
		
		try{
		    
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
		}
		
		return result;

        
    }

    public void dispose() {
        // TODO Auto-generated method stub
    }

}