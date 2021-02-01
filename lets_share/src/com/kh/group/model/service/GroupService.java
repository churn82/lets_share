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
	
	public int insertMatch(String mbId) {
		Connection conn = jdt.getConnection();
		int res = 0;
		return res;
	}
	
	// GROUP_ID를 가져오는 함수
	public ArrayList<Integer> getGroupId(String mbId){
		Connection conn = jdt.getConnection();
		ArrayList<Integer> groupIds = null;
		try {
			groupIds = groupDao.getGroupId(conn, mbId);
			jdt.commit(conn);
		}catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupIds;
	}
	
}
