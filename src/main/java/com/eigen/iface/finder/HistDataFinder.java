package com.eigen.iface.finder;

import java.util.Date;
import java.util.List;

import com.eigen.constant.HistDataType;
import com.eigen.model.MHistData;
import com.eigen.model.MProfile;

public interface HistDataFinder {

	public List<MHistData> get_byProfile_byType_byDate(MProfile mProfile, HistDataType type, Date dtFrDate, Date dtToDate);

}
