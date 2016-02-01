/**
 * 
 */
package com.eigen.constant;

public enum HistDataType {
	
	EQUITY		("E"),
	FUND		("F"),
	BOND		("B");
	
	private final String code;

	private HistDataType(String sCode) {
		code = sCode;
	}
	
	public String getCode() {
		return code;
	}
	
	public static HistDataType fromCode(String sCode) {
		HistDataType type = null;
        for (HistDataType t : HistDataType.values()) {
            if (t.getCode().toLowerCase().equals(sCode.toLowerCase())) {
            	type = t;
            	break;
            }
        }
        return type;
    }
	
}
