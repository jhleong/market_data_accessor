package com.eigen.impl.finder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eigen.constant.DataProvider;
import com.eigen.iface.dao.ProfileDao;
import com.eigen.iface.finder.EikonDataFinder;
import com.eigen.iface.finder.ProfileFinder;
import com.eigen.iface.finder.YahooDataFinder;
import com.eigen.iface.manager.HistDataManager;
import com.eigen.iface.manager.ProfileManager;
import com.eigen.model.MHistData;
import com.eigen.model.MProfile;

@Service
public class ProfileFinderImpl implements ProfileFinder {
	
    private static final Logger logger = Logger.getLogger(ProfileFinderImpl.class.getName());
	
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private ProfileManager profileManager;
	@Autowired
	private HistDataManager histDataManager;
    @Autowired
	private YahooDataFinder yahooDataFinder;
    @Autowired
	private EikonDataFinder eikonDataFinder;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	//@Cacheable("profile")
	public MProfile get_bySymbol(DataProvider dataProvider, String sSymbol) {
		
    	boolean bUpdateProfile = false;
    	boolean bUpdateHistData = false;
    	
		MProfile mProfile = profileDao.get_bySymbol(dataProvider, sSymbol);
		if (mProfile == null) {
			mProfile = getProfile(dataProvider, sSymbol);
			if (mProfile != null) {
				profileManager.doSave(mProfile);
			}
			//
			mProfile = profileDao.get_bySymbol(dataProvider, sSymbol);
			if (mProfile == null) return null;
			//
			/* <<< jhleong
			bUpdateHistData = true;
			>>> */
		}
		
		/* <<< jhleong 
		Calendar cToday = Calendar.getInstance();
		try {
			cToday.setTime(dateFormat.parse(dateFormat.format(new Date())));
		} catch (ParseException e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw new RuntimeException(e);
		}
		if (mProfile.getLastUpdateTs().before(cToday)) {
			// Get live data when not updated for more than one day
			// TODO: Check online availability first
			bUpdateProfile = true;
			bUpdateHistData = true;
		}
		
    	if (bUpdateProfile) {
    		// TODO: Set new values
    		MProfile newProfile = getProfile(dataProvider, sSymbol);
			mProfile.setBond_type(newProfile.getBond_type());
			profileManager.doUpdate(mProfile);
			//
			mProfile = profileDao.get_bySymbol(sSymbol);
			if (mProfile == null) return null;
    	}
		
    	if (bUpdateHistData) {
        	List<MHistData> ls = null;
        	ls = getHistData(dataProvider, mProfile);
        	if (!ls.isEmpty()) {
        		histDataManager.doDelete_byProfileId(mProfile.getId());
        		histDataManager.doSave(ls);
        	}
    	}
    	>>> jhleong */
    	
		return mProfile;
	}

	private MProfile getProfile(DataProvider dataProvider, String sSymbol) {
		MProfile mProfile = null;
		//
		switch (dataProvider) {
		case YAHOO:
			mProfile = yahooDataFinder.getProfile(sSymbol);
			break;
		case EIKON_DESKTOP:
			mProfile = eikonDataFinder.getProfile(sSymbol);
			break;
		}
		//
		return mProfile;
	}

	/* <<< jhleong 
	private List<MHistData> getHistData(DataProvider dataProvider, MProfile mProfile) {
		List<MHistData> ls = new ArrayList<MHistData>();
		//
		switch (dataProvider) {
		case YAHOO:
			ls = yahooDataFinder.getHistData(mProfile);
			break;
		case EIKON_DESKTOP:
			ls = eikonDataFinder.getHistData(mProfile);
			break;
		}
		//
		return ls;
	}
 >>> jhleong */
}
