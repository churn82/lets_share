package com.kh.group.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.dao.GroupDao;
import com.kh.group.model.vo.Group;

public class GroupService {
	
	GroupDao groupDao = new GroupDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	// 프로시저 PL_UPDATE_MEMBER_CNT 실행하는 함수
	public int procedureMemberCnt(int groupIdx) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = groupDao.procedureMemberCnt(conn, groupIdx);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
		
	}
	
	
	// 그룹을 만드는 함수 
	public int insertGroup(Group group) { 
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = groupDao.insertGroup(conn, group);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	public ArrayList<Group> getGroupList(){
		ArrayList<Group> groupList = null;
		Connection conn = jdt.getConnection();
		try {
			groupList = groupDao.getGroupList(conn);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupList;
	}
	
	public ArrayList<Group> getGroupList(int date){
		ArrayList<Group> groupList = null;
		Connection conn = jdt.getConnection();
		try {
			groupList = groupDao.getGroupList(conn, date);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupList;
	}
	
	public ArrayList<Group> getGroupList(String service){
		ArrayList<Group> groupList = null;
		Connection conn = jdt.getConnection();
		try {
			groupList = groupDao.getGroupList(conn, service);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupList;
	}
	
	public ArrayList<Group> getGroupList(int date, String service){
		ArrayList<Group> groupList = null;
		Connection conn = jdt.getConnection();
		try {
			groupList = groupDao.getGroupList(conn, date, service);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupList;
	}
}
