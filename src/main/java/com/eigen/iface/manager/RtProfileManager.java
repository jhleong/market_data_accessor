package com.eigen.iface.manager;

import com.eigen.model.MRtProfile;

public interface RtProfileManager {

	public void doSave(MRtProfile o);
	
	public void doDelete_byRicname(String sRicname);

}
