package com.eigen.iface.dao;

import com.eigen.model.MProfile;

public interface ProfileDao {

	public void doSave(MProfile o);
	public void doUpdate(MProfile o);

	public void doDelete_byRicName(String sRicName);

	public MProfile get_byRicName(String sRicName);

}
