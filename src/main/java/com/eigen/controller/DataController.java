package com.eigen.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eigen.constant.DataProvider;
import com.eigen.constant.HistDataType;
import com.eigen.iface.finder.HistDataFinder;
import com.eigen.iface.finder.ProfileFinder;
import com.eigen.model.MHistData;
import com.eigen.model.MProfile;

@RestController
public class DataController {
	
    private static final Logger logger = Logger.getLogger(DataController.class.getName());
	
    @Autowired
	private HistDataFinder histDataFinder;
    @Autowired
	private ProfileFinder profileFinder;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @RequestMapping("/get_profile/{dp}/{sb}")
	@Cacheable("c_profile")
    public MProfile getProfile(
    		@PathVariable("dp") int nDataProviderId,
    		@PathVariable("sb") String sSymbol) {
    	//
    	DataProvider dataProvider = DataProvider.fromId(nDataProviderId);
    	MProfile p = profileFinder.get_bySymbol(dataProvider, sSymbol);
    	return p;
    }

    @RequestMapping("/get_bar_data/{dp}/{sb}/{fr}/{to}")
	@Cacheable("c_bar_data")
    public List<MHistData> getBarData(
    		@PathVariable("dp") int nDataProviderId,
    		@PathVariable("sb") String sSymbol,
    		@PathVariable("fr") String sFrDate,
    		@PathVariable("to") String sToDate) {
    	//
    	Date dtFrDate;
    	Date dtToDate;
		try {
			dtFrDate = dateFormat.parse(sFrDate);
			dtToDate = dateFormat.parse(sToDate);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw new RuntimeException(e);
		}
		
		MProfile p = getProfile(nDataProviderId, sSymbol);
		List<MHistData> ls = histDataFinder.get_byProfile_byType_byDate(p, HistDataType.EQUITY, dtFrDate, dtToDate);
		return ls;
    }

    @RequestMapping(value = "/get_bar_data_csv/{dp}/{sb}/{fr}/{to}", produces = "text/csv")
	@Cacheable("c_bar_data_csv")
    public String getBarData_csv(
    		HttpServletResponse response,
    		@PathVariable("dp") int nDataProviderId,
    		@PathVariable("sb") String sSymbol,
    		@PathVariable("fr") String sFrDate,
    		@PathVariable("to") String sToDate) {
    	//
    	response.setContentType("data:text/csv; charset=utf-8"); 
        response.setHeader("Content-Disposition", "attachment; filename=" + sSymbol + ".csv");
    	//
		List<MHistData> ls = getBarData(nDataProviderId, sSymbol, sFrDate, sToDate);
		StringBuilder sb = new StringBuilder();
		if (!ls.isEmpty()) {
			sb.append("Date,Open,High,Low,Close,Volume,Adj Close");
			for (MHistData d: ls) {
				sb.append("\n" + dateFormat.format(d.getTs().getTime()) +
						"," + d.getOpen() +
						"," + d.getHigh() +
						"," + d.getLow() +
						"," + d.getClose() +
						"," + d.getVolume() +
						"," + d.getAdjClose());
			}
		}
		return sb.toString();
    }

    @RequestMapping("/get_nav_data/{dp}/{sb}/{tp}/{fr}/{to}")
	@Cacheable("c_nav_data")
    public List<MHistData> getNavData(
    		@PathVariable("dp") int nDataProviderId,
    		@PathVariable("sb") String sSymbol,
    		@PathVariable("tp") String sType,
    		@PathVariable("fr") String sFrDate,
    		@PathVariable("to") String sToDate) {
    	//
    	Date dtFrDate;
    	Date dtToDate;
		try {
			dtFrDate = dateFormat.parse(sFrDate);
			dtToDate = dateFormat.parse(sToDate);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw new RuntimeException(e);
		}
		
		MProfile p = getProfile(nDataProviderId, sSymbol);
		HistDataType t = HistDataType.fromCode(sType);
		List<MHistData> ls = histDataFinder.get_byProfile_byType_byDate(p, t, dtFrDate, dtToDate);
		return ls;
    }

    @RequestMapping(value = "/get_nav_data_csv/{dp}/{sb}/{tp}/{fr}/{to}", produces = "text/csv")
	@Cacheable("c_nav_data_csv")
    public String getNavData_csv(
    		@PathVariable("dp") int nDataProviderId,
    		@PathVariable("sb") String sSymbol,
    		@PathVariable("tp") String sType,
    		@PathVariable("fr") String sFrDate,
    		@PathVariable("to") String sToDate) {
    	//
		List<MHistData> ls = getNavData(nDataProviderId, sSymbol, sType, sFrDate, sToDate);
		StringBuilder sb = new StringBuilder();
		if (!ls.isEmpty()) {
			sb.append("Date,Open,High,Low,Close,Volume,Adj Close");
			for (MHistData d: ls) {
				sb.append("\n" + dateFormat.format(d.getTs().getTime()) +
						"," + d.getOpen() +
						"," + d.getHigh() +
						"," + d.getLow() +
						"," + d.getClose() +
						"," + d.getVolume() +
						"," + d.getAdjClose());
			}
		}
		return sb.toString();
    }
    
}
