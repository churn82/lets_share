package com.kh.common.scheduler.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.scheduler.model.dao.SchedulerDao;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.vo.Group;
import com.kh.group.model.vo.GroupMatching;

public class SchedulerService {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	SchedulerDao schedulerDao = new SchedulerDao();
	
	
	public ArrayList<GroupMatching> getThreeDaysLeft(){
		ArrayList<GroupMatching> threeDaysLeft = null;
		Connection conn = jdt.getConnection();
		try {
			threeDaysLeft = schedulerDao.getThreeDaysLeft(conn);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return threeDaysLeft;
	}
	
	public ArrayList<GroupMatching> getExpiration(){
		ArrayList<GroupMatching> Expiration = null;
		Connection conn = jdt.getConnection();
		try {
			Expiration = schedulerDao.getExpiration(conn);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return Expiration;
	}
	
	public ArrayList<Integer> getExpirationGroupIds(){
		ArrayList<Integer> expirationGroupIds = null;
		Connection conn = jdt.getConnection();
		try {
			expirationGroupIds = schedulerDao.getExpirationGroupIds(conn);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return expirationGroupIds;
	}
	
	public int updateStExDateNull(int groupId, String memberId) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = schedulerDao.updateStExDateNull(conn, groupId, memberId);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
}
