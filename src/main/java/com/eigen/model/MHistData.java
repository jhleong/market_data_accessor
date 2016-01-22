package com.eigen.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hist_data")
public class MHistData {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id = -1;

	@Column(name="symbol")
	private String symbol = "";
    
	@Column(name="timestamp")
    private Calendar timestamp = Calendar.getInstance();
 
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
    private long volume = 0;
    
	@Column(name="last_update_dt")
    private Calendar lastUpdateDt = Calendar.getInstance();

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

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
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

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public Calendar getLastUpdateDt() {
		return lastUpdateDt;
	}

	public void setLastUpdateDt(Calendar lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}
	
}
