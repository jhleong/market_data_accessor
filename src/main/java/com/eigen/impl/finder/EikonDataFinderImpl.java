package com.eigen.impl.finder;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eigen.constant.DataProvider;
import com.eigen.constant.HistDataType;
import com.eigen.iface.finder.EikonDataFinder;
import com.eigen.impl.finder.model.EikonBarRecord;
import com.eigen.impl.finder.model.EikonMetaDataRecord;
import com.eigen.impl.finder.model.EikonNavRecord;
import com.eigen.model.MHistData;
import com.eigen.model.MProfile;

@Service
public class EikonDataFinderImpl implements EikonDataFinder {

    private static final String BASE_URL = "http://win.eigencat.co:8877/eikon/";
    private static final String URL_GET_PROFILE = BASE_URL + "rt_meta_data/{par_ricname}";
    private static final String URL_GET_BAR_DATA = BASE_URL + "rt_bar_data/{par_ricname}/{par_interval}/{par_from_date}/{par_to_date}";
    private static final String URL_GET_NAV_DATA = BASE_URL + "rt_nav_data/{par_ricname}/{par_type}/{par_interval}/{par_from_date}/{par_to_date}";
	
    private static final Logger logger = Logger.getLogger(EikonDataFinderImpl.class.getName());
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	@Override
	public MProfile getProfile(String sSymbol) {

		MProfile mProfile = null;
    	
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("par_ricname", sSymbol);
        
		RestTemplate restTemplate = new RestTemplate();
		EikonMetaDataRecord m = restTemplate.getForObject(URL_GET_PROFILE, EikonMetaDataRecord.class, params);
		if (m != null) {
			MProfile p = new MProfile();
			p.setData_provider(DataProvider.EIKON_DESKTOP.getId());
			p.setBond_type(m.getBond_type());
			p.setCurrency(m.getCurrency());
			p.setExchange(m.getExchange());
			//
			Calendar cExpiry = Calendar.getInstance();
			try {
				cExpiry.setTime(dateFormat.parse(m.getExpiry()));
			} catch (ParseException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			p.setExpiry(cExpiry);
			//
			p.setInstrument_type(m.getInstrument_type());
			p.setLanguage_id(m.getLanguage_id());
			p.setLanguage_name(m.getLanguage_name());
			p.setLong_name(m.getLong_name());
			p.setMarket_sector(m.getMarket_sector());
			//
			Calendar cMaturity = Calendar.getInstance();
			try {
				cMaturity.setTime(dateFormat.parse(m.getMaturity()));
			} catch (ParseException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			p.setMaturity(cMaturity);
			//
			p.setNotes(m.getNotes());
			p.setRec_type(m.getRec_type());
			p.setSymbol(m.getRic_name());
			p.setTimezone(m.getTimezone());
			//
			mProfile = p;
		}
		return mProfile;
	}

	@Override
	public List<MHistData> getHistData(MProfile mProfile, HistDataType type) {
		//
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -20);	// Data from 20 years ago
		Date dtLiveFrom = c.getTime();
		Date dtLiveTo = new Date();
		//
    	return getHistData(mProfile, type, dtLiveFrom, dtLiveTo);
	}

	@Override
	public List<MHistData> getHistData(MProfile mProfile, HistDataType type, Date dtFrom, Date dtTo) {
		List<MHistData> ls_histData = null;
		
		if(type == HistDataType.EQUITY)
			ls_histData = getBarData(mProfile, dtFrom, dtTo);
		else if(type == HistDataType.BOND || type == HistDataType.FUND)
			ls_histData = getNavData(mProfile, dtFrom, dtTo);
		//
		List<MHistData> ls = new ArrayList<MHistData>();
		if(ls_histData != null )
			ls.addAll(ls_histData);
		//
		return ls;
	}

	private List<MHistData> getBarData(MProfile mProfile, Date dtFrom, Date dtTo) {
		
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("par_ricname", mProfile.getSymbol());
        params.put("par_interval", "d");	// Interval. DAILY(d), WEEKLY(w), MONTHLY(m), QUARTERLY(q), YEARLY(y)
        params.put("par_from_date", dateFormat.format(dtFrom));
        params.put("par_to_date", dateFormat.format(dtTo));
        
		RestTemplate restTemplate = new RestTemplate();
		List<EikonBarRecord> ls_record = Arrays.asList(
				restTemplate.getForObject(URL_GET_BAR_DATA, EikonBarRecord[].class, params));
		
		List<MHistData> ls = new ArrayList<MHistData>();
		for (EikonBarRecord b: ls_record) {
			MHistData h = new MHistData();
        	h.setProfile_id(mProfile.getId());
			h.setType(HistDataType.EQUITY.getCode());
			h.setClose(BigDecimal.valueOf(b.getCl()));
			h.setOpen(BigDecimal.valueOf(b.getOp()));
			h.setHigh(BigDecimal.valueOf(b.getHi()));
			h.setLow(BigDecimal.valueOf(b.getLo()));
			h.setVolume(BigDecimal.valueOf(b.getVol()));
			//
			Calendar cTs = Calendar.getInstance();
			try {
				cTs.setTime(dateFormat.parse(b.getTs()));
			} catch (ParseException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			h.setTs(cTs);
			//
			ls.add(h);
		}
		
        return ls;
	}

	private List<MHistData> getNavData(MProfile mProfile, Date dtFrom, Date dtTo) {
		List<MHistData> ls_bond = getNavData(mProfile, HistDataType.BOND, dtFrom, dtTo);
		List<MHistData> ls_fund = getNavData(mProfile, HistDataType.FUND, dtFrom, dtTo);
		//
		List<MHistData> ls = new ArrayList<MHistData>();
		ls.addAll(ls_bond);
		ls.addAll(ls_fund);
		//
		return ls;
	}

	private List<MHistData> getNavData(MProfile mProfile, HistDataType dataType, Date dtFrom, Date dtTo) {
		
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("par_ricname", mProfile.getSymbol());
        params.put("par_type", dataType.getCode().toLowerCase());
        params.put("par_interval", "d");	// Interval. DAILY(d), WEEKLY(w), MONTHLY(m), QUARTERLY(q), YEARLY(y)
        params.put("par_from_date", dateFormat.format(dtFrom));
        params.put("par_to_date", dateFormat.format(dtTo));
        
		RestTemplate restTemplate = new RestTemplate();
		List<EikonNavRecord> ls_record = Arrays.asList(
				restTemplate.getForObject(URL_GET_NAV_DATA, EikonNavRecord[].class, params));
		
		List<MHistData> ls = new ArrayList<MHistData>();
		for (EikonNavRecord n: ls_record) {
			MHistData h = new MHistData();
        	h.setProfile_id(mProfile.getId());
			h.setType(dataType.getCode());
			h.setNav(BigDecimal.valueOf(n.getNav()));
			//
			Calendar cTs = Calendar.getInstance();
			try {
				cTs.setTime(dateFormat.parse(n.getTs()));
			} catch (ParseException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			h.setTs(cTs);
			//
			ls.add(h);
		}
		
        return ls;
	}

}
