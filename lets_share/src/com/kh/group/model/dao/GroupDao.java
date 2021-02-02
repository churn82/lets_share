package com.kh.group.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.vo.Group;

public class GroupDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	// 프로시저 PL_UPDATE_MEMBER_CNT 실행하는 함수
	public int procedureMemberCnt(Connection conn, int groupIdx) {
		int res = 0;
		CallableStatement cstm = null;
		String query = "{call PL_UPDATE_MEMBER_CNT(?)}";
		try {
			cstm = conn.prepareCall(query);
			cstm.setInt(1, groupIdx);
			cstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.PR01, e);
		} finally {
			jdt.close(cstm); 
		}
		return res;
	}
	
	
	// 그룹을 만드는 함수 
	public int insertGroup(Connection conn, Group group) {
		int res = 0;
		int res2 = 0;
		int res3 = 0;
		PreparedStatement pstm = null;
		PreparedStatement pstm2 = null;
		CallableStatement cstm = null;
		
		String query = "INSERT INTO SH_GROUP "
				+ "(GROUP_ID, MB_ID, GROUP_PPL_NUMBER, GROUP_PAYDATE, GROUP_ACCOUNT_INFO, GROUP_SHARE_ID, GROUP_SHARE_PASSWORD, SER_CODE)"
				+ "VALUES (SC_GROUP_ID.NEXTVAL, ?, 0, ?, ?, ?, ?, ?)";
		
		String query2 = "INSERT INTO SH_MATCHING (MB_ID, GROUP_ID) VALUES (?, SC_GROUP_ID.CURRVAL)";
		
		String query3 = "{call PL_UPDATE_MEMBER_CNT(SC_GROUP_ID.CURRVAL)}";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, group.getMemberId());
			pstm.setInt(2, group.getGroupPayDate());
			pstm.setString(3, group.getAccountInfo());
			pstm.setString(4, group.getShareId());
			pstm.setString(5, group.getSharePw());
			pstm.setString(6, group.getServiceCode());
			
			pstm2 = conn.prepareStatement(query2);
			pstm2.setString(1, group.getMemberId());
			
			cstm = conn.prepareCall(query3);
			
			res = pstm.executeUpdate();
			res2 = pstm2.executeUpdate();
			res3 = cstm.executeUpdate();
			
			System.out.println("res1 : "+res+" / res2 :"+res2+" / res3 : "+res3);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.GR01, e);
		} finally {
			jdt.close(pstm);
			jdt.close(pstm2);
			jdt.close(cstm);
		}
		return res;
	}
	public ArrayList<Group> getGroupList(Connection conn){
		ArrayList<Group> groupList = new ArrayList<Group>();
		String query = "SELECT * FROM SH_GROUP WHERE GROUP_PPL_NUMBER < 4";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Group group = new Group();
				group.setGroupId(rset.getInt(1));
				group.setMemberId(rset.getString(2));
				group.setMemberCnt(rset.getInt(3));
				group.setGroupPayDate(rset.getInt(4));
				group.setAccountInfo(rset.getString(5));
				group.setShareId(rset.getString(6));
				group.setSharePw(rset.getString(7));
				group.setDate(rset.getDate(8));
				group.setAutoYN(rset.getString(9).charAt(0));
				group.setServiceCode(rset.getString(10));
				group.setMemberCntWish(rset.getInt(11));
				groupList.add(group);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return groupList;		
	}
	
	public ArrayList<Group> getGroupList(Connection conn, int date){
		ArrayList<Group> groupList = new ArrayList<Group>();
		String query = "SELECT * FROM SH_GROUP WHERE GROUP_PPL_NUMBER < 4 AND GROUP_PAYDATE = ?";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, date);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Group group = new Group();
				group.setGroupId(rset.getInt(1));
				group.setMemberId(rset.getString(2));
				group.setMemberCnt(rset.getInt(3));
				group.setGroupPayDate(rset.getInt(4));
				group.setAccountInfo(rset.getString(5));
				group.setShareId(rset.getString(6));
				group.setSharePw(rset.getString(7));
				group.setDate(rset.getDate(8));
				group.setAutoYN(rset.getString(9).charAt(0));
				group.setServiceCode(rset.getString(10));
				group.setMemberCntWish(rset.getInt(11));
				groupList.add(group);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return groupList;		
	}
	
	public ArrayList<Group> getGroupList(Connection conn, String service){
		ArrayList<Group> groupList = new ArrayList<Group>();
		String query = "SELECT * FROM SH_GROUP WHERE GROUP_PPL_NUMBER < 4 AND SER_CODE = ?";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, service);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Group group = new Group();
				group.setGroupId(rset.getInt(1));
				group.setMemberId(rset.getString(2));
				group.setMemberCnt(rset.getInt(3));
				group.setGroupPayDate(rset.getInt(4));
				group.setAccountInfo(rset.getString(5));
				group.setShareId(rset.getString(6));
				group.setSharePw(rset.getString(7));
				group.setDate(rset.getDate(8));
				group.setAutoYN(rset.getString(9).charAt(0));
				group.setServiceCode(rset.getString(10));
				group.setMemberCntWish(rset.getInt(11));
				groupList.add(group);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return groupList;		
	}
	
	public ArrayList<Group> getGroupList(Connection conn, int date, String service){
		ArrayList<Group> groupList = new ArrayList<Group>();
		String query = "SELECT * FROM SH_GROUP WHERE GROUP_PPL_NUMBER < 4 AND GROUP_PAYDATE = ? AND SER_CODE = ?";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, date);
			pstm.setString(2, service);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Group group = new Group();
				group.setGroupId(rset.getInt(1));
				group.setMemberId(rset.getString(2));
				group.setMemberCnt(rset.getInt(3));
				group.setGroupPayDate(rset.getInt(4));
				group.setAccountInfo(rset.getString(5));
				group.setShareId(rset.getString(6));
				group.setSharePw(rset.getString(7));
				group.setDate(rset.getDate(8));
				group.setAutoYN(rset.getString(9).charAt(0));
				group.setServiceCode(rset.getString(10));
				group.setMemberCntWish(rset.getInt(11));
				groupList.add(group);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return groupList;		
	}

	
	
}
