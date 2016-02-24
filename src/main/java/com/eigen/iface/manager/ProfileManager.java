package com.eigen.iface.manager;

import com.eigen.constant.DataProvider;
import com.eigen.model.MProfile;

public interface ProfileManager {

	public void doSave(MProfile o);
	public void doUpdate(MProfile o);
	
	public void doDelete_bySymbol(DataProvider dataProvider, String sSymbol);

}
