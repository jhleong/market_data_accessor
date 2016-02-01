package com.eigen.iface.finder;

import com.eigen.constant.DataProvider;
import com.eigen.model.MProfile;

public interface ProfileFinder {

	public MProfile get_bySymbol(DataProvider dataProvider, String sSymbol);

}
