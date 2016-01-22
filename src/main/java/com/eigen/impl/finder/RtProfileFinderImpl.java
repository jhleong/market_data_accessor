package com.eigen.impl.finder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eigen.iface.finder.DbDataFinder;
import com.eigen.iface.finder.RtProfileFinder;
import com.eigen.model.MRtProfile;

@Service
public class RtProfileFinderImpl implements RtProfileFinder {
	
	@Autowired
	private DbDataFinder dbDataFinder;
	
	@Override
	@Cacheable("rt_profile")
	public MRtProfile get_byRicname(String sRicname) {
		return dbDataFinder.getRtProfile(sRicname);
	}

}
