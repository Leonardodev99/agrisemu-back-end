package com.semulea.agrisemu.entties.employers.enums;

public enum TypeContract {
	
	DETERMINED_TIME(1),
	UNDETERMINED_TIME(2);
	
	private int code;
	
	private TypeContract(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static TypeContract valueOf(int code) {
		for(TypeContract value: TypeContract.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid WorkerLevel code");
	}

}
