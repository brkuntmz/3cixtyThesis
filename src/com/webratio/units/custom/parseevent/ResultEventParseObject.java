package com.webratio.units.custom.parseevent;

import java.util.ArrayList;
import java.util.List;

public class ResultEventParseObject {

	private List<String> categories;
	private List<String> seeAlsos;
	private List<String> events;
	private List<String> titles;
	private List<String> dateTimeBegins;
	private List<String> dateTimeEnds;
	private List<String> descriptions;
	private List<String> imgs;
	private List<String> lngs;
	private List<String> lats;
	private List<String> placeNames;
	private List<String> positions;
	
	
	public ResultEventParseObject(){
		
		categories = new ArrayList<String>();
		seeAlsos = new ArrayList<String>();
		events = new ArrayList<String>();
		titles = new ArrayList<String>();
		dateTimeBegins = new ArrayList<String>();
		dateTimeEnds = new ArrayList<String>();
		descriptions = new ArrayList<String>();
		imgs = new ArrayList<String>();
		lngs = new ArrayList<String>();
		lats = new ArrayList<String>();
		placeNames = new ArrayList<String>();
		positions = new ArrayList<String>();
		
	}


	public List<String> getCategories() {
		return categories;
	}


	public void setCategories(List<String> categories) {
		this.categories = categories;
	}


	public List<String> getSeeAlsos() {
		return seeAlsos;
	}


	public void setSeeAlsos(List<String> seeAlsos) {
		this.seeAlsos = seeAlsos;
	}


	public List<String> getEvents() {
		return events;
	}


	public void setEvents(List<String> events) {
		this.events = events;
	}


	public List<String> getTitles() {
		return titles;
	}


	public void setTitles(List<String> titles) {
		this.titles = titles;
	}


	public List<String> getDateTimeBegins() {
		return dateTimeBegins;
	}


	public void setDateTimeBegins(List<String> dateTimeBegins) {
		this.dateTimeBegins = dateTimeBegins;
	}


	public List<String> getDateTimeEnds() {
		return dateTimeEnds;
	}


	public void setDateTimeEnds(List<String> dateTimeEnds) {
		this.dateTimeEnds = dateTimeEnds;
	}


	public List<String> getDescriptions() {
		return descriptions;
	}


	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}


	public List<String> getImgs() {
		return imgs;
	}


	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}


	public List<String> getLngs() {
		return lngs;
	}


	public void setLngs(List<String> lngs) {
		this.lngs = lngs;
	}


	public List<String> getLats() {
		return lats;
	}


	public void setLats(List<String> lats) {
		this.lats = lats;
	}


	public List<String> getPlaceNames() {
		return placeNames;
	}


	public void setPlaceNames(List<String> placeNames) {
		this.placeNames = placeNames;
	}


	public List<String> getPositions() {
		return positions;
	}


	public void setPositions(List<String> positions) {
		this.positions = positions;
	}
	
	
	
}
