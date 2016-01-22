package com.eigen.impl.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eigen.iface.dao.RtProfileDao;
import com.eigen.iface.manager.RtProfileManager;
import com.eigen.model.MRtProfile;

@Service
public class RtProfileManagerImpl implements RtProfileManager {
    
    @Autowired
	private RtProfileDao rtProfileDao;

	@Override
	public void doSave(MRtProfile o) {
		rtProfileDao.doSave(o);
	}

	@Override
	public void doDelete_byRicname(String sRicname) {
		rtProfileDao.doDelete_byRicname(sRicname);
	}

}
