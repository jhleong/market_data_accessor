package com.eigen.impl.finder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.eigen.iface.finder.YahooDataFinder;
import com.eigen.model.MHistData;
import com.eigen.model.MProfile;
import com.eigen.util.HttpUtil;

@Service
public class YahooDataFinderImpl implements YahooDataFinder {

    private static final String DATA_URL = "http://ichart.yahoo.com/table.csv";
    private static final String CSV_DELIMITER = ",";
	
    private static final Logger logger = Logger.getLogger(YahooDataFinderImpl.class.getName());
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public MProfile getProfile(String sRicName) {
		// TODO: get from Yahoo
		MProfile mProfile = new MProfile();
		mProfile.setRic_name(sRicName.toUpperCase());
		return mProfile;
	}

	@Override
	public List<MHistData> getHistData(MProfile mProfile) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -20);	// Data from 20 years ago
		Date dtLiveFrom = c.getTime();
		Date dtLiveTo = new Date();
    	return getHistData(mProfile, dtLiveFrom, dtLiveTo);
	}

	@Override
	public List<MHistData> getHistData(MProfile mProfile, Date dtFrom, Date dtTo) {
		List<MHistData> ls = new ArrayList<MHistData>();
		
    	Calendar from = Calendar.getInstance();
    	Calendar to = Calendar.getInstance();
    	
    	from.setTime(dtFrom);
    	to.setTime(dtTo);
    	
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("s", mProfile.getRic_name());

        params.put("a", String.valueOf(from.get(Calendar.MONTH)));
        params.put("b", String.valueOf(from.get(Calendar.DAY_OF_MONTH)));
        params.put("c", String.valueOf(from.get(Calendar.YEAR)));

        params.put("d", String.valueOf(to.get(Calendar.MONTH)));
        params.put("e", String.valueOf(to.get(Calendar.DAY_OF_MONTH)));
        params.put("f", String.valueOf(to.get(Calendar.YEAR)));

        params.put("g", "d");	// Interval. DAILY("d"), WEEKLY("w"), MONTHLY("m")

        params.put("ignore", ".csv");

        String url = DATA_URL + "?" + HttpUtil.getURLParamsString(params);

		try {
			URL request = new URL(url);
	        URLConnection connection = request.openConnection();
	        InputStreamReader is = new InputStreamReader(connection.getInputStream());
	        BufferedReader br = new BufferedReader(is);
	        br.readLine(); // skip the first line
	        for (String line = br.readLine(); line != null; line = br.readLine()) {
	        	MHistData o = parseCsvLine(line);
	        	o.setProfile_id(mProfile.getId());
	        	ls.add(o);
	        }
	        br.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
        
        return ls;
	}

	private MHistData parseCsvLine(String line) {
		MHistData o = new MHistData();
		String[] data = line.split(CSV_DELIMITER);
		//
		// Format: Date,Open,High,Low,Close,Volume,Adj Close
		//
        Calendar c = Calendar.getInstance();
        try {
			c.setTime(dateFormat.parse((String) data[0]));
		} catch (ParseException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		o.setDt(c);
		//
		o.setOpen(new BigDecimal((String) data[1]));
		o.setHigh(new BigDecimal((String) data[2]));
		o.setLow(new BigDecimal((String) data[3]));
		o.setClose(new BigDecimal((String) data[4]));
		o.setVolume(new Long((String) data[5]));
		o.setAdjClose(new BigDecimal((String) data[6]));
		//
		return o;
	}

}
