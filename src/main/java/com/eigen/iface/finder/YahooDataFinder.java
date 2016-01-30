package com.eigen.iface.finder;

import java.util.Date;
import java.util.List;

import com.eigen.model.MHistData;
import com.eigen.model.MProfile;

public interface YahooDataFinder {

	public MProfile getProfile(String sRicName);
	public List<MHistData> getHistData(MProfile mProfile);
	public List<MHistData> getHistData(MProfile mProfile, Date dtFrom, Date dtTo);

}
