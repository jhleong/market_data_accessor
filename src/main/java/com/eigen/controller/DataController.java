package com.eigen.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("/get_profile")
	@Cacheable("c_profile")
    public MProfile getProfile(@RequestParam(value="sb", defaultValue="") String sSymbol) {
    	MProfile p = profileFinder.get_byRicName(sSymbol);
    	return p;
    }

    @RequestMapping("/get_bar_data")
	@Cacheable("c_bar_data")
    public List<MHistData> getBarData(@RequestParam(value="sb", defaultValue="") String sSymbol,
    		@RequestParam(value="fr", defaultValue="") String sFrDate,
    		@RequestParam(value="to", defaultValue="") String sToDate) {
    	Date dtFrDate;
    	Date dtToDate;
		try {
			dtFrDate = dateFormat.parse(sFrDate);
			dtToDate = dateFormat.parse(sToDate);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw new RuntimeException(e);
		}
		
		MProfile p = getProfile(sSymbol);
		List<MHistData> ls = histDataFinder.get_byProfile_byDate(p, dtFrDate, dtToDate);
		return ls;
    }

    @RequestMapping("/get_bar_data_csv")
    public String getBarData_csv(@RequestParam(value="sb", defaultValue="") String sSymbol,
    		@RequestParam(value="fr", defaultValue="") String sFrDate,
    		@RequestParam(value="to", defaultValue="") String sToDate) {
		List<MHistData> ls = getBarData(sSymbol, sFrDate, sToDate);
		StringBuilder sb = new StringBuilder();
		if (!ls.isEmpty()) {
			sb.append("Date,Open,High,Low,Close,Volume,Adj Close");
			for (MHistData d: ls) {
				sb.append("\n" + dateFormat.format(d.getDt().getTime()) +
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
