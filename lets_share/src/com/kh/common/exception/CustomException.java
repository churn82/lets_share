package com.kh.common.exception;

import com.kh.common.code.ErrorCode;

public class CustomException extends RuntimeException{

	
	public ErrorCode error;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4827430474153125429L;

	//1. 실제로 예외가 발생한 것이 아니라 비지니스 로직상 예외에 해당하기 때문에 
	// 	 처리해줄 때 사용할 생성자.  stackTrace(console)찍지 않는다.
	public CustomException(ErrorCode error) {
		this.error = error;
		//stackTrace 공백으로 초기화
		this.setStackTrace(new StackTraceElement[0]);
	}
	
	public CustomException(ErrorCode error, Exception e) {
		this.error = error;
		//콘솔에 error 찍음
		e.printStackTrace();
	}
	

}
