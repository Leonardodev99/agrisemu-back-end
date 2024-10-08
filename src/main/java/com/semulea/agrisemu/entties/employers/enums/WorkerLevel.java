package com.semulea.agrisemu.entties.employers.enums;

public enum WorkerLevel {
	
	JUNIOR(1),
	MID_LEVEL(2),
	SENIOR(3);
	
	private int code;
	
	private WorkerLevel(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static WorkerLevel valueOf(int code) {
		for(WorkerLevel value : WorkerLevel.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid WorkerLevel code");
	}
	

}
