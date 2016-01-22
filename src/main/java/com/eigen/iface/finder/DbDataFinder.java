package com.eigen.iface.finder;

import java.util.Date;
import java.util.List;

import com.eigen.model.MHistData;
import com.eigen.model.MRtProfile;

public interface DbDataFinder {

	public MRtProfile getRtProfile(String sRicname);
	public List<MHistData> getHistData(String sSymbol, Date dtFrom, Date dtTo);

}
