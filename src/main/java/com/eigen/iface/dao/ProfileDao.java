package com.eigen.iface.dao;

import com.eigen.model.MProfile;

public interface ProfileDao {

	public void doSave(MProfile o);
	public void doUpdate(MProfile o);

	public void doDelete_bySymbol(String sSymbol);

	public MProfile get_bySymbol(String sSymbol);

}
