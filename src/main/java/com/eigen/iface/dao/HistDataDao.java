package com.eigen.iface.dao;

import java.util.Date;
import java.util.List;

import com.eigen.model.MHistData;

public interface HistDataDao {

	public void doSave(MHistData o);

	public void doDelete_bySymbol(String sSymbol);

	public List<MHistData> getHistData_all_bySymbol(String sSymbol);
	public List<MHistData> getHistData(String sSymbol, Date dtFrom, Date dtTo);

}
