package com.eigen.impl.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eigen.iface.dao.ProfileDao;
import com.eigen.iface.manager.ProfileManager;
import com.eigen.model.MProfile;

@Service
public class ProfileManagerImpl implements ProfileManager {
    
    @Autowired
	private ProfileDao profileDao;

	@Override
	public void doSave(MProfile o) {
		profileDao.doSave(o);
	}

	@Override
	public void doUpdate(MProfile o) {
		profileDao.doUpdate(o);
	}

	@Override
	public void doDelete_byRicName(String sRicName) {
		profileDao.doDelete_byRicName(sRicName);
	}

}
