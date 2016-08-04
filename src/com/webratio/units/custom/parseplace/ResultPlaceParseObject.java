package com.webratio.units.custom.parseplace;

import java.util.ArrayList;
import java.util.List;

public class ResultPlaceParseObject {

	private List<String> images;
	private List<String> ratings;
	private List<String> telephones;
	private List<String> names;
	private List<String> descriptions;
	private List<String> lons;
	private List<String> uris;
	private List<String> categories;
	private List<String> lats;
	private List<String> urls;
	private List<String> positions;
	
	public ResultPlaceParseObject(){
		
		images = new ArrayList<String>();
		ratings = new ArrayList<String>();
		telephones = new ArrayList<String>();
		names = new ArrayList<String>();
		descriptions = new ArrayList<String>();
		lons = new ArrayList<String>();
		uris = new ArrayList<String>();
		categories = new ArrayList<String>();
		lats = new ArrayList<String>();
		urls = new ArrayList<String>();
		positions = new ArrayList<String>();
		
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
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

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public List<String> getLons() {
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
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getLats() {
		return lats;
	}

	public void setLats(List<String> lats) {
		this.lats = lats;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public List<String> getPositions() {
		return positions;
	}

	public void setPositions(List<String> positions) {
		this.positions = positions;
	}
	
	
	
	
}
