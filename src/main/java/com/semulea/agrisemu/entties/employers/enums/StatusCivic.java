package com.semulea.agrisemu.entties.employers.enums;

public enum StatusCivic {
	
	SINGLE(1),
	MERRIED(2),
	DIVORCED(3),
	WIDOWER(4);
	
	private int code;
	
	private StatusCivic(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static StatusCivic valueOf( int code) {
		for(StatusCivic value : StatusCivic.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid WorkerLevel code");
	}

	

}
