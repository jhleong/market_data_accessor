/**
 * 
 */
package com.eigen.constant;

public enum DataProvider {
	
	YAHOO			(1),
	EIKON_DESKTOP	(2);
	
	private final int id;

	private DataProvider(int nId) {
		id = nId;
	}
	
	public int getId() {
		return id;
	}
	
	public static DataProvider fromId(int nId) {
		DataProvider dp = null;
        for (DataProvider p : DataProvider.values()) {
            if (p.getId() == nId) {
            	dp = p;
            	break;
            }
        }
        return dp;
    }
	
}
