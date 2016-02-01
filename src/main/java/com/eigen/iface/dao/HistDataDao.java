package com.eigen.iface.dao;

import java.util.Date;
import java.util.List;

import com.eigen.model.MHistData;

public interface HistDataDao {

	public void doSave(MHistData o);

	public void doDelete_byProfileId(long nProfile_id);

	public List<MHistData> getHistData_all_byProfileId(long nProfile_id);
	public List<MHistData> getHistData_byProfileId_byType_byDate(long nProfile_id, String sType, Date dtFrom, Date dtTo);

}
