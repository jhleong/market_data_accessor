package com.eigen.iface.finder;

import java.util.Date;
import java.util.List;

import com.eigen.model.MHistData;

public interface DbDataFinder {

	public List<MHistData> getHistData(String sSymbol, Date dtFrom, Date dtTo);

}
