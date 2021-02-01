package com.kh.common.exception;

import com.kh.common.code.ErrorCode;

public class DataAccessException extends CustomException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 321928736227498772L;

	public DataAccessException(ErrorCode error, Exception e) {
		super(error, e);
	}
}
