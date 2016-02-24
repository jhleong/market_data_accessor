package com.eigen.iface.finder;

import java.util.Date;
import java.util.List;

import com.eigen.constant.HistDataType;
import com.eigen.model.MHistData;
import com.eigen.model.MProfile;

public interface EikonDataFinder {

	public MProfile getProfile(String sSymbol);
	public List<MHistData> getHistData(MProfile mProfile, HistDataType type);
	public List<MHistData> getHistData(MProfile mProfile, HistDataType type, Date dtFrom, Date dtTo);

}
