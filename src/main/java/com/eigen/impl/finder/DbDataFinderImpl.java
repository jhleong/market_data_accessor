package com.eigen.impl.finder;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eigen.iface.dao.HistDataDao;
import com.eigen.iface.finder.DbDataFinder;
import com.eigen.model.MHistData;

@Service
public class DbDataFinderImpl implements DbDataFinder {
	
    @Autowired
	private HistDataDao histDataDao;

	@Override
	public List<MHistData> getHistData(String sSymbol, Date dtFrom, Date dtTo) {
		return histDataDao.getHistData(sSymbol, dtFrom, dtTo);
	}

}
