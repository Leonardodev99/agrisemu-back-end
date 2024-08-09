package com.semulea.agrisemu.entties.employers.departments.enums;

public enum StateAbsence {
	
	UNJUSTIFIED(1),
	JUSTIFIED(2),
	ABSENCE_REMOVED(3),
	THERE_WAS_NO_MISSING(4);
	
	private int code;
	
	private StateAbsence(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static StateAbsence valueOf( int code) {
		for(StateAbsence value : StateAbsence.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid WorkerLevel code");
	}


}
