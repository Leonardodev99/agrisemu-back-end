package com.semulea.agrisemu.entties.employers.enums;

public enum Sex {
	
	M(1),
	F(2);
	
	private int code;
	
	private Sex(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Sex valueOf( int code) {
		for(Sex value : Sex.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid WorkerLevel code");
	}

}
