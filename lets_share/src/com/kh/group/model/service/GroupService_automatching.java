package com.kh.group.model.service;

import java.sql.Connection;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.dao.GroupDao_automatching;

public class GroupService_automatching {

	GroupDao_automatching groupDao_auto = new GroupDao_automatching();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	//트랜잭션 처리된 함수 : 자동매칭 과정에서 오류가 발생하면, 모두 롤백한다.
	public int autoMatching(String userSerCode, int userPeriod,String userId, String userName) {
		Connection conn = jdt.getConnection();
		int groupId = 0;
		try {
			groupId = groupDao_auto.findGroup(conn, userSerCode, userPeriod, userId); //조건에 맞는 그룹의 ID를 검색
			if(groupId != 0) {
				groupDao_auto.addGroup(conn, userId, groupId, userPeriod, userName); //찾은 그룹에 유저를 넣어준다.
				if(groupDao_auto.isMax(conn, groupId)) { //그룹에 유저를 넣어준 후, 그룹이 가득찼다면
					groupDao_auto.exitQueue(conn, groupId); //그룹을 매칭 대기열에서 제외한다.
				}
				jdt.commit(conn); //이상의 작업 중 오류가 발생하지 않으면 커밋한다.
			}
		}catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);		
		}finally {
			jdt.close(conn);
		}
		return groupId;
	}
	
}
