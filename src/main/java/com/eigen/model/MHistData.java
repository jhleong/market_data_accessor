package com.eigen.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hist_data")
public class MHistData {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id = -1;
	
	@Column(name="profile_id")
	private long profile_id = -1;
    
	@Column(name="ts")
    private Calendar ts = Calendar.getInstance();

	@Column(name="type")
    private String type = "";
 
	@Column(name="open")
    private BigDecimal open = BigDecimal.ZERO;
	
	@Column(name="low")
    private BigDecimal low = BigDecimal.ZERO;
	
	@Column(name="high")
    private BigDecimal high = BigDecimal.ZERO;
	
	@Column(name="close")
    private BigDecimal close = BigDecimal.ZERO;
    
	@Column(name="adj_close")
    private BigDecimal adjClose = BigDecimal.ZERO;
    
	@Column(name="nav")
    private BigDecimal nav = BigDecimal.ZERO;
    
	@Column(name="volume")
    private BigDecimal volume = BigDecimal.ZERO;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(long profile_id) {
		this.profile_id = profile_id;
	}

	public Calendar getTs() {
		return ts;
	}

	public void setTs(Calendar ts) {
		this.ts = ts;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public BigDecimal getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(BigDecimal adjClose) {
		this.adjClose = adjClose;
	}

	public BigDecimal getNav() {
		return nav;
	}

	public void setNav(BigDecimal nav) {
		this.nav = nav;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
}
