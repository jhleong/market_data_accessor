package com.eigen.impl.finder;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eigen.constant.HistDataType;
import com.eigen.iface.dao.HistDataDao;
import com.eigen.iface.finder.HistDataFinder;
import com.eigen.model.MHistData;
import com.eigen.model.MProfile;

@Service
public class HistDataFinderImpl implements HistDataFinder {

    @Autowired
	private HistDataDao histDataDao;

	@Override
	@Cacheable("hist_data")
	public List<MHistData> get_byProfile_byType_byDate(MProfile mProfile, HistDataType type, Date dtFrDate, Date dtToDate) {
		return histDataDao.getHistData_byProfileId_byType_byDate(mProfile.getId(), type.getCode(), dtFrDate, dtToDate);
	}

}
