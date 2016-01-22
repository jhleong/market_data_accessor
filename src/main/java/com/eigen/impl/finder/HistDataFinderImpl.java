package com.eigen.impl.finder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eigen.iface.finder.DbDataFinder;
import com.eigen.iface.finder.HistDataFinder;
import com.eigen.iface.finder.YahooDataFinder;
import com.eigen.iface.manager.HistDataManager;
import com.eigen.model.MHistData;

@Service
public class HistDataFinderImpl implements HistDataFinder {
	
    private static final Logger logger = Logger.getLogger(HistDataFinderImpl.class.getName());
    
    @Autowired
	private DbDataFinder dbDataFinder;
    @Autowired
	private YahooDataFinder yahooDataFinder;
    @Autowired
	private HistDataManager histDataManager;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String getData(String sSymbol, String sFrDate, String sToDate) {
    	Date dtFrom;
    	Date dtTo;
		try {
			dtFrom = dateFormat.parse(sFrDate);
			dtTo = dateFormat.parse(sToDate);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw new RuntimeException(e);
		}
    	
    	String s = "";
    	boolean bGetLive = false;
    	
    	List<MHistData> ls = dbDataFinder.getHistData(sSymbol, dtFrom, dtTo);
    	bGetLive = (ls.isEmpty());
    	if (!bGetLive) {
    		Calendar cToday = Calendar.getInstance();
    		try {
				cToday.setTime(dateFormat.parse(dateFormat.format(new Date())));
			} catch (ParseException e) {
				logger.log(Level.SEVERE, e.getMessage());
				throw new RuntimeException(e);
			}
    		if (ls.get(0).getLastUpdateDt().before(cToday)) {
    			// Get live data when not updated for more than one day
    			// TODO: Check online availability first
    			bGetLive = true;
    		}
    	}
    	if (bGetLive) {
    		Calendar c = Calendar.getInstance();
    		c.add(Calendar.YEAR, -10);	// Data from 10 years ago
    		Date dtLiveFrom = c.getTime();
    		Date dtLiveTo = new Date();
        	ls = yahooDataFinder.getHistData(sSymbol, dtLiveFrom, dtLiveTo);
        	if (!ls.isEmpty()) {
        		histDataManager.doSave(ls);
        		ls = dbDataFinder.getHistData(sSymbol, dtFrom, dtTo);
        	}
    	}
    	
    	if (!ls.isEmpty()) {
    		s = convertListToCsv(ls);
    	}
    	
    	return s;
	}

	private String convertListToCsv(List<MHistData> ls) {
		StringBuilder sb = new StringBuilder();
		if (!ls.isEmpty()) {
			sb.append("Date,Open,High,Low,Close,Volume,Adj Close");
			for (MHistData d: ls) {
				sb.append("\n" + dateFormat.format(d.getDate().getTime()) +
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
