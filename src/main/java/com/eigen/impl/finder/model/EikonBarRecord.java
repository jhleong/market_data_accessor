package com.eigen.impl.finder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EikonBarRecord {
	
	private double cl;
	private double op;
	private double hi;
	private double lo;
	private double vol;
	private String ts;
	
	public double getOp() {
		return op;
	}

	public void setOp(double op) {
		this.op = op;
	}

	public double getHi() {
		return hi;
	}

	public void setHi(double hi) {
		this.hi = hi;
	}

	public double getLo() {
		return lo;
	}

	public void setLo(double lo) {
		this.lo = lo;
	}

	public double getVol() {
		return vol;
	}

	public void setVol(double vol) {
		this.vol = vol;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public double getCl() {
		return cl;
	}
	
	public void setCl(double cl) {
		this.cl = cl;
	}
	
}