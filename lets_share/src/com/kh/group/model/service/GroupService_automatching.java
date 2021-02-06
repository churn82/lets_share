package com.kh.group.model.service;

import java.sql.Connection;
import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.dao.GroupDao_automatching;

public class GroupService_automatching {

	GroupDao_automatching groupDao_auto = new GroupDao_automatching();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	//매칭조건에 맞는 그룹 아이디 검색
	public int findGroup(String userSerCode, int userPeriod,String userId){
		
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = groupDao_auto.findGroup(conn, userSerCode, userPeriod, userId);
		}catch(DataAccessException e){
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	//그룹아이디와 사용자아이디를 받아 그룹(sh_matching)테이블에 등록
	public int addGroup(String userId, int groupId, int userPeriod) {
		
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = groupDao_auto.addGroup(conn, userId, groupId, userPeriod);
			jdt.commit(conn);
		}catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	//그룹의 인원수를 갱신하고 그룹이 가득 찼는지를 반환
	public boolean isMax(int groupId) {
		Connection conn = jdt.getConnection();
		boolean res = false;
		try {
			res = groupDao_auto.isMax(conn, groupId);
			jdt.commit(conn);
		}catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	//그룹을 매칭허용 상태로 변경
	public int addQueue(int groupId) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = groupDao_auto.addQueue(conn, groupId);
			jdt.commit(conn);
		}catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	//그룹을 매칭불가 상태로 변경
	public int exitQueue(int groupId) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = groupDao_auto.exitQueue(conn, groupId);
			jdt.commit(conn);
		}catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
}
