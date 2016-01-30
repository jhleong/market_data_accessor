package com.eigen.iface.finder;

import java.util.Date;
import java.util.List;

import com.eigen.model.MHistData;
import com.eigen.model.MProfile;

public interface HistDataFinder {

	public List<MHistData> get_byProfile_byDate(MProfile mProfile, Date dtFrDate, Date dtToDate);

}
