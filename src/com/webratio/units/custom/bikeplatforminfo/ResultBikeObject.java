package com.webratio.units.custom.bikeplatforminfo;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class ResultBikeObject {

	private List<String> emptySlots;
	private List<String> freeBikes;
	private List<String> lats;
	private List<String> lons;
	private List<String> latlons;
	private List<String> names;
	private List<String> cities;
	
	private String code;
	
	public ResultBikeObject(){
		
		emptySlots=new ArrayList<String>();
		freeBikes= new ArrayList<String>();
		lats= new ArrayList<String>();
		lons= new ArrayList<String>();
		latlons=new ArrayList<String>();
		names=new ArrayList<String>();
		cities=new ArrayList<String>();
		
	}

	
	
	public List<String> getCities() {
		return cities;
	}



	public void setCities(List<String> cities) {
		this.cities = cities;
	}



	public List<String> getNames() {
		return names;
	}



	public void setNames(List<String> names) {
		this.names = names;
	}



	public List<String> getFreeBikes() {
		return freeBikes;
	}

	public void setFreeBikes(List<String> freeBikes) {
		this.freeBikes = freeBikes;
	}

	public List<String> getEmptySlots() {
		return emptySlots;
	}

	public void setEmptySlots(List<String> emptySlots) {
		this.emptySlots = emptySlots;
	}
	

	public List<String> getLats() {
		return lats;
	}

	public void setLats(List<String> lats) {
		this.lats = lats;
	}

	public List<String> getLons() {
		return lons;
	}

	public void setLons(List<String> lons) {
		this.lons = lons;
	}

	
	public List<String> getLatlons() {
		return latlons;
	}

	public void setLatlons(List<String> latlons) {
		this.latlons = latlons;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
	
}
