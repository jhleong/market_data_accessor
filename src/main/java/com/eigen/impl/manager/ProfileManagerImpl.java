package com.eigen.impl.manager;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eigen.constant.DataProvider;
import com.eigen.iface.dao.ProfileDao;
import com.eigen.iface.manager.ProfileManager;
import com.eigen.model.MProfile;

@Service
public class ProfileManagerImpl implements ProfileManager {
    
    @Autowired
	private ProfileDao profileDao;

	@Override
	public void doSave(MProfile o) {
		//o.setLastUpdateTs(Calendar.getInstance());
		profileDao.doSave(o);
	}

	@Override
	public void doUpdate(MProfile o) {
		//o.setLastUpdateTs(Calendar.getInstance());
		profileDao.doUpdate(o);
	}

	@Override
	public void doDelete_bySymbol(DataProvider dataProvider, String sSymbol) {
		profileDao.doDelete_bySymbol(dataProvider, sSymbol);
	}

}
