package com.eigen.iface.dao;

import com.eigen.model.MRtProfile;

public interface RtProfileDao {

	public void doSave(MRtProfile o);

	public void doDelete_byRicname(String sRicname);

	public MRtProfile get_byRicname(String sRicname);

}
