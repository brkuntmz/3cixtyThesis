package com.webratio.units.custom.getevents;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.webratio.rtx.RTXBLOBData;

public class ResultEventParseObject {

	private List<String> categories;
	private List<String> seeAlsos;
	private List<String> titles;
	private List<String> dateTimeBegins;
	private List<String> dateTimeEnds;
	private List<String> descriptions;
	private List<String> lons;
	private List<String> lats;
	private List<String> eventNames;
	private List<String> positions;
	private List<String> publishers;
	private List<RTXBLOBData> images;
	private List<String> distances;
	private List<String> timeStart;
	private List<String> timeEnd;
	private String code;
	
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public ResultEventParseObject(){
		
		categories = new ArrayList<String>();
		seeAlsos = new ArrayList<String>();
		titles = new ArrayList<String>();
		dateTimeBegins = new ArrayList<String>();
		dateTimeEnds = new ArrayList<String>();
		descriptions = new ArrayList<String>();
		lons = new ArrayList<String>();
		lats = new ArrayList<String>();
		eventNames = new ArrayList<String>();
		positions = new ArrayList<String>();
		publishers= new ArrayList<String>();
		images= new ArrayList<RTXBLOBData>();
		distances= new ArrayList<String>();
		timeStart= new ArrayList<String>();
		timeEnd= new ArrayList<String>();
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


	public List<String> getLons() {
		return lons;
	}


	public void setLngs(List<String> lons) {
		this.lons = lons;
	}


	public List<String> getLats() {
		return lats;
	}


	public void setLats(List<String> lats) {
		this.lats = lats;
	}


	public List<String> getEventNames() {
		return eventNames;
	}


	public void setEventNames(List<String> eventNames) {
		this.eventNames = eventNames;
	}


	public List<String> getPositions() {
		return positions;
	}


	public void setPositions(List<String> positions) {
		this.positions = positions;
	}


	public List<String> getPublishers() {
		// TODO Auto-generated method stub
		return publishers;
	}
	public void setPublishers(List<String> publishers) {
		this.publishers = publishers;
	}


	public List<RTXBLOBData> getImages() {
		return images;
	}


	public void setImages(List<RTXBLOBData> images) {
		this.images = images;
	}


	public List<String> getDistances() {
		return distances;
	}


	public void setDistances(List<String> distances) {
		this.distances = distances;
	}


	public List<String> getTimeStart() {
		return timeStart;
	}


	public void setTimeStart(List<String> timeStart) {
		this.timeStart = timeStart;
	}


	public List<String> getTimeEnd() {
		return timeEnd;
	}


	public void setTimeEnd(List<String> timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	
	
	
	
}
