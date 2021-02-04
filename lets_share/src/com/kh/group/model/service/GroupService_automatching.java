package com.kh.group.model.service;

import java.sql.Connection;
import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.dao.GroupDao_automatching;

public class GroupService_automatching {

	GroupDao_automatching groupDao_auto = new GroupDao_automatching();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public int findGroup(String userSerCode, int userPeriod){
		
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = groupDao_auto.findGroup(conn, userSerCode, userPeriod);
		}catch(DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}

	
	
	
}
