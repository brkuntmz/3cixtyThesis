package com.webratio.units.custom.parsevenue;

import java.util.ArrayList;
import java.util.List;

public class ResultVenueParseObject {
	
	private List<String> names;
	private List<String> counts;
	private List<String> uris;
	
	public ResultVenueParseObject(){
		
		names=new ArrayList<String>();
		counts=new ArrayList<String>();
		uris=new ArrayList<String>();
	}
	
	
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
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
