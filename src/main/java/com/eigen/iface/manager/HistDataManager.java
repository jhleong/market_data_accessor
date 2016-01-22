package com.eigen.iface.manager;

import java.util.List;

import com.eigen.model.MHistData;

public interface HistDataManager {

	public void doSave(MHistData o);
	public void doSave(List<MHistData> ls);
	
	public void doDelete_bySymbol(String sSymbol);

}
