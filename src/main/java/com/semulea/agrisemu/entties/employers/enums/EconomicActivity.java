package com.semulea.agrisemu.entties.employers.enums;

public enum EconomicActivity {
	
	AGRICULTURE(1),
	FISHING(2),
	FORESTRY(3),
	INDUSTRY(4),
	CONSTRUCTION(5),
	MANUFACTURING(6),
	TRADE(7),
	FINANCE(8),
	HEALTH(9),
	EDUCATION(10),
	TRANSPORT(11),
	TOURISM(12),
	COMMUNICATION(2),
	INFORMATION_TECHNOLOGY(13),
	CONSULTING(14),
	HIGHER_EDUCATION(15),
	SCIENTIFIC_RESEARCH(16),
	LEISURE(17),
	ENTERTAINMENT(18);
	
	private int code;
	
	private EconomicActivity(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static EconomicActivity valueOf( int code) {
		for(EconomicActivity value : EconomicActivity.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid WorkerLevel code");
	}

}
