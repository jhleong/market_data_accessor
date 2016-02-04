package com.eigen.impl.finder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EikonMetaDataRecord {
	
	private int bond_type;
	private String currency;
	private String exchange;
	private String expiry;
	private int instrument_type;
	private int language_id;
	private String language_name;
	private String long_name;
	private int market_sector;
	private String maturity;
	private String notes;
	private int rec_type;
	private String ric_name;
	private String timezone;
	
	public int getBond_type() {
		return bond_type;
	}
	
	public void setBond_type(int bond_type) {
		this.bond_type = bond_type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public int getInstrument_type() {
		return instrument_type;
	}

	public void setInstrument_type(int instrument_type) {
		this.instrument_type = instrument_type;
	}

	public int getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}

	public String getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	public int getMarket_sector() {
		return market_sector;
	}

	public void setMarket_sector(int market_sector) {
		this.market_sector = market_sector;
	}

	public String getMaturity() {
		return maturity;
	}

	public void setMaturity(String maturity) {
		this.maturity = maturity;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getRec_type() {
		return rec_type;
	}

	public void setRec_type(int rec_type) {
		this.rec_type = rec_type;
	}

	public String getRic_name() {
		return ric_name;
	}

	public void setRic_name(String ric_name) {
		this.ric_name = ric_name;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
}