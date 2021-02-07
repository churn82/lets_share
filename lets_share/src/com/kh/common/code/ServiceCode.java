package com.kh.common.code;

//서비스코드와 그에 맞는 서비스의 이름을 기록
public enum ServiceCode {
	
	SR01("NETFLIX"),
	SR02("WATCHA"),
	SR03("COUPANG"),
	SR04("WAVVE"),
	SR05("TIVING");
	
	public String serviceName;
	ServiceCode(String serviceName){
		this.serviceName = serviceName;
	}
	public String toString() {
		return serviceName;
	}
}
