package com.eigen.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rt_profile")
public class MRtProfile {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id = -1;

	@Column(name="ricname")
	private String ricname = "";

	@Column(name="type")
	private Integer type = 0;

	@Column(name="currency")
	private String currency = "";

	@Column(name="exchange")
	private String exchange = "";

	@Column(name="instrument_type")
	private Integer instrument_type = 0;

	@Column(name="long_name")
	private String long_name = "";

	@Column(name="market_sector")
	private Integer market_sector = 0;
	
	@Column(name="maturity")
    private Calendar maturity = Calendar.getInstance();

	@Column(name="timezone")
	private String timezone = "";
    
	@Column(name="last_update_dt")
    private Calendar lastUpdateDt = Calendar.getInstance();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRicname() {
		return ricname;
	}

	public void setRicname(String ricname) {
		this.ricname = ricname;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getInstrument_type() {
		return instrument_type;
	}

	public void setInstrument_type(Integer instrument_type) {
		this.instrument_type = instrument_type;
	}

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
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

	public Calendar getLastUpdateDt() {
		return lastUpdateDt;
	}

	public void setLastUpdateDt(Calendar lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}
	
}
