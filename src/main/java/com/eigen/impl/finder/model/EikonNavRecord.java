package com.eigen.impl.finder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EikonNavRecord {
	
	private double nav;
	private String ts;
	
	public double getNav() {
		return nav;
	}
	
	public void setNav(double nav) {
		this.nav = nav;
	}
	
	public String getTs() {
		return ts;
	}
	
	public void setTs(String ts) {
		this.ts = ts;
	}
	
}