package com.webratio.units.custom.parseeventcategories;

import java.util.ArrayList;
import java.util.List;

public class ResultEventCategoriesParseObject {

	private List<String> categories;
	private List<String> counts;
	private List<String> uris;
	
	public ResultEventCategoriesParseObject(){
		
		categories=new ArrayList<String>();
		counts=new ArrayList<String>();
		uris=new ArrayList<String>();
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getCounts() {
		return counts;
	}

	public void setCounts(List<String> counts) {
		this.counts = counts;
	}

	public List<String> getUris() {
		return uris;
	}

	public void setUris(List<String> uris) {
		this.uris = uris;
	}
	
	
	
}
