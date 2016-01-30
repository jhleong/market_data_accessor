package com.eigen.impl.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eigen.iface.dao.HistDataDao;
import com.eigen.iface.manager.HistDataManager;
import com.eigen.model.MHistData;

@Service
public class HistDataManagerImpl implements HistDataManager {
    
    @Autowired
	private HistDataDao histDataDao;

	@Override
	public void doSave(MHistData o) {
		histDataDao.doSave(o);
	}

	@Override
	public void doSave(List<MHistData> ls) {
		for (MHistData o: ls) {
			histDataDao.doSave(o);
		}
	}

	@Override
	public void doDelete_byProfileId(long nProfile_id) {
		histDataDao.doDelete_byProfileId(nProfile_id);
	}

}
