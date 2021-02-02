package com.kh.common.exception;

import com.kh.common.code.ErrorCode;

public class ToAlertException extends CustomException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6257251052687469205L;

	public ToAlertException(ErrorCode error) {
		super(error);
	}
	
	public ToAlertException(ErrorCode error, Exception e) {
		super(error,e);
	}
}
