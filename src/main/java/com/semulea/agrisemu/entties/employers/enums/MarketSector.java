package com.semulea.agrisemu.entties.employers.enums;

public enum MarketSector {
	
	PUBLIC_SECTOR(1),
	PRIVATE_SECTOR(2),
	THIRD_SECTOR(3);
	
	private int code;
	
	private MarketSector(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static MarketSector valueOf( int code) {
		for(MarketSector value : MarketSector.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid WorkerLevel code");
	}

}
