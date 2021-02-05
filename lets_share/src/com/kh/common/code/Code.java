package com.kh.common.code;

public enum Code {

	DOMAIN("http://localhost:9090"),
	EMAIL("tlfpehd@naver.com");
	public String desc;
	Code(String desc){
		this.desc = desc;
	}
	
	public String toString() {
		return desc;
	}
}
