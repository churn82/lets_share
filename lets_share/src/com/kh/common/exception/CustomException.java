package com.kh.common.exception;

import com.kh.common.code.ErrorCode;

public class CustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4827430474153125429L;
	public ErrorCode error;

	public CustomException(ErrorCode error) {
		this.error = error;
		
		this.setStackTrace(new StackTraceElement[0]);
	}
	public CustomException(ErrorCode error, Exception e) {
		this.error = error;
		//콘솔에 log 작성
		e.printStackTrace();
	}

}
