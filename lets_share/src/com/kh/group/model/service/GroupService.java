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
	
	// =========================프로시저 PL_UPDATE_MEMBER_CNT 실행하는 함수========================= 
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
	
	// =========================그룹을 생성하는 함수========================= 
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
	
	// =========================그룹 정보를 Arraylist에 담아 가져오는 함수=========================
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
	public ArrayList<Group> getGroupListId(String groupId){
		ArrayList<Group> groupList = null;
		Connection conn = jdt.getConnection();
		try {
			groupList = groupDao.getGroupListId(conn, groupId);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupList;
	}
	public ArrayList<Group> getGroupListService(String service){
		ArrayList<Group> groupList = null;
		Connection conn = jdt.getConnection();
		try {
			groupList = groupDao.getGroupListService(conn, service);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupList;
	}
	public ArrayList<Group> getGroupList(String groupId, String service){
		ArrayList<Group> groupList = null;
		Connection conn = jdt.getConnection();
		try {
			groupList = groupDao.getGroupList(conn, groupId, service);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupList;
	}
	
	//=========================그룹 대기 테이블에 유저 입력 함수=========================
	public int insertStandBy(int groupId, String userId) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = groupDao.insertStandBy(conn, groupId, userId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error); 
		}finally {
			jdt.close(conn);
		}
		return res;
	}

	//=========================매칭테이블의 group_id를 가져오는 함수=========================
	public int getGroupId(String userId) {
		int groupId = 0;
		Connection conn = jdt.getConnection();
		try {
			groupId = groupDao.getGroupId(conn, userId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error); 
		}finally {
			jdt.close(conn);
		}
		return groupId;
	}
	
	
	
	//=========================그룹 정보를 group에 담아 가져오는 함수=========================
	public Group getGroup(int groupId) {
		Group group = null;
		Connection conn = jdt.getConnection();	
		try {
			group = groupDao.getGroup(conn, groupId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return group;
	}
}

