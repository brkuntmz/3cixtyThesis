package com.webratio.units.custom.getplaces;

import java.util.ArrayList;
import java.util.List;

import com.webratio.rtx.RTXBLOBData;

public class ResultPlaceParseObject {

	private List<RTXBLOBData> images;
	private List<String> ratings;
	private List<String> telephones;
	private List<String> names;
	private List<String> addresses;
	//private List<String> lons;
	//private List<String> uris;
	private List<String> categories;
	///private List<String> lats;
	private List<String> seeAlsos;
	private List<String> distances;
	private String code;
	private List<String> publishers;
	
	

	public ResultPlaceParseObject(){
		
		images = new ArrayList<RTXBLOBData>();
		ratings = new ArrayList<String>();
		telephones = new ArrayList<String>();
		names = new ArrayList<String>();
		addresses = new ArrayList<String>();
		//lons = new ArrayList<String>();
		//uris = new ArrayList<String>();
		categories = new ArrayList<String>();
		//lats = new ArrayList<String>();
		seeAlsos = new ArrayList<String>();
		distances = new ArrayList<String>();
		publishers= new ArrayList<String>();
		
	}

	public List<RTXBLOBData> getImages() {
		return images;
	}

	public void setImages(List<RTXBLOBData> images) {
		this.images = images;
	}

	public List<String> getRatings() {
		return ratings;
	}

	public void setRatings(List<String> ratings) {
		this.ratings = ratings;
	}

	public List<String> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<String> telephones) {
		this.telephones = telephones;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}



	/*public List<String> getLons() {
		return lons;
	}

	public void setLons(List<String> lons) {
		this.lons = lons;
	}

	public List<String> getUris() {
		return uris;
	}

	public void setUris(List<String> uris) {
		this.uris = uris;
	}*/

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	/*public List<String> getLats() {
		return lats;
	}

	public void setLats(List<String> lats) {
		this.lats = lats;
	}*/

	
	
	
	public String getCode() {
		return code;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public List<String> getSeeAlsos() {
		return seeAlsos;
	}

	public void setSeeAlsos(List<String> seeAlsos) {
		this.seeAlsos = seeAlsos;
	}

	public List<String> getDistances() {
		return distances;
	}

	public void setDistances(List<String> distances) {
		this.distances = distances;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<String> publishers) {
		this.publishers = publishers;
	}
	
	
}
