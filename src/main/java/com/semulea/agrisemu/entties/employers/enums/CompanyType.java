package com.semulea.agrisemu.entties.employers.enums;

public enum CompanyType {
	
	SMALL(1),
	MEDIUM(2),
	LARGE(3),
	MULTINATIONALS(4);
	
	private int code;
	
	private CompanyType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static CompanyType valueOf( int code) {
		for(CompanyType value : CompanyType.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid WorkerLevel code");
	}

}
