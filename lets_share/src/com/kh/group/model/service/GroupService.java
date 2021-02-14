package com.kh.group.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.dao.GroupDao;
import com.kh.group.model.vo.Group;
import com.kh.group.model.vo.GroupMatching;
import com.kh.group.model.vo.GroupStandBy;

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
	public int insertGroup(Group group, String memeberName) { 
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = groupDao.insertGroup(conn, group, memeberName);
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

	//=========================그룹 대기 정보를 Arraylist에 담아 가져오는 함수=========================
	public ArrayList<GroupStandBy> getStandByList(int groupId){
		ArrayList<GroupStandBy> standByList = null;
		Connection conn = jdt.getConnection();	
		try {
			standByList = groupDao.getStandByList(conn, groupId); 
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return standByList;
	}

	//=========================그룹 가입 승인 함수=========================
	public int approval(int groupId, String memberId, String memberName) { 
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = groupDao.approval(conn, groupId, memberId, memberName);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	//=========================가입 거절 함수=========================
	public int refuse(int groupId, String memberId) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = groupDao.refuse(conn, groupId, memberId);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	//=========================그룹ID 소속의 매칭데이터를 가져오는 함수=========================
	public ArrayList<GroupMatching> getMatching(int groupId){
		Connection conn = jdt.getConnection();
		ArrayList<GroupMatching> matchingList = null;
		try {
			matchingList = groupDao.getMatching(conn, groupId);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return matchingList;
	}

	//=========================서비스 하루 당 가격을 가져오는 함수=========================
	public int getServicePerDay(String serviceCode) {
		Connection conn = jdt.getConnection();
		int serviceperDay = 0;
		try {
			serviceperDay = groupDao.getServicePerDay(conn, serviceCode);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return serviceperDay;
	}
	
	//=========================매칭 테이블의 PAY_DATE정보를 수정하는 함수=========================
	public int updatePayDate(String memberId, int groupId, int payDate) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = groupDao.updatePayDate(conn, memberId, groupId, payDate);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error); 
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	//=========================매칭 테이블 전체를 가져오는 함수=========================
	public GroupMatching getMatching(int groupId, String memberId) {
		Connection conn = jdt.getConnection();
		GroupMatching groupMatching = null;
		try {
			groupMatching = groupDao.getMatching(conn, groupId, memberId);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupMatching;
	}

	//=========================ST_DATE를 sysdate로 바꾸는 함수=========================
	public int updateStDate(int groupId, String memberId) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = groupDao.updateStDate(conn, groupId, memberId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error); 
		}finally {
			jdt.close(conn);
		}
		return res;
	}

	//=========================PL_SET_EXDATE_FROM_STDATE실행 함수=========================
	public int execProcedureSEFS(int groupId, String memberId) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = groupDao.execProcedureSEFS(conn, groupId, memberId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error); 
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	//=========================PL_SET_EXDATE_FROM_EXDATE실행 함수=========================
	public int execProcedureSEFE(int groupId, String memberId) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = groupDao.execProcedureSEFE(conn, groupId, memberId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error); 
		}finally {
			jdt.close(conn);
		}
		return res;
	}

	//=========================그룹 비밀번호를 변경하는 함수=========================
	public int updateServicePw(int groupId, String servicePw) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = groupDao.updateServicePw(conn, groupId, servicePw);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error); 
		}finally {
			jdt.close(conn);
		}
		return res;
	}

	//=========================PL_SET_EXDATE_FROM_EXDATE실행 함수=========================
	public int execProcedureOG(int groupId, String memberId) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = groupDao.execProcedureOG(conn, groupId, memberId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error); 
		}finally {
			jdt.close(conn);
		}
		return res;
	}

	//=========================그룹별 매칭 테이블 데이터를 가져오는 함수=========================
	public ArrayList<GroupMatching> getGroupMember(int groupId){
		Connection conn = jdt.getConnection();
		ArrayList<GroupMatching> groupMatchings = null;
		try {
			groupMatchings = groupDao.getGroupMember(conn, groupId);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupMatchings;
	}

	//=========================그룹 해지 시 그룹원들 만기일 중 가장 늦는거 가져오기=========================
	public Date getMaxExDate(int groupId) {
		Connection conn = jdt.getConnection();
		Date maxExDate = null;
		try {
			maxExDate = groupDao.getMaxExDate(conn, groupId);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return maxExDate;
	}
	
	//=========================그룹 해지 날짜 update해주기=========================
	public int updateCloseDate(int groupId, Date closeDate) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = groupDao.updateCloseDate(conn, groupId, closeDate);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error); 
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	//=========================그룹에 등록된 사람 있는지 확인=========================
	public boolean checkGroup(String memberId) {
		boolean flag = true;
		Connection conn = jdt.getConnection();
		try {
			flag = groupDao.checkGroup(conn, memberId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error); 
		}finally {
			jdt.close(conn);
		}
		return flag;
	}

	//=========================소속 그룹 아이디 리스트 형태로 가져오기=========================
	public ArrayList<Integer> getgroupIdList(String userId){
		Connection conn = jdt.getConnection();
		ArrayList<Integer> groupIdList = null;
		try {
			groupIdList = groupDao.getgroupIdList(conn, userId);
			jdt.commit(conn);
		} catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return groupIdList;
	}
}

