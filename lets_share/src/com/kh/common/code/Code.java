package com.kh.common.code;

public enum Code {

	DOMAIN("http://localhost:9090");
	public String desc;
	Code(String desc){
		this.desc = desc;
	}
	
	public String toString() {
		return desc;
	}
}
