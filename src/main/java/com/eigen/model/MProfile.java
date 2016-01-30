package com.eigen.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "profile", uniqueConstraints = @UniqueConstraint(columnNames = "ric_name"))
public class MProfile {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id = -1;

	@Column(name="ric_name")
	private String ric_name = "";

	@Column(name="long_name")
	private String long_name = "";

	@Column(name="rec_type")
	private String rec_type = "";

	@Column(name="bond_type")
	private Integer bond_type = 0;

	@Column(name="currency")
	private String currency = "";

	@Column(name="exchange")
	private String exchange = "";
	
	@Column(name="expiry")
    private Calendar expiry = Calendar.getInstance();

	@Column(name="instrument_type")
	private Integer instrument_type = 0;

	@Column(name="language_id")
	private Integer language_id = 0;

	@Column(name="language_name")
	private Integer language_name = 0;

	@Column(name="market_sector")
	private Integer market_sector = 0;
	
	@Column(name="maturity")
    private Calendar maturity = Calendar.getInstance();

	@Column(name="timezone")
	private String timezone = "";

	@Column(name="notes")
	private String notes = "";
    
	@Column(name="last_update_ts")
    private Calendar lastUpdateTs = Calendar.getInstance();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRic_name() {
		return ric_name;
	}

	public void setRic_name(String ric_name) {
		this.ric_name = ric_name;
	}

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public Integer getBond_type() {
		return bond_type;
	}

	public void setBond_type(Integer bond_type) {
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

	public Calendar getExpiry() {
		return expiry;
	}

	public void setExpiry(Calendar expiry) {
		this.expiry = expiry;
	}

	public Integer getInstrument_type() {
		return instrument_type;
	}

	public void setInstrument_type(Integer instrument_type) {
		this.instrument_type = instrument_type;
	}

	public Integer getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(Integer language_id) {
		this.language_id = language_id;
	}

	public Integer getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(Integer language_name) {
		this.language_name = language_name;
	}

	public Integer getMarket_sector() {
		return market_sector;
	}

	public void setMarket_sector(Integer market_sector) {
		this.market_sector = market_sector;
	}

	public Calendar getMaturity() {
		return maturity;
	}

	public void setMaturity(Calendar maturity) {
		this.maturity = maturity;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Calendar getLastUpdateTs() {
		return lastUpdateTs;
	}

	public void setLastUpdateTs(Calendar lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
	}
	
}
