package com.eigen.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "profile", uniqueConstraints = @UniqueConstraint(columnNames = "symbol"))
public class MProfile {
	
	@Id
	@SequenceGenerator(name="profile_id_gen", allocationSize=1, sequenceName="profile_id_seq")
	@GeneratedValue(generator = "profile_id_gen")
	@Column(name="id")
	private long id = -1;

	@Column(name="symbol")
	private String symbol = "";

	@Column(name="long_name")
	private String long_name = "";

	@Column(name="rec_type")
	private Integer rec_type = 0;

	@Column(name="bond_type")
	private Integer bond_type = 0;

	@Column(name="currency")
	private String currency = "";

	@Column(name="exchange")
	private String exchange = "";
    
	@JsonSerialize(using = CalendarSerializer.class)
    @JsonDeserialize(using = CalendarDeserializer.class)
	@Column(name="expiry")
    private Calendar expiry = Calendar.getInstance();

	@Column(name="instrument_type")
	private Integer instrument_type = 0;

	@Column(name="language_id")
	private Integer language_id = 0;

	@Column(name="language_name")
	private String language_name = "";

	@Column(name="market_sector")
	private Integer market_sector = 0;
    
	@JsonSerialize(using = CalendarSerializer.class)
    @JsonDeserialize(using = CalendarDeserializer.class)
	@Column(name="maturity")
    private Calendar maturity = Calendar.getInstance();

	@Column(name="timezone")
	private String timezone = "";

	@Column(name="notes")
	private String notes = "";
    
	@Column(name="last_update_ts")
    private Calendar lastUpdateTs = Calendar.getInstance();
	
	@Column(name="data_provider")
	private Integer data_provider = 0;
	
	@Column(name="hit_perday")
	private Integer hit_perday = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	public Integer getRec_type() {
		return rec_type;
	}

	public void setRec_type(Integer rec_type) {
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

	public String getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(String language_name) {
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

	public Integer getData_provider() {
		return data_provider;
	}

	public void setData_provider(Integer data_provider) {
		this.data_provider = data_provider;
	}

	public Integer getHit_perday() {
		return hit_perday;
	}

	public void setHit_perday(Integer hit_perday) {
		this.hit_perday = hit_perday;
	}
	
}
